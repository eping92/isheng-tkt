package com.isheng.model.auth.enums;

public enum MenuType {
	
	/**
	 * 最顶层tab
	 */
	ROOT("主菜单"),
	/**
	 * 菜单
	 */
	MENU("菜单"),
	/**
	 * 按钮
	 */
	BUTTON("按钮");

	private String text;

	private MenuType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
