/**
 企业文件
 */
define(function (require, exports, module) {

    tpl.entFileItem = require('tplurl-website/file/FileItemView.tpl');

    window.FileItemView = Backbone.View.extend({
        tagName: 'li',
        className: 'entFileItem',
        _modelBinder: undefined,
        _this: undefined,

        initialize: function () {
            _this = this;
            tplpre.entFileItem = Handlebars.compile(tpl.entFileItem);
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
            'click .checkbox-sprite': 'multiSelectFile',
            'click ': 'toggleSelectFile',
            'click .file-name,[name=file-icon]': 'changeCurrentFile'
        },


        render: function () {
            this.$el.html(tplpre.entFileItem);

            var bindings = {
                'name': '[name=file-name]',
                'type': {selector: '[name=file-icon]', elAttribute: 'class', converter: this.typeConverter},
                'checked': {selector: '.checkbox-sprite', elAttribute: 'class', converter: this.checkConverter}
            }
            this._modelBinder.bind(this.model, this.el, bindings);

            if (this.model.get('editable')) {
                this.setEditable();
                this.$el.find('.file-name').editable('show');
            }
            return this;
        },

        setEditable: function () {
            this.$el.find('.file-name').editable({
                'onblur': 'ignore',
                toggle: 'manual',
                'url': this.saveFolder
            });
        },

        changeCurrentFile: function () {
            log.debug(" FileItemView > changeCurrentFile");
            router.fileRouter.navigate('#' + this.model.get('diskType') + '/open/' + this.model.get('fileId'), true);
        },

        saveFolder: function (params) {
            log.debug('params value:', params.value);
            var d = new $.Deferred;
            if (!params.value) {
                return d.reject('文件名不能为空!'); //returning error via deferred object
            } else {
                _this.model.set('oldName', _this.model.get("name"));
                _this.model.set('name', params.value);
                if (_this.model.get("fileId") !== 'new') {
                    _this.model.rename(this._renameFileCallback(d));
                } else {
                    _this.model.saveFolder(_this._saveFolderCallback(d));
                }
                return d.promise();
            }
        },

        _renameFileCallback: function (defered) {
            return function (result) {
                var msg = "";
                switch (result) {
                    case constants.errorType.OK_MARK:
                        msg = "重命名成功!";
                        break;
                    case constants.errorType.errorNoPermission:
                        msg = "您没有编辑权限!";
                        break;
                    case constants.errorType.errorSameFile:
                        msg = "文件夹下已存在同名的文件!";
                        break;
                    case constants.errorType.errorSameFolder:
                        msg = "文件夹下已存在同名的文件夹!";
                        break;
                    case constants.errorType.errorFileLocked:
                        msg = "对不起!文件被人他人锁定!";
                        break;
                    case constants.errorType.errorVersionConflict:
                        msg = "对不起! 文件版本冲突!";
                        break;
                    case constants.errorType.error500:
                        msg = "error500!";
                        break;
                }
                $.dialog.tips(msg);
                defered.resolve();
            }
        },

        _saveFolderCallback: function (defered) {
            var _this = this;
            if (_this.model.isEntDisk()) {
                return function (result) {
                    var savedFolder = JSON.parse(result);
                    _this.model.set('editable', false);
                    _this.model.initFromFolder(savedFolder);
                    collection.entFileList.add(_this.model);
                    collection.entFileList.trigger(events.newFile, _this.model);
                    defered.resolve();
                };
            } else {
                return function (result) {
                    var savedFolder = JSON.parse(result);
                    _this.model.set('editable', false);
                    _this.model.initFromPersonFolder(savedFolder);
                    collection.personFileList.add(_this.model);
                    collection.personFileList.trigger(events.newFile, _this.model);
                    defered.resolve();
                };
            }
        },

        multiSelectFile: function (event) {
            this.model.set('checked', !this.model.get('checked'));
            event.stopPropagation();
        },

        toggleSelectFile: function (event) {
            var nameAttr = $(event.target).attr('name');
            if (nameAttr === 'file-name' || nameAttr === 'file-icon')
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
        }

    });
    /*view end*/
});
