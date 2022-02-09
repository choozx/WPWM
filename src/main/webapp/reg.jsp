<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>




<article style="margin-top: 250px;" class="container">
	<div class="regTbl1">
		<a href="edit">개인정보 관리</a>
	</div>
	<div class="regTbl2">
		<a href="RegUpdate?id=${reg.id }">비밀번호 변경</a>
	</div>

	<div class="regTbl3">
		<a href="leave">회원탈퇴</a>
	</div>
</article>
	
	<table >
	<tr>
	<td class="regfont1">ID</td>
	<td class="regfont2">${sessionScope.accountInfo.id }</td>
	</tr>
	<tr>
	<td class="regfont1">닉네임</td>
	<td class="regfont2">${sessionScope.accountInfo.nickname }</td>
	</tr>
</table>




</body>
</html>