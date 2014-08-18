define(function (require, exports, module) {

    require("jquery");
    require("jquerycookie");
    require("json");
    require("wind");

    // views
    require('./views/website/indexview');
    require('./views/website/headerview');
    require('./views/website/mainboxview');
    require('./views/website/file/FileLeftView');
    require('./views/website/file/FileRightView');
    require('./views/website/file/EntFolderTree');
    require('./views/website/file/EntFileTable');
    require('./views/website/file/FileItemView');
    require('./views/website/file/FileCollectItemView');
    require('./views/website/file/PersonFolderTree');
    require('./views/website/file/NewFileView');
    require('./views/website/file/TakePhotoView');
    require('./views/website/file/EntFileTree');
    require('./views/website/file/PersonFileTree');
    require('./views/website/file/EntFolderSelectTree');

    // user view
    require('./views/website/user/UserLayout');
    require('./views/website/conference/ConferenceView');
    require('./views/website/conference/ConferenceInfoView');
    require('./views/website/user/MessageView');
    require('./views/website/user/MessageItemView');
    require('./views/website/user/DepartmentUserTree');
    require('./filerouter');
    require('./settingrouter');

    // model
    require('./model/conference/ConferenceDTO');
    require('./model/conference/ConferenceMemberDTO');
    // collection
    require('./collection/conference/ConferenceListDTO');
    require('./collection/conference/MemberListDTO');

    var AppRouter = Backbone.Router.extend({
        routes: {
            "": "homepage",
            "index": "homepage",
            'user/show': 'showUser',
            "user/login": "loginUser",
            "user/reg": "regUser",
            "loginout": "loginOut",
            'conference': 'showConferenceView',
            'message': 'showMessage',
            'message/close': 'closeMessage',
            'slide/close': 'closeSlideRight',
            'chat/:userId': 'chatWithUser'
        },


        before: function (route) {
            log.debug(' route filter before > route: ', route);
            if (!route || route === 'index') {
                return true;
            }
            if (!view.mainbox) {
                this.navigate('index', true);
                return false;
            }
        },

        after: function () {
            // $("#mainbox").slideUp();
        },

        initialize: function () {
            view.indexpage = new IndexView({
                el: $("body")
            });

            view.header = new HeaderView({
                model: model.session,
                el: $("#headerbox")
            });
        },

        chatWithUser: function (userId) {
            if (!view.userLayout) {
                view.userLayout = new UserLayout({
                    model: collection.departmentList
                });
            }

        },

        /**
         * 关闭右边Slide
         */
        closeSlideRight: function () {
            view.mainbox.hideSlideRight();
        },

        /**
         * 显示会议视图
         */
        showConferenceView: function () {
            view.mainbox.showSlideRight();
            if (!view.conferenceView) {
                collection.conferenceList = new ConferenceListDTO();
                collection.conferenceList.fetch();
                view.conferenceView = new ConferenceView({collection: collection.conferenceList});
            }
            if (view.slideRight && view.slideRight.id !== view.conferenceView.id)
                view.slideRight.remove();
            view.slideRight = view.conferenceView;
            view.slideRight.delegateEvents();
            $('#slide-right').append(view.slideRight.el);
        },
        /**
         * 显示信息管理
         */
        showMessage: function () {
            view.mainbox.showSlideRight();
            if (!view.messageView) {
                view.messageView = new MessageView({collection: collection.fileMessageList});
            } else {
                view.messageView.listenTo(collection.messageList, 'add', view.messageView.onHandleMessage);
            }

            if (view.slideRight && view.slideRight.id !== view.messageView.id)
                view.slideRight.remove();
            view.slideRight = view.messageView;
            view.slideRight.delegateEvents();

            $('#slide-right').append(view.slideRight.el);
            view.slideRight.showMessageNum();
        },
        closeMessage: function () {
            if (view.slideRight) {
                view.slideRight.collectionBinder.unbind();
                view.slideRight.off();
                view.slideRight.remove();
                view.mainbox.hideSlideRight();
                //$('#slide-right').addClass('hide');
                // $('#content-right').animate({width: '82.9787%'}, 500);
            }

        },
        /**
         * 联系人界面显示
         */
        showUser: function () {
            log.debug('route: showUser');
            view.mainbox.showSlideRight();
            if (!view.userLayout) {
                view.userLayout = new UserLayout({
                    model: collection.departmentList
                });
            }
            if (view.slideRight && view.slideRight.id !== view.userLayout.id) {
                view.slideRight.remove();
            }
            view.slideRight = view.userLayout;
            view.slideRight.delegateEvents();
            view.usualContactTreeView.delegateEvents();
            collection.usualContactList.reset(collection.usualContactList.models);

            view.mainbox.$el.find('#slide-right').append(view.slideRight.el);
        },

        /**
         * 进入系统后的首页
         */
        homepage: function (path) {
            view.mainbox = new MainboxView({
                model: model.setting,
                el: $("#mainbox")
            });

            view.contentLeft = view.fileLeftView = new FileLeftView();
            $("#content-left").append(view.contentLeft.el);

            view.entFolderTree = new EntFolderTree({
                collection: collection.entFileList
            });
            view.fileLeftView.$el.find('#entFolderTreeDiv').append(view.entFolderTree.el);


            view.personFolderTree = new PersonFolderTree({
                collection: collection.personFileList
            });
            view.fileLeftView.$el.find("#personFolderTreeDiv").append(view.personFolderTree.el);

            // 初始化文件右边VIEW
            view.contentRight = view.fileRightView = new FileRightView();
            view.mainbox.$el.find("#content-right").append(view.contentRight.el);

            collection.fileBreadList = new FileBreadListDTO();
            collection.fileBreadList.reset(model.rootEntFolder);

            // 右边文件列表集合
            collection.currentFileList = new EntFileListDTO();

            // 企业文件列表
            view.fileTable = new EntFileTable({
                el: view.fileRightView.$el.find("#fileTable"),
                collection: collection.currentFileList
            });


        },

        loginOut: function () {
            view.mainbox = new MainboxView({
                el: $("#mainbox")
            });
        }

    });

    exports.initialize = function () {
        router.approuter = new AppRouter();
        router.fileRouter = new FileRouter();
        router.settingRouter = new SettingRouter();
        Backbone.history.start(); // 当所有的 路由 创建并设置完毕，调用 Backbone.history.start() 开始监控 hashchange 事件并分配路由
    };
});
