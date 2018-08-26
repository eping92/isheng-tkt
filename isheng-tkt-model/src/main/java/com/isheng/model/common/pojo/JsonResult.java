package com.isheng.model.common.pojo;

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
public class JsonResult extends LinkedHashMap<String, Object> {

	/**  */
	private static final long serialVersionUID = 3088241066983370022L;

	private static final String CODE = "code";

	private static final String MSG = "msg";

	private static final String DATA = "data";

	public JsonResult() {
	};

	public JsonResult(ErrMsg errMsg) {
		put(CODE, errMsg.getCode());
		put(MSG, errMsg.getText());
	}

	public JsonResult(int code) {
		this.put(CODE, code);
	}

	public JsonResult(String msg) {
		put(MSG, msg);
	}

	public JsonResult(int code, String msg) {
		put(CODE, code);
		put(MSG, msg);
	}

	public JsonResult(int code, String msg, Object data) {
		put(CODE, code);
		put(MSG, msg);
		put(DATA, data);
	}

	public boolean isSuccess() {
		Object obj = get(CODE);
		return null != obj && ErrMsg.SUCCESS.getCode() == (int) obj;
	}

	public int getCode() {
		Object obj = get(CODE);
		return null != obj ? (int) obj : ErrMsg.FAILED.getCode();
	}

	public JsonResult setCode(int code) {
		put(CODE, code);
		return this;
	}

	public String getMsg() {
		Object obj = get(MSG);
		return null != obj ? (String) obj : "";
	}

	public JsonResult setMsg(String msg) {
		put(MSG, msg);
		return this;
	}

	public JsonResult setDate(Map<String, Object> data) {
		put(DATA, data);
		return this;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getData() {
		return (Map<String, Object>) get(DATA);
	}

	public JsonResult addData(String key, Object value) {
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
