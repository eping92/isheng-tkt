package com.isheng.service.auth.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.idgen.IdGenerate;
import com.isheng.common.util.ObjUtil;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.model.auth.entity.Menu;
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
			if (menu.getSort() <= 0) {
				menu.setSort(this.getNextSort(menu.getParentId(), menu.getMenuType()));
			}
			if (ObjUtil.isNotNull(menu.getUrl()) && !"#".equals(menu.getUrl())) {
				String code = menu.getUrl().replaceFirst("/", "").replace("/", ":");//如：/menu/add改成menu:add
				menu.setCode(code);
			}
			
			id = IdGenerate.nextId();
			menu.setId(id);
			menu.setCreateTime(new Date());
			int result = menuDao.save(menu);
			if (result <= 0) {
				throw new BizException(ErrMsg.FAILED);
			}
		} catch (Exception e) {
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

}
