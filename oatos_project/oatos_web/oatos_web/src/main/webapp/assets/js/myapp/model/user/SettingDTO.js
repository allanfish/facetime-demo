define(function (require, exports, module) {

    /**
     * 设置DTO
     */
    window.SettingDTO = Backbone.Model.extend({
        urlRoot: "",

        defaults: {
            contentLeftWidth: 206,
            contentRightMargin: 20,
            folderTreeBottomHeight: 105,
            headerHeight: 70,

            slideRightWidth: 270,
            minContentRightWidth: 272,
            width: 0,
            height: 0,
            innerWidth: 0,
            innerHeight: 0
        },

        getContentRightWidth: function () {
            return this.get("innerWidth") - this.get("contentLeftWidth") - this.get("contentRightMargin");
        },

        getContentRightWidthMinusSlide: function () {
            return this.get("innerWidth") - this.get("contentLeftWidth") - this.get("contentRightMargin") - this.get("slideRightWidth");
        }
    })
})