<%@page import="board.DAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="board" scope="request" class="board.Board">
	<jsp:setProperty name="board" property="*" />
</jsp:useBean>

<%
	board.setTime(new Timestamp(System.currentTimeMillis()));
	board.setIp(request.getRemoteAddr());
	
	DAO instance=DAO.getInstance();
	instance.insertArticle(board);
		
 	response.sendRedirect("list.jsp");
%>