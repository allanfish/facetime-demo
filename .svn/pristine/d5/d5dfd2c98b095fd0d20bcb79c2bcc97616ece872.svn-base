/**
 * 回收站主页
 */
define(function (require, exports, module) {

    tpl.recycleTableView = require('tplurl-website/file/RecycleTableView.tpl');

    window.EntRecycleView = Backbone.View.extend({
        tagName: 'div',
        id: 'entView-recycle',
        _modelBinder: undefined,

        initialize: function () {
            log.debug("ent 1 ","init");
            this.listenTo(this.collection, events.loadRecycleFiles, this.loadRecycleFiles);
            log.debug("ent 2 ","init");
            this._modelBinder = new Backbone.ModelBinder();
            log.debug("ent 3 ","init");
            tplpre.recycleTableView = Handlebars.compile(tpl.recycleTableView);
            log.debug("ent 4 ","init4");
            var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);

            this.collectionBinder = new Backbone.CollectionBinder(elManagerFactory);
            log.debug("ent 5 ","init5");
        },
        // this.render();

    viewCreator: function (model) {
        log.debug("render data:", model);
        return new RecycleItemView({
                model: model
            });
    },

    render: function () {
        this.$el.html(tplpre.recycleTableView);
        //this._modelBinder.bind(this.model, this.$el);
            log.debug("this collection  ---", this.collection)
        this.collectionBinder.bind(this.collection, $("#ent-file-list"));
        return this;
    },

    close: function () {
        this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },

        loadRecycleFiles: function (recycleFiles) {
            log.debug(" [event] ", events.loadRecycleFiles);
            log.debug(" recycleFiles ", recycleFiles);
            // TODO
            this.render();
        }
        /**end view*/
    });




});