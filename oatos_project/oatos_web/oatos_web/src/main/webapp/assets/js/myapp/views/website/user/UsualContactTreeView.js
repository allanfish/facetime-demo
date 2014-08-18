define(function (require, exports, module) {

    require("jqueryztree");
    require("wind");
    require('./UserInfoCardView');
    require('./ChatWindowView');
    require('./ChatWindowsView');

    tpl.usualContactItemView = require('tplurl-website/user/UsualContactItemView.tpl')
    tpl.usualContactTreeView = require('tplurl-website/user/UsualContactTreeView.tpl')

    var UsualContactItemView = Backbone.View.extend({
        tagName: 'li',
        _modelBinder: undefined,
        userInfoCardView: undefined,
        chatWindowView: undefined,
        chatWindowsView: undefined,

        events: {
            "mouseenter div.row-fluid": "showDel",
            "mouseleave div.row-fluid": "removeDel"
        },

        initialize: function () {
            tplpre.usualContactItemView = Handlebars.compile(tpl.usualContactItemView);
            this._modelBinder = new Backbone.ModelBinder();

        },

        render: function () {
            this.$el.html(tplpre.usualContactItemView);
            var bindings = {
                'userName': '[class=userName]',
                'userId': {selector: '[name=usual_contact_id]', elAttribute: 'value', converter: this.getUserId},
                'icon': {selector: '[class=avatar]', elAttribute: 'src', converter: this.getAvatar},
                'jobTitle': '[class=jobTitle]',
                'departmentName': '[class=departmentName]',
                'onlineStatus': {selector: '[name=onlineStatus]', elAttribute: 'class', converter: this.getOnline},
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

        getUserId: function (direction, value) {
            return value + "";
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

        showDel: function() {
            if(this.$el.find(".contact-del").hasClass("hide")) {
                this.$el.find(".span1").removeClass("hide");
                this.$el.find(".row-fluid").addClass("back-color");
            }
        },

        removeDel: function() {
            if(this.$el.find(".contact-del").hasClass("hide")) {
                this.$el.find(".span1").addClass("hide");
                this.$el.find(".row-fluid").removeClass("back-color");
            }
        },

        createCharObj: function (value) {
            return "#chatuser" + value;
        },

        close: function () {
            this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        }
    });

    window.UsualContactTreeView = Backbone.View.extend({
        tagName: 'ul',
        id: 'usualContactTree',
        collectionBinder: undefined,
        singleDialog: undefined,
        multipleDialog: undefined,
        events: {
            "click ul.userList li div .chat-span": "showChat"
        },

        initialize: function () {
            tplpre.usualContactTreeView = Handlebars.compile(tpl.usualContactTreeView);
            var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
            collectionBinder = new Backbone.CollectionBinder(elManagerFactory);
            this.render();
        },

        render: function () {
            this.$el.html(tplpre.usualContactTreeView);
            collectionBinder.bind(this.collection, this.$el.find('.userList'));
            this.delegateEvents();
            return this;
        },

        viewCreator: function (model) {
            return new UsualContactItemView({model: model});
        },

        close: function () {
            this.collectionBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },
        showChat: function (e) {
            var userId = parseInt($(e.currentTarget.parentNode).find("input[name='usual_contact_id']").attr('value'));
            var userModel = this.collection.findWhere({userId: userId});
            collection.chatUserList.unshift(userModel);
            $this=this;
            //log.debug("collection.chatUserList.size():", collection.chatUserList.size());
            var avatar="/os/assets/website/img/defaultAvatar64man.png";
            var userName=userModel.get('userName');
            //var titleContent="<div class='diyTitle'><img style='width:20px;height:20px' src='"+avatar+"'/><span style='margin-left: 5px;' class='userName'>"+userName+"</span><span style='margin-left: 20px;'>视频</span></div>";
            var titleContent = '<div class="row-fluid show-grid dialog-head"><div class="span2"><div class="dialog-avatar"><div class="Status"></div><div class="img-avatar"><img src="'+avatar+'"/></div></div></div><div class="span2">'+userName+'</div><div class="span2"><i class="icon-eye-open" title="视频聊天"></i></div><div class="span2"><i class="icon-remove" title="删除常用联系人"></i></div></div>';
            if (collection.chatUserList.size() < 2) {
                $this.chatWindowView = new ChatWindowView({model: userModel});
                $this.singleDialog = $.dialog({
                    id: "testID1",
                    title: titleContent,
                    content: $this.chatWindowView.el,
                    max:false,
                    close: function () {
                        $this.singleDialog = undefined;
                        if(collection.chatUserList.size()<2){
                            collection.chatUserList=new UserListDTO();
                        }
                    }
                });
                return false;
            } else {

                if ($this.singleDialog != undefined){
                    $this.singleDialog.close();
                }
                chatWindowsView = new ChatWindowsView({collection: collection.chatUserList});

                if ($this.multipleDialog == undefined) {

                    $this.multipleDialog = $.dialog({
                        id: "testID2",
                        title: titleContent,
                        content: chatWindowsView.el,
                        max:false,
                        close: function () {
                            $this.multipleDialog = undefined;

                            collection.chatUserList=new UserListDTO();

                        }
                    });
                    var  oldSenderMessageList=new MessageListDTO();
                    var oldRevMessageList=new MessageListDTO();
                    var reUserId=collection.chatUserList.at(1).get('userId');
                    oldSenderMessageList=collection.messageList.where({sender:cache.userId,receiver:reUserId,messageType:'Chat'});
                    oldRevMessageList=collection.messageList.where({sender:reUserId,receiver:cache.userId,messageType:'Chat'});

                    if(oldSenderMessageList.length>0){
                        this. renderChatItem(oldSenderMessageList,$("#chatuser"+reUserId));
                    }
                    if(oldRevMessageList.length>0){
                        this. renderChatItem(oldRevMessageList,$("#chatuser"+reUserId));
                    }

                } else {
                    $this.multipleDialog.content(chatWindowsView.el).title(titleContent);
                    if($('td.ui_main').size()>0 && $('td.ui_main').css('display')=='none'){
                        $('a.ui_min').trigger('click');
                    }
                    var liLen= $('.chat-windows ul.nav li').first().height();
                    var liTotalNum=$('.chat-windows ul.nav li').size();
                    var liTotalLen=liLen*liTotalNum;
                    var parentHeight=$('.chat-windows div.tabbable').height();
                    $('.chat-windows ul.nav').height(liTotalLen);

                    if(liTotalLen>parentHeight){
                        $('.chat-windows div.tabbable').find('a.scrollTop').show();
                        $('.chat-windows div.tabbable').find('a.scrollBottom').show();
                    }

                }

                $('.chat-windows ul.nav li').first().find('a').trigger('click');
            }
            log.debug(collection.messageList);
        },
        renderChatItem:function(messageCol,obj){
            _.each(messageCol,function (message) {
                var messageDTO = new MessageDTO(message.toJSON());
                var date = new Date(parseInt(messageDTO.get("sendDate")));
                var time = moment(date).format("HH:mm:ss");
                messageDTO.set("sendDate", time);
                var chatItemView = new ChatItemView({model: messageDTO});

                if (messageDTO.get("sender") == cache.userId) {
                    chatItemView.$el.find(".message_title").addClass("receiver");
                } else {
                    chatItemView.$el.find(".message_title").addClass("sender");
                }

               obj.find(".chat-body").append(chatItemView.el);

            });
        }
    });
});
