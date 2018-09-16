package com.isheng.service.auth;

import java.util.List;

import com.isheng.common.base.BaseService;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.RoleMenu;
import com.isheng.model.auth.request.RoleMenuQuery;

public interface RoleMenuService extends BaseService<RoleMenu, RoleMenuQuery> {

	/**
	 * 批量插入
	 * @param roleId
	 * @param menuIds
	 * @throws BizException
	 */
	void batchAdd(String roleId, List<String> menuIds) throws BizException;
	
	/**
	 * 根据角色id获取所有角色和权限对应关系
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	List<RoleMenu> getListByRoleId(String roleId) throws BizException;

}
