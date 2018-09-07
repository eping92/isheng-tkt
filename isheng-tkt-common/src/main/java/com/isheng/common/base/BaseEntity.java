package com.isheng.common.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.isheng.common.idgen.IdGenerate;

/**
 * 基础entity
 *
 *
 * @author Administrator
 * @version $Id: BaseEntity.java 2018年9月1日 下午6:19:44 $
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -2399738930327105420L;

	private String id = IdGenerate.nextId();

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getCreateTimeText() {
		if (null != createTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(createTime);
		}
		return "";
	}
	
	public String getUpdateTimeText() {
		if (null != updateTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(updateTime);
		}
		return "";
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser="
				+ updateUser + ", updateTime=" + updateTime + "]";
	}

}
