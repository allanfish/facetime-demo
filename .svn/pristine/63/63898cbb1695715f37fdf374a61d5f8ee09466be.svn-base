define(function (require, exports, module) {

    require("jqueryztree");

    window.EntFolderSelectTree = Backbone.View.extend({

        tagName: 'ul',
        className: 'ztree',
        id: "EntFolderSelectTree",
        zTree: undefined,

        initialize: function () {
            this.listenTo(this.collection, events.loadFile, this.loadEntFiles);
            this.listenTo(this.collection, events.newFile, this.addFileNode);
            this.listenTo(this.collection, events.removeFile, this.removeNode);
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
                        if (treeNode.isParent && treeNode.fileId !== 'entRoot') {
                            _this.collection.fetchFolder(treeNode.fileId);
                        }
                        _this.zTree.expandNode(treeNode);
                        return true;
                    }
                }
            }, []);
            this.addFileNode(model.rootEntFolder);
            if (this.collection.length > 0) {
                this.loadEntFiles(this.collection.models);
            }
            log.debug('[render]-[EntFolderSelectTree]');
            return this;
        },

        showDialog: function (okCallback) {
            var that = this;
            $.dialog({
                title: "企业文件选择树",
                content: this.el,
                max: false,
                min: false,
                width: 200,
                height: 320,
                init: function () {
                    that.render();
                    that.delegateEvents();
                },
                ok: okCallback,
                cancelVal: "关闭",
                cancel: true
            });
        },

        /**
         * 清理Tree的选中项
         */
        clean: function () {
            this.zTree.cancelSelectedNode();
        },

        /**
         * 获取唯一选中的文件夹
         *
         * @returns {*}
         */
        getSelectedFolder: function () {
            var folders = this.getSelectedFolders();
            return folders && folders[0];
        },

        /**
         * 获取选中的多个文件夹
         *
         * @returns {*}
         */
        getSelectedFolders: function () {
            var that = this;
            var selectedFolders = _.map(this.zTree.getSelectedNodes(), function (treeNode) {
                return that.collection.where({fileId: treeNode.fileId})[0];
            });
            return selectedFolders;
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

        removeNode: function (file) {
            if (file.isFolder()) {
                this.zTree.removeNode(this.zTree.getNodeByParam("fileId", file.get("fileId")));
            }
        },

        /**
         * 在企业文件夹树中添加子节点
         */
        loadEntFiles: function (entFiles) {
            var entFolders = _.filter(entFiles, function (file) {
                return file.get('type') === 'folder'
            });
            _.each(entFolders, this.addFileNode, this);
        }

        /*object end */});
});
