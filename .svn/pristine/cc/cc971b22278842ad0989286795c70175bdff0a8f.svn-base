define(function (require, exports, module) {

    require('./views/website/file/FileDetailView');
    require('./views/website/file/FileCollectView');
    window.SWFUpload = require('swfupload');
    require('swfupload.jquery');
    require('./views/website/file/UploadManagerView');
    require('./views/website/file/UploadItemView');
    require('./views/website/file/RecycleView');
    require('./views/website/file/EntRecycleView');
    require('./views/website/file/PersonalRecycleView');
    require('./views/website/file/RecycleItemView');
    require('./views/website/file/FileInterestView');
    require('./views/website/file/FileInterestItemView');
    require('./views/website/file/FileShareView');


    window.FileRouter = Backbone.Router
        .extend({
            routes: {
                ":diskType/open/:fileId": 'openFile',
                "file/show/:fileId": "showFileDetailView",
                'disk/folder/create': 'createDiskFolder',
                'disk/file/create': 'createDiskFile',
                'disk/file/favorite': 'showFileCollectView',
                'disk/file/favorite/search': 'showFileCollectView',
                'disk/show/upload': 'showUploadManager',
                'disk/show/recycle': 'showRecycleView',
                'disk/file/interest': 'showFileInterest',
                'disk/file/share': 'showFileShared'
            },

            before: function (route, params) {
                log.debug(' route filter before > route: ', route,
                    ', params: ', params);
                if (!route || route === 'index') {
                    return true;
                }
                if (!view.mainbox) {
                    router.approuter.navigate('index', true);
                    return false;
                }
                return true;
            },


            createDiskFolder: function () {
                view.fileTable.onCreateFolder();
            },

            createDiskFile: function () {
                view.fileTable.onCreateFile();
            },

            /**
             * 选中指定的文件或文件夹
             *
             * @param {string}
             *            fileId
             */
            openFile: function (diskType, fileId) {
                log.debug('open file: ', fileId);
                if (view.contentRight && view.contentRight.id !== view.fileRightView.id) {
                    view.contentRight.remove();
                    view.contentRight = view.fileRightView;
                    view.mainbox.$el.find("#content-right").append(view.contentRight.el);
                    view.fileTable.delegateEvents();
                }

                if ('entRoot' === fileId) {
                    model.currentFolder.set(model.rootEntFolder.toJSON());
                    return true;
                }
                if ('personRoot' == fileId) {
                    model.currentFolder.set(model.rootPersonFolder.toJSON());
                    return true;
                }
                if ('new' == fileId)
                    return;

                var fileCollection = diskType === constants.fileType.shareDisk ? collection.entFileList : collection.personFileList;
                var selectFile = fileCollection.get(fileId);
                if (selectFile.get("type") == 'folder') {
                    if (!selectFile.get("loaded")) {
                        fileCollection.fetchFolder(fileId, function () {
                            model.currentFolder.set(selectFile.toJSON());
                        });
                    } else {
                        model.currentFolder.set(selectFile.toJSON());
                    }
                }
            },

            /**
             * 显示文件详情面板
             *
             * @param {string}
             *            fileId
             */
            showFileDetailView: function (fileId) {
                if (fileId === 'new')
                    return;
                var selectFile = model.currentFolder.isEntDisk() ? collection.entFileList
                    .get(fileId) : collection.personFileList.get(fileId);
                log.debug(" showFileDetailView:  selectFile", selectFile);
                view.mainbox.showSlideRight();
                if (!view.fileDetailView) {
                    view.fileDetailView = new FileDetailView({
                        model: selectFile
                    });
                }
                if (!view.slideRight) {
                    view.slideRight = view.fileDetailView;
                    $('#slide-right').append(view.slideRight.el);
                } else {
                    if (view.slideRight.id == view.fileDetailView.id) {
                        if (view.fileDetailView.model.cid !== selectFile.cid) {
                            view.fileDetailView.model = selectFile;
                            view.fileDetailView.render();
                        }
                    } else if (view.slideRight.id !== view.fileDetailView.id) {
                        view.slideRight.remove();
                        view.slideRight = view.fileDetailView;
                        view.slideRight.delegateEvents();
                        $('#slide-right').append(view.slideRight.el);
                    }
                }
            },

            /*
             * 显示收藏夹页面；
             * */
            showFileCollectView: function () {
                if (view.contentRight) {
                    view.contentRight.remove();
                }
                if (!view.fileCollectView) {
                    collection.fileCollectList = new EntFileListDTO();
                    resturl.getFavoriteFiles(cache.userId,function (result) {
                        var favoriteFilesDTO = JSON.parse(result);
                        log.debug('getFavoriteFiles rest: ', favoriteFilesDTO);
                        _.each(favoriteFilesDTO.shareFileList, function (entFile) {
                            collection.fileCollectList.add(new EntFileDTO().initFromFile(entFile));
                        });
                    }).start();
                    view.fileCollectView = new FileCollectView({
                        collection: collection.fileCollectList
                    });
                }
                view.contentRight = view.fileCollectView;
                view.mainbox.$el.find("#content-right").append(
                    view.contentRight.el);
                view.contentRight.delegateEvents();
            },

            /***
             * 展示回收站的主框架
             */
            showRecycleView: function () {
                view.contentRight && view.contentRight.remove();
                view.recycleView = view.recycleView || new RecycleView();
                view.contentRight = view.recycleView;
                view.contentRight.delegateEvents();
                view.mainbox.$el.find("#content-right").append(
                    view.contentRight.el);
            },

            /**
             * 显示文件上传右边试图
             */
            showUploadManager: function (argument) {
                view.mainbox.showSlideRight();
                if (!view.uploadManagerView) {
                    view.uploadManagerView = new UploadManagerView({
                        collection: collection.uploadFileList
                    });
                }
                if (view.slideRight && view.slideRight.id !== view.fileDetailView.id)
                    view.slideRight.remove();
                view.slideRight = view.uploadManagerView;
                $('#slide-right').append(view.slideRight.el);
            },

            /*
             * 显示关注文件页面；
             * */
            showFileInterest: function () {
                if (view.contentRight) {
                    view.contentRight.remove();
                }
                view.fileInterestView = new FileInterestView();
                view.contentRight = view.fileInterestView;
                view.mainbox.$el.find("#content-right").append(view.contentRight.el);
                view.contentRight.delegateEvents();
            }

            /* router end */
        });
});