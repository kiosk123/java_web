<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	table{border: :1px solid black; border-collapse: collapse;}
	td, th{margin: 10px; padding: 5px; border: 1px solid black; border-collapse: collapse;}
	.align_center{text-align: center;}
	hr{margin-top: 30px; margin-bottom: 30px;}
	.warning{font-weight: bold; color: red;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>목록페이지</title>
</head>
<body>

<!-- 회원정보 표시 -->
<table>
	<tr>
		<th>아이디</th>
		<th>부서</th>
		<th>내선전화</th>
		<th>핸드폰</th>
		<th>등록일</th>
		<th>패스워드 변경일</th>
		<th>로그인 시도 횟수</th>
	</tr>
	<c:choose>
		<c:when test="${memberList.size() ne 0}">
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.id}</td>
				<td>${member.department}</td>
				<td>${member.phoneIn}</td>
				<td>${member.phoneEx}</td>
				<td>${member.registDate}</td>
				<td>${member.pwUpdate}</td>
				<td>${member.loginCnt}</td>
			</tr>
		</c:forEach>
		</c:when>	
		<c:otherwise>
			<tr>
				<td colspan="7" class="align_center">입력된 정보가 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>

<hr>

<!-- 회원 정보 등록 -->
<form action="/jpa_web/regMember" method="POST">
<table>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="id" maxlength="25" required/></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="pw" maxlength="25" required/></td>
	</tr>
	<tr>
		<th>부서</th>
		<td><input type="text" name="department" maxlength="25" required/></td>
	</tr>
	<tr>
		<th>내선전화</th>
		<td><input type="text" name="phoneIn" maxlength="25" required/></td>
	</tr>
	<tr>
		<th>핸드폰</th>
		<td><input type="text" name="phoneEx" maxlength="25" required/></td>
	</tr>
	<tr>
		<td colspan="2" class="align_center">
			<input type="submit" value="등록" />
		</td>
	</tr>
</table>
</form>

<span class="warning">${message}</span>

</body>
</html>
