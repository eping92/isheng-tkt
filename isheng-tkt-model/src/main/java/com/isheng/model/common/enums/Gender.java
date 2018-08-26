package com.isheng.model.common.enums;

/**
 * 性别枚举
 * @author Administrator
 *
 */
public enum Gender {

	/**
	 * 男性
	 */
	MALE("男"),
	/**
	 * 女
	 */
	FEMALE("女"),
	/**
	 * 保密
	 */
	SECRET("保密");

	private String text;

	private Gender(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
