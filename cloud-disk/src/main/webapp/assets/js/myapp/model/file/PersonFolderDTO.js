/**
 * 个人文件夹DTO
 */
 define(function(require, exports, module) {

 	window.PersonFolderDTO = Backbone.Model.extend({
 		urlRoot: "",
 		idAttribute: "folderId",

 		defaults: {
 			loaded: false
 		},

 		initialize: function() {
 			return this;
 		}
 	});

 });