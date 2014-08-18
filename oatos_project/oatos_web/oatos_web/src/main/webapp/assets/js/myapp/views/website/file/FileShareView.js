/**
 * 文件设置外链分享页面
 */
define(function (require, exports, module) {

    tpl.fileShareView = require('tplurl-website/file/FileShareView.html');

    window.FileShareView = Backbone.View.extend({
        tagName: 'div',
        id: 'fileShareView',
        className: 'modal fade hide file-share-view',
        _modelBinder: undefined,
        file: undefined,
        textarea: undefined,

        initialize: function () {
            this._modelBinder = new Backbone.ModelBinder();
            this.file = this.model.get("file");
            this.render();
        },

        render: function () {
            var bindings = {
                'file.name': '[name=name]',
                'file.updateTime': {selector: '[name=updateTime]'},
                'file.size': {selector: '[name=size]', converter: this.sizeConverter},
                'file.type': {selector: '[name=file-icon]', elAttribute: 'class', converter: this.typeConverter},
                'checkExpireDate': {selector: '[name=checkExpireDate]', elAttribute: 'class', converter: this.checkConverter},
                'checkPassword': {selector: '[name=checkPassword]', elAttribute: 'class', converter: this.checkConverter},
                'checkDownloadAmt': {selector: '[name=checkDownloadAmt]', elAttribute: 'class', converter: this.checkConverter},
                'expireDate': '[name=expireDate]',
                'downloadAmt': '[name=downloadAmt]',
                'password': '[name=password]'
            }

            tplpre.fileShareView = Handlebars.compile(tpl.fileShareView);
            this.$el.html(tplpre.fileShareView);
            this._modelBinder.bind(this.model, this.el, bindings);
            this.textarea = this.$el.find("[name=sharelink-url]");
            return this;
        },

        events: {
            'click .set': 'showDetailSets',
            'click [name^=check]': 'toggleCheck',
            'click #generatebtn': 'generateShareLink'
        },

        close: function () {
            this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.$el.modal('hide');
            this.remove();
        },

        toggleCheck: function (event) {
            var name = $(event.target).attr("name");
            this.model.set(name, !this.model.get(name));
            if (this.model.get(name))
                $(event.target).parents("div.controls").find("input[name]").removeAttr("readonly");
            else
                $(event.target).parents("div.controls").find("input[name]").attr("readonly", "readonly");
        },

        showDetailSets: function (event) {
            log.debug('showDetailSets--- start!', event.target);
            var nameAttr = this.$(event.target).attr('name');
            this.$("#set" + nameAttr).toggleClass('hide');
            this.$("#unset" + nameAttr).toggleClass('hide');
        },

        checkConverter: function (direction, value) {
            return value + '';
        },

        sizeConverter: function (direction, value) {
            if (direction === 'ModelToView') {
                log.debug("sizeConverter: value: ", value);
                return util.convertSize(value);
            }
        },

        typeConverter: function (direction, value) {
            if (direction === 'ModelToView') {
                log.debug(" typeConverter: value: ", value);
                if (!value)
                    return 'file-unknow';
                var lower = value.toLocaleLowerCase();
                if ('folder' === value)
                    return 'file-folder';

                if (constants.txtType.indexOf(lower) !== -1)
                    return 'file-txt';
                if (constants.pdfType.indexOf(lower) !== -1)
                    return 'file-pdf';
                if (constants.imgType.indexOf(lower) !== -1)
                    return 'file-img';
                if (constants.mp3Type.indexOf(lower) !== -1)
                    return 'file-mp3';
                if (constants.docType.indexOf(lower) !== -1)
                    return 'file-doc';
                if (constants.excelType.indexOf(lower) !== -1)
                    return 'file-excel';
                if (constants.mp4Type.indexOf(lower) !== -1)
                    return 'file-mp4';
                if (constants.pptType.indexOf(lower) !== -1)
                    return 'file-ppt';
                if (constants.zipType.indexOf(lower) !== -1)
                    return 'file-zip';
                return 'file-unknow';
            }
        },

        generateShareLink: function () {
            var shareLinkParam = {
                entId: cache.entId,
                userId: cache.userId,
                fileId: this.file.fileId
            };
            if (this.model.get("checkExpireDate") && this.model.get("expireDate"))
                shareLinkParam.expirationTime = moment(this.model.get("expireDate"), 'YYYY-MM-DD').toDate().getTime() + "";
            if (this.model.get("checkDownloadAmt") && this.model.get("checkDownloadAmt"))
                shareLinkParam.maxDownload = parseInt(this.model.get("downloadAmt"));
            if (this.model.get("checkPassword") && this.model.get("password"))
                shareLinkParam.password = this.model.get("password");
            log.debug(" shareLink param: ", shareLinkParam);
            var that = this;
            resturl.createShareFileLink(shareLinkParam,function (result) {
                log.debug('share result:', result);
                var shareLink = JSON.parse(result);
                that.textarea.val(shareLink.linkCode)
            }).start();
        }
        /**end view*/});

});