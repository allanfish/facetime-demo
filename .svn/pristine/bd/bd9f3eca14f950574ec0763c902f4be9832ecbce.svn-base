define(function (require, exports, module) {

    tpl.recycleItemView = require('tplurl-website/file/RecycleItemView.tpl');

    window.RecycleItemView = Backbone.View.extend({
        tagName: 'li',
        className: 'entFileItem',
        id: 'recycle-item',
        _modelBinder: undefined,

        initialize: function () {
            tplpre.recycleItemView = Handlebars.compile(tpl.recycleItemView);
            this._modelBinder = new Backbone.ModelBinder();
        },

        close: function () {
            this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },

        events: {
            'click .checkbox-sprite': 'multiSelectFile',
            'click  ul.recycle-ul .btn:first': 'onRestore',
            'click  ul.recycle-ul .btn:last': 'onDelete'
        },

        render: function () {
            this.$el.html(tplpre.recycleItemView);
            //事件渲染
            this.bindHoverIntend();

            //自定义的绑定属性
            var bindings = {
                'name': '[name=file-name]',
                'type': {
                    selector: '[name=file-icon]',
                    elAttribute: 'class',
                    converter: this.typeConverter
                },
                'checked': {
                    selector: '.checkbox-sprite',
                    elAttribute: 'class',
                    converter: this.checkConverter
                }
            }
            //按自定义方式绑定，如未自定义，则按照name 属性进行渲染
            this._modelBinder.bind(this.model, this.el, bindings);
            return this;
        },


        /**
         * 点击按钮还原文件
         * @param event
         */
        onRestore: function (event) {
            var fileId = this.model.get("fileId");
            // alert("www---" + fileId );
            log.debug("onRestore: 您要还原的文件：id", fileId);
        },
        /**
         * 删除文件
         * @param event
         */
        onDelete: function (event) {
            var fileId = this.model.get("fileId");
            // alert("www---" + fileId );
            log.debug("onDelete: 您要删除的文件id ", fileId);
        },
        /**
         * 鼠标移动按钮显示，隐藏
         */
        bindHoverIntend: function () {
            var that = this;
            this.$el.find(" ul.recycle-ul ").hoverIntent(function () {
                that.$el.find(" ul.recycle-ul li:last ").addClass("show");
            }, function () {
                that.$el.find(" ul.recycle-ul li:last ").removeClass("show");
            });
        },

        typeConverter: function (direction, value) {
            if (direction === 'ModelToView') {
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

        checkConverter: function (direction, value) {
            return value + '';
        },


        multiSelectFile: function (event) {
            this.model.set('checked', !this.model.get('checked'));
            event.stopPropagation();
        }

    });
});