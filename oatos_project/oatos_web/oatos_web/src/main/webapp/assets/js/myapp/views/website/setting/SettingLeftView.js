/**
  * 设置界面左边导航视图
  */
  define(function(require, exports, module) {

  	tpl.settingLeftView = require('tplurl-website/setting/SettingLeftView.tpl');

  	window.SettingLeftView = Backbone.View.extend({
  		tagName: 'div', 
  		id: 'setting-left',

  		initialize: function() {
  			tplpre.settingLeftView = Handlebars.compile(tpl.settingLeftView);
  			this.render();
  		},

  		render: function() {
  			this.$el.html(tplpre.settingLeftView);
  			return this;
  		}

  	/**end view*/});

  });