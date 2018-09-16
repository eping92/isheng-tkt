package com.isheng.service.auth;

import java.util.List;

import com.isheng.common.base.BaseService;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.model.auth.request.MenuQuery;


/**
 * 菜单管理
 *
 *
 * @author Administrator
 * @version $Id: MenuService.java 2018年9月8日 上午9:04:38 $
 */
public interface MenuService extends BaseService<Menu, MenuQuery>{

	public long getNextSort(String parentId, MenuType menuType) throws BizException;
	
	/**
	 * 查询用户的所有权限
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public List<Menu> getListByUserId(String userId) throws BizException;
	
	/**
	 * 查询指定角色的所有菜单
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	public List<Menu> getListByRoleId(String roleId) throws BizException;
	
	/**
	 * 角色的所有权限
	 * 
	 * @param roles
	 * @return
	 * @throws BizException
	 */
	List<Menu> getListByRoles(List<Role> roles) throws BizException;
	
}
