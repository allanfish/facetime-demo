/**
 * 收藏夾VIEW
 */
define(function (require, exports, module) {

    tpl.fileCollectView = require('tplurl-website/file/FileCollectView.tpl');

    window.FileCollectView = Backbone.View.extend({
        tagName: 'div',
        id: 'fileCollectView',
        collectionBinder: undefined,

        events: {
            'click #fileCollect-query': 'queryCollectFile'
        },

        initialize: function () {
            tplpre.fileCollectView = Handlebars.compile(tpl.fileCollectView);
            var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
            this.collectionBinder = new Backbone.CollectionBinder(elManagerFactory);
            this.render();
        },

        viewCreator: function (model) {
            return new FileCollectItemView({model: model});
        },

        render: function () {
            this.$el.html(tplpre.fileCollectView);
            this.collectionBinder.bind(this.collection, this.$el.find('#ent-file-list'));
            return this;
        },

        queryCollectFile: function () {
            var keyword = this.$("#fileCollect-search").val();
            log.debug("keyword: ", !keyword || keyword.length === 0);
            if (!keyword || keyword.length == 0) {
                this.collectionBinder.unbind();
                this.collectionBinder.bind(this.collection, this.$el.find("#ent-file-list"));
            } else {
                collection.resultUpdateList = collection.resultUpdateList || new EntFileListDTO();
                collection.resultUpdateList.reset(this.collection.filter(function (each) {
                    return each.get("name").match(keyword);
                }));
                this.collectionBinder.unbind();
                this.collectionBinder.bind(collection.resultUpdateList, this.$el.find("#ent-file-list"));
            }

        },

        close: function () {
            this.collectionBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        }

        /** end view */});

});