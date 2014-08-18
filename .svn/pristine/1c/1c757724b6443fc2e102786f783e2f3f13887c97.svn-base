/**
  * 用户聊天框VIEW
  */
define(function(require, exports, module) {

	tpl.chatWindowView = require('tplurl-website/user/ChatWindow.tpl');
	tpl.chatItemView = require('tplurl-website/user/ChatItemView.tpl');


	window.ChatWindowView = Backbone.View.extend({
		tagName: 'div', 
		className: 'chat-window',
		_modelBinder: undefined, 
		chatItemView: undefined,
		that: undefined,
		events: {
			"click .btn.send-btn": "sendMessage"
		},

		initialize: function() {
			that = this;
			this.listenTo(collection.messageList, 'add', this.onAddMessage);
			tplpre.chatWindowView = Handlebars.compile(tpl.chatWindowView);
			this._modelBinder = new Backbone.ModelBinder();
			this.render();
		},

		close: function() {
			this.collectionBinder.unbind();
			this.off();
			this.undelegateEvents();
			this.remove();
		}, 

		render: function() {
			this.$el.html(tplpre.chatWindowView);
			this._modelBinder.bind(this.model, this.el);
			this.$el.find(".chat-foot input").bind("keyup", function(event){
				if (event.keyCode == "13") {
					that.sendMessage();
				}
			});
			return this;
		},

		onAddMessage: function(message) {
			if(message.get("messageType") == "Chat" && (message.get("sender") == this.model.get("userId") || (message.get("sender") == cache.userId && message.get("receiver") == this.model.get("userId"))) ) {
				var messageDTO = new MessageDTO(message.toJSON());
				var date = new Date(parseInt(messageDTO.get("sendDate")));
				var time = moment(date).format("HH:mm:ss");
				messageDTO.set("sendDate",time);
				this.chatItemView = new ChatItemView({model: messageDTO});
				this.chatItemView.$el.find(".content").html(messageDTO.get("messageBody"));  //这一句可以删掉 暂时保留
				if (messageDTO.get("sender") == this.model.get("userId")) {
					this.chatItemView.$el.find(".message_title").addClass("receiver");
				} else {
					this.chatItemView.$el.find(".message_title").addClass("sender");
				}
				this.$el.find(".chat-body").append(this.chatItemView.el);
			}
		},

		sendMessage: function() {
			var content = this.$el.find(".chat-foot input");
			if(content.val() != "" && content.val() != null) {
				var messageDTO = new MessageDTO({
					chatId: 0,
					confirmed: false,
					displaying: false,
					fromUser: cache.username,
					messageBody: content.val(),
					messageType: 'Chat',
					offlineMessage: false,
					receiver: this.model.get("userId"),
					receiverName: this.model.get("userName"),
					sendDate: Date.parse(new Date()),
					sender: cache.userId,
					showed: false,
					status: 'New'
				});
				resturl.sendMessage(messageDTO,function(){
					collection.messageList.add(messageDTO);
					content.val("");
				}).start();
			}
		}
	});

	window.ChatItemView = Backbone.View.extend({
		tagName: 'div',
		className: 'chat-item',
		initialize: function() {
			tplpre.chatItemView = Handlebars.compile(tpl.chatItemView);
			this.render();
		},
		render: function() {
			this.$el.html(tplpre.chatItemView(this.model.toJSON()));
			return this;
		}
	});

});