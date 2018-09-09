package com.isheng.model.auth.entity;

import com.isheng.common.base.BaseEntity;

/**
 * 用户角色对应信息
 *
 *
 * @author Administrator
 * @version $Id: UserRole.java 2018年9月8日 下午5:55:57 $
 */
public class UserRole extends BaseEntity {

	/**  */
	private static final long serialVersionUID = -4252778357288291852L;
	
	private String userId;
	
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRole [userId=" + userId + ", roleId=" + roleId + "]";
	}
	
}
