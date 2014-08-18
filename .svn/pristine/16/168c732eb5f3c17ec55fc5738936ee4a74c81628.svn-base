<div >
  <ul class="breadcrumb">
   <li><a href="#user/setting" >设置</a><span class="divider">&gt;</span></li>
   <li class="active">个人信息设置</li>
 </ul>
 <form class="form-horizontal">
  <div  class='well well-small'>
    <div class="pull-left" >
      <img  style="width: 140px; height: 140px;" class="img-rounded" id="user-icon" >
    </div>
    <div class='offset2'>
      <div class="control-group">
       <label class='control-label'>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
       <div class="controls">
        {{userName}}
        </div>
     </div>
     <div class="control-group">
      <label class='control-label'>在线状态：</label>
      <div class="controls">
        <label for="">
          <select id="user-online">
            <option value="online">在线</option>
            <option value="offline">离线</option>
            <option value="corbet">隐身</option>
            <option value="busy">忙碌</option>
            <option value="leave">离开</option>
          </select>
        </label>
      </div>
    </div>
    <div class="control-group">
      <label class='control-label'>个性签名：</label>
      <div class="controls">
       <input type="text"  name="signature"  id="signature" data-error-style="inline"  >
       <span class="help-inline error-message"></span>
     </div>
   </div>
   <div class="control-group">
    <label class='control-label'>修改头像：</label>
    <div class="controls">
      <input type="file" >
      <input type="button" class="btn btn-primary take-photo" value="拍照"/>
    </div>
  </div>
</div>
<div class="clearfix"></div>

<div class='well well-small'>
  <fieldset>
    <legend>个人信息</legend>
    <div class="control-group">
      <label class='control-label'> 真实姓名：</label>
      <div class="controls">
        <input type="text" id="realName"  data-error-style="inline" name="realName" value="{{realName}}"/>
        <span class="help-inline error-message"></span>
      </div>
    </div>
    <div class="control-group">
     <label class='control-label'> 性别：</label>
     <div class="controls">
       <input type="radio" name="gender" id="mgender" value="m" />男
       <input type="radio" name="gender" id="fgender" value="f" />女
     </div>
   </div>
   <div class="control-group">
    <label class='control-label'> 生日：</label>
    <div class="controls">
      <input type="text" id="user-birthday" />
    </div>
  </div>

  <div class="control-group">
    <label class='control-label'> 年龄：</label>
    <div class="controls">
      {{age}}
    </div>
  </div>

  <div class="control-group">
   <label class='control-label'>爱好：</label>
   <div class="controls">
     <textarea rows="2" name="hobby">{{hobby}}</textarea>
   </div>
 </div>

</fieldset>
</div>

<div class='well well-small'>
  <fieldset>
    <legend>工作信息</legend>
    <div class="control-group">
      <label class="control-label" for="inputEmail">职位：</label>
      <div class="controls">
        <input type="text" id="jobTitle"  value="{{jobTitle}}"/>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="inputPassword">城市：</label>
      <div class="controls">
        <input type="text" id="city" name="city" value="{{city}}"/>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label" for="inputPassword">专业：</label>
      <div class="controls">
        <input type="text" id="major" name="major" value="{{major}}"/>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label" for="inputPassword">邮箱：</label>
      <div class="controls">
       <input type="text" id="registerMailAccount"  name="registerMailAccount" value="{{registerMailAccount}}"/>
     </div>
   </div>

   <div class="control-group">
    <label class="control-label" for="inputPassword">座机：</label>
    <div class="controls">
      <input type="text" id="inputPassword" placeholder="38000" />
    </div>
  </div>

  <div class="control-group">
    <label class="control-label" for="inputPassword">手机：</label>
    <div class="controls">
      <input type="text" id="phone" name="phone" value="{{phone}}"/>
    </div>
  </div>

</fieldset> 
</div>
<button type="submit" id="setting-userinfo" class="btn btn-large btn-primary"> 保存</button>
</div>
</form>

        <div style="display:none" class="alert alert-error offset1">
          <strong>糟糕!</strong>更新用户信息失败，服务器打瞌睡了！
        </div>

        <div style="display:none" class="alert alert-success offset1">
          <strong>恭喜!</strong> 更新用户信息成功！
        </div>

</div>