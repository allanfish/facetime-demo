/**
 * sea.js 定义
 */
define(function (require, exports, module) {
    /**
     * 导入需要的模版
     * @type {*}
     */

    tpl.recycleTableView = require('tplurl-website/file/RecycleTableView.tpl');

    window.PersonalRecycleView = Backbone.View.extend({
        tagName: "div",
        id: "personal-recycle",
        collectionBinder: undefined,

        initialize: function () {
            tplpre.recycleTableView = Handlebars.compile(tpl.recycleTableView);

            var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
            this.collectionBinder = new Backbone.CollectionBinder(elManagerFactory);

            this.render();
        },

        viewCreator: function (model) {
            return new RecycleItemView({
                model: model
            });
        },

        render: function () {
            this.$el.html(tplpre.recycleTableView);
            this.collectionBinder.bind(this.collection, this.$el.find("#ent-file-list"));
            return this;
        },

        close: function () {
            this.collectionBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        }

    });


});

