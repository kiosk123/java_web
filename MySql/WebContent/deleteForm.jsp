<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

 	//게시물 번호와 페이지 번호를 추출
	int boardNum=Integer.parseInt(request.getParameter("num"));
	int pageNum=Integer.parseInt(request.getParameter("pageNum"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글삭제</title>
<style type="text/css">
table{
    border:1px solid black;   /*표 바깥으로 1px 실성*/
    border-collapse:collapse; /*겹치는 선은 한 줄로 표시*/
}

td{
    margin:10px; /*셀과 셀 사이 여백*/
    padding:5px; /*셀 테두리와 내용 사이의 여백*/
    border:1px solid black; /*셀 주변에 테두리 그리기*/
    border-collapse:collapse; /*겹치는 선은 한 줄로 표시*/
}
</style>
</head>
<body>
<form action="deletePro.jsp?num=<%=boardNum%>&pageNum=<%=pageNum%>" method="post">
	<table>
		<tr>
			<td>비밀번호</td>
		</tr>
		
		<tr>
			<td>
				<input type="password" name="password" 
					size="8" maxlength="12"/>
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="submit" value="글삭제" />
				<input type="button" value="글목록" 
					onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>