/**
 * 新建文件界面
 */
define(function (require, exports, module) {

    require("./HtmlEditor");
    tpl.newFileView = require('tplurl-website/file/NewFileView.tpl');

    window.NewFileView = Backbone.View.extend({
        tagName: 'div',
        id: 'newFileView',
        className: 'modal fade hide',
        _modelBinder: undefined,
        htmlEditor: undefined,

        initialize: function () {
            this.listenTo(this, 'saveFileOK', this.saveFileOK);
            this._modelBinder = new Backbone.ModelBinder();
            this.render();
        },

        render: function () {
            tplpre.newFileView = Handlebars.compile(tpl.newFileView);
            this.$el.html(tplpre.newFileView);
            this.htmlEditor = new HtmlEditor();
            this.$el.find('.modal-body').append(this.htmlEditor.el);

            var bindings = {
                'prefix': '[name=prefix]',
                'suffix': '[name=suffix]',
                'content': '#editor'
            };

            this._modelBinder.bind(this.model, this.el, bindings);
            return this;
        },

        events: {
            'click .modal-footer .btn:last': 'saveFile'
        },

        close: function () {
            this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.$el.modal('hide');
            this.remove();
        },

        saveFileOK: function () {
            log.debug('handle saveFileOK event');
            this.close();
            $.dialog.tips('文件保存成功!');
        },

        saveFile: function (event) {
            $(event.target).button('loading');
            var that = this;
            this.model.saveFile(function (result) {
                var savedFile = JSON.parse(result);
                var fileDTO = new EntFileDTO();
                fileDTO.initFromFile(savedFile);
                fileDTO.set('diskType', model.currentFolder.get("diskType"));
                collection.currentFileList.add(fileDTO);
                fileDTO.isEntDisk() ? collection.entFileList.add(fileDTO) : collection.personFileList.add(fileDTO);
                that.trigger('saveFileOK');
            });
        }

        /**end view*/});

});