package com.isheng.dao.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.dao.mapper.auth.RoleMenuMapper;
import com.isheng.dao.service.auth.RoleMenuDao;
import com.isheng.model.auth.entity.RoleMenu;
import com.isheng.model.auth.request.RoleMenuQuery;

@Component("roleMenuDao")
@Service(interfaceClass = RoleMenuDao.class)
public class RoleMenuDaoImpl extends AbstractBaseDao<RoleMenu, RoleMenuQuery> implements RoleMenuDao {

	@Resource
	private RoleMenuMapper roleMenuMapper;
	
	@Override
	protected BaseMapper<RoleMenu, RoleMenuQuery> getMapper() {
		return this.roleMenuMapper;
	}
	
	@Override
	public void batchSave(String roleId, List<String> menuIds) throws BizException {
		RoleMenu entity = null;
		for (String menuId : menuIds) {
			if (!StringUtils.isEmpty(roleId) && !this.isRepet(roleId, menuId)) {
				entity = new RoleMenu(roleId, menuId);
				this.save(entity);
			}
		}
	}

	@Override
	public List<RoleMenu> listByRoleId(String roleId) throws BizException {
		return roleMenuMapper.selectByRoleId(roleId);
	}

	@Override
	public boolean isRepet(String roleId, String menuId) throws BizException {
		return roleMenuMapper.isRepet(roleId, menuId) >= 1;
	}

	

	
}
