/**
  * 文件详情VIEW
  */
  define(function(require, exports, module) {

  	tpl.fileDetailView = require('tplurl-website/file/FileDetailView.tpl');

  	window.FileDetailView = Backbone.View.extend({

  		tagName: 'div', 
  		id: 'file-detail',
  		_modelBinder: undefined, 

      events: {
      'click  .file-detail-info ul li': 'fileInfoToggle'
    },

  		initialize: function() {
        log.debug(" FileDetailView initialize");
  			tplpre.fileDetailView = Handlebars.compile(tpl.fileDetailView);
  			_.bindAll(this);
  			this._modelBinder = new Backbone.ModelBinder();
  			this.render();
  		},

  		close: function(){
  			this._modelBinder.unbind();
  			this.off();
  			this.undelegateEvents();
  			this.remove();
  		}, 

  		render: function() {
            if(this.model.isFolder()){
                this.model.set('size','--');
            }

            this.model.set('createTime',moment(new Date(parseInt(this.model.get('createTime')))).format("YYYY-MM-DD HH:mm:ss"));
            this.model.set('updateTime',moment(new Date(parseInt(this.model.get('updateTime')))).format("YYYY-MM-DD HH:mm:ss"));
            this.$el.html(tplpre.fileDetailView(this.model.toJSON()));
  			this._modelBinder.bind(this.model, this.el);
  			return this;
  		},

      fileInfoToggle: function(event){
        var startname=$(event.target).attr('name');
        var endname=$(event.currentTarget).attr('name');
        if(startname!=endname) return true;
        $("#"+endname).toggleClass('hide');
      }

  	/**end view*/});

  });