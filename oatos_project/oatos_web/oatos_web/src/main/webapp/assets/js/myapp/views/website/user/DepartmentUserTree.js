define(function (require, exports, module) {
    window.DepartmentUserTree = Backbone.View.extend({

        tagName: 'ul',
        id: "departmentUserTree",
        className: 'departmentUserTree ztree',
        zTree: undefined,

        initialize: function () {

            this.listenTo(collection.departmentList, events.loadDepartment, this.addDepartmentNodes);
            this.render();
        },


        render: function () {
            this.$el.html(" ");

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
                        idKey: "id",
                        pIdKey: "parentId",
                        rootPId: ""
                    }
                },
                callback: {
                    beforeClick: function (treeId, treeNode) {
                        _this.zTree.expandNode(treeNode);
                        return true;
                    }
                }
            }, []);
            if (this.collection.length > 0) {
                this.addDepartmentNodes(this.collection);
            }
        },


        /**
         * 将该部门员工树恢复到初始状态, 即清除所有选中的
         */
        reset: function () {
            this.zTree.checkAllNodes(false);
        },

        /**
         * 返回选中的用户
         * @return [array] 用户数组
         */
        getCheckUsers: function () {
            var nodes = zTree.getCheckedNodes(true);
            return  _.map(_.where(nodes, {'type': 'user'}), function (userNode) {
                return collection.userList.where({'userId': userNode.id })[0];
            });
        },


        showCheckbox: function (show) {
            this.zTree.setting.check.enable = show;
        },

        addDepartmentNodes: function (departmentList) {
            departmentList.each(function (department) {
                var parentNode = department.get("parentId") ? this.zTree.getNodeByParam("id", department.get("parentId")) : null;
                this.zTree.addNodes(parentNode, {
                    id: department.get("departmentId"),
                    parentId: department.get("parentId") || 0,
                    name: department.get("name"),
                    open: false,
                    type: 'department',
                    isParent: true
                }, true);

                this.addUserNodes(collection.userList.where({'departmentId': department.get("departmentId")}));
            }, this);
        },


        addUserNodes: function (userList) {
            _.each(userList, function (user) {
                var parentNode = user.get("departmentId") ? this.zTree.getNodeByParam("id", user.get("departmentId")) : null;
                this.zTree.addNodes(parentNode, {
                    id: user.get("userId"),
                    parentId: user.get("departmentId") || 0,
                    name: user.get("realName") || user.get("userName"),
                    open: false,
                    type: 'user',
                    isParent: false
                }, true);
            }, this);

        }


        /**end view*/
    })
})