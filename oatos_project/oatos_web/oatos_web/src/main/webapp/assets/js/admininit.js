define(function(require) {

	var $ = jQuery = require('jquery');
	var Backbone = require('backbone');
	var _ = require('underscore');
	var Handlebars = require('handlebars');
	window.moment = require('moment');
	require('moment-zh-cn');

	window.$ = $;
	window.Backbone = Backbone;
	window._ = _;
	window.Handlebars = Handlebars;

	require('backboneroutefilter');
	require('backbone.localStorage');
	// require('backbone.layoutmanager');

	// require("backbone.relational");
	Backbone.Validation = require('backbonevalidation');
	Backbone.ModelBinder = require('backbonemodelbinder');
	require('backbone.collectionbinder');

	// 不能使用require('bootstrap')($);
	require('bootstrap');
	require('tbl-editable');
	require('fuelux-wizard');

	require("wind");
	require('./myapp/utils/constants');
	require("./myapp/utils/util");

	require("amq.jquery.adapter");
	require("amq");

	require('dialog');

	// dtos
	require('./myapp/model/ent/EnterpriseDTO');
	require('./myapp/model/user/UserDTO');
	require('./myapp/collection/user/UserListDTO');
	require('./myapp/model/user/DepartmentDTO');
	require('./myapp/collection/user/DepartmentListDTO');
	require('./myapp/collection/user/UsualContactListDTO');

	require('./myapp/model/file/EntFileDTO');
	require('./myapp/collection/file/EntFileListDTO');

	var admin = {
		model: {},
		modelbinder: {},
		tpl: {},
		view: {},
		tplpre: {},
		collection: {},
		htmlbody: {},
		temp: {},
		constants: {},
		resturl: {},
		cache: {},
		events: {}, 
		router: {}
	};

	var initAmq = function() {
		var clientId = util.guid();
		org.activemq.Amq.init({
			uri: 'amq',
			logging: true,
			timeout: 45,
			clientId: clientId,
			headers: {
				"UT": cache.clientToken.token
			}
		});
		log.info("init amq -> ok");
	};

	var getUserProfileTask = eval(Wind.compile('async', function() {
		var userInfo = $await(resturl.getUserProfile(cache.userId));
		model.currentUser.set(userInfo);
		cache.username = model.currentUser.get("userName");
		cache.entId = model.currentUser.get("enterpriseId");
		log.info("[ajax] user load -> OK");
		log.info('entId: ', cache.entId);

		addAmqListener();
	}));

	// 加载当前用户和同事部门数据
	var getDepartmentAndUserWithStatusByUserIdTask = eval(Wind.compile('async', function() {
		var departmentAndUser = $await(resturl.getDepartmentAndUserWithStatusByUserId(cache.userId));
		collection.userList.add(_.map(departmentAndUser.userList, function(user) {
			return new UserDTO(user);
		}));

		collection.departmentList.add(_.map(departmentAndUser.departmentList, function(department) {
			return new DepartmentDTO(department);
		}));

		// 发出初始化公司同事事件
		collection.departmentList.trigger('department: init', collection.departmentList);
		log.info("[ajax] department load -> OK");
	}));

	// 加载企业数据
	var getEnterpriseTask = eval(Wind.compile('async', function() {
		var entInfoJson = $await(resturl.getEnterprise(cache.entId));

		model.currentEnterprise.set(entInfoJson);
		log.info("enterprise load -> OK");
	}));

	var getEntFolderAndFileByFolderIdTask = eval(Wind.compile("async", function() {
		// 加载根企业文件夹数据
		var rootFolders = $await(resturl.getEntFolderAndFileByFolderId({
			userId: cache.userId,
			folderIds: []
		}));
		log.info("rootFolders load -> ok, length: ", rootFolders['forderList'].length);

		var entFiles = _.map(rootFolders['forderList'].concat(rootFolders['fileList']),function(each) {
			return each.fileId ?  new EntFileDTO().initFromFile(each) :  new EntFileDTO().initFromFolder(each);
		});

		collection.entFileList.add(entFiles).trigger(events.loadFile, entFiles);
		model.currentFolder.trigger('change'); 	
	}));

	// 获取常用联系人数据
	var getUsualContactGroups = eval(Wind.compile('async', function() {
		var usualContact = $await(resturl.getUsualContactGroupsByUserId(cache.userId));
		log.info("usualContact:" , usualContact);
		collection.usualContactList.add(_.map(usualContact.userList, function(user) {
			//log.debug("user:" , user);
			return new UserDTO(user);
		}));
		log.info("[ajax] usualContact load -> OK");
	}));


	var initData = function() {
		collection.entFileList = new EntFileListDTO();
		collection.departmentList = new DepartmentListDTO();
		collection.userList = new UserListDTO();
		collection.usualContactList = new UsualContactListDTO();
		model.currentUser = new UserDTO();
		model.currentEnterprise = new EnterpriseDTO();

		model.rootEntFolder = new EntFileDTO({
			parentId: null,
			fileId: 'root',
			name: '企业文件'
		});
		model.currentFolder = new EntFileDTO(model.rootEntFolder.toJSON());

		var initTask = eval(Wind.compile('async', function() {
			$await(getUserProfileTask());
			getEnterpriseTask().start();
			getDepartmentAndUserWithStatusByUserIdTask().start();
			getEntFolderAndFileByFolderIdTask().start();
			getUsualContactGroups().start();
		}));
		initTask().start();
	};



	var addAmqListener = function() {
		var userTopic = constants.amqTopic + cache.userId;
		var entTopic = constants.amqTopic + cache.entId;

		log.info("listener to topics: ", userTopic, entTopic);
		org.activemq.Amq.addListener(userTopic, userTopic, this.messageHandler);
		org.activemq.Amq.addListener(entTopic, entTopic, this.messageHandler);
	};

	var messageHandler = function(message) {
		var text = message.childNodes[0].data;
		log.info("amq msg > ", text);
	};
	
	var initlogger = function() {
		require('log4javascript');
		window.log = log4javascript.getLogger();
		var consoleAppender = new log4javascript.BrowserConsoleAppender();
		consoleAppender.setThreshold(log4javascript.Level.DEBUG);
		log.addAppender(consoleAppender);
	};

	$(function() {
		_.extend(window, admin);
		$.fn.editable.defaults.mode = 'inline';
		initlogger();
		resturl = require('./myapp/utils/resturl');
		constants = require('./myapp/utils/constants');
		events = require('./myapp/utils/app.events');

		cache.clientToken = require('./myapp/model/user/ClientToken');
		cache.userId = cache.clientToken.userId;
		log.info('userId: ', cache.userId);

		initAmq();

		initData();

		var BBRouter = require('./myapp/adminrouter');
		BBRouter.initialize();
	});

});
