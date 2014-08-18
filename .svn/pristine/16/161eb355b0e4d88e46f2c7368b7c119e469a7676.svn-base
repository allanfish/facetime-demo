define(function(require, exports, module) {

    tpl.userView = require('tplurl-website/UserView.tpl');

    window.userView = Backbone.View.extend({

        initialize : function() {
            tplpre.userView = Handlebars.compile( tpl.userView);
            this.render();
        },

        render : function() {
            $(this.el).html(tplpre.userView);
            return this;
        }
    });

});

