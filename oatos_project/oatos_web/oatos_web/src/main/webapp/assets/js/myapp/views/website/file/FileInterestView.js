/*
* 关注的文件
* */
define(function (require, exports, module) {

    tpl.fileInterestView = require('tplurl-website/file/FileInterestView.tpl');

    window.FileInterestView = Backbone.View.extend({
        tagName: 'div',
        id: 'fileInterestView',
        collectionBinder: undefined,

        events: {

        },

        initialize: function () {
            tplpre.fileInterestView = Handlebars.compile(tpl.fileInterestView);
            this.render();
        },

        render: function () {
            view.fileInterestItemView=new FileInterestItemView();
            this.$el.html(tplpre.fileInterestView);
            this.$("#ent-file-list").append(view.fileInterestItemView.$el);
            return this;
        },

        queryCollectFile: function () {
            var keyword = this.$("#fileInterest-search").val();
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

