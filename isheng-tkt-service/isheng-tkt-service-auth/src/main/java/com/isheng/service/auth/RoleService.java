package com.isheng.service.auth;

import java.util.List;

import com.isheng.common.base.BaseService;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.RoleQuery;

public interface RoleService extends BaseService<Role, RoleQuery> {
	
	/**
	 * 得到用户的所有角色信息
	 * 
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public List<Role> getListByUserId(String userId) throws BizException;

}
