<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="js/jquery-1.11.3.js"></script>
<script>
	$(function(){
		$("button").click(function(){
			alert($("input[name='upfile']").val());
		});
	});
	
</script>
<title>쿼리스트링 추출 테스트</title>
</head>
<body>
	<input type="file" name="upfile" />
	<button>클릭</button>
</body>
</html>