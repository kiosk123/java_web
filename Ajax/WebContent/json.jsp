<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-1.11.3.js"></script>
<script>
$(function(){
	
	$("button").click(function(){
		var x={};
		x.name=$("input[type='text']").val();
		
		$.ajax({
			type:"POST",
			url:"result.jsp",
			data:JSON.stringify(x),
			success:function(result,status,xhr){
				alert(result.trim());
			}
		});
	});
})
</script>
<title>Insert title here</title>
</head>
<body>
<input type="text" name="name"/>
<button>클릭</button>
</body>
</html>