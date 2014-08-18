/**
 * 拍照view
 */
define(function (require, exports, module) {

    window.TakePhotoView = Backbone.View.extend({
        tagName: 'div',
        id: 'takePhotoView',
        className: 'takePhotoView',

        initialize: function () {
           // this.render();
        },

        render: function () {
            this.$el.html(" ");
            $.dialog({
                title: '拍照',
                content: this.el,
                max: false,
                min: false,
                width: 480,
                height: 480

            });

            swfobject.embedSWF("webcam/capturePhotos.swf?t=20130222", "takePhotoView", '480', '480', '9.0.0', 'expressInstall.swf', {
                "locale": 'zh_CN',
                'uploadServletPath': '/os/swf/takePicture',
                'UT': cache.clientToken.token
            }, {
                "wmode": "transparent",
                "allowFullScreen": "true"
            });
            return this;
        }


        /**end view*/});

});