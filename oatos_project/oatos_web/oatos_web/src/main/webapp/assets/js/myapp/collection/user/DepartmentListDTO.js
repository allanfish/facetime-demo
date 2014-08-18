 define(function(require, exports, module) {
 	/**
 	 * 部门集合DTO
 	 */
 	window.DepartmentListDTO = Backbone.Collection.extend({
 		model : DepartmentDTO,
 		localStorage: new Backbone.LocalStorage("departmentListStorage"), 
 		url : ""
 	});

 });
