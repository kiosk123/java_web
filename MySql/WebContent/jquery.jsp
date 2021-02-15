<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"></script>
<script src="jquery/jquery-1.11.3.js"></script>
<script src="jquery/jquerysession.js"></script>
<script>
$(function(){
	$.session.set('dog','hello')

		
});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%session.setAttribute("dog", "hello") ;%>
</body>
</html>