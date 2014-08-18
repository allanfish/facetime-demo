<div class="accordion" id="usu-department-accordion-{{_parentId}}">
	{{#each departmentUserList}}
	<div class="accordion-group" departmentId="{{department.departmentId}}">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#usu-department-accordion-{{_parentId}}" href="#usu_department_{{department.departmentId}}"> {{department.name}} </a>
		</div>
		<div id="usu_department_{{department.departmentId}}" class="accordion-body in collapse">
			<div class="accordion-inner top-accordion-inner">
				<ul class='userList'>
				{{#each userList}}
					{{#if checked}}
					{{else}}
					<li>
						<input type="checkbox" name="usual_contact_id" class="contact-add" value="{{userId}}" />
						<img src="/os/assets/website/img/defaultAvatar64man.png" alt="">
						<label for="">{{userName}}</label>
					</li>
					{{/if}}
				{{/each}}
				</ul>  
				<div parentDepartmentId='{{department.departmentId}}' class="inner-department"></div>
			</div>
		</div>
	</div>
	{{/each}}
</div>