/**
  网盘试图左边导航树
*/
define(function(require, exports, module) {

	require("jqueryztree");
	require("wind");

	app.tpl.fileLeftView = require('tplurl-website/file/file_left.tpl');

	window.FileLeftView = Backbone.View.extend({

		initialize: function() {
			this.render();
		},

		render: function() {
			app.tplpre.fileLeftView = Handlebars.compile(app.tpl.fileLeftView);
			$(this.el).html(app.tplpre.fileLeftView);
			return this;
		}, 

		events: {
			'show #person-folder-accordion': 'showPersonFolderTree'			
		}, 

		showPersonFolderTree: function(event){
			if(app.collection.personFolderList.length == 0){
				app.collection.personFolderList.fetchFolder();
			}
		}

	});
});
