/********************** 全局工具类 ******************/
/** json转字符串 */
function toString(jsonData) {
	if (jsonData) {
		return JSON.stringify(jsonData);
	}
	return "";
};

/** 字符串转json */
function toJson(strData) {
	if (strData && $.trim(strData).length >= 1) {
		return JSON.parse(strData);
	}
};

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
 * post请求
 * @param _url
 * @param _params
 * @returns
 */
function httpPost(_url, _params) {
	var params = _params || {};
	$.post(_url, {
		params: JSON.stringify(params);
	}).done(function(data) {
		return data;
	}).fail(function() {
		return null;
	});
}

/**
 * get请求
 * @param _url
 * @param _params
 * @returns
 */
function httpGet(_url, _params) {
	var params = _params || {};
	$.get(_url, {
		params: JSON.stringify(params);
	}).done(function(data) {
		return data;
	}).fail(function() {
		return null;
	});
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
