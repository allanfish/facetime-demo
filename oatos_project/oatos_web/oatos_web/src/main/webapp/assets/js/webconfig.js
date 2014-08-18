seajs.config({
    alias: {
        // url path
        'baseurl': '/os/assets/',
        'jsurl': '/os/assets/js/',
        'modelurl': "jsurl/myapp/model/",
        'collectionurl': 'jsurl/myapp/collection/',
        'viewurl': 'jsurl/myapp/views/',
        'tplurl-webmanagement': '/os/assets/website/templates-webadmin',
        'tplurl-website': '/os/assets/website/templates-website',
        'tplurl-mobile': '/os/assets/mobile/templates',

        '$': 'modules/jquery/1.9.1/jquery.js',
        'jquery': 'modules/jquery/1.9.1/jquery.js',
        'jqm': 'modules/jquerymobile/jquery.mobile-1.1.0.min.js',
        'jquerycookie': 'modules/jquerycookie/jquery.cookie.js',
        'jquery.hotkeys': 'modules/jquery-hotkeys/jquery.hotkeys.js',
        'jquery.hoverIntent': 'modules/jquery-hoverIntent/jquery.hoverIntent.js',
        'json': 'modules/json/1.0.2/json.js',
        'jqueryztree': 'modules/jqueryztree/jquery.ztree.all-3.5.min.js',
        'moment': 'modules/moment/2.0.0/moment.js',
        'moment-zh-cn': 'modules/moment/2.0.0/zh-cn.js',
        // 'lhgdialog': 'modules/lhgdialog/lhgdialog.js',
        'dialog': 'modules/lhgdialog/lhgcore.lhgdialog.min.js',
        'select2': "modules/select2/select2.js",
        'select2-zh-CN': "modules/select2/select2_locale_zh-CN.js",

        'underscore': 'modules/underscore/underscore.js',
        'underscore.string': 'modules/underscore-string/underscore.string.js',
        'backbone': 'modules/backbone/1.0.0/backbone.js',

        'log4javascript': 'modules/log4javascript/log4javascript.js',
        'amq': 'modules/activemq/amq.js',

        'amq.jquery.adapter': 'modules/activemq/amq_jquery_adapter.js',
        'backbone.modelbinder': 'modules/backbone-modelbinder/backbone-modelbinder.js',
        'backbone.collectionbinder': 'modules/backbone-modelbinder/backbone-collectionbinder.js',
        'backbone.validation': 'modules/backbone-validation/backbone.validation.js',
        'backbone.routefilter': 'modules/backbone-routefilter/backbone-routefilter.js',
        'backbone.marionette': 'modules/backbone-marionette/backbone.marionette.js',
        'backbone.localStorage': 'modules/backbone-localStorage/backbone.localStorage.js',
        'backbone.layoutmanager': 'modules/backbone-layoutmanager/backbone.layoutmanager.js',
        'backbone.deepmodel': "modules/backbone-deepmodel/deep-model.js",

        'backbone.relational': 'modules/backbone-relational/backbone.relational.js',
        'handlebars': 'modules/handlebars/1.0.0/handlebars.js',
        'coffee': 'modules/coffee/1.3.3/coffee-script.js',

        'less': 'modules/less/1.3.0/less.js',
        'bootstrap': 'modules/bootstrap/bootstrap.js',
        'tbl-editable': 'modules/bootstrap-editable/bootstrap-editable.min.js',
        'bootstrap-datepicker': 'modules/bootstrap-datepicker/bootstrap-datepicker.js',
        'bootstrap-datepicker.zh-CN': 'modules/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.js',

        'bootstrap-wysiwyg': 'modules/bootstrap-wysiwyg/bootstrap-wysiwyg.js',

        'swfobject': 'modules/swfobject/2.2.0/swfobject.js',
        'swfupload': 'modules/swfupload/swfupload.js',
        'swfupload.jquery': 'modules/swfupload/jquery.swfupload.js',
        'swfupload.swf': 'modules/swfupload/swfupload.swf',

        'wind': 'modules/wind/wind-all-0.7.3.js'
    },

    preload: [ 'plugin-json', 'plugin-text', 'plugin-coffee', 'plugin-less' ],
    debug: 2

});

seajs.use('jsurl/webinit');
