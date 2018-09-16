package com.isheng.dao.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.dao.mapper.auth.UserRoleMapper;
import com.isheng.dao.service.auth.UserRoleDao;
import com.isheng.model.auth.entity.UserRole;
import com.isheng.model.auth.request.UserRoleQuery;

@Component("userRoleDao")
@Service(interfaceClass = UserRoleDao.class)
public class UserRoleDaoImpl extends AbstractBaseDao<UserRole, UserRoleQuery> implements UserRoleDao {
	
	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	protected BaseMapper<UserRole, UserRoleQuery> getMapper() {
		return this.userRoleMapper;
	}

	@Override
	public void batchSave(String userId, List<String> roleIds) throws BizException {
		UserRole entity = null;
		for (String roleId : roleIds) {
			if (!StringUtils.isEmpty(roleId) && !isRepet(userId, roleId)) {
				entity = new UserRole(userId, roleId);
				save(entity);
			}
		}
	}

	@Override
	public List<UserRole> listByUserId(String userId) throws BizException {
		return userRoleMapper.selectByUserId(userId);
	}

	@Override
	public boolean isRepet(String userId, String roleId) throws BizException {
		return this.userRoleMapper.isRepet(userId, roleId) >= 1;
	}

	
}
