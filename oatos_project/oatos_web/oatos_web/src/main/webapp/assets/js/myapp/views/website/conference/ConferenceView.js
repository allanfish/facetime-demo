/**
  * 视频会议VIEW
  */
define(function(require, exports, module) {

  tpl.conferenceView = require('tplurl-website/conference/ConferenceView.tpl');
  tpl.conferenceItemView = require('tplurl-website/conference/ConferenceItemView.tpl');

  var ConferenceItemView = Backbone.View.extend({
    tagName: 'li', 
    _modelBinder: undefined, 
    conferenceInfoView: undefined,

    events: {
      "mouseover ": "showDel",
      "mouseout ": "hideDel",
      "mouseover .showInfo": "showInfo"
    },

    initialize: function() {
      tplpre.conferenceItemView = Handlebars.compile(tpl.conferenceItemView);
      this._modelBinder = new Backbone.ModelBinder();
      this.render();
    },

    render: function() {
      this.$el.html(tplpre.conferenceItemView);
      this._modelBinder.bind(this.model, this.el);
      this.conferenceInfoView = new ConferenceInfoView({model: this.model});
      this.$el.popover({
        'trigger': 'hover', 
        'placement': 'left',
        'html': true,
        'content': this.conferenceInfoView.el
      });
      return this;
    },

    showInfo: function() {
      this.$el.find(".showInfo").popover('show');
    },

    showDel: function() {
      this.$el.find(".del").show();
      this.$el.popover('show');
    },

    hideDel: function() {
      this.$el.find(".del").hide();
    },

    close: function(){
      this._modelBinder.unbind();
      this.off();
      this.undelegateEvents();
      this.remove();
    }
  }); 

  window.ConferenceView = Backbone.View.extend({
    tagName: 'div',
    id: 'conference-view',
    collectionBinder: undefined,

    events: {
      "click .operate-panel .btn-group ul li a:contains('即时会议')": "showAddNowConference",
      "click .add_now_conference .operate-panel .addMember": "AddMember",
      "click .add_now_conference .operate-panel button:contains('取消')": "closeAddNowConference",
      "click .add_now_conference .operate-panel button:contains('确定')": "AddNowConference",
      "click .operate-panel .btn-group ul li a:contains('预约会议')": "showAddPlanConference",
      "click .add_plan_conference .operate-panel button:contains('取消')": "closeAddPlanConference",
      "click .add_plan_conference .operate-panel button:contains('确定')": "AddPlanConference"
    },

    initialize: function() {
      tplpre.conferenceView = Handlebars.compile(tpl.conferenceView);
      var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewCreator);
      collectionBinder = new Backbone.CollectionBinder(elManagerFactory);
      this.render();
    },

    render: function() {
      this.$el.html(tplpre.conferenceView(this.collection.toJSON()));
      collectionBinder.bind(this.collection, this.$el.find('#conference-list'));
      //var addMemberTree = view.departmentUserTree;
      var addMemberTree = new DepartmentUserTree({
          collection: collection.departmentList
      });
      addMemberTree.showCheckbox(true);
      this.$el.find(".add_now_conference .addMember").dialog({
          title:"添加与会者",
          content: addMemberTree.el,
          max: false,
          min: false,
          width: 200,
          height: 320,
          button: [
              {
                  name: '添加'
              },
              {
                  name: '取消',
                  callback: function() {
                      addMemberTree.reset();
                  }
              }
          ]
      });
      this.userSearch();
      return this;
    },

    userSearch: function () {
        this.$el.find("#addMember").select2({
            allowClear: true,
            minimumInputLength: 2,
            multiple: true,
            data: collection.userList.map(function (user) {
                return {id: user.get("userId"), text: user.get("userName")};
            })
        });
    },

    showAddNowConference: function() {
      this.$el.find(".conference_view .operate-panel .btn-group").removeClass("open");
      this.$el.find(".add_now_conference").removeClass("conference_hide");
      this.$el.find(".conference_view").addClass("conference_hide");
      return false;
    },

    AddMember: function() {

    },

    closeAddNowConference: function() {
      this.$el.find(".conference_view").removeClass("conference_hide");
      this.$el.find(".add_now_conference").addClass("conference_hide");
      return false;
    },

    AddNowConference: function() {
      var memberDTO = new ConferenceMemberDTO({
        userId: 98893,
        userName: "t2",
        userIcon: "images/icon/woman.png",
        inviteUserId: cache.userId,
        inviteUserName: cache.username,
        status: constants.ConferenceMemberStatus.Invited
      });
      var memberListDTO = new MemberListDTO();
      memberListDTO.add(memberDTO);
      var conDTO = new ConferenceDTO({
        theme: this.$el.find(".add_now_conference .operate-panel input:eq(0)").val(),
        startTime: Date.parse(new Date()),
        length: 30,
        enterpriseId: cache.entId,
        record: this.$el.find(".add_now_conference .operate-panel input:eq(2)").is(':checked'),
        members: memberListDTO,
        docList: [],
        type: constants.ConferenceType.immediate,
        createrId: cache.userId,
        createrName: cache.username,
        status: constants.ConferenceStatus.Held
      });
      resturl.createConference(conDTO, function(data){
          log.debug("createConference:" , data);
      }).start();
      log.debug("ConferenceView AddNowConference conDTO:" , conDTO.toJSON());
      return false;
    },

    showAddPlanConference: function() {
      this.$el.find(".conference_view .operate-panel .btn-group").removeClass("open");
      this.$el.find(".add_plan_conference").removeClass("conference_hide");
      this.$el.find(".conference_view").addClass("conference_hide");
      return false;
    },

    closeAddPlanConference: function() {
      this.$el.find(".conference_view").removeClass("conference_hide");
      this.$el.find(".add_plan_conference").addClass("conference_hide");
      return false;
    },

    AddPlanConference: function() {
      var conDTO = new ConferenceDTO({
        theme: this.$el.find(".add_plan_conference .operate-panel input:eq(0)").val(),
        startTime: new Date(),
        length: 30,
        record: this.$el.find(".add_plan_conference .operate-panel input:eq(2)").is(':checked')
      });
      log.debug("ConferenceView AddPlanConference conDTO:" , conDTO.toJSON());
      return false;
    },

    close: function(){
      this.collectionBinder.unbind();
      this.off();
      this.undelegateEvents();
      this.remove();
    },

    viewCreator : function(model){
      return new ConferenceItemView({model: model});
    }
  });

});