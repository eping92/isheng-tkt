/********************** 全局工具类 ******************/

/**
 * 操作成功的提示信息
 * @param okMsg
 * @returns
 */
function alertOk(okMsg) {
	if (!okMsg) {
		okMsg = "操作成功！";
	}
	alertMsg(true, okMsg);
}

/**
 * 操作失败的提示信息
 * @param errMsg
 * @returns
 */
function alertErr(errMsg) {
	if (!errMsg) {
		errMsg = "操作失败！";
	}
	alertMsg(false, errMsg);
}

/**
 * 拼凑提示信息
 * @param bool
 * @param msg
 * @returns
 */
function alertMsg(bool, msg) {
	var style = "alert alert-success";
	var html = "<strong>恭喜您！</strong>" + msg;
	if (!bool) {
		style = "alert alert-warning";
		html = "<strong>警告！</strong>" + msg;
	}
	$("#_alertMsg").html(html).parent().attr("class", style).show().fadeOut(5000);
}

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
		params: JSON.stringify(params)
	}).done(function(data) {
		return data;
	}).fail(function(err) {
		return err;
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
		params: JSON.stringify(params)
	}).done(function(data) {
		return data;
	}).fail(function(err) {
		return err;
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
