<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DUO</title>
</head>
<body>
	<form action="SuggestionDelController" name="deleteform" method="post"
		onsubmit="return confirmdel();">
		<table id="duoDelTbl" align="center" border="1">
			<tr>
				<td colspan="2"><h3 style="color: white;">삭제하기</h3></td>
			</tr>
			<tr>
				<td class="duoreg-td01" >작성자 이름</td>
				<td style="color: white;"> ${param.name}</td>
			</tr>
			<tr>
				<td class="duoreg-td01">삭제 비밀번호</td>
				<td class="duoreg-td02"><input name="userPw" style="width: 95%"
					placeholder="4자리 숫자로 입력해주세요" type="password" maxlength="4"></td>
			</tr>
			<tr>
				<td class="duoreg-td01"><button class="btn_duocancel"
						onclick="history.go(-1)" type="button">취소</button></td>
				<td class="duoreg-td02"><button class="btn_duodel" name="no"
						value="${param.no}">삭제</button></td>
			</tr>
		</table>
	</form>
</body>
</html>