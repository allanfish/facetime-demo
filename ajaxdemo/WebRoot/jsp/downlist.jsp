<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="../js/prototype1.6.js"></script>
		<script type="text/javascript">
		
		       //*select 下拉列表做部门员工级联查询显示
		      // *使用prototype组件开发，封装了XMLDOM和XMLHttpRequest
		      // *version2.0
		      
		      //*显示部门列表
		      function deptList(deptId){
		            deptId.options.add(new Option("--DEPT--","0"));
		           new Ajax.Request(
		           "../deptlist",
		           {
		            method:"post",
		            onSuccess: function(req){
		             var objs=req.responseText.evalJSON();
		             for(var i=0;i<objs.length;i++){
		              var obj=objs[i];
		              var name=obj.name;
		              var id=obj.id;
		              deptId.options.add(new Option(name,id));
		             } 
		          }
		       }  
		   )
	 }
	 
		// *根据部门号级联员工列表显示
		 function empList(deptId,empId){
		       //var deptno=$(deptId).selectedIndex;
		       //alert($(deptId).selectedIndex);
		       new Ajax.Request(
		             "../emplist",
		        {
		          method: "post",
		          parameters: {"id":$F(deptId)},
		          onSuccess: function(req){
		            empId.options.length=0;//clear items
		            empId.options.add(new Option("--EMP--","0"));
		            var objs=req.responseText.evalJSON();
		            for(var i=0;i<objs.length;i++){
		              var obj=objs[i];
		              var name=obj.name;
		              var id=obj.id;
		              empId.options.add(new Option(name,id));
		            }
		         }
		       }
		    )
		 }
		 
		 //*实现浏览器引擎功能:Explore Engine
		 function engine(engId){
		    new Ajax.Request(
		    "../engine",
		    {
		      method: "post",
		      parameters: {"name":$F(engId)},
		      onSuccess: function(req){
		        clearItems();
		        $("hideDiv").style.display="block";
		        $("show").style.display="none";
		        var objs=req.responseText.evalJSON();
		        for(var i=0;i<objs.length;i++){
		         var obj=objs[i];
		         var tr=$("jtb").insertRow($("jtb").rows.length);
		         var td_id=tr.insertCell(tr.cells.length);
		         td_id.innerHTML=obj.id;
		         var td_name=tr.insertCell(tr.cells.length);
		         td_name.innerHTML=obj.name;
		         td_name.onclick=function(){
		            $(engId).value=this.innerHTML;
		            $("hideDiv").style.display="none";
		         }
		        }
		      }
		    }
	     )
	 }
		 
		 //clear 表单项
		 function clearItems(){
		   for(var i=$("jtb").rows.length-1;i>=0;i--){
		            $("jtb").deleteRow(i);
		   }
		 }
		 
		 //显示单个员工信息
		 function empshow(){
		    new Ajax.Request(
		       "../engine",
		       {
		        method: "post",
		        parameters: { "name":$F("ctt")},
		        onSuccess: function(req){
		        $("show").style.display="block";
		            var objs=req.responseText.evalJSON();
		            for(var i=0;i< objs.length;i++){
		              var obj=objs[i];
		              $("show").innerHTML="id:"+obj.id+" name:"
		                      +obj.name+" deptno:"+obj.deptno;
		            }
		        }
		       }
		    )
		 }
		</script>
	</head>
	<body onload="deptList(deptId)">
		<fieldset>
			<legend>
				部门员工列表
			</legend>
			<p>
				<label>
					部门
				</label>
				<select id="deptId" onchange="empList('deptId',empId);"></select>
				<label>
					员工
				</label>
				<select id="empId"></select>
			</p>
		</fieldset>
		<hr />
		<fieldset>
			<legend>
				Google搜索引擎
			</legend>
			<p>
				<input type="text" name="content" id="ctt" onkeyup="engine('ctt');" />
				<input type="button" value="google" id="google"
					onclick="empshow();" />
			<div id="show"></div>
			</p>
			<p>
			<div id="hideDiv">
				<table id="jtb"></table>
			</div>
			</p>
		</fieldset>
	</body>
</html>
