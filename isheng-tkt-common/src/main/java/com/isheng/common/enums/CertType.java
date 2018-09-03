package com.isheng.common.enums;

/**
 * 证件类型
 * @author Administrator
 *
 */
public enum CertType {

	/**
	 * 身份证
	 */
	IC("身份证"),
	/**
	 * 护照
	 */
	PP("护照"),
	/**
	 * 港澳通行证
	 */
	HMP("港澳通行证"),
	/**
	 * 军人证
	 */
	SC("军人证"),
	/**
	 * 台胞证
	 */
	TWP("台胞证"),
	/**
	 * 回乡证
	 */
	HMC("回乡证"),
	/**
	 * 文职证
	 */
	CC("文职证"),
	/**
	 * 其他证件
	 */
	OTHER("其他");
	
	private String text;

	private CertType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
