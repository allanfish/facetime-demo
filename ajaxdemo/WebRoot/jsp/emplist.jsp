<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="../js/prototype1.6.js"></script>
		<script type="text/javascript">
              function deptList(deptId){
                var url="/ajaxTwo/deptlist";
             //   deptTag.options.length=0;//清空下拉列表
               new Ajax.Request(
                    url,
                    {
                      method:"post",
                      onSuccess:function(xmlHttp){
                        var doc=xmlHttp.responseXML;
                        var depts=doc.getElementsByTagName("dept");
                        $(deptId).options.add(new Option("---请选择部门---","0"));
                        for(var i=0;i<depts.length;i++){
                            var dept=depts[i];
                            var ids=dept.getElementsByTagName("id");
                            var names=dept.getElementsByTagName("name");
                            var id=ids[0].firstChild.data;
                            var name=names[0].firstChild.data;
                             $(deptId).options.add(new Option(name,id));
                        }
                     }
                    }
               )
              }
              
              function empList(deptId,empId){
              $(empId).options.length=0;
              $(empId).options.add(new Option("本部门员工","0"));
               var url="/ajaxTwo/emplist";
               var deptno=$F(deptId);
               new Ajax.Request(
                url,
                {
                 method:"post",
                 parameters:"id="+deptno,
                 onSuccess:function(request){
                      var doc=request.responseXML;
                      var emps=doc.getElementsByTagName("emp");
                      for(var i=0;i<emps.length;i++){
                        var emp=emps[i];
                        var ids=emp.getElementsByTagName("id");
                        var id=ids[0].firstChild.data;
                        var names=emp.getElementsByTagName("name");
                        var name=names[0].firstChild.data;
                        $(empId).options.add(new Option(name,id));                        
                      }
                 }
                }
               )
              }
      </script>
	</head>

	<body onload="deptList('dept');">
		部门：
		<select id="dept" onchange="empList('dept','emp');"></select>
		员工：
		<select id="emp"></select>
	</body>
</html>
