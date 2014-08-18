define(function(require, exports, module) {

	require("jqueryztree");

	tpl.addUsualContactTreeView = require('tplurl-website/user/AddUsualContactTreeView.tpl')


	window.AddUsualContactTreeView = Backbone.View.extend({
		tagName: 'ul',
		id: 'AddUsualContactTree', 
		_this: undefined,

		initialize: function() {
			_this = this;
			tplpre.addUsualContactTreeView = Handlebars.compile(tpl.addUsualContactTreeView);
			this.render();
		},

		render: function() {
			var rootDepartments = this.collection.where({'parentId': null});
			log.debug('rootDepartments: ',rootDepartments.length);
			var departmentUserList = _.map(rootDepartments,function(department){
				return {
					_parentId: 0,
					department: department.toJSON(),
					userList: _.map(collection.userList.where({'departmentId': department.get('departmentId')}), function(userModel){
						if(collection.usualContactList.where({'userId':userModel.get('userId')}).length > 0) {
							userModel.set('checked',true);
						}else {
							userModel.set('checked',false);
						}
						return userModel.toJSON();
					})
				}
			});
			this.$el.html(tplpre.addUsualContactTreeView({departmentUserList: departmentUserList}));
			this.recurse_render(rootDepartments);
			return this;
		}, 

		recurse_render: function(currentDepartments){
			_.each(currentDepartments, function(department){
				var children = this.collection.where({'parentId' : department.get('departmentId')}); 
				if(!children.length)
					return ;

				var childrenList = children.map(function(inner_depart){
					return {
						_parentId: department.get('departmentId'),
						department: inner_depart.toJSON(),
						userList: _.map( 
							collection.userList.where({'departmentId': inner_depart.get('departmentId')}), function(userModel){
								if(collection.usualContactList.where({'userId':userModel.get('userId')}).length > 0) {
									userModel.set('checked',true);
								}else {
									userModel.set('checked',false);
								}
								return userModel.toJSON();
							})
					}});

				var html = tplpre.addUsualContactTreeView({departmentUserList: childrenList});
				_this.$el.find('div[parentDepartmentId='+department.get('departmentId')+']').append(html);
				this.recurse_render(children);
			}, this);
		}
	});
});
