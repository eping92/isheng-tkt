layui.use('element', function() {
	var element = layui.element;
});

/**
 * index vue mount
 */
var MAIN = new Vue({
	el : '#main',
	data : {
		realName : '何平波'
	},
	methods:{
		login: function() {
			alert(1);
		}
	}
});
