package com.isheng.model.auth.request;

import com.isheng.common.base.BaseQuery;
import com.isheng.model.auth.enums.MenuType;

public class MenuQuery extends BaseQuery {

	/**  */
	private static final long serialVersionUID = 215677083216021544L;

	private String name;

	private String code;

	private String url;

	private MenuType type;

	private long parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "MenuQuery [name=" + name + ", code=" + code + ", url=" + url + ", type=" + type + ", parentId="
				+ parentId + "]";
	}

}
