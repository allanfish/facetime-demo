define(function (require, exports, module) {

    tpl.fileInterestItem = require('tplurl-website/file/FileInterestItemView.tpl');

    window.FileInterestItemView = Backbone.View.extend({
        tagName: 'li',
        className: 'entFileItem',
        _modelBinder: undefined,
        _this: undefined,

        initialize: function () {
            _this = this;
            tplpre.fileInterestItem = Handlebars.compile(tpl.fileInterestItem);
            log.debug('FileInterestItemView initialize',tplpre.fileInterestItem);
            _.bindAll(this);
            this._modelBinder = new Backbone.ModelBinder();
            this.render();
        },

        close: function () {
            this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },

        events: {
            'click .checkbox-sprite': 'multiSelectFile',
            'click ': 'toggleSelectFile',
            'click [name=icon-uncollect]': 'fileUncollect'
        },


        render: function () {
            this.$el.html(tplpre.fileInterestItem);
           log.debug('FileInterestItemView render',this.$el.html());
            /*var bindings = {
                'name': '[name=file-name]',
                'updateTime': {selector: '[name=update]', converter: this.timeConverter},
                'size': {selector: '[name=size]', converter: this.sizeConverter},
                'type': {selector: '[name=file-icon]', elAttribute: 'class', converter: this.typeConverter},
                'checked': {selector: '.checkbox-sprite', elAttribute: 'class', converter: this.checkConverter}
            }*/
            //this._modelBinder.bind(this.model, this.el, bindings);
           /* if (this.model.get('editable')) {
                this.setEditable();
                this.$el.find('.file-name').click();
            } */
            this.delegateEvents();
            return this;
        },


        multiSelectFile: function (event) {
            this.model.set('checked', !this.model.get('checked'));
            event.stopPropagation();
        },

        toggleSelectFile: function (event) {
            var nameAttr = $(event.target).attr('name');
            if (nameAttr === 'file-name' || nameAttr === 'file-icon' || nameAttr === 'icon-uncollect')
                return true;

            collection.currentFileList.each(function (fileModel) {
                if (fileModel.id !== this.model.id && fileModel.get('checked')) {
                    fileModel.set('checked', false);
                }
            }, this);
            this.model.set('checked', !this.model.get('checked'));
            if (this.model.get('checked')) {
                router.fileRouter.navigate('file/show/' + this.model.get('fileId'), true);
            }
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

        timeConverter: function () {
            return moment(new Date(parseInt(this.model.get("updateTime")))).format("YYYY-MM-DD HH:mm:ss");
        },

        sizeConverter: function () {
            return util.convertSize(this.model.get("size"));
        },

        fileUncollect: function () {
            var that = this;
            var favoriteFilesDTO = {
                'userId': cache.userId,
                'favoriteFileIdList': [this.model.get('fileId')]
            };
            resturl.delFavoriteFile(favoriteFilesDTO,function () {
                that.$("[name=icon-uncollect]").attr('class', 'icon-star-empty');
            }).start();
        }



    });
});
