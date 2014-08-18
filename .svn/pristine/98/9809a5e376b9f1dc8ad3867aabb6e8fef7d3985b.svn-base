/**
 * 设置密码视图
 */
define(function(require, exports, module) {

  tpl.resetPwdView = require('tplurl-website/setting/ResetPwdView.tpl');

  var passwordDTO = {};

  function encodePassword(id, oldPassword, newPassword) {

    if (newPassword == null) {
      var nonce = Security.randomCharString();
      var sha256 = Crypto.SHA256(oldPassword);

      var lastpassword = Security.codeDecode(nonce, Security.byteStringToHexString(sha256));
      var hashKey = Crypto.SHA256(id + sha256 + nonce);

      passwordDTO = {
        'id': id,
        'oldPasswrod': lastpassword,
        'nonce': nonce,
        'hashKey': hashKey
      };

    } else {
      var oldpwd = Crypto.SHA256(oldPassword);

      var nonce = Security.randomCharString();
      var sha256 = Crypto.SHA256(newPassword);
      var lastpassword = Security.codeDecode(nonce, Security.byteStringToHexString(sha256));
      var hashKey = Crypto.SHA256(id + sha256 + nonce);

      passwordDTO = {
        'id': id,
        'oldPasswrod': oldpwd,
        'nonce': nonce,
        'hashKey': hashKey,
        'newPassword': lastpassword
      };

    }
    //return dto;
  }

  function addTips(flag, tag, msg) {
    var group = tag.parents(".control-group");
    if (flag == "error") {
      group.addClass("error");
      group.removeClass("success");
    } else {
      group.removeClass("error");
      group.addClass("success");
    }
    group.find(".help-inline").empty();
    group.find(".controls").append("<span class=\"help-inline error-message\">" + msg + "</span>");

  }

  function checkPWD(oldPassword, newPassword, renewPassword) {

    if (oldPassword.length < 6 || oldPassword.length > 16) {
      addTips("error", $("#oldPassword"), "密码错误！");
      return false;
    } else {
      addTips("success", $("#oldPassword"), "ok！");
    }

    if (newPassword.length < 6 || newPassword.length > 16) {
      addTips("error", $("#newPassword"), "新密码长度必须在[6-16]之间！");
      return false;
    } else {
      addTips("success", $("#newPassword"), "ok！");
    }

    if (newPassword != renewPassword) {
      addTips("error", $("#renewPassword"), "两次输入密码不一致！");
      return false;
    }

    addTips("success", $("#newPassword"), "ok");
    addTips("success", $("#renewPassword"), "ok");

    return true;

  }

  window.resetPwdView = Backbone.View.extend({
    tagName: 'div',
    id: 'resetPwdView',
    _modelBinder: undefined,

    initialize: function() {
      this._modelBinder = new Backbone.ModelBinder();
      tplpre.resetPwdView = Handlebars.compile(tpl.resetPwdView);
      this.render();
    },

    render: function() {
      this.$el.html(tplpre.resetPwdView);
      this._modelBinder.bind(this.model, this.$el);
      return this;
    },

    close: function() {
      this._modelBinder.unbind();
      this.off();
      this.undelegateEvents();
      this.remove();
    },

    events: {
      "blur #oldPassword": "checkOldPassWord",
      "click #setting-pwd": "resetpwdSubmit",
      //  "blur #newPassword":"checkPWD",
      // "blur #renewPassword":"checkPWD",

    },

    checkOldPassWord: function(event) {

      var userId = model.currentUser.get("userId");

      var oldPassword = $("#oldPassword").val();
      passwordDTO = {};

      encodePassword(userId, oldPassword, null);

      log.debug("passwordDTO :", passwordDTO);


      eval(Wind.compile('async', function() {
        var checkResult = $await(resturl.checkPassword(passwordDTO));
        log.debug("checkResult :", checkResult);
        if ("OK" == checkResult) {

          addTips("success", $("#oldPassword"), "原始密码正确！");

        } else {
          addTips("error", $("#oldPassword"), "原始密码错误！");
          // alert("老密码错了");
        }

      }))().start();
    },



    resetpwdSubmit: function() {
      var userId = model.currentUser.get("userId");
      var entId = model.currentUser.get("enterpriseId");
      var userType = model.currentUser.get("userType");

      var oldPassword = $("#oldPassword").val();

      var newPassword = $("#newPassword").val();

      var renewPassword = $("#renewPassword").val();


      if (checkPWD(oldPassword, newPassword, renewPassword) == true) {

        //如果userType == 2 使用修改管理员密码，使用企业Id
        if (userType == 2) {

          encodePassword(entId, oldPassword, newPassword);
          log.debug("passwordDTO --->", passwordDTO);

          eval(Wind.compile('async', function() {
            var changeAdminPassword = $await(resturl.changeAdminPassword(passwordDTO));
            log.debug("changeAdminPassword :", changeAdminPassword);

            if ("OK" == changeAdminPassword) {
              //更换css
              this.$('.alert-error').hide();
              this.$('.alert-success').fadeIn();

            } else {
              this.$('.alert-success').hide();
              this.$('.alert-error').fadeIn();
            }

          }))().start();

          //否则使用 修改普通用户密码
        } else {
          encodePassword(userId, oldPassword, newPassword);

          eval(Wind.compile('async', function() {
            var changePassword = $await(resturl.changePassword(passwordDTO));
            log.debug("changePassword :", changePassword);
            log.debug("value --- :", changePassword == "OK");
            if ("OK" == changePassword) {
              //更换css
              // alert("您是普通，您的密码修改成功！");
              this.$('.alert-error').hide();
              this.$('.alert-success').fadeIn();


            } else {
              // alert("密码修改失败");
              this.$('.alert-success').hide();
              this.$('.alert-error').fadeIn();
            }

          }))().start();


        }
      }

    }

    /**end view*/
  });

});