package com.isheng.model.common.exception;

import com.isheng.common.enums.ErrMsg;

/**
 * 基础异常类
 * @author Administrator
 *
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -579522096454722829L;

	private int code;
	
	public BizException() {
		super();
	}
	
	public BizException(ErrMsg errMsg) {
		super(errMsg.getText());
		this.code = errMsg.getCode();
	}
	
	public BizException(ErrMsg errMsg, Throwable throwable) {
		super(errMsg.getText(), throwable);
		this.code = errMsg.getCode();
	}

	public BizException(String msg) {
		super(msg);
	}

	public BizException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public BizException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public BizException(int code, String msg, Throwable throwable) {
		super(msg, throwable);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
