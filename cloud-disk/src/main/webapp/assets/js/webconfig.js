seajs.config({
	alias : {
		'$' : 'modules/jquery/1.7.2/jquery.js',
		'jquery' : 'modules/jquery/1.7.2/jquery.js',
		'jqm' : 'modules/jquerymobile/jquery.mobile-1.1.0.min.js',
		'jquerycookie' : 'modules/jquerycookie/jquery.cookie.js',
		'json' : 'modules/json/1.0.2/json.js',
		'jqueryztree': 'modules/jqueryztree/jquery.ztree.all-3.5.min.js',

		'underscore' : 'modules/underscore/1.3.3/underscore.js',
		'backbone' : 'modules/backbone/0.9.2/backbone.js',

		'backbonemodelbinder' : 'modules/backbonemodelbinder/backbone-modelbinder.js',
		'backbonevalidation' : 'modules/backbonevalidation/backbone-validation-amd.js',
		'backboneroutefilter' : 'modules/backboneroutefilter/backbone-routefilter.js',

		'handlebars' : 'modules/handlebars/1.0.0/handlebars.js',
		'coffee' : 'modules/coffee/1.3.3/coffee-script.js',
		'less' : 'modules/less/1.3.0/less.js',

		'bootstrap': 'modules/bootstrap/bootstrap.js',
		'tbl-editable': 'modules/bootstrap-editable/bootstrap-editable.min.js',

		'tplurl-webmanagement': '/assets/website/templates-webadmin',
		'tplurl-website' : '/assets/website/templates-website',
		'tplurl-mobile' : '/assets/mobile/templates',

		'wind' : 'modules/wind/wind-all-0.7.3.js',
		'baseurl' : '/assets/',
		'jsurl' : '/assets/js'
	},

	preload : [ 'plugin-json', 'plugin-text', 'plugin-coffee', 'plugin-less' ],
	debug : 2

});

seajs.use('jsurl/webinit');
