<%@page pageEncoding="utf-8"%>
<html>
	<head>
		<script type="text/javascript" src="../js/prototype1.6.js"></script>
		<script type="text/javascript">
	     function jsonShow(){
	      new Ajax.Request(
	       "../json",
	       {
	        "method":"post",
	        "onSuccess":function(req){
	           var objs=req.responseText.evalJSON();
	            for(var i=0;i<objs.length;i++){
	               var obj=objs[i];
	               var tr=$("jtb").insertRow($("jtb").rows.length);
	               var tb_id=tr.insertCell(tr.cells.length);
	                tb_id.innerHTML=obj.id;
	               var tb_name=tr.insertCell(tr.cells.length);
	               tb_name.innerHTML=obj.name;
	             }
	          }
	        }
	     )
	   }
	  </script>
	</head>
	<body>
		<fieldset>
			<legend>
				使用JSON显示表单
			</legend>
			<input type="button" value="SHOW" onclick="jsonShow();" />
			<table id="jtb">
			</table>
		</fieldset>
	</body>
</html>
