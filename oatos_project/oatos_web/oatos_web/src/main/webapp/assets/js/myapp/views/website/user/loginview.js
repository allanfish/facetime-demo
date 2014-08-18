define(function(require, exports, module) {

	app.tpl.loginview = require('tplurl-website/user/login.tpl');
	
	window.LoginView = Backbone.View.extend({
		
		initialize: function () {
			this._modelBinder = new Backbone.ModelBinder();
			this.render();    
	    },
	
	    render: function () {
   
			app.tplpre.loginview = Handlebars.compile( app.tpl.loginview );
			$(this.el).html(app.tplpre.loginview);

			Backbone.Validation.bind(this, {
				valid: function(view, attr, selector) {
					var control = view.$('[' + selector + '=' + attr + ']');
					var group = control.parents(".control-group");
					group.removeClass("error");
					group.addClass("success");
					
					if (control.data("error-style") === "tooltip"){
						// CAUTION: calling tooltip("hide") on an uninitialized tooltip
						// causes bootstraps tooltips to crash somehow...
						if (control.data("tooltip"))
							control.tooltip("hide");
					}else if (control.data("error-style") === "inline"){
						group.find(".help-inline.error-message").remove();
					}else{
						group.find(".help-block.error-message").remove();
					}
				},				
				invalid: function(view, attr, error, selector) {
					var control = view.$('[' + selector + '=' + attr + ']');
					var group = control.parents(".control-group");
					group.removeClass("success");
					group.addClass("error");
					
					if (control.data("error-style") === "tooltip"){
						var position = control.data("tooltip-position") || "right";
						control.tooltip({
							placement: position,
							trigger: "manual",
							title: error
						});
					
						control.tooltip("show");
					}
					else if (control.data("error-style") === "inline"){
						if (group.find(".help-inline").length === 0){
							group.find(".controls").append("<span class=\"help-inline error-message\"></span>");
						}
						var target = group.find(".help-inline");
						target.text(error);
					}else {
						if (group.find(".help-block").length === 0) {
							group.find(".controls").append("<p class=\"help-block error-message\"></p>");
						}
						var target = group.find(".help-block");
						target.text(error);
					}
				}
			});
				
			this._modelBinder.bind(this.model, this.$('#loginform'));
	        return this;
	    },

	    events: {
			"click #loginbutton": "loginsubmit",
			
	    },

		loginsubmit: function(e){
			e.preventDefault();
			this.$('.alert').hide();
			this.model.loginaction(this.model);
		}
	});


})


