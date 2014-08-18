/**
 * 多用户聊天框VIEW
 */
define(function (require, exports, module) {

    tpl.chatWindowsView = require('tplurl-website/user/ChatWindows.tpl');
    tpl.chatUserView = require('tplurl-website/user/ChatUserView.tpl');
    tpl.chatPanelView = require('tplurl-website/user/ChatPanelView.tpl');


    window.ChatWindowsView = Backbone.View.extend({
        tagName: 'div',
        className: 'chat-windows',
        collectionBinder: undefined,
        collectionBinderPanel: undefined,
        events: {
            "click ul.nav li a.userInfo": "switchChatUser",
            "click ul.nav li b": "removeChatUser",
            "click a.scrollTop": "moveUpChatUser",
            "click a.scrollBottom": "moveDownChatUser"
        },

        initialize: function () {
            tplpre.chatWindowsView = Handlebars.compile(tpl.chatWindowsView);
            var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
            collectionBinder = new Backbone.CollectionBinder(elManagerFactory);
            var elManagerFactoryPanel = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreatorPanel);
            collectionBinderPanel = new Backbone.CollectionBinder(elManagerFactoryPanel);
            this.render();
        },

        viewCreator: function (model) {
            return new ChatUserView({model: model});
        },

        viewCreatorPanel: function (model) {
            return new ChatPanelView({model: model});
        },

        render: function () {
            this.$el.html(tplpre.chatWindowsView);
            collectionBinder.bind(this.collection, this.$el.find('.nav-tabs'));
            collectionBinderPanel.bind(this.collection, this.$el.find('.tab-content'));
            return this;
        },

        close: function () {
            this.collectionBinder.unbind();
            this.collectionBinderPanel.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },
        switchChatUser:function(e){
            var userId = parseInt($(e.currentTarget).parent().find('b.removeUser').attr('val'));
            var userModel = collection.chatUserList.findWhere({userId: userId});
            var avatar="/os/assets/website/img/defaultAvatar64man.png";
            var userName=userModel.get('userName');
            var titleContent="<div class='diyTitle'><img style='width:20px;height:20px' src='"+avatar+"'/><span style='margin-left: 5px;' class='userName'>"+userName+"</span><span style='margin-left: 20px;'>视频</span></div>";
            view.usualContactTreeView.multipleDialog.title(titleContent);
        },
        removeChatUser: function (e) {
            var userId = parseInt($(e.currentTarget).attr('val'));
            var rmUserModel = collection.chatUserList.findWhere({userId: userId});
            collection.chatUserList.remove(rmUserModel);
            if (collection.chatUserList.length < 2) {
                var userModel = collection.chatUserList.pop();
                log.debug(userModel);
                userId = userModel.get('userId');
                $('#usualContactTree ul.userList li').each(function (n) {
                    if ($(this).find("input[name='usual_contact_id']").attr('value') == userId) {
                        view.usualContactTreeView.multipleDialog.close();
                        $(this).trigger('click');
                        var oldSenderMessageList=new MessageListDTO();
                        var oldRevMessageList=new MessageListDTO();
                        var reUserId=collection.chatUserList.at(0).get('userId');
                        oldSenderMessageList=collection.messageList.where({sender:cache.userId,receiver:reUserId,messageType:'Chat'});
                        oldRevMessageList=collection.messageList.where({sender:reUserId,receiver:cache.userId,messageType:'Chat'});

                        if(oldSenderMessageList.length>0){
                            view.usualContactTreeView. renderChatItem(oldSenderMessageList, $(".chat-window"));
                        }
                        if(oldRevMessageList.length>0){
                            view.usualContactTreeView. renderChatItem(oldRevMessageList, $(".chat-window"));
                        }
                        return false;
                    }
                });

                /* collection.newChatUserList=new UserListDTO();
                 collection.newChatUserList=collection.chatUserList;
                 view.usualContactTreeView.multipleDialog.close();
                 $this.chatWindowView = new ChatWindowView({model: userModel});
                 view.usualContactTreeView.singleDialog = $.dialog({
                 id: "testID1",
                 title: "聊天1",
                 content:  view.usualContactTreeView.chatWindowView.el,
                 close: function () {
                 $this.singleDialog = undefined;
                 if(collection.chatUserList.size()<2){
                 collection.chatUserList=new UserListDTO();
                 //$this.multipleDialog = undefined;
                 }


                 }
                 });*/
            } else {
                if ($('.ui_title .diyTitle .userName').text() == rmUserModel.get('userName')) {
                    userId = parseInt(this.$el.find('ul.nav li').first().find('b.removeUser').attr('val'));
                    var newUserModel = collection.chatUserList.findWhere({userId: userId});
                    var avatar = "/os/assets/website/img/defaultAvatar64man.png";
                    var userName = newUserModel.get('userName');
                    var titleContent = "<div class='diyTitle'><img style='width:20px;height:20px' src='" + avatar + "'/><span style='margin-left: 5px;' class='userName'>" + userName + "</span><span style='margin-left: 20px;'>视频</span></div>";
                    view.usualContactTreeView.multipleDialog.title(titleContent);
                }

                var liLen= this.$el.find('ul.nav li').first().height();
                var liTotalNum=this.$el.find('ul.nav li').size();
                var liTotalLen=liLen*liTotalNum;
                var parentHeight=this.$el.find('div.tabbable').height();
                this.$el.find('ul.nav').height(liTotalLen);

                if(liTotalLen<=parentHeight){
                    this.$el.find('div.tabbable').find('a.scrollTop').hide();
                    this.$el.find('div.tabbable').find('a.scrollBottom').hide();
                }
            }
        },
        moveUpChatUser: function (e) {
            var liLen = this.$el.find('ul.nav li').first().height();
            // var liTotalNum=this.$el.find('ul.nav li').size();
            var liTotalLen = this.$el.find('ul.nav').height();//liLen*liTotalNum;
            var marginTop = Math.abs(parseInt(this.$el.find('ul.nav').css('margin-top')));
            var newMarginTop = liLen + marginTop;
            var parentHeight = this.$el.find('div.tabbable').height();

            if ((newMarginTop + parentHeight - 16) <= liTotalLen) {
                this.$el.find('ul.nav').css('margin-top', '-' + newMarginTop + 'px');
            }
        },
        moveDownChatUser: function (e) {
            var liLen = this.$el.find('ul.nav li').first().height();
            var marginTop = parseInt(this.$el.find('ul.nav').css('margin-top'));
            var newMarginTop = marginTop + liLen;

            if (newMarginTop <= 0) {
                this.$el.find('ul.nav').css('margin-top', newMarginTop + 'px');
            }

        }

    });

    window.ChatUserView = Backbone.View.extend({
        tagName: 'li',
        _modelBinder: undefined,

        initialize: function () {
            tplpre.chatUserView = Handlebars.compile(tpl.chatUserView);
            this._modelBinder = new Backbone.ModelBinder();
        },
        render: function () {
            this.$el.html(tplpre.chatUserView);
            var bindings = {
                'userName': '[class=userName]',
                'userId': [
                    {selector: '[data-toggle=tab]', elAttribute: 'href', converter: this.getUserId},
                    {selector: '[class=removeUser]', elAttribute: 'val', converter: this.getUserVal}
                ]

            };
            this._modelBinder.bind(this.model, this.el, bindings);
            return this;
        },

        getUserId: function (direction, value) {
            return "#chatuser" + value;
        },
        getUerVal: function (vallue) {
            return value;
        }
    });

    window.ChatPanelView = Backbone.View.extend({
        tagName: 'div',
        className: 'tab-pane',
        _modelBinder: undefined,
        that: undefined,
        events: {
            "click .btn.send-btn": "sendMessage"
        },

        initialize: function () {
            that = this;
            this.listenTo(collection.messageList, 'add', this.onAddMessage);
            tplpre.chatPanelView = Handlebars.compile(tpl.chatPanelView);
            this._modelBinder = new Backbone.ModelBinder();
        },
        render: function () {
            this.$el.html(tplpre.chatPanelView);
            this._modelBinder.bind(this.model, this.el);
            this.$el.attr("id", this.getUserId(this.model.get("userId")));
            this.$el.find(".chat-foot input").bind("keyup", function (event) {
                if (event.keyCode == "13") {
                    that.sendMessage();
                }
            });
            return this;
        },

        getUserId: function (value) {
            return "chatuser" + value;
        },

        onAddMessage: function (message) {
            if (message.get("messageType") == "Chat" && (message.get("sender") == this.model.get("userId") || (message.get("sender") == cache.userId && message.get("receiver") == this.model.get("userId")))) {
                var messageDTO = new MessageDTO(message.toJSON());
                var date = new Date(parseInt(messageDTO.get("sendDate")));
                var time = moment(date).format("HH:mm:ss");
                messageDTO.set("sendDate", time);
                this.chatItemView = new ChatItemView({model: messageDTO});
                //this.chatItemView.$el.find(".content").html(messageDTO.get("messageBody"));  //这一句可以删掉 暂时保留
                if (messageDTO.get("sender") == this.model.get("userId")) {
                    this.chatItemView.$el.find(".message_title").addClass("receiver");
                } else {
                    this.chatItemView.$el.find(".message_title").addClass("sender");
                }
                this.$el.find(".chat-body").append(this.chatItemView.el);
            }
        },

        sendMessage: function () {
            var content = this.$el.find(".chat-foot input");
            if (content.val() != "" && content.val() != null) {
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
                resturl.sendMessage(messageDTO,function () {
                    collection.messageList.add(messageDTO);
                    content.val("");
                }).start();
            }
        }
    });

});