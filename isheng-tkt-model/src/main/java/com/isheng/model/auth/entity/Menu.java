package com.isheng.model.auth.entity;

import com.isheng.common.base.BaseEntity;
import com.isheng.model.auth.enums.MenuType;

/**
 * 菜单
 *
 *
 * @author Administrator
 * @version $Id: Menu.java 2018年7月21日 上午12:28:54 $
 */
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 5955332391431717453L;

	private String name;

	private String code;

	private String url;

	private MenuType type;

	private long parentId;

	private int sort;

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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", code=" + code + ", url=" + url + ", type=" + type + ", parentId=" + parentId
				+ ", sort=" + sort + "]";
	}

}
