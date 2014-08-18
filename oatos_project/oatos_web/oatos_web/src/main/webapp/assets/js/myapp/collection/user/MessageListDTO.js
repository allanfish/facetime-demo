 define(function(require, exports, module) {
 	/**
 	 * 消息集合DTO
 	 */
 	 window.MessageListDTO = Backbone.Collection.extend({
 	 	model : MessageDTO,
 	 	localStorage: new Backbone.LocalStorage("messageListDTO"), 
 	 	url : ""
 	 });

 	});
