/*************************** 权限管理 **********************************/
if (!MENU) {
	var MENU = {};
}

MENU.URL = {
	list: "/menu/list",
	add: "/menu/add",
	del: "/menu/delete",
	update: "/menu/update"
}

var params = {
	pageNo: 1,
	pageSize: 10,
	name: ""
}

$(".nav nav-tabs li").click(function() {
	$this = $(this);
});



function list() {
	layer.load("数据加载中...");
	var data = httpPost(MENU.URL.list, params);
	$("#_menuList").html(data);
}
