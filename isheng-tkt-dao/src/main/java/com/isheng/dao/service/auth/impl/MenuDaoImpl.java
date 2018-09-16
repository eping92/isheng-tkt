package com.isheng.dao.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.dao.mapper.auth.MenuMapper;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.MenuQuery;

@Component("menuDao")
@Service(interfaceClass = MenuDao.class)
public class MenuDaoImpl extends AbstractBaseDao<Menu, MenuQuery> implements MenuDao {

	@Resource
	private MenuMapper menuMapper;

	@Override
	protected BaseMapper<Menu, MenuQuery> getMapper() {
		return menuMapper;
	}
	
	@Override
	public List<Menu> listByUserId(String userId) throws BizException {
		return menuMapper.selectByUserId(userId);
	}

	@Override
	public List<Menu> listByRoleId(String roleId) throws BizException {
		return menuMapper.selectByRoleId(roleId);
	}

	@Override
	public List<Menu> listByRoles(List<Role> roles) throws BizException {
		return menuMapper.selectByRoles(roles);
	}

	

}
