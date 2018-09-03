package com.isheng.common.model;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import com.isheng.common.enums.ErrMsg;

/**
 * service数据返回封装类
 *
 *
 * @author Administrator
 * @version $Id: ResultModel.java 2018年8月6日 下午11:10:56 $
 */
public class ResultResp implements Serializable{

	private static final long serialVersionUID = -5130128292735384177L;
	
	private int code;
	
	private String msg;
	
	private Object data;
	
	public ResultResp() {};
	
	public ResultResp(ErrMsg errMsg) {
		this.code = errMsg.getCode();
		this.msg = errMsg.getText();
	}
	
	public ResultResp(ErrMsg errMsg, Object data) {
		this.code = errMsg.getCode();
		this.msg = errMsg.getText();
		this.data = data;
	}
	
	public ResultResp(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResultResp(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public ResultResp setCode(int code) {
		this.code = code;
		return this;
	}
	
	public ResultResp setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public ResultResp setData(Object data) {
		this.data = data;
		return this;
	}
	
	public ResultResp setResponse(ErrMsg errMsg) {
		this.code = errMsg.getCode();
		this.msg = errMsg.getText();
		return this;
	}
	
	public ResultResp setResponse(ErrMsg errMsg, Object data) {
		this.code = errMsg.getCode();
		this.msg = errMsg.getText();
		this.data = data;
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public boolean isSuccess() {
		return ErrMsg.SUCCESS.getCode() == this.code;
	}
	
	public String toJson() {
		return JSONObject.toJSONString(this);
	}
	

}
