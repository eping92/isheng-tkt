package com.isheng.security.domain;

import java.io.Serializable;

public class UserSession implements Serializable {

	/**  */
	private static final long serialVersionUID = -8122426840027092561L;

	private String userId;
	
	private String loginName;
	
	private String realName;
	
	private String nick;
	
	private String mobile;

	/**
	 * Getter method for property <tt>userId</tt>.
	 * 
	 * @return property value of userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Setter method for property <tt>userId</tt>.
	 * 
	 * @param userId value to be assigned to property userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Getter method for property <tt>loginName</tt>.
	 * 
	 * @return property value of loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * Setter method for property <tt>loginName</tt>.
	 * 
	 * @param loginName value to be assigned to property loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * Getter method for property <tt>realName</tt>.
	 * 
	 * @return property value of realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * Setter method for property <tt>realName</tt>.
	 * 
	 * @param realName value to be assigned to property realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * Getter method for property <tt>nick</tt>.
	 * 
	 * @return property value of nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Setter method for property <tt>nick</tt>.
	 * 
	 * @param nick value to be assigned to property nick
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Getter method for property <tt>mobile</tt>.
	 * 
	 * @return property value of mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Setter method for property <tt>mobile</tt>.
	 * 
	 * @param mobile value to be assigned to property mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
