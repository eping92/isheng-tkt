package com.isheng.model.auth.enums;

/**
 * 用户状态
 */
public enum UserStatus {
	
	/**
	 * 待激活
	 */
	INIT("待激活"),
	/**
	 * 启用
	 */
	ENABLE("启用"),
	/**
	 * 禁用
	 */
	DISABLE("禁用"),
	/**
	 * 锁定
	 */
	LOCK("锁定"),
	/**
	 * 注销
	 */
	CANCEL("注销");
	
	private String text;

	private UserStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
