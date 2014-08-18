<%@page pageEncoding="utf-8"%>
<html>
	<head>
		<script type="text/javascript" src="../js/prototype1.6.js"></script>
		<script type="text/javascript">
	     function engine(paraId){
	      
	      new Ajax.Request(
	       "../engine",
	       {
	        method:"post",
	        parameters:{name:$F(paraId) },
	        onSuccess:function(req){
	           var objs=req.responseText.evalJSON();
	           $("div").style.display="block";
	            //每次显示数据前先删除旧的数据
	            clearItems();
	            for(var i=0;i<objs.length;i++){
	               var obj=objs[i];
	               var tr=$("jtb").insertRow($("jtb").rows.length);
	               var tb_id=tr.insertCell(tr.cells.length);
	                tb_id.innerHTML=obj.id;
	               var tb_name=tr.insertCell(tr.cells.length);
	               tb_name.innerHTML=obj.name;
	               //给name注册一个事件
	               tb_name.onclick=function(){
	                 $(paraId).value=this.innerHTML;
	                 $("div").style.display="none";
	               }
	             }
	          }
	        }
	     )
	   }
	   
	   function clearItems(){
	     for(i=$("jtb").rows.length-1;i>=0;i--){
	         $("jtb").deleteRow(i);
	     }
	   }
	  </script>
	</head>
	<body>
		<fieldset>
			<legend>
				使用JSON显示表单
			</legend>
			<input type="text" id="name" name="name" onkeyup="engine('name');" />
			<div id="div">
				<table id="jtb">
				</table>
			</div>
		</fieldset>
	</body>
</html>
