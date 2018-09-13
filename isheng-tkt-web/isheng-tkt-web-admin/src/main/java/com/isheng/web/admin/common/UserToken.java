package com.isheng.web.admin.common;

import org.apache.shiro.authc.AuthenticationToken;

import com.isheng.model.auth.entity.User;

public class UserToken implements AuthenticationToken {

	/**  */
	private static final long serialVersionUID = -6904220433073964301L;
	
	/**
	 * 用户
	 */
	private User user;
	/**
	 * 密码
	 */
	private String password;
	
	public UserToken(User user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	public Object getPrincipal() {
		return this.user.getLoginName();
	}

	@Override
	public Object getCredentials() {
		return this.password;
	}

	/**
	 * Getter method for property <tt>user</tt>.
	 * 
	 * @return property value of user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter method for property <tt>user</tt>.
	 * 
	 * @param user value to be assigned to property user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Getter method for property <tt>password</tt>.
	 * 
	 * @return property value of password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method for property <tt>password</tt>.
	 * 
	 * @param password value to be assigned to property password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserToken [user=" + user + ", password=" + password + "]";
	}
	
	

}
