 define(function(require, exports, module) {
 	/**
 	 * 与会成员集合DTO
 	 */
 	window.MemberListDTO = Backbone.Collection.extend({
 		model : ConferenceMemberDTO,
 		localStorage: new Backbone.LocalStorage("memberListStorage"), 
 		url : ""
 	});

 });
