<!-- 联系人 -->
<div class="accordion" id="colleague_view">
	<div class="accordion-heading custom-project-title">
		<span class="left"> 即时通讯 </span>
		<a class="right" href="#slide/close" class="btn btn-link"><i class="icon-remove" style="font-size:16px;"></i></a>
	</div>
	<div class="clear"></div>

	<div class="tabbable">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#tab1"><i class="icon-user"></i> <span>常用联系人</span></a></li>
			<li class=""><a data-toggle="tab" href="#tab2"><i class="icon-table"></i> <span>公司同事</span></a></li>
		</ul>

		<div class="tab-content">
			<div id="tab1" class="tab-pane active">
				<div id="usual_colleague_view">
					<div class="accordion-inner">
						<div class="operate-panel">
							<div class="input-append">
								<input class="input-small" type="text">
								<button class="btn" type="button">Search</button>
							</div>
							<div class="dealContact">
								<i class="icon-plus" title="添加常用联系人"></i>
								<i class="icon-remove" title="删除常用联系人"></i>
							</div>
						</div>
						<div id="usualContactTreeWrap"></div>
						<div class="contact-del hide">
							<button class="btn usual-contact-del-btn" type="button">确认</button>
							<button class="btn usual-contact-close-del" type="button">取消</button>
						</div>
						<div class="contact-add hide">
							<button class="btn usual-contact-add-btn" type="button">确认</button>
							<button class="btn usual-contact-close-add" type="button">取消</button>
						</div>
					</div>
				</div>
			</div>
			<div id="tab2" class="tab-pane">
				<div id="colleague_list_view">
					<div class="accordion-inner">
						<div class="input-append user-search">
							<input class="input-small"   type="text">
							<button class="btn" type="button">Search</button>
						</div>
						<div id="departmentTreeWrap"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- / 联系人 -->