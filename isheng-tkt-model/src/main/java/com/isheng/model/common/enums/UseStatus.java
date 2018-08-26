package com.isheng.model.common.enums;

/**
 * 使用状态
 * @author Administrator
 *
 */
public enum UseStatus {
	
	/**
	 * 启用
	 */
	 ENABLE("启用"),
	 /**
	  * 停用
	  */
	 DISABLE("停用");
	
	private String text;

	private UseStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
