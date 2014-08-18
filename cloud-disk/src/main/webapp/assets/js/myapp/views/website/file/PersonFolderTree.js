/**
 * 个人文件夹导航树View
 */
define(function(require, exports, module) {

	require("jqueryztree");
	require("wind");

	window.PersonFolderTree = Backbone.View.extend({
		initialize: function() {
			this.render();
		},

		render: function() {
			var that = this;
			$.fn.zTree.init($(this.el), {
					view: {
						dblClickExpand: false,
						showLine: true,
						selectedMulti: false
					},
					data: {
						simpleData: {
							enable: true,
							idKey: "folderId",
							pIdKey: "parentId",
							rootPId: ""
						}
					},
					callback: {
						beforeClick: function(treeId, treeNode) {
							var zTree = $.fn.zTree.getZTreeObj(that.elementId());
							var hasLoaded = that.model.get(treeNode.folderId).get("loaded");
							if (hasLoaded) {
								zTree.expandNode(treeNode);
								return false;
							}
							that.model.fetchFolder(treeNode.folderId);
							return false;
						}
						}
		}, []);
			return this;
		},

		elementId: function() {
			return $(this.el).attr("id");
		},

		/**
		 * 在企业文件夹树中添加子节点
		 */
		addFolderNode: function(childFolder) {
			console.log("PersonFolderTree: addFolderNode: childFolder parentIdL "+childFolder.get("parentFolderId")+", folderName:" + childFolder.get("folderName"));
			var parentId = childFolder.get("parentId");
			var zTree = $.fn.zTree.getZTreeObj($(this.el).attr("id"));
			var treeNode = parentId ? zTree.getNodeByParam("folderId", parentId) : null;

			zTree.addNodes(treeNode, [ {
				folderId: childFolder.get("folderId"),
				parentId: childFolder.get("parentFolderId") || 0,
				name: childFolder.get("folderName"),
				open: false,
				isParent: true
			} ]);
		}
	});
});
