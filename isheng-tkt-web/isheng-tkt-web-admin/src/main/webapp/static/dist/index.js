layui.use('element', function() {
	var element = layui.element;
});

/**  请求地址 */
var URL = {
		login: "/login",//登录
		logout: "/logout"//登出
};

/**
 * index vue mount
 */
var MAIN = new Vue({
	el : '#main',
	data : {
		loginName : '何平波'
	},
	methods:{
		login: function() {
			this.$http({
				method: METHOD.post,
				url: URL.login,
				params:{
					loginName: "eping92", 
					pwd: "123456"
				}
			}).then(function(response) {
				alert(responseMsg(response));
			}),function(err) {
				alert(toString(err));
			}
			
		},
		logout: function() {
			this.$http({
				url: URL.logout,
			}).then(function(response) {
				alert(responseMsg(response));
			}),function(err){
				alert(err);
			}
		}
	}
});

