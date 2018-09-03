package com.isheng.common.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.isheng.common.enums.ErrMsg;

/**
 * 用于前端页面返回数据
 *
 *
 * @author Administrator
 * @version $Id: JsonResult.java 2018年8月19日 上午11:56:17 $
 */
public class ResultModel extends LinkedHashMap<String, Object> {

	/**  */
	private static final long serialVersionUID = 3088241066983370022L;

	private static final String CODE = "code";

	private static final String MSG = "msg";

	private static final String DATA = "data";

	public ResultModel() {
	};

	public ResultModel(ErrMsg errMsg) {
		put(CODE, errMsg.getCode());
		put(MSG, errMsg.getText());
	}

	public ResultModel(int code) {
		this.put(CODE, code);
	}

	public ResultModel(String msg) {
		put(MSG, msg);
	}

	public ResultModel(int code, String msg) {
		put(CODE, code);
		put(MSG, msg);
	}

	public ResultModel(int code, String msg, Object data) {
		put(CODE, code);
		put(MSG, msg);
		put(DATA, data);
	}

	public boolean isSuccess() {
		Object obj = get(CODE);
		return null != obj && ErrMsg.SUCCESS.getCode() == (Integer) obj;
	}

	public int getCode() {
		Object obj = get(CODE);
		return null != obj ? (Integer) obj : ErrMsg.FAILED.getCode();
	}

	public ResultModel setCode(int code) {
		put(CODE, code);
		return this;
	}

	public String getMsg() {
		Object obj = get(MSG);
		return null != obj ? (String) obj : "";
	}

	public ResultModel setMsg(String msg) {
		put(MSG, msg);
		return this;
	}

	public ResultModel setDate(Map<String, Object> data) {
		put(DATA, data);
		return this;
	}
	
	public ResultModel setResult(ErrMsg errMsg) {
		put(CODE, errMsg.getCode());
		put(MSG, errMsg.getText());
		return this;
	}
	
	public ResultModel setResult(ResultResp resp) {
		if (null == resp) {
			put(CODE, ErrMsg.RESP_NULL.getCode());
			put(MSG, ErrMsg.RESP_NULL.getText());
		}
		put(CODE, resp.getCode());
		put(MSG, resp.getMsg());
		put(DATA, resp.getData());
		return this;
	}
	
	public ResultModel setResult(ErrMsg errMsg, Map<String, Object> data) {
		put(CODE, errMsg.getCode());
		put(MSG, errMsg.getText());
		put(DATA, data);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getData() {
		return (Map<String, Object>) get(DATA);
	}

	public ResultModel addData(String key, Object value) {
		Map<String, Object> data = getData();
		if (null == data) {
			data = new LinkedHashMap<String, Object>();
			setDate(data);
		}
		data.put(key, value);
		return this;
	}
	
	public String toJson() {
		return JSONObject.toJSONString(this);
	}

}
