/**
 * 请求方式
 */
var METHOD = { 
		post: "POST", 
		get: "GET"
}

/**
 * json转字符串
 * @param data
 * @returns
 */
function toString(data) {
	if (data) {
		return JSON.stringify(data);
	}
	return "";
}

/**
 * 字符串转json
 * @param data
 * @returns
 */
function toJson(data) {
	if (data) {
		return JSON.parse(data);
	}
	return null;
}

/**
 * 后台封装的相应消息
 * @param response
 * @returns
 */
function responseMsg (response) {
	if (response) {
		if (response.body) {
			return response.body.msg;
		} 
	}
	return "响应消息为空";
}

/**
 * 后台封装的响应码
 * @param response
 * @returns
 */
function responseCode (response) {
	if (response) {
		if (response.body) {
			return response.body.code;
		}
	}
	return "";
}

/**
 * 后台是否处理成功
 * @param response
 * @returns {Boolean}
 */
function isSuccess(response) {
	var code = responseCode(response);
	return "9999" == code;
}