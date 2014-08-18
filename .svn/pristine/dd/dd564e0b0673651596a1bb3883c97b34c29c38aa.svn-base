define(function(require, exports, module) {

	app.tpl.entFileItem = require('tplurl-website/file/EntFileItem.tpl');

	window.EntFileItem = Backbone.View.extend({
		// model type: EntFolderDTO || EntFileDTO
		events: {},

		initialize: function(){
			// this.listenTo(this.model, "change", this.render);
			// this.listenTo(this.model, "change", this.render);
			this.render();
		},

		render: function(){
			app.tplpre.entFileItem = Handlebars.compile(app.tpl.entFileItem);
			var entFile = this.model.toJSON();
			entFile.id = this.model.id;
			return app.tplpre.entFileItem(entFile);
		}

	});

});