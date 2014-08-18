/**
  * 视频会议信息VIEW
  */
  define(function(require, exports, module) {

  	tpl.conferenceInfoView = require('tplurl-website/conference/ConferenceInfoView.tpl');


  	window.ConferenceInfoView = Backbone.View.extend({
  		tagName: 'div', 
  		id: 'conference-info',
  		_modelBinder: undefined, 

  		initialize: function() {
  			tplpre.conferenceInfoView = Handlebars.compile(tpl.conferenceInfoView);
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
  			this.$el.html(tplpre.conferenceInfoView);
  			this._modelBinder.bind(this.model, this.el);
  			return this;
  		}
  	});

  });