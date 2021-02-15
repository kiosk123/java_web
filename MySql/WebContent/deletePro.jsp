<%@page import="board.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>

<%
	//게시물 번호와 페이지번호 패스워드를 추출한다.
	int boardNum=Integer.parseInt(request.getParameter("num"));
	int pageNum=Integer.parseInt(request.getParameter("pageNum"));
	String password=request.getParameter("password");
	
	DAO instance=DAO.getInstance();
	
	//글수정이 되었는지 확인한다. check=1이면 글수정 성공
	int check=instance.deleteBoard(boardNum, password);
	
	if(check==1){
		response.sendRedirect("list.jsp?pageNum="+pageNum);
	}else{
		%>
		
	<script type="text/javascript">
		alert("비밀번호가 일치하지 않습니다.");
		history.go(-1);//비밀번호가 틀리면 바로전페이지로 이동한다.
	</script>

<%
	}
	
%>