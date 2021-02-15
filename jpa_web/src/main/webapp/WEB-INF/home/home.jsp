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
<title>���������</title>
</head>
<body>

<!-- ȸ������ ǥ�� -->
<table>
	<tr>
		<th>���̵�</th>
		<th>�μ�</th>
		<th>������ȭ</th>
		<th>�ڵ���</th>
		<th>�����</th>
		<th>�н����� ������</th>
		<th>�α��� �õ� Ƚ��</th>
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
				<td colspan="7" class="align_center">�Էµ� ������ �����ϴ�.</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>

<hr>

<!-- ȸ�� ���� ��� -->
<form action="/jpa_web/regMember" method="POST">
<table>
	<tr>
		<th>���̵�</th>
		<td><input type="text" name="id" maxlength="25" required/></td>
	</tr>
	<tr>
		<th>��й�ȣ</th>
		<td><input type="password" name="pw" maxlength="25" required/></td>
	</tr>
	<tr>
		<th>�μ�</th>
		<td><input type="text" name="department" maxlength="25" required/></td>
	</tr>
	<tr>
		<th>������ȭ</th>
		<td><input type="text" name="phoneIn" maxlength="25" required/></td>
	</tr>
	<tr>
		<th>�ڵ���</th>
		<td><input type="text" name="phoneEx" maxlength="25" required/></td>
	</tr>
	<tr>
		<td colspan="2" class="align_center">
			<input type="submit" value="���" />
		</td>
	</tr>
</table>
</form>

<span class="warning">${message}</span>

</body>
</html>
