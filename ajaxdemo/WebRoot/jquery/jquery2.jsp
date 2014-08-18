<%@page pageEncoding="utf-8"%>
<html>
	<head>
		<script type="text/javascript" src="jquery-1.4.min.js"></script>
		<script type="text/javascript">
	            $(document).ready(function(){
	           // 动态指定元素属性
	           //*  $("a").attr("href",function(){});
	           $("a").click(function(){
	                  alert("hello jquery");
	           });
	           
	           // *根据元素名，动态改变颜色
	          // $("tr:even").css("background","#ffff66");
	           //$("tr:odd").css("background","#6666ff");
	           $("tr").each(function(i,item){
	             $(item).css("background",["#ffff66","#6666ff"][i%2]);
	           });
	           
	           //鼠标移动到元素上就变色
	           var c;
	           $("tr").hover(
	               function(){
	               c=$(this).css("background");
	               $(this).css("background","#5ff5ff");
	               }
	               ,
	               function(){
	                $(this).css("background",c);
	              }
	      );
	               
   })
	            
	            
	   </script>
	</head>
	<body>
		<fieldset>
			<legend>
				jquery测试
			</legend>
			<a href="#">超链接1</a>
			<br>
			<a href="#">超链接2</a>
			<hr>
			<table>
				<tr>
					<td>
						股指期货即将上演
					</td>
					<td>
						2010-3-4
					</td>
				</tr>
				<tr>
					<td>
						李连杰教云南农民种咖啡
					</td>
					<td>
						2010-4-4
					</td>
				</tr>
				<tr>
					<td>
						股指期货即将上演
					</td>
					<td>
						2010-3-4
					</td>
				</tr>
				<tr>
					<td>
						李连杰教云南农民种咖啡
					</td>
					<td>
						2010-4-4
					</td>
				</tr>
				<tr>
					<td>
						股指期货即将上演
					</td>
					<td>
						2010-3-4
					</td>
				</tr>
				<tr>
					<td>
						李连杰教云南农民种咖啡
					</td>
					<td>
						2010-4-4
					</td>
				</tr>
			</table>
		</fieldset>
	</body>
</html>
