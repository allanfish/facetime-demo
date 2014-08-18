<!-- 信息管理 -->
<div class="accordion" id="message_content_view">
     <div class="accordion-heading custom-project-title">
         <span class="left">消息管理</span>
         <a class="right" href="#message/close" class="btn btn-link"><i class="icon-remove" style="font-size:16px;"></i></a>
    </div>
    <div style="margin-bottom: 18px;" class="tabbable">
        <ul class="nav nav-tabs">
            <li class="active" val="file"><a data-toggle="tab" href="#tabFile" style="display: inline-block;" id="file_manage_opt">文件管理<span class="badge badge-important" id="new_file_msg_num" style="display: none;">0</span></a></li>
            <li class="" val="instance"><a data-toggle="tab" href="#tabInstance" style="display: inline-block;" id="instance_message_opt">即时<span class="badge badge-important" id="new_instance_msg_num" style="display: none;">0</span></a></li>
            <li class="" val="system"><a data-toggle="tab" href="#tabSystem" style="display: inline-block;" id="system_message_opt">系统<span class="badge badge-important" id="new_system_msg_num" style="display: none;">0</span></a></li>
        </ul>
        <div class="searchBox">
            <div class="input-append">
                <input type="text" class="input-small" name="keyword">
                <button type="button" class="btn search">Search</button>
                <button type="button" class="btn advanceSearch">ADS</button>
            </div>
            <div class="advanceBox" style="display:none;">
                <div class="advanceBoxHeader">
                    <!--<input type="text" class="input-small" value="" id="messageTimeStart"  data-date-format="yyyy-mm-dd" value="">至
                    <input type="text" class="input-small" value="" id="messageTimeEnd"  data-date-format="yyyy-mm-dd" value="">-->
                    <span class="input-append date datepicker" data-date="0d" data-date-format="yyyy-mm-dd" data-provide="datepicker">
                        <input style="width: 75px;" size="16" type="text" value="" name="messageTimeStart" readonly><span class="add-on"><i class="icon-th"></i></span>
                    </span>至
                    <span class="input-append date datepicker" data-date="0d" data-date-format="yyyy-mm-dd" data-provide="datepicker">
                        <input style="width: 75px;" size="16" type="text" value="" name="messageTimeEnd" readonly><span class="add-on"><i class="icon-th"></i></span>
                    </span>
                </div>
                <div class="advanceBoxContent">
                    <input type="checkbox" name="messageType[]" value="file">文件
                    <input type="checkbox" name="messageType[]" value="image">图片
                    <input type="checkbox" name="messageType[]" value="video">视频
                    <input type="checkbox" name="messageType[]" value="audio">音频
                    <input type="checkbox" name="messageType[]" value="other">其他
                </div>
                <div class="advanceBoxFooter"><a href="javascript:;" style="float: right;" class="advance">高级搜索</a></span></div>

            </div>
        </div>
        <div style="padding-bottom: 9px; border-bottom: 1px solid #ddd;" class="tab-content">
            <div id="tabFile" class="tab-pane active">
                <div class="nodata">暂时还没有文件消息</div>
                <ul id="fileMessageContent"></ul>
            </div>
            <div id="tabInstance" class="tab-pane">
                <div class="nodata">暂时还没有即时通讯消息</div>
                <ul id="instanceMessageContent"></ul>
            </div>
            <div id="tabSystem" class="tab-pane">
                <div class="nodata">暂时还没有系统消息</div>
                <ul id="systemMessageContent"></ul>
            </div>
        </div>
    </div>
</div>
<!-- / 联系人 -->