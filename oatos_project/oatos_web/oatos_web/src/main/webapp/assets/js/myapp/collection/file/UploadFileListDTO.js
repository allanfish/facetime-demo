/**
 * 上传的文件集合
 */
 define(function(require, exports, module) {

 	window.UploadFileListDTO = Backbone.Collection.extend({
 		model : UploadFileDTO,
 		url : "/uploadFileDTO"
 	});

 });
