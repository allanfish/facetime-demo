/**
 * 系统设置视图
 */
define(function(require, exports, module) {

	tpl.systemSettingView = require('tplurl-website/setting/SystemSettingView.tpl');

	window.systemSettingView = Backbone.View.extend({
		tagName : 'div',
		id : 'systemSettingView',
		_modelBinder : undefined,

		initialize : function() {
			this._modelBinder = new Backbone.ModelBinder();
			tplpre.systemSettingView = Handlebars
					.compile(tpl.systemSettingView);
			this.render();
		},

		render : function() {
			this.$el.html(tplpre.systemSettingView);
			this._modelBinder.bind(this.model, this.$el);
			return this;
		},

		close : function() {
			this._modelBinder.unbind();
			this.off();
			this.undelegateEvents();
			this.remove();
		},

	/** end view */
	});

});