<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery.min.js"></script>
<meta charset="UTF-8">
<title>JSON 바인딩 테스트 페이지</title>
</head>
<body>
<button>클릭</button>
<script>
$(function(){
	$("button").click(function(){				
		x={};
		x.month="12";
		
		//alert(JSON.stringify(x)); 
		$.ajax({
			type:"POST",
			url:"/test",
			data:JSON.stringify(x),
			success:function(result,status,xhr){
				alert("성공");		
			},
			error:function(xhr,status,error){
				alert("실패");			
			},
			contentType:"application/json"
		});
	});
});


</script>
</body>
</html>