define(function (require, exports, module) {

    tpl.mainboxview = require('tplurl-website/mainbox.tpl');

    window.MainboxView = Backbone.View.extend({

        contentLeft: undefined,
        contentRight: undefined,
        slideRight: undefined,

        initialize: function () {
            this.listenTo(this.model, 'change:innerWidth', this.onChangeWidth);
            this.render();
        },


        render: function () {
            tplpre.mainboxview = Handlebars.compile(tpl.mainboxview);
            this.$el.append(tplpre.mainboxview);
            this.contentLeft = this.$el.find("#content-left");
            this.contentRight = this.$el.find("#content-right");
            this.slideRight = this.$el.find("#slide-right");

            this.contentLeft.width(this.model.get("contentLeftWidth"));
            this.slideRight.width(this.model.get("slideRightWidth"));
            this.contentRight.width(this.model.getContentRightWidth());

            return this;
        },

        onChangeWidth: function () {
            var contentRightWidth = this.slideRight.hasClass("hide") ? this.model.getContentRightWidth() : this.model.getContentRightWidthMinusSlide();
            if (contentRightWidth > this.model.get("minContentRightWidth"))
                this.contentRight.width(contentRightWidth);
        },

        showSlideRight: function () {
            if (this.slideRight.hasClass("hide")) {
                var _this = this;
                this.contentRight.animate({
                    width: this.model.getContentRightWidthMinusSlide() + "px"
                }, 500, function () {
                    _this.slideRight.removeClass('hide');
                });
            }
        },

        hideSlideRight: function () {
            if (!this.slideRight.hasClass("hide")) {
                var _this = this;
                view.slideRight && view.slideRight.remove();
                this.contentRight.animate({
                    width: this.model.getContentRightWidth() + "px"
                }, 500, function () {
                    _this.slideRight.addClass('hide');
                });
            }
        }
    });
})