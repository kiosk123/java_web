<%@page import="board.Board"%>
<%@page import="board.DAO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
	int pageSize=10;//한페이지에 보일 게시물의 수
		
	String pageNum=request.getParameter("pageNum");
	if(pageNum==null)
		pageNum="1";
	
	int currentPage=Integer.parseInt(pageNum);
	
	//페이지마다 표시할 게시물 가져오기 위한 변수
	int startRow=(currentPage-1)*pageSize+1; //DB에서 추출할 게시물 시작 ROW
	int endRow=currentPage*pageSize; //DB에서 추출할 게시물 마지막 ROW
	int count=0; //전체 게시물의 개수
	int number=0; 
	
	DAO instance=DAO.getInstance();
	count=instance.getBoardCount();
	
	List<Board> boardList=instance.getBoards(startRow, endRow);
	
	number=count-(currentPage-1)*pageSize;
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
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
	<caption>글목록</caption>
	
	<tr>
		<td colspan="6" align="right">
		<a href="writeForm.jsp">글쓰기</a>
		</td>
	</tr>
	
	<tr>
		<td align="center">
			번호
		</td>
		<td align="center">
			제목
		</td>
		<td align="center">
			작성자
		</td>
		<td align="center">
			작성일
		</td>
		<td align="center">
			조회
		</td>
		<td align="center">
			IP
		</td>
	</tr>

	<%  //for문 시작
		for(int i=0;i<boardList.size();i++){
			Board board=boardList.get(i);
	%>
	
	<tr>
		<td colspan="1" align="center">
			<%=board.getNum()%>
		</td>
		
		<td>
		<%
			String indent="";
			
			//답글이라 들여쓰기가 존재하면
			if(board.getIndent()>0){
				for(int j=0;j<board.getIndent();j++)
					out.print("&nbsp;&nbsp;");
			}		
		%>
		
		<a href="content.jsp?num=<%=board.getNum() %>&pageNum=<%=currentPage%>
		&modify=false"><!-- modify는 조회수 업데이트 여부에 사용된다. -->
			<%=board.getTitle()%>
		</a>		
		</td>
		
		<td align="center">
			<%=board.getWriter() %>
		</td>
		
		<td align="center">
			<%=board.getTime()%>
		</td>
		
		<td>
			<%=board.getReadCount()%>
		</td>
		
		<td>
			<%=board.getIp()%>
		</td>
	</tr>
	<%}//for문 종료 %>
</table>

<%//페이징처리
	if(count>0){
		
		//전체 페이지의 수를 구한다.
		int pageCount=(int)Math.ceil((double)count/(double)pageSize);
		//현재 게시물이 속해있는 페이지라인의 시작페이지를 구한다 
		//예를 들어 페이지 번호가 10개씩 보인다고 할때 5번페이지의 시작페이지번호는 1번페이지다
		int startPage=(int)(currentPage/10)*10+1;//한페이지당 10개씩 보여주므로 10으로나누고 곱함
		
		int endPage=startPage+10-1;//끝페이지번호 공식
		
		if(endPage>pageCount)
			endPage=pageCount;//끝페이지는 전체페이지개수보다 클수없기때문에
		
		//startPage가 한페이지보다 보여주는 값(10)보다 클때
		if(startPage>10){%>
			<a href="list.jsp?pageNum=<%=startPage-10%>">[이전]</a><%
		}
			
		for(int i=startPage;i<=endPage;i++){%>
			<a href="list.jsp?pageNum=<%=i%>">[<%=i%>]</a><%
		}
		
		if(endPage<pageCount){%>
			<a href="list.jsp?pageNum=<%=startPage+10%>">[다음]</a>
		<%}
	}	
%>
</body>
</html>