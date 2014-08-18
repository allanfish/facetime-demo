define(function (require, exports, module) {

    /**
     * 新建文件DTO
     */
    window.NewFileDTO = Backbone.Model.extend({
        urlRoot: "",
        defaults: {
            name: '新建文件.oatw',
            prefix: '新建文件',
            suffix: '.oatw',
            content: '欢迎使用OATOS文本编辑器'
        },

        /**
         * 保存文件到服务器
         * @param callback
         */
        saveFile: function (callback) {
            this.set({
                'name': this.get("prefix") + this.get("suffix"),
                'guid': util.guid() + this.get("suffix"),
                'folderId': model.currentFolder.get("fileId"),
                'entDiskFile': model.currentFolder.isEntDisk(),
                'type': model.currentFolder.isEntDisk() ? constants.fileType.shareDisk : constants.fileType.onlineDisk,
                'userId': cache.userId,
                'userName': cache.username,
                'enterpriseId': cache.entId,
                'fromId': cache.userId,
                'toId': model.currentFolder.isEntDisk() ? cache.entId : cache.userId,
                'toType': model.currentFolder.isEntDisk() ? constants.fileType.shareDisk : constants.fileType.onlineDisk
            });

            var saveFileJson = _.omit(this.toJSON(), 'prefix', 'suffix');

            resturl.saveFile(saveFileJson, callback).start();
        }
    });
});
