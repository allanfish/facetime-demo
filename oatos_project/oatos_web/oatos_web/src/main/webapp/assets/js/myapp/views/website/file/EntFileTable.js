define(function (require) {

    tpl.fileTable = require('tplurl-website/file/EntFileTable.tpl');

    /**
     * 文件列表
     */
    window.EntFileTable = Backbone.View.extend({

        collectionBinder: undefined,
        _breadBinder: undefined,
        swfupload: undefined,
        _elManager: undefined,

        initialize: function () {
            this.listenTo(model.currentFolder, 'change', this.onSelectFolder);
            tplpre.fileTable = Handlebars.compile(tpl.fileTable);

            this.elManager = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
            this.collectionBinder = new Backbone.CollectionBinder(this.elManager);
            this._breadBinder = new Backbone.CollectionBinder(new Backbone.CollectionBinder.ViewManagerFactory(this.breadCreator));

            this.render();
        },

        viewCreator: function (model) {
            return new FileItemView({model: model});
        },

        breadCreator: function (model) {
            return new FileBreadItem({model: model});
        },

        render: function () {
            this.$el.append(tplpre.fileTable);
            this.collectionBinder.bind(this.collection, this.$el.find('#ent-file-list'));
            this._breadBinder.bind(collection.fileBreadList, this.$el.find('ul.breadcrumb'));
            this._initSwfupload();

            return this;
        },

        close: function () {
            this.collectionBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },

        onCreateFolder: function () {
            var currentFolderId = model.currentFolder.get("fileId");
            if (currentFolderId === 'root')
                currentFolderId = null;
            var newFolder = new EntFileDTO({
                'fileId': 'new',
                'type': 'folder',
                'name': '',
                'parentId': model.currentFolder.get("fileId"),
                'editable': true,
                'diskType': model.currentFolder.get("diskType")
            });
            this.collection.unshift(newFolder);
            log.debug('[click] on new  folder event');
        },

        onCreateFile: function () {
            log.debug('[click] on new file event');
            var newFileView = new NewFileView({model: new NewFileDTO()});
            newFileView.$el.modal({
                keyboard: true,
                backdrop: 'static'
            });
        },

        events: {
            "click .file-operation a.create-folder": 'onCreateFolder',
            "click .file-operation a.create-file": 'onCreateFile',

            "click ul.operate-btn a:contains('下载')": 'onClickDownload',
            "click ul.operate-btn a:contains('关注')": 'onClickDownload',
            "click ul.operate-btn a:contains('分享')": 'onClickShare',
            "click ul.operate-btn a:contains('收藏')": 'onClickFaviourte',
            "click ul.operate-btn a:contains('删除')": 'onClickDelete',
            "click ul.operate-btn a:contains('移动')": 'onClickMove',
            "click ul.operate-btn a:contains('复制到')": 'onClickCopyTo',
            "click ul.operate-btn a:contains('重命名')": 'onClickRename'
        },


        /**
         * 初始化Flash文件上传组件
         *
         * @private
         */
        _initSwfupload: function () {
            $("#fileupload").swfupload({
                upload_url: resturl.uploadUrl,
                use_query_string: true,
                file_post_name: "file", // 对应controller中的参数
                file_size_limit: "0",
                file_types: "*.*",
                file_types_description: "All Files",
                file_upload_limit: "0",
                flash_url: "/os/assets/js/sea/modules/swfupload/swfupload.swf",
                button_image_url: '/os/assets/js/sea/modules/swfupload/img/btn_sprit_min.gif',
                button_width: 88,
                button_height: 18,
                button_placeholder: $("#btn-upload")[0],
                button_text: '<span class="theFont">上传</span>',
                button_text_style: ".theFont { font-size: 13px;background-color: yellow;}",
                debug: false,
                custom_settings: {something: "here"}
            });

            this.swfupload = $("#fileupload").data('__swfu');
            var that = this;
            var listeners = {
                swfuploadLoaded: function (event) {
                    log.info(" swfuploadLoaded ");
                },
                fileDialogStart: function (event) {
                    router.fileRouter.navigate('disk/show/upload', true);
                },
                fileQueued: function (event, file) {
                    var uploadFile = new UploadFileDTO(file);
                    uploadFile.set("status", "wait");
                    collection.uploadFileList.add(uploadFile);
                    that.listenTo(uploadFile, 'change:status', function (model, value) {
                        switch (model.get("status")) {
                            case 'pause':
                                that.swfupload.stopUpload();
                                break;
                            case 'cancel':
                                that.swfupload.stopUpload();
                                break;
                            case 'uploading':
                                that.swfupload.startUpload(model.get('id'));
                                break;
                        }
                    })
                },
                fileQueueError: function (event, file, errorCode, message) {
                    log.error('File queue error', message);
                },
                fileDialogComplete: function (event, numFilesSelected, numFilesQueued) {
                    if (_.contains(['entRoot', 'personRoot'], model.currentFolder.get("fileId"))) {
                        $.dialog.tips("根目录下不能上传文件!");
                        return false;
                    }
                    var uploadFiles = collection.uploadFileList.where({"status": 'wait'});
                    if (uploadFiles.length > 0) {
                        uploadFiles[0].set("status", "uploading");
                    }
                },
                uploadStart: function (event, file) {
                    log.info('Upload start - ', file.name);
                    collection.uploadFileList.get(file.id).set("status", "uploading");
                    var params = {
                        'ei': cache.entId || 0,
                        'ui': cache.userId || 0,
                        'fi': (model.currentFolder && model.currentFolder.get("fileId")) || 0,
                        'gn': util.guid(),
                        'tp': model.currentFolder.isEntDisk() ? constants.fileType.shareDisk : constants.fileType.onlineDisk,
                        'UT': cache.token
                    };
                    _.each(_.keys(params), function (key) {
                        that.swfupload.addPostParam(key, params[key]);
                    });
                },
                uploadProgress: function (event, file, complete) {
                    collection.uploadFileList.get(file.id).set("uploadedSize", complete);
                },
                uploadSuccess: function (event, file, serverData) {
                    log.info('Upload success: ', serverData);

                    switch (serverData) {
                        case constants.errorType.errorSameFile:
                            $.dialog.tips('对不起! 文件夹下存在同名文件!');
                            collection.uploadFileList.get(file.id).set("status", "error");
                            return false;
                        case constants.errorType.error500:
                            $.dialog.tips('系统错误! 文件上传失败!');
                            collection.uploadFileList.get(file.id).set("status", "error");
                            return false;
                    }

                    collection.uploadFileList.get(file.id).set("status", "success");
                    var fileDTO = new EntFileDTO();
                    fileDTO.set("fileId", parseInt(serverData));
                    fileDTO.set("diskType", model.currentFolder.get("diskType"));
                    fileDTO.loadFile(function (file) {
                        collection.currentFileList.add(file);
                        if (file.isEntDisk()) {
                            collection.entFileList.add(file).trigger(events.newFile, file);
                        } else {
                            collection.personFileList.add(file).trigger(events.newFile, file);
                        }

                        if (file.isEntDisk()) {
                            resturl.sendMessage({
                                messageType: constants.messageType.FileUploadDone,
                                receiver: cache.entId,
                                sender: cache.userId,
                                messageBody: JSON.stringify({
                                    fileId: file.get("fileId"),
                                    name: file.get("name"),
                                    type: file.get("diskType"),
                                    result: constants.errorType.OK_MARK
                                })
                            }).start();
                        }
                    });
                },
                uploadComplete: function (event, file) {
                    // 上传下一个
                    var uploadFiles = collection.uploadFileList.where({"status": 'wait'});
                    if (uploadFiles.length > 0) {
                        log.debug("Now upload: ", uploadFiles[0].get("name"));
                        uploadFiles[0].set("status", "uploading");
                    }
                },
                uploadError: function (event, file, errorCode, message) {
                    log.error('Upload error - ', message);
                    if (_.indexOf(['pause', 'cancel'], collection.uploadFileList.get(file.id).get("status")) === -1) {
                        collection.uploadFileList.get(file.id).set("status", "error");
                    }
                }
            };
            $.each(listeners, function (key, value) {
                $("#fileupload").bind(key, value);
            });
        },

        onClickRename: function () {
            var entFiles = this.collection.where({checked: true});
            if (entFiles.length === 0) {
                $.dialog.alert("请选择文件或文件夹!");
                return false;
            }
            if (entFiles.length > 1) {
                $.dialog.alert("对不起, 一次只能重命名一个文件或文件夹!");
                return false;
            }
            var checkFile = entFiles[0];
            checkFile.set("editable", true);
            var view = this.collectionBinder.getManagerForModel(checkFile).getView();
            view.render();
        },


        onClickDownload: function () {
            log.debug("onClick download");
            var entFiles = this.collection.where({'checked': true});
            if (entFiles.length == 1) {
                this._downloadSingleFile(entFiles[0]);
            } else {
                this._downloadMultiFiles(entFiles);
            }
        },

        _downloadMultiFiles: function (entFiles) {
            // TODO: check permission
            var downloadDTO = {
                userId: cache.userId,
                enterpriseId: cache.entId,
                type: model.currentFolder.isEntDisk() ? constants.fileType.shareDisk : constants.fileType.onlineDisk
            }

            downloadDTO.folder = {
                folderId: model.currentFolder.get("fileId")
            };

            var files = _.filter(entFiles, function (file) {
                return !file.isFolder();
            });
            downloadDTO.fileList = _.map(files, function (file) {
                return {
                    fileId: file.get("fileId")
                }
            });

            var folders = _.where(entFiles, {type: 'folder'});

            _.map(folders, function (folder) {
                var destFolder = {
                    folderId: folder.get("fileId"),
                    name: folder.get("name"),
                    parentId: folder.get("parentId")
                    // TODO 等待yang的接口
                }
            });
        },


        _downloadSingleFile: function (file) {
            // TODO:  check permission
            var param = {
                'ei': cache.entId,
                'ui': cache.userId,
                'UT': cache.token,
                'fid': file.get("fileId"),
                'tp': file.isEntDisk() ? constants.fileType.shareDisk : constants.fileType.onlineDisk
            }

            var strParam = _.reduce(param, function (memo, value, key) {
                if (key === 'ei')
                    return memo + "?" + key + "=" + value;
                else
                    return memo + "&" + key + "=" + value;
            }, "");
            log.debug('download param: ', strParam);

            window.open("/os/file/fileDownload" + strParam, '_blank');
        },

        onClickShare: function () {
            log.debug("onClick share");
            var entFiles = this.collection.where({'checked': true});
            if (entFiles.length > 1) {
                $.dialog.alert("对不起! 一次只能分享一个文件或文件夹!");
                return;
            }

            var shareFileDTO = new ShareLinkDTO({file: entFiles[0].toJSON()});
            var fileShareView = new FileShareView({model: shareFileDTO});
            fileShareView.$el.modal({
                keyboard: true,
                backdrop: 'static'
            });
        },

        onClickFaviourte: function () {
            log.debug("onClickFaviourte");
            var json = {
                'userId': cache.userId,
                'favoriteFileIdList': []
            };
            var entFiles = this.collection.where({'checked': true});
            _.each(entFiles, function (file) {
                if (file.get("type") !== 'folder') {
                    json.favoriteFileIdList.push(file.get("fileId"));
                }
            });
            resturl.addFavoriteFile(json,function (result) {
                $.dialog.tips('OK' === result ? "添加收藏成功!" : "系统错误!");
            }).start();
        },

        onClickDelete: function () {
            log.debug("onClickDelete");
            var entFiles = this.collection.where({'checked': true});
            var that = this;
            _.each(entFiles, function (fileModel) {
                fileModel.destory(that._createDeleteCallback(fileModel));
            });
        },

        _createDeleteCallback: function (fileModel) {
            var that = this;
            if (fileModel.isEntDisk()) {
                return function (result) {
                    that.showDeleteMsg(result);
                    if ('OK' === result) {
                        that.collection.remove(fileModel);
                        collection.entFileList.trigger(events.removeFile, fileModel);
                        collection.entFileList.remove(fileModel);
                    }
                };
            } else {
                return function (result) {
                    that.showDeleteMsg(result);
                    if ('OK' === result) {
                        that.collection.remove(fileModel);
                        collection.personFileList.remove(fileModel);
                        collection.personFileList.trigger(events.removeFile, fileModel);
                    }
                };
            }
        },


        showDeleteMsg: function (errorType) {
            var msg = "未知系统错误!";
            switch (errorType) {
                case "OK":
                    msg = '删除成功!';
                    break;
                case "errorNoPermission":
                    msg = '您没有删除权限!';
                    break;
                case "errorFileLocked":
                    msg = "文件或文件夹已被锁定!";
                    break;
                case "errorVersionConflict":
                    msg = "文件版本冲突!";
                    break;
                case "error500":
                    msg = "系统错误!";
                    break;
            }
            $.dialog.tips(msg);
        },

        onClickMove: function () {
            log.debug("onClickMove");
            var that = this;
            var selectFiles = this.collection.where({checked: true});
            if (selectFiles.length === 0) {
                $.dialog.alert("请选择文件或文件夹!");
                return false;
            }
            view.entFolderSelectTree = view.entFolderSelectTree || new EntFolderSelectTree({collection: collection.entFileList});
            view.entFolderSelectTree.showDialog(function () {
                var destFolder = view.entFolderSelectTree.getSelectedFolder();
                if (selectFiles.length === 1) {
                    selectFiles[0].move(destFolder, that._moveCallback(destFolder, selectFiles));
                } else {
                    that._moveMultiFiles(destFolder, selectFiles);
                }
            });
        },

        /**
         * 移动多个文件
         *
         * @param entFiles
         * @private
         */
        _moveMultiFiles: function (destFolder, entFiles) {
            log.debug("selectFiles: ", entFiles);
            var fileList = _.map(_.filter(entFiles, function (file) {
                return !file.isFolder();
            }), function (file) {
                return _.extend({
                    entId: cache.entId,
                    userId: cache.userId,
                    fileId: file.get("fileId")
                }, file.isEntDisk() ? {version: file.get("version")} : {});
            });
            log.debug("fileList: ", fileList);
            var folderList = _.map(_.filter(entFiles, function (file) {
                return file.isFolder();
            }), function (file) {
                return _.extend({
                    entId: cache.entId,
                    userId: cache.userId,
                    folderId: file.get("fileId")
                }, file.isEntDisk() ? {version: file.get("version")} : {});
            })
            log.debug("folderList: ", folderList);

            var param = {
                entId: cache.entId,
                userId: cache.userId,
                toFolderId: destFolder.get("fileId"),
                fileList: fileList,
                folderList: folderList
            };
//            this._moveCallback(destFolder, entFiles)("OK");
            if (model.currentFolder.isEntDisk()) {
                resturl.moveShareFolderAndFile(param, this._moveCallback(destFolder, entFiles)).start();
            } else {
                resturl.movePersonalFolderAndFile(param, this._moveCallback(destFolder, entFiles)).start();
            }
        },

        _moveCallback: function (destFolder, selectFiles) {
            return function (result) {
                var msg = null;
                switch (result) {
                    case constants.errorType.OK_MARK:
                        msg = "移动文件成功!";
                        break;
                    case constants.errorType.errorNoPermission:
                        msg = "移动失败! 您没有移动权限!";
                        break;
                    case constants.errorType.errorSameFolder:
                        msg = "移动失败! 目标文件夹下有同名文件夹!";
                        break;
                    case constants.errorType.errorSameFile:
                        msg = "移动失败! 目标文件夹下有同名文件";
                        break;
                    case constants.errorType.errorFileLocked:
                        msg = "移动失败! 文件被锁定!";
                        break;
                    case constants.errorType.errorVersionConflict:
                        msg = "移动失败! 文件版本冲突!";
                        break;
                    case constants.errorType.error500:
                        msg = "系统故障!!";
                        break;
                }

                if (constants.errorType.OK_MARK) {
                    _.each(selectFiles, function (file) {
                        file.set("parentId", destFolder.get("fileId"));
                    })
                    collection.currentFileList.remove(selectFiles);
                    if (model.currentFolder.isEntDisk()) {
                        collection.entFileList.set(selectFiles, {remove: false}).trigger(events.moveFiles, destFolder, selectFiles);
                    } else {
                        collection.personFileList.set(selectFiles, {remove: false}).trigger(events.moveFiles, destFolder, selectFiles);
                    }
                    $.dialog.tips(msg);
                } else {
                    $.dialog.alert(msg);
                }
            }
        },

        onClickCopyTo: function () {
            log.debug("onClickCopyTo");
        },

        /**
         * 用户选中了某个文件夹
         */
        onSelectFolder: function () {
            log.debug('[event] [change] current ent folder to ', model.currentFolder.get('name'));
            this._changeBread();
            var fileId = model.currentFolder.get("fileId");
            var fileCollection = model.currentFolder.isEntDisk() ? collection.entFileList : collection.personFileList;
            this.collection.reset(fileCollection.where({'parentId': fileId}));
        },

        _changeBread: function () {
            var foldernav = [];
            if (model.currentFolder.isEntDisk()) {
                foldernav = collection.entFileList.getFolderNav(model.currentFolder.get("fileId"));
            } else {
                foldernav = collection.personFileList.getFolderNav(model.currentFolder.get("fileId"));
            }
            collection.fileBreadList.reset(foldernav);
        }

        /**end view*/
    });


    /**
     *  面包屑项
     */
    var FileBreadItem = Backbone.View.extend({
        tagName: 'li',
        template: '<a href="#" name="file-name"></a><span class="divider">/</span>',
        _modelBinder: undefined,
        _this: undefined,

        initialize: function () {
            this._modelBinder = new Backbone.ModelBinder();
            _this = this;
            this.render();
        },

        render: function () {
            this.$el.html(this.template);
            var bindings = {
                'name': '[name=file-name]',
                'id': {selector: '[name=file-name]', elAttribute: 'href', converter: this.openFileHrefConverter}
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

        openFileHrefConverter: function () {
            return '#' + _this.model.get("diskType") + '/open/' + _this.model.get('fileId');
        }

        /** end view*/});
})
;
