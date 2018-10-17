package com.isheng.service.auth.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.util.ObjUtil;
import com.isheng.common.util.WebUtil;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.model.auth.request.MenuQuery;
import com.isheng.service.auth.MenuService;

@Component("menuService")
@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl extends AbstractBaseService<Menu, MenuQuery> implements MenuService {

	@Reference
	private MenuDao menuDao;
	
	@Override
	protected BaseDao<Menu, MenuQuery> getDao() {
		return menuDao;
	}
	
	@Override
	public String add(Menu menu) throws BizException {
		//数据验证
		this.dataValid(menu);
		
		String id = "";
		try {
			long nextSort = (menu.getSort() > 0) ? menu.getSort() : this.getNextSort(menu.getParentId(), menu.getMenuType());
			String code = WebUtil.parentUri(menu.getUrl());//如：/menu/add改成menu:add
			menu.setSort(nextSort);
			menu.setCode(code);
			id = menuDao.save(menu);
		} catch (Exception e) {
			logger.error("添加菜单失败,menu={}", menu);
			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		
		return id;
	}
	
	@Override
	public int update(Menu menu) throws BizException {
		this.dataValid(menu);
		return menuDao.update(menu);
	}

	@Override
	public long getNextSort(String parentId, MenuType menuType) throws BizException {
		MenuQuery query = new MenuQuery();
		query.setParentId(parentId);
		query.setMenuType(menuType);
		return menuDao.countByParam(query) + 1;
	}

	@Override
	public List<Menu> getListByUserId(String userId) throws BizException {
		List<Menu> list = null;
		if (ObjUtil.isNotNull(userId)) {
			list = menuDao.listByUserId(userId);
		}
		return null != list ? list : new ArrayList<Menu>(0);
	}
	
	@Override
	public List<Menu> getListByRoleId(String roleId) throws BizException {
		List<Menu> list = null;
		if (ObjUtil.isNotNull(roleId)) {
			list = menuDao.listByRoleId(roleId);
		}
		return list;
	}
	
	@Override
	public List<Menu> getListByRoles(List<Role> roles)  throws BizException {
		List<Menu> list = null;
		if (null != roles && roles.isEmpty()) {
			list = menuDao.listByRoles(roles);
		}
		return list;
	}
	
	@Override
	protected void dataValid(Menu menu) throws BizException {
		if (null == menu) {
			throw new BizException(ErrMsg.PARAM_NULL);
		}
		if (null == menu.getMenuType()) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "类型不能为空");
		}
		if (StringUtils.isEmpty(menu.getName())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "名称不能为空");
		}
		if (ObjUtil.isNotNull(menu.getUrl()) && !"#".equals(menu.getUrl())) {
			boolean isExist = menuDao.isExist(menu.getId(), "url", menu.getUrl());
			if (isExist) {
				throw new BizException(ErrMsg.PARAM_ERR.getCode(), "权限地址已存在");
			}
		}
	}

	@Override
	public List<Menu> getMenuTree(String userId) throws BizException {
		List<Menu> list = this.getAll();//getListByUserId(userId);
		if (null == list || list.isEmpty()) {
			return new ArrayList<>(0);
		}
		
		//得到用户的所有一级权限
		List<Menu> roots = new ArrayList<>();
		for (Menu menu : list) {
			if (MenuType.ROOT == menu.getMenuType()) {
				roots.add(menu);
			}
		}
		
		//得到下级权限
		for (Menu menu : roots) {
			menu.setChildList(this.getChilds(menu.getId(), list));
		}
		
		return roots;
	}
	
	/**
	 * 递归循环出子菜单
	 * 
	 * @param id
	 * @param roots
	 * @return
	 */
	private List<Menu> getChilds(String id, List<Menu> list) {
		List<Menu> childs = new ArrayList<>();
		for (Menu m : list) {
			if (StringUtils.isNoneBlank(id) && id.equals(m.getParentId())) {
				childs.add(m);
			}
		}
		
		//循环子菜单的子菜单
		for (Menu m : childs) {
			if (StringUtils.isBlank(m.getUrl())) {
				m.setChildList(getChilds(m.getId(), list));
			}
		}
		
		return childs;
	}
}
