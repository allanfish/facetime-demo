define(function (require, exports, module) {

    tpl.demoView = require('tplurl-website/DemoView.html');

    window.DemoView = Backbone.View.extend({

        tagName: 'div',
        id: 'demoView',
        className: 'demoView',

        initialize: function () {
            tplpre.demoView = Handlebars.compile(tpl.demoView);
            this.render();
        },

        render: function () {
            this.$el.html(tplpre.demoView);
            this.initUserSearch();
            return this;
        },

        initUserSearch: function () {
            this.$el.find("#useSearch").select2({
                allowClear: true,
                minimumInputLength: 2,
                multiple: true,
                data: collection.userList.map(function (user) {
                    return {id: user.get("userId"), text: user.get("userName")};
                })
            });
        },

        events: {
        }

    });

});
