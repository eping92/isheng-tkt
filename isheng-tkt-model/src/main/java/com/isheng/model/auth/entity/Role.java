package com.isheng.model.auth.entity;

import com.isheng.common.base.BaseEntity;

public class Role extends BaseEntity {

	private static final long serialVersionUID = 1734619732560148539L;

	private String name;

	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", desc=" + desc + "]";
	}

}
