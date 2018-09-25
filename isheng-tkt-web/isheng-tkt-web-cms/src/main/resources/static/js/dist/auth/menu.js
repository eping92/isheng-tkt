var menu = new Vue({
	el: ".table-responsive",
	data: {
		titleList: ['权限名称', '类型', '权限码', '请求地址', '创建人', '创建时间', '更新人', '更新时间', '说明备注', '操作'],
		dataList: []
	},
	/** 页面加载完成后自动执行的生命周期函数 */
	mounted: function() {
		this.list();
	},
	methods: {
		list: function() {
			this.$http({
				method: 'POST',
				url: '/menu/list',
				params:{
					
				}
			}).then(function(result) {
				alert(JSON.stringify(result));
			}), function(err) {
				alert('查询出错');
			}
		},
		add: function() {
			
		},
		detail: function() {
			
		},
		update: function() {
			
		}, 
		del: function() {
			
		}
	}
});

