<div id="fileTopView">
    <div class='file-operation'>
        <div style="display: inline-block" id="fileupload">
            <button class="btn btn-primary btn-medium">
                <span id="btn-upload">上传</span>
            </button>
        </div>
        <div class="btn-group">
            <a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:;">新建
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li><a href="javascript:;" class="create-folder">新建文件夹</a></li>
                <li><a href="javascript:;" class="create-file">新建文件</a></li>
            </ul>
        </div>
        <form action="" class="form-search pull-right">
            <input type="text" class="input-medium search-query">
            <div class="btn-group ">
                <button class="btn btn-success">查询</button>
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="javascript:;">当前文件夹</a></li>
                    <li><a href="javascript:;">全部文件夹</a></li>
                </ul>
            </div>
        </form>
    </div>
    <ul class="breadcrumb"></ul>
    <!-- <select name="" id="">
        <option value=""查看全部></option>
        <option value="">文件名</option>
        <option value="">文件大小</option>
        <option value="">更新时间</option>
    </select> -->
</div>
<!-- 文件列表 -->
<div id="fileTable">
	<ul id="ent-file-list" class="file-table">
		<li class='entFileItem file-table-header'>
			<span class='checkbox-sprite false'>
				<input type="checkbox" style='visibility:hidden'>
			</span>
			<ul class="operate-btn">
				<li><a href="javascript:;" class="btn btn-link"><i class="icon-star"></i> 下载</a></li>
				<li><a href="javascript:;" class="btn btn-link hide"><i class="icon-star"></i> 关注</a></li>
				<li><a href="javascript:;" class="btn btn-link"><i class="icon-star"></i> 分享</a></li>
				<li><a href="javascript:;" class="btn btn-link"><i class="icon-star"></i> 收藏</a></li>
				<li><a href="javascript:;" class="btn btn-link"><i class="icon-star"></i> 删除</a></li>
				<li>
					<div class="btn-group">
						<button  class="btn btn-link dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></button>
						<ul class="dropdown-menu">
							<li><a href="javascript:;">移动</a></li>
							<li><a href="javascript:;">复制到</a></li>
							<li><a href="javascript:;">重命名</a></li>
						</ul>
					</div>
				</li>
			</ul>
		</li>
		<!-- 文件列表内容 -->
	</ul>
</div>
	<!-- /文件列表 -->
