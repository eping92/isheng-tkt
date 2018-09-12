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
public class ResultResp<T> implements Serializable{

	private static final long serialVersionUID = -5130128292735384177L;
	
	/**
	 * 响应码
	 */
	private int code;
	/**
	 * 提示信息
	 */
	private String msg;
	/**
	 * 返回的数据结果
	 */
	private T data;
	
	public ResultResp() {};
	
	public ResultResp(ErrMsg errMsg) {
		this.code = errMsg.getCode();
		this.msg = errMsg.getText();
	}
	
	public ResultResp(ErrMsg errMsg, T data) {
		this.code = errMsg.getCode();
		this.msg = errMsg.getText();
		this.data = data;
	}
	
	public ResultResp(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResultResp(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public ResultResp<T> setCode(int code) {
		this.code = code;
		return this;
	}
	
	public ResultResp<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public ResultResp<T> setData(T data) {
		this.data = data;
		return this;
	}
	
	public ResultResp<T> setResponse(ErrMsg errMsg) {
		this.code = errMsg.getCode();
		this.msg = errMsg.getText();
		return this;
	}
	
	public ResultResp<T> setResponse(ErrMsg errMsg, T data) {
		this.code = errMsg.getCode();
		this.msg = errMsg.getText();
		this.data = data;
		return this;
	}
	
	public ResultResp<T> parseResult(ResultResp<T> result) {
		ResultResp<T> resp = new ResultResp<>();
		resp.setCode(result.getCode());
		resp.setMsg(result.getMsg());
		resp.setData(result.getData());
		return resp;
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

	public boolean isSuccess() {
		return ErrMsg.SUCCESS.getCode() == this.code;
	}
	
	public String toJson() {
		return JSONObject.toJSONString(this);
	}
	

}
