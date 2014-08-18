define(function(require, exports, module) {

	require("jquery");
	require("jquerycookie");
	require("json");
	require("wind");

	// views
	require('./views/website/indexview');
	require('./views/website/headerview');
	require('./views/website/mainboxview');
	require('./views/website/file/FileLeftView');
	require('./views/website/file/EntFileTable');
	require('./views/website/file/EntFolderTree');
	require('./views/website/file/PersonFolderTree');
	require('./views/website/file/EntFileItem');

	// dtos
	require('./model/user/UserInfoDTO');
	require('./model/ent/EnterpriseDTO');
	require('./model/file/EntFolderDTO');
	require('./model/file/EntFileDTO');
	require('./collection/file/EntFolderListDTO');
	require('./collection/file/EntFileListDTO');

	require('./model/userregmodel');
	require('./collection/usercollection');
	require('./model/file/PersonFolderDTO');
	require('./model/file/PersonFileDTO');
	require('./collection/file/PersonFolderListDTO');
	require('./collection/file/PersonFileListDTO');

	var AppRouter = Backbone.Router.extend({
		routes: {
			"": "homepage",
			"index": "homepage",
			"user/login": "loginUser",
			"user/reg": "regUser",
			"loginout": "loginOut",
			"*path": "homepage"

		},

		before: function(route) {

			$("#mainbox").hide();
			// $("#mainbox").delay(2000);
			$("#mainbox").fadeIn();

			console.log("before", app.model.session.get("login_state"));

		},

		after: function() {
			// $("#mainbox").slideUp();
		},

		initialize: function() {
			app.view.indexpage = new IndexView({
				el: $("body")
			});
			app.view.header = new HeaderView({
				model: app.model.session,
				el: $("#headerbox")
			});
		},

		homepage: function(path) {
			app.collection.entFolderList = new EntFolderListDTO();
			app.collection.entFileList = new EntFileListDTO();
			app.collection.entFileList.bind("file: add", function(model) {
				console.log('file:add: ' + model);
			});

			app.collection.personFolderList = new PersonFolderListDTO();
			app.collection.personFolderList.bind('file: add', function(model) {
				console.log("personFolderTree add event");
				app.view.personFolderTree.addFolderNode(model);
			});
			app.collection.personFileList = new PersonFileListDTO();
			app.collection.personFileList.bind('file: add', function(model) {
				console.log("personFileList add event");

			});
			app.model.currentEntFolder = new EntFolderDTO({
				parentId: null,
				folderId: null,
				name: 'root'
			});

			app.view.mainbox = new MainboxView({
				el: $("#mainbox")
			});

			app.view.fileleft = new FileLeftView({
				el: $("#content-left")
			});
			app.view.entFolderTree = new EntFolderTree({
				el: $("#entFolderTree"),
				model: app.collection.entFolderList
			});
			app.view.personFolderTree = new PersonFolderTree({
				el: $("#personFolderTree"),
				model: app.collection.personFolderList
			});

			app.view.entFileTable = new EntFileTable({
				el: $("#content-right"),
				model: app.collection.entFolderList
			});

			var task = eval(Wind.compile("async", function() {
				var userInfoJson = $await(app.resturl.getUserProfile(app.model.clientToken.userId));
				console.log("user info: " + userInfoJson);
				app.model.currentUser = new UserInfoDTO(userInfoJson);
				app.model.userId = app.model.currentUser.get("userId");
				app.model.entId = app.model.currentUser.get("enterpriseId");

				var entInfoJson = $await(app.resturl.getEnterprise(app.model.entId));
				console.log("enterprise info:" + entInfoJson);
				app.model.currentEnterprise = new EnterpriseDTO(entInfoJson);

				app.collection.entFolderList.fetchFolder(null, function() {
					app.model.currentEntFolder.trigger("selectEntFolder");
				});
			}));
			task().start();
		},

		loginOut: function() {
			app.model.session.delsession();
			app.view.mainbox = new MainboxView({
				el: $("#mainbox")
			});
		}

	});

	exports.initialize = function() {
		new AppRouter();
		Backbone.history.start(); // 当所有的 路由 创建并设置完毕，调用 Backbone.history.start() 开始监控 hashchange 事件并分配路由
	};
})
