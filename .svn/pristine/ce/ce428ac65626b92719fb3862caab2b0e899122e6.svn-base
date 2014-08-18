define(function (require, exports, module) {

    require("jqueryztree");

    window.EntFolderTree = Backbone.View.extend({

        tagName: 'ul',
        className: 'ztree',
        id: "entFolderTree",
        zTree: undefined,

        initialize: function () {
            this.listenTo(this.collection, events.loadFile, this.loadEntFiles);
            this.listenTo(this.collection, events.newFile, this.onNewFolder);
            this.listenTo(this.collection, events.removeFile, this.removeNode);
            this.listenTo(this.collection, events.moveFiles, this.onMoveFiles);
            this.render();
        },

        render: function () {
            this.$el.html("");
            var _this = this;
            this.zTree = $.fn.zTree.init(this.$el, {
                view: {
                    dblClickExpand: true,
                    showLine: true,
                    selectedMulti: false
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "fileId",
                        pIdKey: "parentId",
                        rootPId: ""
                    }
                },
                callback: {
                    beforeClick: function (treeId, treeNode) {
                        router.fileRouter.navigate(constants.fileType.shareDisk + "/open/" + treeNode.fileId, true);
                        _this.zTree.expandNode(treeNode);
                        return true;
                    }
                }
            }, []);
            this.addFileNode(model.rootEntFolder);
            if (this.collection.length > 0) {
                this.loadEntFiles(this.collection.models);
            }
            log.debug('[render]-[EntFolderTree]');
            return this;
        },

        addFileNode: function (folder) {
            var parentId = folder.get("parentId");
            var treeNode = parentId ? this.zTree.getNodeByParam("fileId", parentId) : null;

            this.zTree.addNodes(treeNode, {
                fileId: folder.get("fileId"),
                parentId: folder.get("parentId") || 0,
                name: folder.get("name"),
                open: false,
                isParent: true
            }, true)
        },

        onNewFolder: function (newFolder) {
            log.debug("onNewFolder: ", newFolder.toJSON());
            log.debug('collection.entFileList add event.');
            this.addFileNode(newFolder);
        },

        removeNode: function (file) {
            if (file.isFolder()) {
                this.zTree.removeNode(this.zTree.getNodeByParam("fileId", file.get("fileId")));
            }
        },


        onMoveFiles: function (destFolder, moveFiles) {
            var destNode = this.zTree.getNodeByParam({fileId: destFolder.get("fileId")});
            _.each(moveFiles, function (file) {
                var moveNode = this.zTree.getNodeByParam({fileId: file.get("fileId")});
                this.zTree.moveNode(destNode, moveNode, 'inner');
            }, this);
        },
        /**
         * 在企业文件夹树中添加子节点
         */
        loadEntFiles: function (entFiles) {
            log.debug('[event] events.loadFile, entFiles length:', entFiles.length);
            var entFolders = _.filter(entFiles, function (file) {
                return file.get('type') === 'folder'
            });
            _.each(entFolders, this.addFileNode, this);
        }

        /*object end */});
});
