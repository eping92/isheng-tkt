/** http请求类型 */
var httpMethod = {
		post: 'POST',
		get: 'get'
};

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

/** post请求 */
function httpPost(_url, _params) {
	this.$http({
		method: httpMethod.post,
		url: _url,
		params: _params
	}).then(function(result) {
		return result;
		
	}), function(err) {
		console.log(err);
	}
};

/** get请求 */
function httpGet(_url, _params) {
	this.$http({
		method: httpMethod.get,
		url: _url,
		params: _params
	}).then(function(result) {
		return result;
		
	}), function(err) {
		console.log(err);
	}
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