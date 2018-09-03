package com.isheng.common.enums;

public enum Bool {
	
	/**
	 * 是
	 */
	YES("是"),
	/**
	 * 否
	 */
	NO("否");
	
	private String text;

	private Bool(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
