/**
 * 用户信息视图
 */

define(function (require, exports, module) {

    tpl.userInfoView = require('tplurl-website/setting/UserInfoView.tpl');

    window.UserInfoView = Backbone.View.extend({
        tagName: 'div',
        id: 'userInfoView',
        _modelBinder: undefined,

        initialize: function () {
            this._modelBinder = new Backbone.ModelBinder();
            tplpre.userInfoView = Handlebars.compile(tpl.userInfoView);
            this.render();
        },

        render: function () {

            this.$el.html(tplpre.userInfoView(this.model.toJSON()));


            Backbone.Validation.bind(this, {

                valid: function (view, attr, selector) {
                    var control = view.$('[' + selector + '=' + attr + ']');
                    var group = control.parents(".control-group");
                    group.removeClass("error");
                    group.addClass("success");
                    group.find(".help-inline").empty();
                    log.debug("error-msg :　", group.find(".help-inline").html());

                },

                invalid: function (view, attr, error, selector) {
                    var control = view.$('[' + selector + '=' + attr + ']');
                    var group = control.parents(".control-group");
                    group.removeClass("success");
                    group.addClass("error");

                    if (group.find(".help-inline").length === 0) {
                        group.find(".controls").append("<span class=\"help-inline error-message\"></span>");
                    }
                    var target = group.find(".help-inline");
                    target.text(error);
                }
            });

            log.debug('html: ', this.$el.html());
            var bindings = Backbone.ModelBinder.createDefaultBindings(this.el, 'name');

            bindings['gender'] = [
                {
                    selector: '#mgender',
                    elAttribute: 'checked',
                    converter: this.mgenderConverter
                },
                {
                    selector: '#fgender',
                    elAttribute: 'checked',
                    converter: this.fgenderConverter
                }
            ];

            bindings['icon'] = [
                {
                    selector: '#user-icon',
                    elAttribute: 'src'
                }
            ];

            bindings['onlineStatus'] = [
                {
                    selector: '#user-online',
                    elAttribute: 'value'
                }
            ];

            bindings['birthday'] = [
                {
                    selector: '#user-birthday',
                    elAttribute: 'value',
                    converter: this.dateConverter
                }
            ];

            this._modelBinder.bind(this.model, this.el, bindings);
            return this;
        },


        close: function () {
            this._modelBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();
        },

        events: {
            "click #setting-userinfo": "updateUserinfo",
            'click .take-photo': 'onTakePhoto'
        },

        /**
         * 拍照
         */
        onTakePhoto: function () {
            view.takePhotoView = view.takePhotoView || new TakePhotoView();
            view.takePhotoView.render();
        },

        mgenderConverter: function (direction, value) {
            if (direction == 'ModelToView') {
                if (!value) {
                    return "checked";
                } else if (value == "m") {
                    return "checked";
                } else {
                    return null;
                }
            }
        },

        fgenderConverter: function (direction, value) {
            if (direction == 'ModelToView') {
                if (!value) {
                    return null;
                } else if (value == "f") {
                    return "checked";
                } else {
                    return null;
                }
            }
        },
        dateConverter: function (direction, value) {

            var d, s = "";
            d = new Date(value);
            s += (d.getMonth() + 1) + "/";
            s += d.getDate() + "/";
            s += d.getYear();
            log.debug(s);
            return (s);

        },
        updateUserinfo: function (event) {
            //this.model.validate();
            log.debug('realName: ', this.model.get("realName"));
            if (this.model.isValid(true)) {
                // alert("1123123");
                eval(Wind.compile('async', function () {
                    var updateUserProfile = $await(resturl.updateUserProfile(view.userInfoView.model.toJSON()));
                    //log.debug("updateUserProfile :", updateUserProfile);
                    if ("OK" == updateUserProfile) {
                        //alert("-----------ok-----");
                        //更换css
                        this.$('.alert-error').hide();
                        this.$('.alert-success').fadeIn();
                    } else {
                        //alert("-----------fail-----");
                        this.$('.alert-success').hide();
                        this.$('.alert-error').fadeIn();
                    }

                }))().start();
            }
            event.stopPropagation();
            event.preventDefault();


        } /**end view*/
    });

});