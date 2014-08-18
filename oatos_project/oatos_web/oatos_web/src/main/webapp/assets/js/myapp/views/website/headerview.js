define(function(require, exports, module) {
	tpl.headerview = require('tplurl-website/header.tpl');
    tpl.userView = require('tplurl-website/UserView.tpl');

    userView = Backbone.View.extend({

        initialize : function() {
            tplpre.userView = Handlebars.compile( tpl.userView);
            this.render();
        },

        render : function() {
            $(this.el).html(tplpre.userView);
            return this;
        }
    });

	window.HeaderView = Backbone.View.extend({

		initialize : function() {
			// this._modelBinder = new Backbone.ModelBinder();
            tplpre.headerview = Handlebars.compile( tpl.headerview);
			this.render();
		},

		render : function() {
			$(this.el).html(tplpre.headerview);
			// this._modelBinder.bind(this.model, this.$('#logined'));
            this.showUserInfo();
			return this;
		},

		events : {
			"click #usercenterbutton" : "usercentersubmit",
			'click ul.nav li': 'clickNavItem'

		},

		clickNavItem: function(event){
			this.$el.find('ul.nav > li').removeClass('active');
			$(event.currentTarget).addClass('active');
		}, 

		usercentersubmit : function() {
			view.mainbox = new UserCenterView({
				model :  model.session,
				el : $("#mainbox")
			});
		},

		setLogoImg : function() {
			// TODO
			console.log("header view: setLogoImg");
		},

        showUserInfo: function(){

            view.userView=new userView();
            $('ul.nav>li.last').popover({
                html: true,
                placement: 'bottom',
                content: view.userView.el,
                trigger: 'click'
            });
        }

	});

});
