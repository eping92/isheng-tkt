package com.isheng.service.auth.impl;

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
import com.isheng.model.auth.entity.UserRole;
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
		
		if (StringUtils.isEmpty(menu.getId())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "ID不能为空");
		}
		
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
	public List<Menu> getListByUserId(String userId) throws BizException {
		List<UserRole> userRoles = null;
		return null;
	}
	
	@Override
	public List<Menu> getListByRoleId(String roleId) throws BizException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getListByUserRoles(List<UserRole> userRoles) throws BizException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
