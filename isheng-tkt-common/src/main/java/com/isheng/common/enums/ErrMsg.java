package com.isheng.common.enums;

public enum ErrMsg implements BaseEnum {
	
	FAILED(-1, "操作失败"),
	SUCCESS(9999, "操作成功"),
	
	WEB_200(200, "请求成功"),
	WEB_300(300, "重定向"),
	WEB_400(400, "请求无法解析"),
	WEB_401(400, "未经授权"),
	WEB_403(400, "访问被拒绝"),
	WEB_404(400, "找不到文件或目录"),
	WEB_500(500, "服务器内部错误"),
	
	EXP_SYS(-1000, "系统异常"),
	EXP_ADD(-1001, "新增异常"),
	EXP_DEL(-1002, "删除异常"),
	EXP_UP(-1003, "更新异常"),
	EXP_QUERY(-1004, "查询异常"),
	
	PARAM_NULL(-2000, "参数为空"),
	PARAM_ERR(-2000, "参数错误"),
	PARAM_MISS(-2000, "参数不全"),
	
	RESP_NULL(-3000, "返回数据为空"),
	RESP_ERR(-3001, "返回数据错误");
	
	private int code;
	
	private String text;
	
	private ErrMsg(int code, String text) {
		this.code = code;
		this.text = text;
	}
	
	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getText() {
		return text;
	}
	

}
