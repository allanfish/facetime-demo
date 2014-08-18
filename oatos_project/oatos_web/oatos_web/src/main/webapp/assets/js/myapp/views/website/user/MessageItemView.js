/**
 企业文件左边导航树
 */
define(function (require, exports, module) {
    require('./MessageInfoCardView');
    tpl.messageItem = require('tplurl-website/user/MessageItemView.tpl');

    window.MessageItemView = Backbone.View.extend({
        tagName: 'li',
        className: 'messageItem',
        _modelBinder: undefined,
        _this: undefined,
        messageInfoCardView: undefined,

        initialize: function () {
            _this = this;
            tplpre.messageItem = Handlebars.compile(tpl.messageItem);
            _.bindAll(this);
            this._modelBinder = new Backbone.ModelBinder();
        },

        close: function () {
            this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },

        events: {
            //'click .checkbox-sprite': 'multiSelectFile',
            //'click ': 'toggleSelectFile',
            //'click [name=file-name],[name=file-icon]': 'changeCurrentFile'
        },


        render: function () {
            this.$el.html(tplpre.messageItem);

            var bindings = {
                'avatar': {selector:'[class=avatar]',elAttribute: 'src',converter:this.convertAvatar},
                'name': {selector:'[name=user_info]',converter:this.convertUserName},
                'msgopt': {selector:'[name=msg_opt]',converter:this.getOptName},
                'content': [{selector: '[name=message_content]', converter: this.convertContent},{selector: '[name=message_content]',elAttribute: 'val', converter: this.getUserId},{selector: '[name=message_content]',elAttribute: 'data-type', converter: this.getMsgType}],
                'fclass': {selector: '[name=message_content]',elAttribute: 'class', converter: this.getOptClass}

            }

            this._modelBinder.bind(this.model, this.el, bindings);
            this.bindInfoCard();


            return this;
        },
        convertAvatar:function(){
            log.debug('xxx,');
            log.debug(this);
            var sender=this.model.get('sender');
            var senderModel=collection.userList.findWhere({userId:sender});
            var avatar=senderModel.get('icon');

            if(avatar == undefined || avatar== null) {
                avatar = "/os/assets/website/img/defaultAvatar64man.png";
            } else {
                avatar = "/os/assets/website/" + avatar;
            }

            return avatar;
       },
        convertUserName:function(){
            var msgType=this.model.get('messageType');
            var systemMsgTyhpe = constants.messageType.getSystemMsgType();
            var userName='';

            if(_.contains(systemMsgTyhpe,msgType)){
                userName='系统消息';
            }else{
                userName=this.model.get('fromUser');
            }

            return userName;
        },
        convertContent:function(){
            var msgType=this.model.get('messageType');
            var fileMsgType = constants.messageType.getFileMsgType();
            var content=this.model.get('messageBody');

            if(_.contains(fileMsgType,msgType)){
                content=content.name;
            }

            return content;
        },
        getOptName:function(){
            var msgType=this.model.get('messageType');
            var fileMsgType = constants.messageType.getFileMsgType();
            var tipsMap= constants.messageType.getMsgOptDesc();
            var tips=':';

            if(_.contains(fileMsgType,msgType)){
                tips=tipsMap[msgType]['title'];
            }

            return tips;
        },
        getOptClass:function(){
            var msgType=this.model.get('messageType');
            var fileMsgType = constants.messageType.getFileMsgType();
            var tipsMap= constants.messageType.getMsgOptDesc();
            var fclass='';

            if(_.contains(fileMsgType,msgType)){
                fclass=tipsMap[msgType]['fClass'];
            }

            return fclass;
        },
        getUserId:function(){
            return this.model.get('sender');
        },
        getMsgType:function(){
            return this.model.get('messageType');
        },
        bindInfoCard:function(){
            this.messageInfoCardView = new MessageInfoCardView({model: this.model});
            this.$el.popover({
                'trigger': 'hover',
                'placement': 'left',
                'html': true,
                'content': this.messageInfoCardView.el
            });
        }


        /*view end*/});
});
