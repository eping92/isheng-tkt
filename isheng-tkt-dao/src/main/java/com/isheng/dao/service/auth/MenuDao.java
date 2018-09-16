package com.isheng.dao.service.auth;

import java.util.List;

import com.isheng.common.base.BaseDao;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.MenuQuery;

/**
 * 权限菜单
 *
 *
 * @author Administrator
 * @version $Id: MenuDao.java 2018年7月28日 下午4:15:37 $
 */
public interface MenuDao extends BaseDao<Menu, MenuQuery>{
	
	/**
	 * 查询用户的所有权限
	 * 
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	List<Menu> listByUserId(String userId) throws BizException;

	/**
	 * 查询某个角色的所有权限
	 * 
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	List<Menu> listByRoleId(String roleId) throws BizException;
	
	/**
	 * 角色的所有权限
	 * 
	 * @param roles
	 * @return
	 */
	List<Menu> listByRoles(List<Role> roles) throws BizException;

	

	
	
}
