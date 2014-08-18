define(function(require) {

	var $ = jQuery = require('jquery');
	var Backbone = require('backbone');
	var _ = require('underscore');
	var Handlebars = require('handlebars');
	
	window.$ = $;
	window.Backbone = Backbone;
	window._ = _;
	window.Handlebars = Handlebars;

	Backbone.Validation = require('backbonevalidation');
	Backbone.ModelBinder = require('backbonemodelbinder');
	require('backboneroutefilter');
	// 不能使用require('bootstrap')($);
	require('bootstrap');
	require('tbl-editable');

	
	require('./myapp/utils/constants');
	require('./myapp/model/usersession');
	
	window.app = {
		model : {},
		modelbinder : {},
		view : {},
		tpl : {},
		tplpre : {},
		collection : {},
		htmlbody : {},
		temp : {},
		constants : {},
		resturl : {}
	};

	$(function() {
		app.resturl = require('./myapp/utils/resturl');
		app.model.clientToken = require('./myapp/model/user/ClientToken');;
		app.model.session = new UserSession();
		
		var BBRouter = require('./myapp/webrouter');
		BBRouter.initialize();
	});
});
