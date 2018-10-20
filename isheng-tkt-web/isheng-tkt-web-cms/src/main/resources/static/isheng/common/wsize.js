var leftWidth = 160; // 左侧窗口大小
var tabTitleHeight = 33; // 页签的高度
var htmlObj = $("html"), mainObj = $("#main");
var headerObj = $("#header"), footerObj = $("#footer");
var frameObj = $("#left, #openClose, #right, #right iframe");

function wSize(){
	var minHeight = 500, minWidth = 980;
	var strs = getWindowSize().toString().split(",");
	htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
	mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
	frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
	$("#openClose").height($("#openClose").height() - 5);// 
	wSizeWidth();
}

function wSizeWidth(){
	if (!$("#openClose").is(":hidden")){
		var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
		$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
	}else{
		$("#right").width("100%");
	}
}// 

var getWindowSize = function(){
	return ["Height","Width"].map(function(name){
	  return window["inner"+name] ||
		document.compatMode === "CSS1Compat" && document.documentElement[ "client" + name ] || document.body[ "client" + name ];
	});
};

if(!Array.prototype.map)
	Array.prototype.map = function(fn,scope) {
	var result = [],ri = 0;
	for (var i = 0,n = this.length; i < n; i++){
	  if(i in this){
	    result[ri++]  = fn.call(scope ,this[i],i,this);
	  }
	}
	return result;
};

$(window).resize(function(){
	wSize();
});
wSize(); // 在主窗体中定义，设置调整目标