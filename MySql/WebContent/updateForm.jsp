<%@page import="board.Board"%>
<%@page import="board.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	//게시물에 해당하는 게시물 번호를 읽는다.
	int boardNum=Integer.parseInt(request.getParameter("num"));
	
	//게시물이 속한 페이지번호를 읽는다.
	int pageNum=Integer.parseInt(request.getParameter("pageNum"));
	
	//modify변수로 단순히 게시물을 가져오는 것인지 수정하는 것인지 여부판단
	boolean modify=Boolean.parseBoolean(request.getParameter("modify"));
	DAO instance=DAO.getInstance();
	Board board=instance.getBoard(boardNum, modify);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
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

	<!--페이지번호와 게시물 번호를 주소를 통해 넘긴다.-->
	<form method="post" action="updatePro.jsp?pageNum=<%=pageNum%>&num=<%=boardNum%>">
				
		<table>
		 	<caption>글수정</caption>
		 	
			<tr>
				<td colspan="2" align="right">
					<a href="list.jsp?pageNum=<%=pageNum%>">글목록</a>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					이름
				</td>
				<td>
					<input type="text" size="10" maxlength="10" 
						name="writer" value=<%=board.getWriter()%>>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					제목
				</td>
				
				<td>			
					<input type="text" size="40" 
						maxlength="50" name="title" value=<%=board.getTitle()%>>
				</td>								
			</tr>
			
			<tr>
				<td align="center">
					이메일
				</td>
				<td>
				     
					<input type="text" size="40" 
					  	maxlength="30" name="email" value=<%=board.getEmail()%>>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					내용
				</td>
				<td>
					<textarea name="content" rows="13" cols="40" ><%=board.getContent()%></textarea>
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
					<input type="submit" value="글수정">
					<input type="reset" value="다시작성">
					<input type="button" value="목록보기" 
						OnClick="window.location='list.jsp?pageNum=<%=pageNum%>'">
				</td>	
			</tr>
		</table>
	</form>
</body>
</html>