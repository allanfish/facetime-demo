define(function(require, exports, module) {

	require("jquery");
	require("jquerycookie");
	require("json");
	require("wind");

	// views
	require('./views/website/indexview');
	require('./views/website/headerview');
	require('./views/website/mainboxview');
	
	// user view
	require('./views/website/admin/AdminLeftView');
	require('./views/website/admin/AdminWizardView');
	require('./views/website/admin/EntInfoView');

	var AdminRouter = Backbone.Router.extend({
		routes: {
			"": "homepage",
			"index": "homepage",
			"user/login": "loginUser",
			"user/reg": "regUser",
			"loginout": "loginOut",
			'conference': 'showConferenceView',
			"*path": "homepage"
		},

		before: function(route){
			log.debug(' route filter before > route: ', route);
			if(!route || route === 'index'){
				return true; 
			}
			if(!view.mainbox){
				this.navigate('index', true);
				return false;
			}
		}, 

		after: function() {
			// $("#mainbox").slideUp();
		},

		initialize: function() {
			view.indexpage = new IndexView({
				el: $("body")
			});

			view.header = new HeaderView({
				model: model.session,
				el: $("#headerbox")
			});
		},

		/**
		 * [homepage 进入系统后的首页]
		 * 
		 * @param {[string]}
		 *            path
		 * @return {[void]}
		 */
		 homepage: function(path) {
		 	view.mainbox = new MainboxView({
		 		el: $("#mainbox")
		 	});

		 	view.adminLeftView  = new AdminLeftView();
		 	$("#content-left").append(view.adminLeftView.el);

		 	view.contentRight = view.adminWizardView = new AdminWizardView();
		 	$("#content-right").append(view.contentRight.el);
		 }
		});

	exports.initialize = function() {
		router.adminRouter = new AdminRouter();
		Backbone.history.start(); // 当所有的 路由 创建并设置完毕，调用 Backbone.history.start() 开始监控 hashchange 事件并分配路由
	};
});
