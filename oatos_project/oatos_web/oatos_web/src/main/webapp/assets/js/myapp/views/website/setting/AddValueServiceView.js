/**
  * 增值服务
  */
  define(function(require, exports, module) {

  	tpl.addValueServiceView = require('tplurl-website/setting/AddValueServiceView.tpl');

  	window.addValueServiceView = Backbone.View.extend({
  		tagName: 'div', 
  		id: 'addValueServiceView',
      _modelBinder: undefined, 

      initialize: function() {
        this._modelBinder = new Backbone.ModelBinder();
        tplpre.addValueServiceView = Handlebars.compile(tpl.addValueServiceView);
        this.render();
      },

      render: function() {
       this.$el.html(tplpre.addValueServiceView);
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