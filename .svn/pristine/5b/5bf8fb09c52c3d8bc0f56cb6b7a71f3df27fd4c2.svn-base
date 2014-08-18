<%@page pageEncoding="utf-8"%>
<html>
	<head>
		<script type="text/javascript" src="jquery-1.4.min.js"></script>
		<script type="text/javascript">
		  $(document).ready(function(){
		      $("#show1").click(function(){
		        $("#img").show(3000);
		      });
		      
		      $("#hide1").click(function(){
		       $("#img").hide(3000,function(){
		         //$("#img1").attr("width","80%");
		         $("#img1").attr("src","../img/222.jpg");
		       });
		      });
		  }) ;
		  
		  //滑动显示或者隐藏图片
		  $(document).ready(function(){
		     //*滑动显示
		     $("#show2").click(function(){
		      $("#img").slideDown(3000);
		     });
		     //*滑动隐藏
		     $("#hide2").click(function(){
		      $("#img").slideUp(3000);
		     });
		  });
		//*淡入淡出式的显示和隐藏
		$(document).ready(function(){
		  //淡入显示
		  $("#show3").click(function(){
		   $("#img").fadeIn(3000);
		  });
		  //淡出隐藏
		  $("#hide3").click(function(){
		   $("#img").fadeOut(3000);
		  });
		});
		</script>
	</head>
	<body>
		<fieldset>
			<legend>
				测试jquery
			</legend>
			<p>
				<!-- 基本的显示和隐藏 -->
				<input type="button" name="show" id="show1" value="基本显示" />
				<input type="button" name="hide" id="hide1" value="基本隐藏" />
				<!-- 滑动的显示和隐藏 -->
				<input type="button" name="hide" id="show2" value="滑动显示" />
				<input type="button" name="hide" id="hide2" value="滑动隐藏" />
				<!-- 淡入淡出式的显示和隐藏 -->
				<input type="button" name="hide" id="show3" value="淡入显示" />
				<input type="button" name="hide" id="hide3" value="淡出隐藏" />
			</p>
			<hr />
			<img id="img" src="../img/211.jpg" />
			<hr/>
			<img id="img1" src="#"/>
		</fieldset>
	</body>
</html>
