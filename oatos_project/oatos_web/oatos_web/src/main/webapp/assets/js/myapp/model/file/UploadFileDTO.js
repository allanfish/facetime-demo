define(function(require, exports, module) {

	/**
	 * 新建文件DTO
	 */
	 window.UploadFileDTO = Backbone.Model.extend({
	 	urlRoot : "",
	 	defaults : {
	 		id: undefined, //SWFUpload file id, used for starting or cancelling and upload
	 		index: 0, // The index of this file for use in getFile(i)
	 		name: undefined, // 
	 		size: 0, // 
	 		uploadedSize: 0,
	 		status: undefined, // 'wait', 'uploading', 'pause', 'cancel','complete', 'error'
	 		type: undefined, //
	 		creationdate: undefined, // The date the file was created
	 		modificationdate: undefined // The date the file was last modified
	 	},
	 	idAttribute: 'id'
	 });
	});
