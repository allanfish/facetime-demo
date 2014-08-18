$().ready(function() {
  $("#showReg").click(function() {
    $(".log-cont").css("display","none");
    $(".reg-cont").css("display","block");
    return false;
  });
  $("#showLog").click(function() {
    $(".reg-cont").css("display","none");
    $(".log-cont").css("display","block");
    return false;
  });

  function ClientToken(token) {
    var args = token.split('@');
    if (args.length !== 2) {
      throw 'invalid token';
    }
    this.userId = parseInt(args[0]);
    this.userToken = args[1];

    if (isNaN(this.userId)) {
      throw 'invalid token';
    }
  }

  /**
   * 登陆
   */
  var logEntName, logAccount, logPassword, useHttps, loginBtn, clientId, logMessage;

  logEntName = $('#logEntName');
  logAccount = $('#logAccount');
  logPassword = $('#logPassword');
  useHttps = $('#httpsCheck');
  loginBtn = $('#loginBtn');
  clientId = util.guid();
  logMessage = $('#errorTip');

  $("#logForm").validate({
    rules: {
      logEntName: "required",
      logAccount: "required",
      logPassword: "required"
    },
    messages: {
      logEntName: "",
      logAccount: "",
      logPassword: ""
    },
    errorPlacement: function(error, element) {
      $("#errorTip").html("");
      error.appendTo($("#errorTip"));
    },
    invalidHandler: function() {
      return false;
    },
    submitHandler: function(form) {
      var loginDTO = getLoginDto()
      onLogin(loginDTO);
      return false;
    }
  });

  function getLoginDto() {
    var loginDTO = {
      account : logAccount.val(),
      enterpriseName : logEntName.val(),
      password : logPassword.val(),
      agent : util.getAgent(),
      clientId : clientId
    };
    return loginDTO;
  }

  function onLogin(Dto) {
    setInputEnable(false);
    // setup dto
    var loginDTO = Dto;
    
    saveFieldInCookie(loginDTO);

    securityLoginDTO(loginDTO);
    
    showMsg('');

    var p = location.pathname;
    p = p.substring(0, p.lastIndexOf('/')) + '/flexService';
    $.ajax({
      url: p,
      type: 'post',
      headers: {
        'serviceType' : '/pub/ent/enterpriseUserLogin',
        'UT': 'UT',
        'userId': 0,
        'entId': 0,
        'Content-Type': 'text/plain; charset=UTF-8'
      },
      data: JSON.stringify(loginDTO),
      dataType: 'html',
      success: onServerResponse,
      fail : function() {
        showMsg("抱歉！系统正忙，请稍后再试!");
      },
      complete : function() {
        setInputEnable(true);
      }
    });
  }
  function setInputEnable(enable) {
    logEntName.attr("disabled", !enable);
    logAccount.attr("disabled", !enable);
    logPassword.attr("disabled", !enable);
    loginBtn.attr("disabled", !enable);
  }

  function saveFieldInCookie(dto) {
    $.cookie('en', dto.enterpriseName);
    $.cookie('ua', dto.account);
  }

  function securityLoginDTO(dto) {
    dto.nonce = Security.randomCharString();
    dto.password = Crypto.SHA256(dto.password);
    dto.hashKey = Crypto.SHA256(dto.account + dto.password + dto.nonce);

    dto.password = Security.codeDecode(dto.nonce, Security.byteStringToHexString(dto.password));

    return dto;
  }
  function onServerResponse(data) {
    try {
      var clientToken = new ClientToken(data);
      $.cookie('ut', data);
      $.cookie('si', true);

      goSystem();
    } catch (e) {
      switch (data) {
      case 'errorWrongPWD':
        showMsg("用户名或密码错误");
        break;
      case 'errorWrongAccount':
        showMsg("用户名或密码错误");
        break;
      case 'errorUserLocked':
        showMsg("此用户已被锁定，不能登录");
        break;
      case 'errorAuditFail':
        showMsg("该企业已经停止服务");
        break;
      default:
        showMsg("抱歉！系统正忙，请稍后再试。");
        break;
      }
    }
  }
  function showMsg(msg) {
    logMessage.html(msg);
  }

  function goSystem() {
    var url = (useHttps.is(':checked') ? 'https:'
        : location.protocol)
        + '//' + location.host;

    var p = location.pathname;
    p = p.substring(0, p.lastIndexOf('/'))
        + '/index.html';

    url += p;
    url += util.setUrlEncodedKey('clientId', clientId,
        location.search);

    location.assign(url);
  }

  /**
   * 注册
   */
  var entName, adminAccount, password, confirmPassword, adminName, email, phoneNumber, VerCode, agreeCheck, regBtn;

  entName = $('#entName');
  adminAccount = $('#adminAccount');
  password = $('#password');
  confirmPassword = $('#confirmPassword');
  adminName = $('#adminName');
  email = $('#email');
  phoneNumber = $('#phoneNumber');
  VerCode = $('#VerCode');
  agreeCheck = $('#agreeCheck');
  regBtn = $('#regBtn');

  var firstInEntName = true;
  var firstInVerCode = true;
  var NameLenRight = false;

  $("#entName").blur(function() {
    if (firstInEntName) {
      checkNameLen();
      firstInEntName = false;
    }
  });
  $("#entName").keyup(function(){
    if (!firstInEntName) {
      checkNameLen();
    }
  });
  function checkNameLen() {
    if ($("#entName").val() == "" || $("#entName").val() == null) {
      $("#entName").next().html("<label class='error'>请输入企业名称</label>");
      $("#entName").next().next().html("");
      NameLenRight = false;
    } else if ($("#entName").val().length < 2) {
      $("#entName").next().html("<label class='error'>企业名称不得少于两个字符</label>");
      $("#entName").next().next().html("");
      NameLenRight = false;
    } else {
      $("#entName").next().html("");
      $("#entName").next().next().html("<label id='checkName' class='right-tip'>检查</label>");
      NameLenRight = true;
    }
  }
  $("#checkName").live("click",function() {
    var p = location.pathname;
    p = p.substring(0, p.lastIndexOf('/')) + '/flexService';

    $.ajax({
      url : p,
      type : 'post',
      headers : {
        'serviceType' : '/pub/ent/existEnterprise',
        'UT' : 'UT',
        'userId' : 0,
        'entId' : 0,
        'Content-Type' : 'text/plain; charset=UTF-8'
      },
      data : $("#entName").val(),
      dataType : 'html',
      fail : function() {
        $(this).next().html("<label class='name-error'>抱歉！系统正忙，请稍后再试。</label>");
      },
      success : function(data) {
        if (data == false || data == "false") {
          $("#checkName").html("账号可以使用");
        } else {
          $("#entName").next().html("<label class='name-error'>企业名称已经被注册</label>");
          $("#entName").next().next().html("");
        }
      }
    });
  });
  $(".code-image").next().click(function(){
    changeImg();
  });
  function changeImg(){
    var imgSrc = $(".code-image");
    var src = imgSrc.attr("src");
    imgSrc.attr("src",chgUrl(src));
  }
  function chgUrl(url){
    url = url.substring(0,25);
    url = url + "?" + Math.random();
    return url;
  }

  $("#VerCode").blur(function() {
    if (firstInVerCode) {
      checkVerificationCode($(this).val());
      firstInVerCode = false;
    }
  });
  $("#VerCode").keyup(function(){
    if (!firstInVerCode) {
      checkVerificationCode($(this).val());
    }
  });
  function checkVerificationCode(code) {
    var p = location.pathname;
    p = p.substring(0, p.lastIndexOf('/')) + '/servlet/wordVerify';

    $.ajax({
      url : p,
      type : 'post',
      data : "code=" + $("#VerCode").val(),
      dataType : 'html',
      fail : function() {
        $("#VerCode").next().next().next().html("<label class='name-error'>抱歉！系统正忙，请稍后再试。</label>");
      },
      success : function(data) {
        data = $.trim(data);
        if (data == "true") {
          $("#VerCode").next().next().next().html("<label class='right-tip'>验证码正确</label>");
        } else {
          $("#VerCode").next().next().next().html("<label class='name-error'>验证码错误</label>");
        }
      }
    });
  }

  $("#regForm").validate({
    rules: {
      entName:  {
        required: true,
        minlength: 2,
      },
      adminAccount: {
        required: true,
        minlength: 2
      },
      password: {
        required: true,
        minlength: 6,
        maxlength: 20
      },
      confirmPassword: {
        required: true,
        minlength: 6,
        equalTo: "#password"
      },
      adminName: "required",
      email: {
        required: true,
        email: true
      },
      phoneNumber: {
        required: true,
        digits: true,
        minlength: 6,
        maxlength: 20
      },
      agreeCheck: "required"
    },
    messages: {
      entName: {
        required: "请输入企业名称",
        minlength: "企业名称不得少于两个字符"
      },
      adminAccount: {
        required: "请输入管理员账号",
        minlength: "管理员账号不得少于两个字符"
      },
      password: {
        required: "请输入密码",
        minlength: "密码需要由6-20个字符组成",
        maxlength: "密码需要由6-20个字符组成"
      },
      confirmPassword: {
        required: "请输入密码",
        minlength: "密码需要由6-20个字符组成",
        maxlength: "密码需要由6-20个字符组成",
        equalTo: "两次密码不一致"
      },
      adminName: "请输入管理员姓名",
      email: "请填写正确格式的电子邮箱",
      phoneNumber: "建议您输入真实的手机号码",
      agreeCheck: "同意条款才能注册"
    },
    errorPlacement: function(error, element) {
      if (element.is(":checkbox")) {
        element.next().next().html("");
        error.appendTo(element.next().next());
      } else {
        element.next().html("");
        error.appendTo(element.next());
      }
    },
    invalidHandler: function() {
      return false;
    },
    submitHandler: function(form) {
      checkNameLen();
      if (NameLenRight == true) {
        var p = location.pathname;
        p = p.substring(0, p.lastIndexOf('/')) + '/servlet/wordVerify';

        $.ajax({
          url : p,
          type : 'post',
          data : "code=" + $("#VerCode").val(),
          dataType : 'html',
          fail : function() {
            $("#VerCode").next().next().next().html("<label class='name-error'>抱歉！系统正忙，请稍后再试。</label>");
          },
          success : function(data) {
            data = $.trim(data);
            if (data == "true") {
              $("#VerCode").next().next().next().html("<label class='right-tip'>验证码正确</label>");
              onRegister();
            } else {
              $("#VerCode").next().next().next().html("<label class='name-error'>验证码错误</label>");
            }
          }
        });
      }
      return false;
    }
  });
  
  function onRegister() {
    setRegInputEnable(false);
    // setup dto
    var enterpriseDTO = {
      enterpriseName : entName.val(),
      adminName : adminAccount.val(),
      adminPassword : password.val(),
      mail : email.val(),
      mobile : phoneNumber.val(),
      contact : adminName.val(),
      address : '',
      agent : util.getAgent()
    };
    
    //saveFieldInCookie(enterpriseDTO);

    securityRegisterDTO(enterpriseDTO);

    var p = location.pathname;
    p = p.substring(0, p.lastIndexOf('/')) + '/flexService';

    $.ajax({
      url : p,
      type : 'post',
      headers : {
        'serviceType' : '/pub/ent/admin/registerEnterprise',
        'UT' : 'UT',
        'userId' : 0,
        'entId' : 0,
        'Content-Type' : 'text/plain; charset=UTF-8'
      },
      data : JSON.stringify(enterpriseDTO),
      dataType : 'html',
      success : onRegServerResponse,
      fail : function() {
        alert("抱歉！系统正忙，请稍后再试。");
      },
      complete : function() {
        setRegInputEnable(true);
      }
    });
  }
  function setRegInputEnable(enable) {
    entName.attr("disabled", !enable);
    adminAccount.attr("disabled", !enable);
    password.attr("disabled", !enable);
    confirmPassword.attr("disabled", !enable);
    adminName.attr("disabled", !enable);
    email.attr("disabled", !enable);
    phoneNumber.attr("disabled", !enable);
    VerCode.attr("disabled", !enable);
    agreeCheck.attr("disabled", !enable);
    regBtn.attr("disabled", !enable);
  }
  function securityRegisterDTO(dto) {
    dto.nonce = Security.randomCharString();
    dto.adminPassword = Crypto.SHA256(dto.adminPassword);
    dto.hashKey = Crypto.SHA256(dto.enterpriseName + dto.adminPassword + dto.nonce);

    dto.adminPassword = Security.codeDecode(dto.nonce, Security.byteStringToHexString(dto.adminPassword));

    return dto;
  }
  function onRegServerResponse(data) {
    if(data == 'OK') {
      var loginDTO = {
        account : adminAccount.val(),
        enterpriseName : entName.val(),
        password : password.val(),
        agent : util.getAgent(),
        clientId : clientId
      };
      onLogin(loginDTO);
    } else if(data == 'errorEnterpriseAlreadyExist') {
      $("#entName").next().html("<label class='name-error'>企业名称已经被注册</label>");
    } else {
      alert('系统繁忙，稍后再试。');
    }
  }
});