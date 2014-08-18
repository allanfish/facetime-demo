/**
 * 回收站主页面
 */
define(function (require, exports, module) {

    tpl.recycleView = require('tplurl-website/file/RecycleView.tpl');

    window.RecycleView = Backbone.View.extend({
        tagName: 'div',
        id: 'recycle-main',

        initialize: function () {
            tplpre.recycleView = Handlebars.compile(tpl.recycleView);
            this.render();
        },

        render: function () {
            this.$el.html(tplpre.recycleView);
            this._initEntRecycleView();
            this._initPersonRecycleView();

            this.$el.find("a[data-toggle='tab']:last").on("show", function () {
                log.debug("render 3", "show personal file ----");
                collection.personalRecycleFiles.fetchPersonalRecycleFiles();
            });
            return this;
        },


        _initPersonRecycleView: function () {
            collection.personalRecycleFiles = new RecycleFileListDTO();

            view.personalRecycleView = view.personalRecycleView || new PersonalRecycleView({
                collection: collection.personalRecycleFiles,
                el: this.$el.find("#personal-recycle")
            });
        },

        _initEntRecycleView: function () {
            log.debug("render 2", "show ent file ---");
            if (!view.entRecycleView) {
                collection.entRecycleFiles = new RecycleFileListDTO();
                collection.entRecycleFiles.fetchEntRecycleFiles();

                view.entRecycleView = view.entRecycleView || new EntRecycleView({
                    collection: collection.entRecycleFiles,
                    el: this.$el.find("#ent-recycle")
                });
            }
        }


        /**end view*/});

});