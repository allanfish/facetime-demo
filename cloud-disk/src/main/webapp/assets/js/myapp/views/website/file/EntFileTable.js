define(function(require, exports, module) {

	app.tpl.entFileTable = require('tplurl-website/file/EntFileTable.tpl');

	window.EntFileTable = Backbone.View.extend({

		initialize: function() {
			app.tplpre.entFileTable = Handlebars.compile(app.tpl.entFileTable);
			this.listenTo(app.model.currentEntFolder, 'selectEntFolder', this.selectFolder);
			this.render();
		},

		render: function() {
			this.$el.html(app.tplpre.entFileTable);
			return this;
		},

		selectFolder: function() {
			this.$el.html("");
			var that = this;

			_.each(app.collection.entFolderList.children(app.model.currentEntFolder.get("folderId")).concat(
					app.collection.entFileList.children(app.model.currentEntFolder.get("folderId"))), function(folder) {
				var fileItem = new EntFileItem({
					model: folder
				});
				that.$el.append(fileItem.render());
			});
		},
	});
});
