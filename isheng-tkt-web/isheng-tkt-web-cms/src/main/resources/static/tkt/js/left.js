/*****************************
*******左侧导航**************
*****************************/
var left = new Vue({
	el: "#main-nav",
	data: {
		dataList:[]
	},
	mounted: function() {
		this.initMenu();
	},
	methods: {
		initMenu: function() {
			this.$http({
				method: 'POST',
				url: '/menu/menuTree',
				params: {
					
				}
			}).then(function(r) {
				alert(JSON.stringify(r.body));
				this.dataList = r.body;
			}), function(err) {
				alert('查询出错了');
			}
		}
	}
});