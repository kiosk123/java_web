<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//제목글의 기본설정
	int num = 0, ref = 0, step = 0, indent = 0;

	if (request.getParameter("num") != null) { //답변글일 경우에만 수행
		
		num = Integer.parseInt(request.getParameter("num"));
		ref = Integer.parseInt(request.getParameter("ref"));
		step = Integer.parseInt(request.getParameter("step"));
		indent = Integer.parseInt(request.getParameter("indent"));
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
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
<body bgcolor="yellow">

	<form method="post" action="writePro.jsp">
		<input type="hidden" name="num" value="<%=num%>"> 
		<input type="hidden" name="ref" value="<%=ref%>"> 
		<input type="hidden" name="step" value="<%=step%>">
		<input type="hidden" name="indent" value="<%=indent%>">
		
		
		<table>
		 	<caption>글쓰기</caption>
		 	
			<tr>
				<td colspan="2" align="right">
					<a href="list.jsp">글목록</a>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					이름
				</td>
				<td>
					<input type="text" size="10" maxlength="10" name="writer">
				</td>
			</tr>
			
			<tr>
				<td align="center">
					제목
				</td>
				
				<td>
				<%if(num==0){%>
					<input type="text" size="40" maxlength="50" name="title" >
				<%}else{%>
					<input type="text" size="40" maxlength="50" name="title" value="RE:">
				<%}%>
				</td>								
			</tr>
			
			<tr>
				<td align="center">
					이메일
				</td>
				<td>
					<input type="text" size="40" maxlength="30" name="email">
				</td>
			</tr>
			
			<tr>
				<td align="center">
					내용
				</td>
				<td align="left">
					<textarea name="content" rows="13" cols="40"></textarea>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					비밀번호
				</td>
				<td>
					<input type="password" size="8" maxlength="12" name="password" value="">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글쓰기">
					<input type="reset" value="다시쓰기">
					<input type="button" value="목록보기" OnClick="window.location='list.jsp'">
				</td>	
			</tr>
		</table>
	</form>
</body>
</html>