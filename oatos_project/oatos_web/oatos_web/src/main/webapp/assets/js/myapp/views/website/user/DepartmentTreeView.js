define(function(require, exports, module) {

	require("jqueryztree");
	require('./UserInfoCardView');
	
	tpl.departmentTreeView = require('tplurl-website/user/DepartmentTreeView.tpl')
	tpl.departmentItemView = require('tplurl-website/user/DepartmentItemView.tpl')
	tpl.colleagueTreeView = require('tplurl-website/user/ColleagueTreeView.tpl')


	window.DepartmentTreeView = Backbone.View.extend({
		tagName: 'ul',
		id: 'departmentTree', 
		_this: undefined, 
		collectionBinder: undefined,
		sonDepartmentList: undefined,

		initialize: function() {
			_this = this;
			this.sonDepartmentList = new DepartmentListDTO();
			tplpre.departmentTreeView = Handlebars.compile(tpl.departmentTreeView);
			var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
			this.collectionBinder = new Backbone.CollectionBinder(elManagerFactory);
			this.render();
		},

		viewCreator: function(model){
			return new DepartmentItemView({model: model});
		},

		render: function() {
			var rootDepartments = this.collection.where({'parentId': null});
			this.sonDepartmentList.set(rootDepartments);
			this.$el.html(tplpre.departmentTreeView);
			this.collectionBinder.bind(this.sonDepartmentList, this.$el.find('.accordion'));
			this.recurse_render(rootDepartments);
			return this;
		}, 

		recurse_render: function(currentDepartments){
			_.each(currentDepartments, function(department){
				var children = this.collection.where({'parentId' : department.get('departmentId')}); 
				if(!children.length)
					return ;

				/*var childrenList = children.map(function(inner_depart){
					return {
						_parentId: department.get('departmentId'),
						department: inner_depart.toJSON(),
						userList: _.map( 
							collection.userList.where({'departmentId': inner_depart.get('departmentId')}), function(userModel){
								return userModel;
							})
					}});*/
				var grandsonDepartmentList = new DepartmentListDTO();
				grandsonDepartmentList.set(children);
				var sonElManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(_this.viewCreator);
				var sonCollectionBinder = new Backbone.CollectionBinder(sonElManagerFactory);
				var html = tplpre.departmentTreeView();
				_this.$el.find('div[parentDepartmentId='+department.get('departmentId')+']').append(html);
				sonCollectionBinder.bind(grandsonDepartmentList, _this.$el.find('div[parentDepartmentId='+department.get('departmentId')+'] .accordion'));
				this.recurse_render(children);
			}, this);
		}
	});

	window.DepartmentItemView = Backbone.View.extend({
		_modelBinder: undefined,
		collectionBinder: undefined,
		departmentUserList: undefined,

		initialize: function() {
			this.departmentUserList = new UserListDTO();
			var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
			this.collectionBinder = new Backbone.CollectionBinder(elManagerFactory);

    		tplpre.departmentItemView = Handlebars.compile(tpl.departmentItemView);
    		this._modelBinder = new Backbone.ModelBinder();
    	},

    	viewCreator: function(model){
			return new ColleagueTreeView({model: model});
		},

    	render: function() {
    		this.$el.html(tplpre.departmentItemView);
    		var  bindings = {
    			'name': '[class=accordion-toggle]',
    			'departmentId': [{selector: '[class=accordion-group]', elAttribute: 'departmentId', converter: this.setDepartmentId},{selector: '[class=inner-department]', elAttribute: 'parentDepartmentId', converter: this.setDepartmentId},{selector: '[class=accordion-toggle]', elAttribute: 'href', converter: this.setHref},{selector: '[name=accordion-body]', elAttribute: 'id', converter: this.setBodyId}]
    		};
    		this._modelBinder.bind(this.model, this.el, bindings);

			this.departmentUserList.set(collection.userList.where({'departmentId': this.model.get('departmentId')}));
    		this.collectionBinder.bind(this.departmentUserList, this.$el.find(".userList"));
    		return this;
    	},

    	setDepartmentId: function(direction, value) {
    		return "" + value;
    	},

    	setHref: function(direction, value) {
    		return "#department_" + value;
    	},

    	setBodyId: function(direction, value) {
    		return "department_" + value;
    	},

    	setUserListId: function(direction, value) {
    		return "userList_" + value;
    	},

    	close: function(){
	      this._modelBinder.unbind();
	      this.off();
	      this.undelegateEvents();
	      this.remove();
	    }
	});

	window.ColleagueTreeView = Backbone.View.extend({
		tagName: 'li',
		_modelBinder: undefined, 
		userInfoCardView: undefined,

		initialize: function() {
    		tplpre.colleagueTreeView = Handlebars.compile(tpl.colleagueTreeView);
    		this._modelBinder = new Backbone.ModelBinder();
    	},

    	render: function() {
    		this.$el.html(tplpre.colleagueTreeView);
    		var  bindings = {
    			'userName': '[class=userName]'
    		};
    		this._modelBinder.bind(this.model, this.el, bindings);
    		this.userInfoCardView = new UserInfoCardView({model: this.model});
    		this.$el.popover({
    			'trigger': 'hover', 
    			'placement': 'left',
    			'html': true,
    			'content': this.userInfoCardView.el
    		});
    		return this;
    	},
    	close: function(){
	      this._modelBinder.unbind();
	      this.off();
	      this.undelegateEvents();
	      this.remove();
	    }
	});
});
