/**
  * 用户详细信息VIEW
  */
define(function(require, exports, module) {

	tpl.userInfoCardView = require('tplurl-website/user/UserInfoCard.tpl');


	window.UserInfoCardView = Backbone.View.extend({
		tagName: 'div', 
		className: 'name-card',
		_modelBinder: undefined, 

		initialize: function() {
			tplpre.userInfoCardView = Handlebars.compile(tpl.userInfoCardView);
			this._modelBinder = new Backbone.ModelBinder();
			this.render();
		},

		close: function(){
			this.collectionBinder.unbind();
			this.off();
			this.undelegateEvents();
			this.remove();
		}, 

		render: function() {
			this.$el.html(tplpre.userInfoCardView);
			var  bindings = {
    			'userName': '[class=card-user-name]',
    			'icon': {selector: '[class=avatar]', elAttribute: 'src', converter: this.getAvatar},
    			'jobTitle': '[class=jobTitle]',
    			'phone': '[class=phone]',
    			'mobile': '[class=mobile]',
    			'registerMailAccount': '[class=mail]',
    			'signature': '[name=signature]',
    			'onlineStatus': [{selector: '[name=onlineStatus]', elAttribute: 'class', converter: this.getOnline},{selector: '[name=onlineStatus]', converter: this.getOnlineValue}]
    		}
			this._modelBinder.bind(this.model, this.el, bindings);
			return this;
		},

		getAvatar: function (direction, value) {
            var url;
            if(value == undefined || value == null) {
                url = "/os/assets/website/img/defaultAvatar64man.png";
            } else {
                url = "/os/assets/website/" + value;
            }
            return url;
        },

        getOnline: function(direction, value) {
            return value + "";
        },

        getOnlineValue: function(direction, value) {
        	var result;
        	switch (value) {
        		case "online":
	        		result = "在线";
	        		break;
	        	case "busy":
	        		result = "忙碌";
	        		break;
	        	case "leave":
	        		result = "离开";
	        		break;
	        	case "corbet":
	        		result = "隐身";
	        		break;
	        	default:
	        		result = "不在线";
	        		break;
        	}
            return result;
        }
	});

});