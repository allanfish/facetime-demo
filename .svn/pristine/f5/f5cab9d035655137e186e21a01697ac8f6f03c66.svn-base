define(function (require, exports, module) {

    require("jqueryztree");

    /**
     * 企业文件和文件夹树. 该树可以选中文件夹和文件
     *
     * @type {*}
     */
    window.EntFileTree = Backbone.View.extend({

        tagName: 'ul',
        className: 'ztree',
        id: "ent-file-tree",
        zTree: undefined,

        initialize: function () {
            this.listenTo(this.collection, events.loadFile, this.loadEntFiles);
            this.listenTo(this.collection, events.newFile, this.addFileNode);
            this.listenTo(this.collection, events.removeFile, this.removeNode);
            this.render();
        },

        render: function () {
            this.$el.html("");
            var _this = this;
            this.zTree = $.fn.zTree.init(this.$el, {
                view: {
                    dblClickExpand: false,
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
                        if (treeNode.isParent && treeNode.fileId !== 'entRoot')
                            _this.collection.fetchFolder(treeNode.fileId);
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

        showCheckbox: function (show) {
            this.zTree.setting.check.enable = show;
        },

        /**
         * 返回选中的文件数组
         * @returns {array}
         */
        getCheckedFiles: function () {
            var nodes = zTree.getCheckedNodes(true);
            return  _.map(_.where(nodes, {'isParent': false}), function (fileNode) {
                return this.collection.where({'fileId': fileNode.fileId })[0];
            }, this);
        },

        addFileNode: function (folder) {
            var parentNode = folder.get("parentId") ? this.zTree.getNodeByParam("fileId", folder.get("parentId")) : null;
            this.zTree.addNodes(parentNode, {
                fileId: folder.get("fileId"),
                parentId: folder.get("parentId") || 0,
                name: folder.get("name"),
                open: false,
                isParent: folder.isFolder()
            }, true);
        },

        removeNode: function (file) {
            this.zTree.removeNode(this.zTree.getNodeByParam("fileId", file.get("fileId")));
        },

        /**
         * 在企业文件夹树中添加子节点
         */
        loadEntFiles: function (entFiles) {
            log.debug('[event] events.loadFile, entFiles length:', entFiles.length);
            _.each(entFiles, this.addFileNode, this);
        }

        /*object end */});
});
