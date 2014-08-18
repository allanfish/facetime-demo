define(function(require, exports, module) {

	tpl.indexview = require('tplurl-website/index.tpl');
	
	window.IndexView = Backbone.View.extend({
		
		initialize: function () {
			this.render();
		},


		render: function () {
			tplpre.indexview = Handlebars.compile(tpl.indexview );
			$(this.el).html(tplpre.indexview);
			return this;
		}
	});
})