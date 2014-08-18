define(function(require, exports, module) {

	require("jqueryztree");

	window.EntFolderTree = Backbone.View.extend({

		initialize: function() {
			this.listenTo(this.model, "entFolder: add", this.addFolderNode);
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
						app.model.currentEntFolder.set(that.model.get(treeNode.folderId).toJSON());
						var hasLoaded = app.model.currentEntFolder.get("loaded");
						if (hasLoaded) {
							app.model.currentEntFolder.trigger("selectEntFolder");
							zTree.expandNode(treeNode);
							return true;
						}

						that.model.fetchFolder(treeNode.folderId, function() {
							app.model.currentEntFolder.trigger("selectEntFolder");
						});
						return true;
					}
				}
			}, []);
			return this;
		},

		events: {
			'click #entFolderTree li[id^=entFolderTree_]': 'selectFolderItem'
		},

		elementId: function() {
			return $(this.el).attr("id");
		},

		selectFolderItem: function(event) {
			console.log("select Folder item");
		},

		/**
		 * 在企业文件夹树中添加子节点
		 */
		addFolderNode: function(childFolder) {
			var parentId = childFolder.get("parentId");
			var zTree = $.fn.zTree.getZTreeObj(this.elementId());
			var treeNode = parentId ? zTree.getNodeByParam("folderId", parentId) : null;

			zTree.addNodes(treeNode, [ {
				folderId: childFolder.get("folderId"),
				parentId: childFolder.get("parentId") || 0,
				name: childFolder.get("name"),
				open: false,
				isParent: true
			} ]);
		}
	});
});
