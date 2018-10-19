/************************* 首页js ******************************/
if (!INDEX) {
	var INDEX = {};
}

/**
 * 数据请求url
 */
INDEX.url = {
	login: "/login",
	loadMenu: "/loadMenu"
}

/**
 * 加载左侧菜单
 * @returns
 */
function loadMenu() {
	$.post(INDEX.url.loadMenu, function(data) {
		$("#_left").html(data);
	}).fail(function() {
		
	});
}

$(function() {
	loadMenu();
})