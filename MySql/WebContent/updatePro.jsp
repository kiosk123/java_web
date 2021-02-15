<%@page import="board.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="board" scope="request" class="board.Board">
	<jsp:setProperty name="board" property="*" />
</jsp:useBean>



<%
	int pageNum=Integer.parseInt(request.getParameter("pageNum"));
	DAO instance=DAO.getInstance();
	
	//글수정이 되었는지 확인한다. check=1이면 글수정 성공
	int check=instance.updateBoard(board);
	
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