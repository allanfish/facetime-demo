define(function (require, exports, module) {

    require("jqueryztree");

    window.PersonFolderTree = Backbone.View.extend({

        tagName: 'ul',
        id: "personFolderTree",
        className: "ztree",

        initialize: function () {
            this.listenTo(this.collection, events.loadFile, this.loadFiles);
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
                        router.fileRouter.navigate(constants.fileType.onlineDisk + "/open/" + treeNode.fileId, true);
                        _this.zTree.expandNode(treeNode);
                        return true;
                    }
                }
            }, []);
            this.addFileNode(model.rootPersonFolder);
            log.debug('[render]-[PersonFolderTree]');
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
            }, true);
        },

        removeNode: function (file) {
            if (file.isFolder()) {
                this.zTree.removeNode(this.zTree.getNodeByParam("fileId", file.get("fileId")));
            }
        },

        /**
         * 在企业文件夹树中添加子节点
         */
        loadFiles: function (personFiles) {
            log.debug('[event] events.loadFile, personFiles length:', personFiles.length);
            var personFolders = _.filter(personFiles, function (file) {
                return file.get('type') === 'folder'
            });
            _.each(personFolders, this.addFileNode, this);
        }

        /*object end */});
});
