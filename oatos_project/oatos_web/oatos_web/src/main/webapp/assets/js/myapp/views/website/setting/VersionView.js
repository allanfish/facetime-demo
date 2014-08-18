/**
  * 版本信息
  */
  define(function(require, exports, module) {

  	tpl.versionView = require('tplurl-website/setting/VersionView.tpl');

  	window.versionView = Backbone.View.extend({
  		tagName: 'div', 
  		id: 'versionView',
      _modelBinder: undefined, 

      initialize: function() {
        this._modelBinder = new Backbone.ModelBinder();
        tplpre.versionView = Handlebars.compile(tpl.versionView);
        this.render();
      },

      render: function() {
       this.$el.html(tplpre.versionView);
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