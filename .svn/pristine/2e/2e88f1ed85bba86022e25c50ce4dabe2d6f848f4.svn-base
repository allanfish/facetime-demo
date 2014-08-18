/**
 * 设置功能router
 */
define(function (require, exports, module) {

    require('./views/website/setting/SettingLeftView');
    require('./views/website/setting/UserInfoView');
    require('./views/website/setting/ResetPwdView');
    require('./views/website/setting/SystemSettingView');
    require('./views/website/setting/AddValueServiceView');
    require('./views/website/setting/DownloadAppView');
    require('./views/website/setting/VersionView');
    require('./views/website/DemoView');


    window.SettingRouter = Backbone.Router.extend({
        _this: undefined,
        routes: {
            'user/setting': 'showUserSettingView',
            'user/userinfo': 'setUserinfoView',
            'user/resetpwd': 'resetPwdView',
            'user/setsystem': 'setSystemView',
            'user/buyservice': 'buyServiceView',
            'user/downloadapp': 'downloadAppView',
            'user/version': 'versionView',
            'demo': 'showDemo'
        },

        initialize: function () {
            _this = this;
        },

        showDemo: function () {
            if (!view.demoView) {
                view.demoView = new DemoView();
            }

            if (view.contentRight && view.contentRight.$el.attr('id') !== view.demoView.id)
                view.contentRight.remove();
            view.contentRight = view.demoView;
            view.contentRight.delegateEvents();
            $("#content-right").append(view.contentRight.el);
        },

        before: function (route, params) {
            log.debug(' route filter before > route: ', route, ', params: ', params);
            if (!route || route === 'index') {
                return true;
            }
            if (!view.mainbox) {
                router.approuter.navigate('index', true);
                return false;
            }
            return true;
        },

        /**
         * 显示用户设置界面
         */
        showUserSettingView: function () {
            if (!view.settingLeftView)
                view.settingLeftView = new SettingLeftView();
            if (view.contentLeft && view.contentLeft.$el.attr('id') !== view.settingLeftView.id)
                view.contentLeft.remove();
            view.contentLeft = view.settingLeftView;
            $("#content-left").append(view.contentLeft.el);

            if (!view.userInfoView) {
                view.userInfoView = new UserInfoView({
                    model: new UserDTO(model.currentUser.toJSON())
                });
            } else {
                view.userInfoView.model.set(model.currentUser.toJSON());
            }

            if (view.contentRight && view.contentRight.$el.attr('id') !== view.userInfoView.id)
                view.contentRight.remove();
            view.contentRight = view.userInfoView;
            view.contentRight.delegateEvents();
            $("#content-right").append(view.contentRight.el);
        },

        /**
         * 显示用户基本信息界面
         */
        setUserinfoView: function () {
            if (!view.settingLeftView)
                view.settingLeftView = new SettingLeftView();
            if (view.contentLeft && view.contentLeft.$el.attr('id') !== view.settingLeftView.id)
                view.contentLeft.remove();
            view.contentLeft = view.settingLeftView;
            $("#content-left").append(view.contentLeft.el);
            if (!view.userInfoView) {
                view.userInfoView = new UserInfoView({
                    model: new UserDTO(model.currentUser.toJSON())
                });
            } else {
                view.userInfoView.model.set(model.currentUser.toJSON());
            }
            if (view.contentRight && view.contentRight.$el.attr('id') !== view.userInfoView.id)
                view.contentRight.remove();
            view.contentRight = view.userInfoView;
            view.contentRight.delegateEvents();
            $("#content-right").append(view.contentRight.el);
        },
        /**
         * 显示用户修改密码界面
         */
        resetPwdView: function () {
            if (!view.settingLeftView)
                view.settingLeftView = new SettingLeftView();
            if (view.contentLeft && view.contentLeft.$el.attr('id') !== view.settingLeftView.id)
                view.contentLeft.remove();
            view.contentLeft = view.settingLeftView;
            $("#content-left").append(view.contentLeft.el);

            if (!view.resetPwdView)
                view.resetPwdView = new resetPwdView({
                    model: new Backbone.Model()
                });
            if (view.contentRight && view.contentRight.$el.attr('id') !== view.resetPwdView.id)
                view.contentRight.remove();
            view.contentRight = view.resetPwdView;
            view.contentRight.delegateEvents();
            $("#content-right").append(view.contentRight.el);
        },
        /**
         * 显示用户修改密码界面
         */
        setSystemView: function () {
            if (!view.settingLeftView)
                view.settingLeftView = new SettingLeftView();
            if (view.contentLeft && view.contentLeft.$el.attr('id') !== view.settingLeftView.id)
                view.contentLeft.remove();
            view.contentLeft = view.settingLeftView;
            $("#content-left").append(view.contentLeft.el);

            if (!view.systemSettingView)
                view.systemSettingView = new systemSettingView({
                    model: model.currentUser
                });
            if (view.contentRight && view.contentRight.$el.attr('id') !== view.systemSettingView.id)
                view.contentRight.remove();
            view.contentRight = view.systemSettingView;
            $("#content-right").append(view.contentRight.el);
        },

        /**
         * 购买增值服务
         */
        buyServiceView: function () {
            if (!view.settingLeftView)
                view.settingLeftView = new SettingLeftView();
            if (view.contentLeft && view.contentLeft.$el.attr('id') !== view.settingLeftView.id)
                view.contentLeft.remove();
            view.contentLeft = view.settingLeftView;
            $("#content-left").append(view.contentLeft.el);

            if (!view.addValueServiceView)
                view.addValueServiceView = new addValueServiceView({
                    model: model.currentUser
                });
            // view.addValueServiceView = new addValueServiceView({model: model.changePwd});
            if (view.contentRight && view.contentRight.$el.attr('id') !== view.addValueServiceView.id)
                view.contentRight.remove();
            view.contentRight = view.addValueServiceView;
            $("#content-right").append(view.contentRight.el);
        },
        /**
         * 下载移动客户端
         */
        downloadAppView: function () {
            if (!view.settingLeftView)
                view.settingLeftView = new SettingLeftView();
            if (view.contentLeft && view.contentLeft.$el.attr('id') !== view.settingLeftView.id)
                view.contentLeft.remove();
            view.contentLeft = view.settingLeftView;
            $("#content-left").append(view.contentLeft.el);

            if (!view.downloadAppView)
                view.downloadAppView = new downloadAppView({
                    model: model.currentUser
                });
            if (view.contentRight && view.contentRight.$el.attr('id') !== view.downloadAppView.id)
                view.contentRight.remove();
            view.contentRight = view.downloadAppView;
            $("#content-right").append(view.contentRight.el);
        },
        /**
         * 版本信息
         */
        versionView: function () {
            if (!view.settingLeftView)
                view.settingLeftView = new SettingLeftView();
            if (view.contentLeft && view.contentLeft.$el.attr('id') !== view.settingLeftView.id)
                view.contentLeft.remove();
            view.contentLeft = view.settingLeftView;
            $("#content-left").append(view.contentLeft.el);

            if (!view.versionView)
                view.versionView = new versionView({
                    model: model.currentUser
                });
            if (view.contentRight && view.contentRight.$el.attr('id') !== view.versionView.id)
                view.contentRight.remove();
            view.contentRight = view.versionView;
            $("#content-right").append(view.contentRight.el);
        }


        /* router end*/
    });
});