<div>
<ul class="breadcrumb">
	<li><a href="#user/setting" >设置</a><span class="divider">&gt;</span></li>
	<li class="active">修改密码</li>
</ul>
<div class="well well-small">
	<form class="form-horizontal">
  <div class="control-group">
    <label class="control-label" for="inputPassword">旧密码：</label>
    <div class="controls">
      <input type="password" id="oldPassword" data-error-style="inline" placeholder="当前使用的密码">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">新密码：</label>
    <div class="controls">
      <input type="password" id="newPassword" data-error-style="inline" placeholder="新的密码">
    </div>
  </div>
   <div class="control-group">
    <label class="control-label" for="inputPassword">确认密码</label>
    <div class="controls">
      <input type="password" id="renewPassword" data-error-style="inline" placeholder="重新输入新密码">
    </div>
  </div>
  <div class="control-group">
  	<div class="controls">
     <button type="submit" id="setting-pwd" class="btn btn-large btn-primary">保存</button>
    </div>
  </div>
</form>
	      <div style="display:none" class="alert alert-error offset1">
          <strong>糟糕!</strong> 修改密码失败，服务器打瞌睡了！
        </div>

        <div style="display:none" class="alert alert-success offset1">
          <strong>恭喜!</strong> 修改密码成功！
        </div>
</div>

</div>