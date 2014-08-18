/**
  * 后台管理左边导航VIEW
  */
  define(function(require, exports, module) {

  	tpl.adminWizardView = require('tplurl-website/admin/AdminWizardView.tpl');

  	window.AdminWizardView = Backbone.View.extend({
  		tagName: 'div', 
  		id: 'adminWizardView',
      className: 'fuelux',
      entInfoView: undefined,

      initialize: function() {
       tplpre.adminWizardView = Handlebars.compile(tpl.adminWizardView);
       this.render();
     },

     render: function() {
       this.$el.html(tplpre.adminWizardView);

       this.entInfoView = new EntInfoView({model: model.currentEnterprise});
       this.$el.find("#step1").append(this.entInfoView.el);
       return this;
     },

     events: {
      'click #btnWizardPrev': 'wizardPrev',
      'click #btnWizardNext': 'wizardNext',
      'click #btnWizardStep': 'currentStep'
    }, 

    wizardPrev: function(){
      $('#MyWizard').wizard('previous');
    }, 

    wizardNext: function() {
      $('#MyWizard').wizard('next','foo');
    },

    currentStep: function() {
      var item = $('#MyWizard').wizard('selectedItem');
      console.log(item.step);
    }
  /** end view*/});

  });