<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<jsp:include page="common/header.jsp">
	<jsp:param name="pageTitle" value="Login"/>
</jsp:include>

<h1>Please Log In to Your Account</h1>
<p>
	Please use the form below to log in to your account.
</p>

<!-- 폼은 포스트 요청을 한다는 것에 주의 -->
<form action="/petshop/login" method="post">
	<label for="j_username">Login</label>:
	<input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>
	<br />

<!-- For experimentation with an alternate checkbox name
	<input id="_remember_me" name="_remember_me" type="checkbox" value="true"/>
	<label for="_remember_me">Remember Me?</label>
	<br />
 -->

	<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
	<label for="_spring_security_remember_me">Remember Me?</label>
	<br />
	<label for="j_password">Password</label>:
	<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
	<br />

	<input type="submit" value="Login"/>
</form>

<jsp:include page="common/footer.jsp"/>