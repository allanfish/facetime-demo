/**
 * 企业文件集合
 */
 define(function(require, exports, module) {

 	window.EntFileListDTO = Backbone.Collection.extend({
 		model : EntFileDTO,
 		url : "",

 		children: function(folderId){
 			return this.where({folderId: folderId});
 		},

 		rootChildren: function(){
 			return this.where({folderId: null});
 		},

 	});

 });
