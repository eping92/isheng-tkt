package com.isheng.model.auth.entity;

import com.isheng.common.base.BaseEntity;

/**
 * 角色权限信息
 *
 *
 * @author Administrator
 * @version $Id: RoleMenu.java 2018年9月8日 下午5:58:16 $
 */
public class RoleMenu extends BaseEntity {

	/**  */
	private static final long serialVersionUID = 7042865705275416004L;
	
	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 菜单id
	 */
	private String menuId;
	
	public RoleMenu(String roleId, String menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenu [roleId=" + roleId + ", menuId=" + menuId + "]";
	}

}
