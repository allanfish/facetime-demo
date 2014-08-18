/**
 * 回收站文件集合
 */
define(function (require, exports, module) {

    window.RecycleFileListDTO = Backbone.Collection.extend({
        model: EntFileDTO,
        url: "",

        /**
         * 加载企业网盘回收站
         * @param callback
         */
        fetchEntRecycleFiles: function (callback) {
            var that = this;
            resturl.getEntFolderAndFileInRecycle(cache.entId,function (result) {
                log.debug("getEntFolderAndFileInRecycle result:", result);
                var entFilesJson = JSON.parse(result);

                var recycleFolders = entFilesJson['forderList'];
                var targetFolders = _.filter(recycleFolders, function (folder) {
                    return _.where(recycleFolders, {
                        'folderId': folder.parentId
                    }).length === 0;
                });

                log.debug("targetFolders - ----", targetFolders);

                var recycleFiles = entFilesJson['fileList'];
                var targetFiles = _.filter(recycleFiles, function (file) {
                    return _.where(recycleFolders, {
                        'folderId': file.folderId
                    }).length === 0;
                });

                log.debug("recycleFiles-----", recycleFiles);

                var entFiles = _.map(targetFolders.concat(targetFiles), function (each) {
                    var entFileDTO = each.fileId ? new EntFileDTO().initFromFile(each) : new EntFileDTO().initFromFolder(each);
                    entFileDTO.set("parentId", 'entRoot');
                    entFileDTO.set("diskType", constants.fileType.shareDisk);
                    return entFileDTO;
                });
                that.add(entFiles).trigger(events.loadRecycleFiles, entFiles);
            }).start();
        },

        /**
         *加载个人网盘回收站数据
         * @param callback
         */
        fetchPersonalRecycleFiles: function (callback) {

            //  collection.personalRecycleFiles = new EntFileListDTO();
            var that = this;

            resturl.getPersonalFolderAndFileInRecycle(cache.userId,function (result) {
                log.debug("getPersonalFolderAndFileInRecycle result:", result);

                var personalFilesJson = JSON.parse(result);

                var recycleFolders = personalFilesJson['forderList'];
                var targetFolders = _.filter(recycleFolders, function (folder) {
                    return _.where(recycleFolders, {
                        'folderId': folder.parentId
                    }).length === 0;
                });

                log.debug("targetFolders - ----", targetFolders);

                var recycleFiles = personalFilesJson['fileList'];
                var targetFiles = _.filter(recycleFiles, function (file) {
                    return _.where(recycleFolders, {
                        'folderId': file.folderId
                    }).length === 0;
                });

                log.debug("recycleFiles-----", recycleFiles);

                var personalFiles = _.map(targetFolders.concat(targetFiles), function (each) {
                    var entFileDTO = each.fileId ? new EntFileDTO().initFromFile(each) : new EntFileDTO().initFromFolder(each);
                    entFileDTO.set("parentId", 'entRoot');
                    entFileDTO.set("diskType", constants.fileType.shareDisk);
                    return entFileDTO;
                });
                that.add(personalFiles).trigger(events.loadPersonalRecycleFiles, personalFiles);
            }).start();

        }

        /** end collection*/ });

});
