/**
 企业文件左边导航树
 */
define(function (require, exports, module) {

    tpl.messageInfoCardView = require('tplurl-website/user/MessageInfoCardView.tpl');

    window.MessageInfoCardView = Backbone.View.extend({
        tagName: 'div',
        className: 'message-card',
        _modelBinder: undefined,
        _this:undefined,

        initialize: function () {
            tplpre.messageInfoCardView = Handlebars.compile(tpl.messageInfoCardView);
            this._modelBinder = new Backbone.ModelBinder();
            _this=this;
            this.render();
        },

        render: function () {
            this.$el.html(tplpre.messageInfoCardView);
            var bindings = {
                'avatar': {selector: '[class=avatar]', elAttribute: 'src', converter: this.convertAvatar},
                'name': {selector: '[name=user_info]', converter: this.convertUserName},
                'content': {selector: '[name=message_content]', converter: this.convertContent}
            }

            this._modelBinder.bind(this.model, this.el, bindings);

            return this;
        },
        convertAvatar: function () {
            var sender = _this.model.get('sender');
            var senderModel = collection.userList.findWhere({userId: sender});
            var avatar = senderModel.get('icon');

            if (avatar == undefined || avatar == null) {
                avatar = "/os/assets/website/img/defaultAvatar64man.png";
            } else {
                avatar = "/os/assets/website/" + avatar;
            }

            return avatar;
        },
        convertUserName: function () {
            var msgType = _this.model.get('messageType');
            var systemMsgTyhpe = constants.messageType.getSystemMsgType();
            var userName = '';

            if (_.contains(systemMsgTyhpe, msgType)) {
                userName = '系统消息';
            } else {
                userName = _this.model.get('fromUser');
            }

            return userName;
        },
        convertContent: function () {
            var msgType = _this.model.get('messageType');
            var fileMsgType = constants.messageType.getFileMsgType();
            var content = _this.model.get('messageBody');

            if (_.contains(fileMsgType, msgType)) {
                var tipsMap= constants.messageType.getMsgOptDesc();
                var tips=tipsMap[msgType]['title'];
                content = tips+content.name;
            }

            return content;
        },
        close: function () {

        }
        /*view end*/});
});
