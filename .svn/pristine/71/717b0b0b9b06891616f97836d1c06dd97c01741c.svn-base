/**
 网盘试图左边导航树
 */
define(function (require, exports, module) {

    require("jqueryztree");
    require("wind");

    tpl.fileLeftView = require('tplurl-website/file/FileLeftView.tpl');

    window.FileLeftView = Backbone.View.extend({
        tagName: 'div',
        id: 'file-left-view',
        folderTree: undefined,
        fileTreeBottom: undefined,

        initialize: function () {
            this.render();
        },

        render: function () {
            tplpre.fileLeftView = Handlebars.compile(tpl.fileLeftView);
            this.$el.html(tplpre.fileLeftView);
            this.folderTree = this.$el.find("#folderTree");
            this.fileTreeBottom = this.$el.find("#fileTreeBottom");

            this.fileTreeBottom.height(model.setting.get("folderTreeBottomHeight"));
            this.folderTree.height(model.setting.get("innerHeight") - model.setting.get("folderTreeBottomHeight") - model.setting.get("headerHeight"));
            return this;
        }
    });
});
