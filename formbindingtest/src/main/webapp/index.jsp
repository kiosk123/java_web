<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트 폼페이지</title>
</head>
<body>

<form action="/test" method="post">
	no : <input type="text" name="no" /><br>
	content : <input type="text" name="content" /><br>
	checkbox : <input type="checkbox" name="checkbox"/><br>
	<input type="submit" value="전송" />
</form>
<br>

</body>
</html>