define(function (require, exports, module) {

    /**
     * 企业文件集合
     */
    window.EntFileListDTO = Backbone.Collection.extend({
        model: EntFileDTO,
        url: "",
        localStorage: new Backbone.LocalStorage("entFileListStorage"),

        children: function (folderId) {
            return this.where({folderId: folderId});
        },

        rootChildren: function () {
            return this.where({folderId: null});
        },

        getFolderNav: function (folderId) {
            var foldernav = [];
            if (folderId === 'entRoot') {
                foldernav.unshift(model.rootEntFolder);
            } else if (folderId === 'personRoot') {
                foldernav.unshift(model.rootPersonFolder);
            } else {
                var currentFolder = this.get(folderId);
                var isEntDisk = currentFolder.isEntDisk();
                foldernav.unshift(currentFolder);
                while (currentFolder && currentFolder.get('parentId')) {
                    var parentFolder = this.get(currentFolder.get("parentId"));
                    if (!parentFolder)
                        break;
                    foldernav.unshift(parentFolder);
                    currentFolder = parentFolder;
                }
                foldernav.unshift(isEntDisk ? model.rootEntFolder : model.rootPersonFolder);
            }
            return foldernav;
        },

        fetchFolder: function (folderId, callback) {
            var currentFolder = this.get(folderId);
            if (currentFolder && currentFolder.get("loaded"))
                return false;
            if (currentFolder.isEntDisk()) {
                this._fetchEntFolder(folderId, currentFolder, callback);
            } else {
                this._fetchPersonFolder(folderId, currentFolder, callback);
            }
        },

        /**
         * 加载个人文件
         */
        _fetchPersonFolder: function (folderId, currentFolder, callback) {
            eval(Wind.compile("async", function (_this) {
                var childFiles = $await(resturl.getPersonalFolderAndFileByFolderId({
                    userId: cache.userId,
                    folderIds: currentFolder.get('fileId') ? [ folderId ] : []
                }));

                var personFiles = _.map(childFiles['forderList'].concat(childFiles['fileList']), function (each) {
                    var personFile = each.fileId ? new EntFileDTO().initFromFile(each) : new EntFileDTO().initFromPersonFolder(each);
                    personFile.set("diskType", constants.fileType.onlineDisk);
                    return personFile;
                });
                collection.personFileList.add(personFiles).trigger(events.loadFile, personFiles);

                currentFolder.set({'loaded': true});
                log.debug("[async]-[fetchFolder] OK. ")
                callback && callback();
            }))(this).start();
        },

        /**
         * 加载企业文件
         */
        _fetchEntFolder: function (folderId, currentFolder, callback) {
            eval(Wind.compile("async", function (_this) {
                var childFiles = $await(resturl.getEntFolderAndFileByFolderId({
                    userId: cache.userId,
                    folderIds: folderId ? [ folderId ] : []
                }));

                var entFiles = _.map(childFiles['forderList'].concat(childFiles['fileList']), function (each) {
                    var entFile = each.fileId ? new EntFileDTO().initFromFile(each) : new EntFileDTO().initFromFolder(each);
                    entFile.set('diskType', constants.fileType.shareDisk);
                    return entFile;
                });
                collection.entFileList.add(entFiles).trigger(events.loadFile, entFiles);

                log.info('fetchFolder: ', currentFolder.get("name"));
                currentFolder.set({'loaded': true});
                log.debug("[async]-[fetchFolder] OK. ")
                callback && callback();
            }))(this).start();
        }

        /** end model*/ });

});
