<%@page import="board.Board"%>
<%@page import="board.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//게시물 번호
	int boardNum=Integer.parseInt(request.getParameter("num"));

	//게시물이 속한 페이지번호
	int pageNum=Integer.parseInt(request.getParameter("pageNum"));
	
	//modify변수로 단순히 게시물을 가져오는 것인지 수정하는 것인지 여부판단
	boolean modify=Boolean.parseBoolean(request.getParameter("modify"));
	
	DAO instance=DAO.getInstance();
	Board board=instance.getBoard(boardNum,modify);
	
	int ref=board.getRef();
	int step=board.getStep();
	int indent=board.getIndent();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!--게시물 제목 출력 -->
<title><%=board.getTitle()%></title>

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

<table>
	<tr>
		<td align="center">글번호</td>
		<td align="center"><%=board.getNum()%></td>
		
		<td align="center">조회수</td>
		<td align="center"><%=board.getReadCount()%></td>
	</tr>
	
	<tr>	
		<td align="center">작성자</td>
		<td align="center"><%=board.getWriter()%></td>
		
		<td align="center">작성일</td>
		<td align="center"><%=board.getTime()%></td>
	</tr>
	
	<tr>
		<td align="center">이메일</td>
		<td colspan="3" align="left"><%=board.getEmail()%></td>		
	</tr>
	
	<tr>
		<td align="center">글제목</td>
		<td colspan="3" align="left"><%=board.getTitle()%></td>
	</tr>
	
	<tr>
		<td align="center">글내용</td>
		<td colspan="3" align="left" width="375">
				<%=board.getContent()%>			
		</td>
	</tr>
	
	<tr>
		<td colspan="4">
		    <!-- 글수정을 한다면 modify변수는 true로 해준다 -->
			<button onclick="document.location.href
			='updateForm.jsp?num=<%=board.getNum()%>&pageNum=<%=pageNum%>&modify=true'">
				글수정
			</button>
			
			<button onclick="document.location.href
			='deleteForm.jsp?num=<%=board.getNum()%>&pageNum=<%=pageNum%>'">
				글삭제
			</button>
			
			<button onclick="document.location.href
			='writeForm.jsp?num=<%=board.getNum()%>&ref=<%=ref%>&step=<%=step%>&indent=<%=indent%>'">
				답글쓰기
			</button>
			
			<button onclick="document.location.href
			='list.jsp?pageNum=<%=pageNum%>'">
				글목록
			</button>
		</td>
	</tr>
</table>

</body>
</html>