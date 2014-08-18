/**
  * 后台管理左边导航VIEW
  */
  define(function(require, exports, module) {

  	tpl.adminLeftView = require('tplurl-website/admin/AdminLeftView.tpl');


  	window.AdminLeftView = Backbone.View.extend({
  		tagName: 'div', 
  		id: 'adminLeftView',

  		initialize: function() {
  			tplpre.adminLeftView = Handlebars.compile(tpl.adminLeftView);
  			this.render();
  		},

  		render: function() {
  			this.$el.html(tplpre.adminLeftView);
  			return this;
  		}
  	});

  });