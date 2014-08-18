/**
  * HTML编辑器
  */
  define(function(require, exports, module) {

  	require("jquery.hotkeys");
    require("bootstrap-wysiwyg");

    tpl.htmlEditor = require('tplurl-website/file/HtmlEditor.tpl');

    window.HtmlEditor = Backbone.View.extend({
      tagName: 'div',
      id: 'html-editor',
      _this: undefined,
      editor: undefined,
      toolbar: undefined,

      initialize: function() {
        _this = this;
        tplpre.htmlEditor = Handlebars.compile(tpl.htmlEditor);
        this.render();
      },

      render: function() {
       this.$el.html(tplpre.htmlEditor);
       this.editor =  this.$el.find('#editor');
       this.toolbar = this.$el.find('[data-role=editor-toolbar]');
       this.initToolbarBootstrapBindings();
       this.editor.wysiwyg({toolbarSelector: this.toolbar});
       return this;
     },


     initToolbarBootstrapBindings: function() {
      var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
      'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
      'Times New Roman', 'Verdana'],
      fontTarget = this.$el.find('[title=Font]').siblings('.dropdown-menu');
      $.each(fonts, function (idx, fontName) {
        fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
      });
      this.$el.find('a[title]').tooltip();

      this.$el.find('.dropdown-menu input').click(function() {
        return false;
      }).change(function () {
        $(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');
      }).keydown('esc', function () {
        this.value='';
        $(this).change();
      });

      this.$el.find('[data-role=magic-overlay]').each(function () { 
        var overlay = $(this), target = _this.$el.find(overlay.data('target')); 
        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
      });
    }, 

    /**
     * [返回]或者[设置]editor的html内容. 根据用户是否传入content里定
     * @param  {string} content html或者text
     * @return {[type]}         [description]
     */
     html: function(content){
      if(content){
        this.editor.html(content);
      }else{
        return this.editor.html();
      }
    }, 

    showErrorAlert: function(reason, detail) {
      var msg='';
      if (reason==='unsupported-file-type') {
       msg = "Unsupported format " +detail; 
     }else {
       console.log("error uploading file", reason, detail);
     }
     $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
       '<strong>File upload error</strong> '+msg+' </div>').prependTo(this.$el.find('#alerts'));
   }

 /**end view*/});

});
