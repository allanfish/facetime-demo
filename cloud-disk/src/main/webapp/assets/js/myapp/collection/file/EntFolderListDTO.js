/**
 * 企业文件夹集合
 */
define(function(require, exports, module) {

	window.EntFolderListDTO = Backbone.Collection.extend({
		model: EntFolderDTO,
		url: "",

		children: function(folderId) {
			return this.where({
				parentId: folderId
			});
		},

		rootChildren: function() {
			return this.where({
				parentId: null
			});
		},

		fetchFolder: function(folderId, callback) {
			var that = this;
			var async = eval(Wind.compile("async", function() {
				var childFolderJson = $await(app.resturl.getEntFolderAndFileByFolderId({
					userId: app.model.userId,
					folderIds: folderId ? [ folderId ] : []
				}));

				var shareFolders = _.map(childFolderJson['forderList'], function(folder) {
					return new EntFolderDTO(folder);
				});
				if (shareFolders)
					app.collection.entFolderList.add(shareFolders);

				var files = _.map(childFolderJson['fileList'], function(file) {
					return new EntFileDTO(file);
				});
				if (files)
					app.collection.entFileList.add(files);

				var currentFolder = folderId ? that.get(folderId) : null;
				if (currentFolder) {
					currentFolder.set({
						'loaded': true
					});
				}
				console.log(shareFolders);
				callback();
			}));

			async().start();
		}
	});
});
