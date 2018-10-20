/******************* 首页js ************************/
/**
 * 设置左侧导航区域宽度
 * */
$("#left").width(leftWidth);
/**
 * openClose手风琴展开和关闭左侧导航事件
 * @returns
 */
$("#openClose").click(function(){
	if($(this).hasClass("close")){
		$(this).removeClass("close");
		$(this).addClass("open");
		$("#left").animate({width:0,opacity:"hide"});
		$("#right").animate({width:$("#content").width()-$("#openClose").width()-5},function(){
			if(typeof openCloseClickCallBack == 'function'){
				openCloseClickCallBack(true);
			}
			wSize();
		});
	}else{
		$(this).addClass("close");
		$(this).removeClass("open");
		$("#left").animate({width:leftWidth,opacity:"show"});
		$("#right").animate({width:$("#content").width()-$("#openClose").width()-leftWidth-9},function(){
			if(typeof openCloseClickCallBack == 'function'){
				openCloseClickCallBack(true);
			}
			wSize();
		});
	}
});

$(document).ready(function() {
	// 绑定菜单单击事件
	$("#menu a.menu").click(function(){
		// 一级菜单焦点
		$("#menu li.menu").removeClass("active");
		$(this).parent().addClass("active");
		// 左侧区域隐藏
		if ($(this).attr("target") == "mainFrame"){
			$("#left,#openClose").hide();
			wSizeWidth();
			return true;
		}
		// 左侧区域显示
		$("#left,#openClose").show();
		if(!$("#openClose").hasClass("close")){
			$("#openClose").click();
		}
		// 显示二级菜单
		var menuId = "#menu-" + $(this).attr("data-id");
		if ($(menuId).length > 0){
			$("#left .accordion").hide();
			$(menuId).show();
			// 初始化点击第一个二级菜单
			if (!$(menuId + " .accordion-body:first").hasClass('in')){
				$(menuId + " .accordion-heading:first a").click();
			}
			if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
				$(menuId + " .accordion-body a:first i").click();
			}
			// 初始化点击第一个三级菜单
			$(menuId + " .accordion-body li:first li:first a:first i").click();
		}else{
			// 获取二级菜单数据
			$.get($(this).attr("data-href"), function(data){
				if (data.indexOf("id=\"loginForm\"") != -1){
					alert('未登录或登录超时。请重新登录，谢谢！');
					top.location = "/";
					return false;
				}
				$("#left .accordion").hide();
				$("#left").append(data);
				// 链接去掉虚框
				$(menuId + " a").bind("focus",function() {
					if(this.blur) {this.blur()};
				});
				// 二级标题
				$(menuId + " .accordion-heading a").click(function(){
					$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
					if(!$($(this).attr('data-href')).hasClass('in')){
						$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
					}
				});
				// 二级内容
				$(menuId + " .accordion-body a").click(function(){
					$(menuId + " li").removeClass("active");
					$(menuId + " li i").removeClass("icon-white");
					$(this).parent().addClass("active");
					$(this).children("i").addClass("icon-white");
				});
				// 展现三级
				$(menuId + " .accordion-inner a").click(function(){
					var href = $(this).attr("data-href");
					if($(href).length > 0){
						$(href).toggle().parent().toggle();
						return false;
					}
					// 
				});
				// 默认选中第一个菜单
				$(menuId + " .accordion-body a:first i").click();
				$(menuId + " .accordion-body li:first li:first a:first i").click();
			});
		}
		// 大小宽度调整
		wSizeWidth();
		return false;
	});
	// 初始化点击第一个一级菜单
	$("#menu a.menu:first span").click();
	// 
	// 鼠标移动到边界自动弹出左侧菜单
	$("#openClose").mouseover(function(){
		if($(this).hasClass("open")){
			$(this).click();
		}
	});
});
