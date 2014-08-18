/**
  * 设置向导企业信息VIEW
  */
  define(function(require, exports, module) {

    tpl.entInfoView = require('tplurl-website/admin/EntInfoView.tpl');

    window.EntInfoView = Backbone.View.extend({
      tagName: 'div', 
      id: 'EntInfo',
      _modelBinder: undefined, 

      initialize: function() {
        tplpre.entInfoView = Handlebars.compile(tpl.entInfoView);
        this._modelBinder = new Backbone.ModelBinder();
        this.render();
      },

      close: function(){
        this.collectionBinder.unbind();
        this.off();
        this.undelegateEvents();
        this.remove();
      }, 

      render: function() {
        this.$el.html(tplpre.entInfoView);
        this._modelBinder.bind(this.model, this.el);
        return this;
      },

      events: {
        'click :submit': 'changeEntInfo'
      }, 

      changeEntInfo: function(){
        log.debug(" change ent info .");

        eval(Wind.compile('async', function(that) {
          var result = $await(resturl.updateEnterprise(that.model.toJSON()));
          if('OK' === result){
            log.debug(" change ent info ok!");
          }
        }))(this).start();
      }

    /** end view */});

});