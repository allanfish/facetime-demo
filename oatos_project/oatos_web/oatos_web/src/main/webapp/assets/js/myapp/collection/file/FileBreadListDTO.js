/**
 * 企业文件面包屑集合
 */
 define(function(require, exports, module) {

 	window.FileBreadListDTO = Backbone.Collection.extend({
 		model : EntFileDTO,
 		url : ""
 	});

 });
