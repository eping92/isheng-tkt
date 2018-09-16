package com.isheng.service.auth;

import java.util.List;

import com.isheng.common.base.BaseService;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.UserRole;
import com.isheng.model.auth.request.UserRoleQuery;

public interface UserRoleService extends BaseService<UserRole, UserRoleQuery> {
	
	/**
	 * 批量新增
	 * 
	 * @param userId
	 * @param roleIds
	 * @throws BizException
	 */
	public void batchAdd(String userId, List<String> roleIds) throws BizException;
	
	/**
	 * 获取用户的所有用户和角色信息
	 * 
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public List<UserRole> getListByUserId(String userId) throws BizException;

}
