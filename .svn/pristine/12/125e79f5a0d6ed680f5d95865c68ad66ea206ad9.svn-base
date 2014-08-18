/**
 * 企业文件和文件夹DTO
 * 通过type来区分. type=folder代表文件夹
 */
define(function (require, exports, module) {

    window.EntFileDTO = Backbone.Model.extend({
        url: '/entfile',
        defaults: {
            'checked': false,
            'loaded': false,
            'diskType': undefined
        },
        idAttribute: 'fileId',

        isEntDisk: function () {
            return this.get('diskType') === constants.fileType.shareDisk;
        },
        isFolder: function () {
            return this.get("type") === 'folder';
        },


        loadFile: function (callback) {
            var that = this;
            if (this.isEntDisk()) {
                resturl.getShareFileById(this.get("fileId"),function (result) {
                    var shareFileJson = JSON.parse(result);
                    that.initFromFile(shareFileJson);
                    callback(that);
                }).start();
            } else {
                resturl.getPersonFileById(this.get("fileId"),function (result) {
                    var fileJson = JSON.parse(result);
                    that.initFromFile(fileJson);
                    callback(that);
                }).start();
            }
        },


        /**
         * 通过企业文件夹来初始化
         * @param  {ShareFolderDTO} folder 企业文件夹
         * @return {object}
         */
        initFromFolder: function (folder) {
            return this.set({
                'fileId': folder.folderId,
                'name': folder.name,
                'parentId': function () {
                    return folder.parentId ? folder.parentId : 'entRoot';
                }(),
                'thumb': folder.thumb,
                'remark': folder.remark,
                'version': folder.version,
                'type': 'folder',
                'shareLinkId': folder.shareLinkId
            });
        },

        /**
         * 通过个人文件夹来初始化
         */
        initFromPersonFolder: function (folder) {
            return this.set({
                'fileId': folder.folderId,
                'name': folder.folderName,
                'parentId': function () {
                    return folder.parentFolderId ? folder.parentFolderId : 'personRoot';
                }(),
                'thumb': folder.thumb,
                'remark': folder.remark,
                'version': folder.version,
                'type': 'folder',
                'shareLinkId': folder.shareLinkId
            })
        },


        /**
         * 通过文件来初始化
         * @param  {ShareLinkDTO} file 企业文件
         * @return {object}
         */
        initFromFile: function (file) {
            return this.set({
                'fileId': file.fileId,
                'name': file.name,
                'parentId': file.folderId,
                'guid': file.guid,
                'type': function () {
                    if (file.guid.lastIndexOf('.') == -1)
                        return "";
                    return file.guid.substr(file.guid.lastIndexOf('.') + 1);
                }(),
                'createTime': file.createTime,
                'updateTime': file.updateTime,
                'thumb': file.thumb,
                'version': file.version,
                'size': file.size,
                'shareLinkId': file.shareLinkId,
                // 锁定文件的用户ID
                'lockByUserId': file.lockByUserId,
                'remark': file.remark
            });
        },

        /**
         * 移动操作
         *
         * @param destFolder
         * @param callback
         */
        move: function (destFolder, callback) {
            var param = null;
            if (this.isFolder()) {
                param = _.extend({
                    userId: cache.userId,
                    entId: cache.entId,
                    folderId: this.get("fileId"),
                    toFolderId: destFolder.get("fileId")
                }, this.isEntDisk() ? {version: this.get("version")} : {});
            } else {
                param = _.extend({
                    userId: cache.userId,
                    entId: cache.entId,
                    fileId: this.get("fileId"),
                    toFolderId: destFolder.get("fileId")
                }, this.isEntDisk() ? {version: this.get("version")} : {});
            }

            var func = null;
            if (this.isEntDisk()) {
                func = this.isFolder() ? 'moveShareFolder' : 'moveShareFile';
            } else {
                func = this.isFolder() ? 'movePersonalFolder' : 'movePersonalFile';
            }

//            callback('OK');
            resturl[func](param, callback).start();
        },

        /**
         * 文件或文件夹重命名
         */
        rename: function (callback) {
            if (this.isEntDisk()) {
                if (this.isFolder()) {
                    resturl.renameShareFolder({
                        userId: cache.userId,
                        entId: cache.entId,
                        folderId: this.get("fileId"),
                        version: this.get("version"),
                        name: this.get("name")
                    }, callback).start();
                } else {
                    resturl.renameShareFile({
                        userId: cache.userId,
                        entId: cache.entId,
                        fileId: this.get("fileId"),
                        version: this.get("verson"),
                        name: this.get("name")
                    }, callback).start();
                }
            } else {
                if (this.isFolder()) {
                    resturl.renamePersonalFolder({
                        userId: cache.userId,
                        entId: cache.entId,
                        folderId: this.get("fileId"),
                        name: this.get("name")
                    }, callback).start();
                } else {
                    resturl.renamePersonalFile({
                        userId: cache.userId,
                        entId: cache.entId,
                        fileId: this.get("fileId"),
                        name: this.get("name")
                    }, callback);
                }
            }
        },

        /**
         * 保存文件夹, 包括企业文件和个人文件
         * @param callback
         */
        saveFolder: function (callback) {
            if (this.isEntDisk()) {
                var entFile = _.pick(_this.model.toJSON(), 'name', 'parentId');
                if (entFile.parentId === 'entRoot')
                    entFile.parentId = null;
                entFile.enterpriseId = cache.entId;
                entFile.userId = cache.userId;
                entFile.userName = cache.username;
                entFile.operation = constants.operation.NewFolder;

                resturl.newShareFolder(entFile, callback).start();
            } else {
                var personFolder = {
                    'userId': cache.userId,
                    'folderName': this.get("name"),
                    'parentFolderId': this.get('parentId') === 'personRoot' ? null : this.get("parentId")
                };
                resturl.addDiskFolder(personFolder, callback).start();
            }
        },


        /**
         * 执行删除文件到回收站中的操作
         *
         * @param callback
         */
        destory: function (callback) {
            this.get("type") == 'folder' ? this._destoryFolder(callback) : this._destoryFile(callback);
        },

        _destoryFolder: function (callback) {
            if (this.isEntDisk()) {
                var fileparam = {
                    'folderId': this.get("fileId"),
                    'version': this.get("version"),
                    'name': this.get("name"),
                    'userId': cache.userId,
                    'userName': cache.username,
                    'enterpriseId': cache.entId
                };
                resturl.deleteShareFolder(fileparam, callback).start();
            } else {
                resturl.deletePersonFolderToRecycle(this.get("fileId"), callback).start();
            }
        },

        _destoryFile: function (callback) {
            if (this.isEntDisk()) {
                var fileparam = {
                    'fileId': this.get("fileId"),
                    'folderId': this.get("parentId"),
                    'version': this.get("version"),
                    'userId': cache.userId,
                    'userName': cache.username
                };
                resturl.deleteShareFile(fileparam, callback).start();
            } else {
                resturl.deletePersonFileToRecycle(this.get("fileId"), callback).start();
            }

        }

        /** end model*/  });

});
