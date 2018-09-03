package com.isheng.service.auth.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.model.auth.entity.Menu;
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

}
