/**
 * 用户集合DTO
 * @param  {[type]} require       [description]
 * @param  {[type]} exports       [description]
 * @param  {[type]} module)       {	window.UserListDTO =                                        Backbone.Collection.extend({		model : UserDTO [description]
 * @param  {[type]} localStorage: new                   Backbone.LocalStorage("userListStorage") [description]
 * @param  {[type]} url           :                     ""	});}                                [description]
 * @return {[type]}               [description]
 */
 define(function(require, exports, module) {

 	window.UserListDTO = Backbone.Collection.extend({
 		model : UserDTO,
 		localStorage: new Backbone.LocalStorage("userListStorage"), 
 		url : ""
 	});

 });
