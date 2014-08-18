/**
 * 企业文件夹集合
 */
define(function(require, exports, module) {

	window.PersonFolderListDTO = Backbone.Collection.extend({
		model: PersonFolderDTO,
		url: "",
		fetchFolder: function(folderId) {
			var that = this;
			var async = eval(Wind.compile("async", function() {
				var childFolderJson = $await(app.resturl.getPersonalFolderAndFileByFolderId({
					userId: app.model.userId,
					folderIds: folderId ? [ folderId ] : []
				}));

				var folders = _.map(childFolderJson['forderList'], function(folder) {
					return new PersonFolderDTO(folder);
				});
				if (folders)
					app.collection.personFolderList.add(folders);

				var files = _.map(childFolderJson['fileList'], function(file){
					return new PersonFileDTO(file);	
				});
				if(files)
					app.collection.personFileList.add(files);

				if (folderId) {
					var currentFolder = that.get(folderId);
					currentFolder.set({
						'loaded': true
					});
				}
			}));

			async().start();
		}
	});
});
