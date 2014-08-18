/**
  * 文件右边列表VIEW
  */
  define(function(require, exports, module) {

  	tpl.fileRightView = require('tplurl-website/file/FileRightView.tpl');

  	window.FileRightView = Backbone.View.extend({
      tagName: 'div', 
      id: 'fileRightView',

  		initialize: function() {
  			tplpre.fileRightView = Handlebars.compile(tpl.fileRightView);
  			this.render();
  		},

  		render: function() {
  			this.$el.append(tplpre.fileRightView);
  			return this;
  		}

  	/**end view*/});

  });