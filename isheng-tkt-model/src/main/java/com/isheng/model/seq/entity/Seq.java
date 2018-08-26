package com.isheng.model.seq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ID序列
 *
 *
 * @author Administrator
 * @version $Id: Seq.java 2018年7月28日 下午11:51:09 $
 */
public class Seq implements Serializable {
	
	/**  */
	private static final long serialVersionUID = -4101969939267258687L;

	/**
	 * 数据库中的ID，如：SEQ_MEMBER
	 */
	private String id;
	
	/**
	 * 最新的ID
	 */
	private long seq;
	
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	
	public Seq() {};
	
	public Seq(String key, long seq) {
		this.id = key;
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
