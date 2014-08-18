define(function (require, exports, module) {


    tpl.userLayout = require('tplurl-website/user/UserLayout.tpl');
    require('./DepartmentTreeView');
    require('./UsualContactTreeView');
    require('./AddUsualContactTreeView');

    window.UserLayout = Backbone.View.extend({
        tagName: 'div',
        className: 'user-layout',
        id: 'user-layout',
        events: {
            "click .dealContact .icon-remove": "showDel",
            "click .dealContact .icon-plus": "showAdd",
            "click .btn.usual-contact-close-del": "closeDel",
            "click .btn.usual-contact-del-btn": "delUsualContact",
            "click .btn.usual-contact-close-add": "closeAdd",
            "click .btn.usual-contact-add-btn": "addUsualContact"
        },

        initialize: function () {
            this.listenTo(collection.departmentList, 'department: init', this.initDepartmentUser);
            tplpre.userLayout = Handlebars.compile(tpl.userLayout);
            this.render();
        },

        render: function () {
            this.$el.html(tplpre.userLayout);

            view.departmentTreeView = new DepartmentTreeView({collection: collection.departmentList});
            this.$el.find('#departmentTreeWrap').append(view.departmentTreeView.el);
            this.showUsualContact(true);
            log.debug('[render] UserLayout OK');
            this.showCard();
            return this;
        },

        showDel: function () {
            this.$el.find("#usual_colleague_view .contact-del").removeClass("hide");
            this.$el.find("#usual_colleague_view .operate-panel").addClass("hide");
        },

        closeDel: function () {
            this.$el.find("#usual_colleague_view .contact-del").addClass("hide");
            this.$el.find("#usual_colleague_view .operate-panel").removeClass("hide");
        },

        delUsualContact: function () {
            var arrChecked = [];
            $(".usual-contact-list ul [name='usual_contact_id']:checked").each(function () {
                arrChecked.push(parseInt($(this).val()));
            });
            if (arrChecked.length != 0) {
                var async = eval(Wind.compile("async", function (_this) {
                    var delResult = $await(resturl.deleteUsualContact({
                        userId: cache.userId,
                        usualContactUserList: arrChecked
                    }));
                    log.debug("delUsualContact Result:", delResult);
                    if (delResult == "OK") {
                        for (var i = 0; i < arrChecked.length; i++) {
                            collection.usualContactList.remove(collection.usualContactList.where({userId: arrChecked[i]}));
                        }
                        _this.closeDel();
                        _this.showUsualContact(true);
                    } else {
                        alert(delResult);
                    }
                }));
                async(this).start();
            }
        },

        showAdd: function () {
            this.$el.find('#usualContactTreeWrap').empty();
            view.addUsualContactTreeView = new AddUsualContactTreeView({collection: collection.departmentList});
            /*view.addUsualContactTreeView = new DepartmentUserTree({
             collection: collection.departmentList
             });
             view.addUsualContactTreeView.showCheckbox(true);*/
            this.$el.find('#usualContactTreeWrap').append(view.addUsualContactTreeView.el);
            this.$el.find("#usual_colleague_view .contact-add").removeClass("hide");
            this.$el.find("#usual_colleague_view .operate-panel").addClass("hide");
        },

        closeAdd: function () {
            this.showUsualContact(false);
            this.closeAddBtn();
        },
        closeAddBtn: function () {
            this.$el.find("#usual_colleague_view .contact-add").addClass("hide");
            this.$el.find("#usual_colleague_view .operate-panel").removeClass("hide");
        },

        addUsualContact: function () {
            var arrChecked = [];
            $("#usualContactTreeWrap [name='usual_contact_id']:checked").each(function () {
                arrChecked.push(parseInt($(this).val()));
            });
            if (arrChecked.length != 0) {
                var async = eval(Wind.compile("async", function (_this) {
                    var addResult = $await(resturl.setUsualContact({
                        userId: cache.userId,
                        usualContactUserList: arrChecked
                    }));
                    log.debug("setUsualContact Result:", addResult);
                    if (addResult == "OK") {
                        for (var i = 0; i < arrChecked.length; i++) {
                            collection.usualContactList.add(collection.userList.where({userId: arrChecked[i]}));
                        }
                        _this.closeAddBtn();
                        _this.showUsualContact(true);
                    } else {
                        alert(addResult);
                    }
                }));
                async(this).start();
            }
        },

        /**
         * 在企业文件夹树中添加子节点
         */
        initDepartmentUser: function () {
            log.debug('[event] department: init');
        },

        showUsualContact: function (change) {
            this.$el.find('#usualContactTreeWrap').empty();
            if (change) {
                view.usualContactTreeView = new UsualContactTreeView({collection: collection.usualContactList});
            }
            this.$el.find('#usualContactTreeWrap').append(view.usualContactTreeView.el);
        },

        showCard: function () {
            $('.userList li').popover({
                html: true,
                placement: 'left',
                content: 'nameCard',
                trigger: 'hover'
            });
        }
    });
});
