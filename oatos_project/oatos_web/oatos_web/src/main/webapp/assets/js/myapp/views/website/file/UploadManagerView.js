/**
 * 文件上传管理
 */
define(function (require, exports, module) {

    tpl.uploadManagerView = require('tplurl-website/file/UploadManagerView.html');

    window.UploadManagerView = Backbone.View.extend({
        tagName: 'div',
        id: 'uploadDownloadView',
        _collectionBinder: undefined,

        initialize: function () {
            tplpre.uploadManagerView = Handlebars.compile(tpl.uploadManagerView);

            var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
            this._collectionBinder = new Backbone.CollectionBinder(elManagerFactory);

            this.render();
        },

        viewCreator: function (model) {
            return new UploadItemView({model: model});
        },

        render: function () {
            this.$el.html(tplpre.uploadManagerView);
            this._collectionBinder.bind(this.collection, this.$el.find(".cs-upload-items"));
            return this;
        },

        close: function () {
            _collectionBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        }

        /**end view*/});
});