<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
		请输入电话号码：<input type="text" name="mobilenumber" id="mobilenumber">
		<button onclick="getMobile()" value="提交">提交</button>
		<input type="text" id='result' disabled="disabled">
	
	<script type="text/javascript">
		function getMobile(){
			//alert(1);
			var mobilenumber=$("#mobilenumber").val();
			$.ajax({
				url:'getMobile',
				data:{mobilenumber:mobilenumber},
				type:'post',
				dataType:'json',
				success:function(data){
					//alert(data.mobilearea);
					
					$("#result").val(data.mobilearea);
				}
			})
		}
	</script>
</body>
</html>