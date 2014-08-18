/**
  * 移动应用下载
  */
  define(function(require, exports, module) {

  	tpl.downloadAppView = require('tplurl-website/setting/DownloadAppView.tpl');

  	window.downloadAppView = Backbone.View.extend({
  		tagName: 'div', 
  		id: 'downloadAppView',
      _modelBinder: undefined, 

      initialize: function() {
        this._modelBinder = new Backbone.ModelBinder();
        tplpre.downloadAppView = Handlebars.compile(tpl.downloadAppView);
        this.render();
      },

      render: function() {
       this.$el.html(tplpre.downloadAppView);
       this._modelBinder.bind(this.model, this.$el);
       return this;
     },

     close: function(){
      this._modelBinder.unbind();
      this.off();
      this.undelegateEvents();
      this.remove();
    },

  /**end view*/});

  });