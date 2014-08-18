define(function (require, exports, module) {

    tpl.uploadItemView = require('tplurl-website/file/UploadItemView.html');

    window.UploadItemView = Backbone.View.extend({

        tagName: 'li',
        _modelBinder: undefined,

        initialize: function () {
            this.listenTo(this.model, "change:status", this.onStatusChange);
            tplpre.uploadItemView = Handlebars.compile(tpl.uploadItemView);
            this._modelBinder = new Backbone.ModelBinder();
            this.render();
        },

        render: function () {
            this.$el.html(tplpre.uploadItemView(this.model.toJSON()));
            var bindings = {
                'name': [
                    {selector: '[name=name]'},
                    {selector: '[name=name]', elAttribute: 'title'},
                    {selector: '[name=file-icon]', elAttribute: "class", converter: this.type_converter}
                ],
                'status': {selector: '[name=status]', converter: this.status_converter},
                'uploadedSize': [
                    {selector: "[name=prograss-bar]", elAttribute: 'style', converter: this.upload_size_converter},
                    {selector: "[class~=cs-percent]", converter: this.upload_percent_converter}
                ]
            };
            this._modelBinder.bind(this.model, this.el, bindings);
            return this;
        },

        close: function () {
            this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },

        events: {
            'click .cs-upload-item .btn.btn-first': 'pauseUpload',
            'click .cs-upload-item .btn.btn-second': 'cancelUpload'
        },

        pauseUpload: function (event) {
            log.debug("click btn-first");
            event.stopPropagation();
            if (this.model.get("status") !== 'pause') {
                this.$el.find(".btn-first i").removeClass('icon-pause').addClass("icon-play").parents(".btn-first").attr("title", "继续上传");
                this.model.set("status", 'pause');
            } else {
                this.$el.find(".btn-first i").removeClass('icon-play').addClass("icon-pause").parents(".btn-second").attr("title", "暂停");
                this.model.set("status", 'uploading');
            }
        },

        cancelUpload: function (event) {
            log.debug("click btn-second");
            if (this.model.get("status") !== 'cancel') {
                this.$el.find(".btn-first").addClass("hide");
                this.$el.find(".btn-second i").removeClass("icon-remove").addClass("icon-repeat").parents(".btn-second").attr("title", "重新上传");
                this.model.set("status", 'cancel');
            } else {
                this.$el.find(".btn-first").removeClass("hide");
                this.$el.find(".btn-second i").removeClass("icon-repeat").addClass("icon-remove").parents(".btn-second").attr("title", "取消");
                this.model.set("status", 'uploading');
            }
        },

        onStatusChange: function () {
            log.debug(" file upload status change: ", this.model.get("status"));
            if (_.indexOf(['uploading', 'pause'], this.model.get("status")) > -1) {
                this.$el.find(".inline-hide").addClass("inline-show");
                this.$el.find(".hide.progress").addClass("show");
                this.$el.find("span.label-info").removeClass("inline-show").addClass("hide");
            } else {
                this.$el.find(".inline-hide").removeClass("inline-show");
                this.$el.find(".hide.progress").removeClass("show");
            }
            if (this.model.get("status") === 'cancel') {
                this.$el.find(".btn-group.inline-hide").addClass("inline-show");
            }
            if (_.indexOf(['success', 'error'], this.model.get("status")) > -1) {
                this.$el.find("span.label-info").removeClass("hide");

            }
        },

        status_converter: function (direction, value) {
            if (direction === 'ModelToView') {
                switch (value) {
                    case 'wait':
                        return '等待中';
                    case 'uploading':
                        return '上传中';
                    case 'pause':
                        return "暂停";
                    case 'cancel':
                        return "取消";
                    case 'success':
                        return '上传完成';
                    case 'error':
                        return "上传错误";
                }
            }
        },

        type_converter: function (direction, value) {
            if (direction === 'ModelToView') {
                if (!value || value.lastIndexOf(".") == -1)
                    return 'file-unknow';
                var lower = value.substr(value.lastIndexOf(".") + 1).toLocaleLowerCase();
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

        upload_size_converter: function (direction, value, attrName, model) {
            if (direction === 'ModelToView') {
                return "width:" + (Math.round(value * 100 / model.get("size")) + "%");
            }
        },

        upload_percent_converter: function (direction, value, attrName, model) {
            if (direction === 'ModelToView') {
                return (Math.round(value * 100 / model.get("size")) + "%");
            }
        }


        /** end view*/});

});