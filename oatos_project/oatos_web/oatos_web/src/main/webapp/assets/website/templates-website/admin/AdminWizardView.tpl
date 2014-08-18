<!-- WIZARD -->
<div id="MyWizard" class="wizard">
	<ul class="steps">
		<li data-target="#step1" class="active"><span class="badge badge-info">1</span>企业信息<span class="chevron"></span></li>
		<li data-target="#step2"><span class="badge">2</span>新建部门<span class="chevron"></span></li>
		<li data-target="#step3"><span class="badge">3</span>新增成员<span class="chevron"></span></li>
		<li data-target="#step4"><span class="badge">4</span>网盘目录<span class="chevron"></span></li>
		<li data-target="#step5"><span class="badge">5</span>角色权限<span class="chevron"></span></li>
		<li data-target="#step5"><span class="badge">6</span>用户角色<span class="chevron"></span></li>
	</ul>
	<div class="actions">
		<button class="btn btn-mini btn-prev"> <i class="icon-arrow-left"></i>Prev</button>
		<button class="btn btn-mini btn-next" data-last="Finish">Next<i class="icon-arrow-right"></i></button>
	</div>
</div>
<div class="step-content">
	<div class="step-pane active" id="step1"></div>
	<div class="step-pane" id="step2"></div>
	<div class="step-pane" id="step3"></div>
	<div class="step-pane" id="step4"></div>
	<div class="step-pane" id="step5"></div>
</div>

<input type="button" class="btn " id="btnWizardPrev" value="prev">
<input type="button" class="btn " id="btnWizardNext" value="next">
<input type="button" class="btn " id="btnWizardStep" value="current step">
