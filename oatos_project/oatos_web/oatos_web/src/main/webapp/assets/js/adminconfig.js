seajs.config({
	alias : {
		'$' : 'modules/jquery/1.7.2/jquery.js',
		'jquery' : 'modules/jquery/1.7.2/jquery.js',
		'jqm' : 'modules/jquerymobile/jquery.mobile-1.1.0.min.js',
		'jquerycookie' : 'modules/jquerycookie/jquery.cookie.js',
		'jquery.hotkeys' : 'modules/jquery-hotkeys/jquery.hotkeys.js',
		'json' : 'modules/json/1.0.2/json.js',
		'jqueryztree': 'modules/jqueryztree/jquery.ztree.all-3.5.min.js',
		'moment': 'modules/moment/2.0.0/moment.js',
		'moment-zh-cn': 'modules/moment/2.0.0/zh-cn.js',

		'underscore' : 'modules/underscore/underscore.js',
		'backbone' : 'modules/backbone/1.0.0/backbone.js',
		'log4javascript' : 'modules/log4javascript/log4javascript.js',

		'amq': 'modules/activemq/amq.js', 
		'amq.jquery.adapter': 'modules/activemq/amq_jquery_adapter.js',

		'backbonemodelbinder' : 'modules/backbonemodelbinder/backbone-modelbinder.js',
		'backbone.collectionbinder' : 'modules/backbonemodelbinder/backbone-collectionbinder.js',
		'backbonevalidation' : 'modules/backbonevalidation/backbone-validation-amd.js',
		'backboneroutefilter' : 'modules/backboneroutefilter/backbone-routefilter.js',
		'backbone.marionette' : 'modules/backbone-marionette/backbone.marionette.js',
		'backbone.localStorage' : 'modules/backbone-localStorage/backbone.localStorage.js',
		'backbone.layoutmanager' : 'modules/backbone-layoutmanager/backbone.layoutmanager.js',
		'backbone.relational' : 'modules/backbone-relational/backbone.relational.js',

		'handlebars' : 'modules/handlebars/1.0.0/handlebars.js',
		'coffee' : 'modules/coffee/1.3.3/coffee-script.js',
		'less' : 'modules/less/1.3.0/less.js',

		'bootstrap': 'modules/bootstrap/bootstrap.js',
		'tbl-editable': 'modules/bootstrap-editable/bootstrap-editable.min.js',
		'bootstrap-wysiwyg': 'modules/bootstrap-wysiwyg/bootstrap-wysiwyg.js',
		'fuelux-wizard': 'modules/bootstrap-fuelux/wizard.js',

		'dialog': 'modules/lhgdialog/lhgcore.lhgdialog.min.js',

		'tplurl-webmanagement': '/assets/website/templates-webadmin',
		'tplurl-website' : '/assets/website/templates-website',
		'tplurl-mobile' : '/assets/mobile/templates',

		'wind' : 'modules/wind/wind-all-0.7.3.js',
		'baseurl' : '/assets/',
		'jsurl' : '/assets/js',
		'modelurl': "jsurl/myapp/model/",
		'collectionurl': 'jsurl/myapp/collection/',
		'viewurl': 'jsurl/myapp/views/'
	},

	preload : [ 'plugin-json', 'plugin-text', 'plugin-coffee', 'plugin-less' ],
	debug : 2

});

seajs.use('jsurl/admininit');
