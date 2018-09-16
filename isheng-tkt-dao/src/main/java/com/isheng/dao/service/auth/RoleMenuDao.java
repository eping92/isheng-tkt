package com.isheng.dao.service.auth;

import java.util.List;
import com.isheng.common.base.BaseDao;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.RoleMenu;
import com.isheng.model.auth.request.RoleMenuQuery;

public interface RoleMenuDao extends BaseDao<RoleMenu, RoleMenuQuery> {
	
	/**
	 * 批量插入
	 * @param roleId
	 * @param menuIds
	 * @throws BizException
	 */
	void batchSave(String roleId, List<String> menuIds) throws BizException;
	
	/**
	 * 根据角色id获取所有角色和权限对应关系
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	List<RoleMenu> listByRoleId(String roleId) throws BizException;
	
	/**
	 * 角色和权限关系是否重复
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 * @throws BizException
	 */
	boolean isRepet(String roleId, String menuId) throws BizException;

}
