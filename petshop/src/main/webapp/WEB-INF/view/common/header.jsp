<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 스타일 시트를 가져오기 위한 url -->
<c:url value="/resources/styles/style.css" var="cssUrl"/>
<link rel="stylesheet" type="text/css" href="${cssUrl}"/>
<title>petshop : ${pageTitle}</title>
</head>
<body>
<div id="header">
<!--  End of Ch 3  -->
	<div class="username">
		<c:if test="${principal.username}">
		    <!-- 로그인한 방문자를 환영하는 내용 - principal.username을  출력한다.-->
	    	Welcome,  <strong><sec:authentication property="principal.username"/></strong>
	    </c:if>
	    <c:if test="${!principal.username}">
	    	Welcome,  <strong><sec:authentication property="name"/></strong>
	    </c:if>
	</div>
<ul>
	<c:url value="/" var="homeUrl"/>
	<li><a href="${homeUrl}">Home</a></li>

<!--  Early Ch 3  -->
	<c:url value="/j_spring_security_logout" var="logoutUrl"/>
	<li><a href="${logoutUrl}">Log Out</a></li>

<!--  Late Ch 3 after logout URL customization
	<c:url value="/logout" var="logoutUrl"/>
	<li><a href="${logoutUrl}">Log Out</a></li>
 -->
	<c:url value="/account/home" var="accountUrl"/>
	<li><a href="${accountUrl}">My Account</a></li>

	<c:url value="/wishlist/home" var="wishlistUrl"/>
	<li><a href="${wishlistUrl}">My Wishlist</a></li>
	
</ul>
<br />
</div>