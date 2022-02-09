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
	<form action="SuggestionUpdateC" method="post" name="duoUpdateForm" onsubmit="return duoUpdateCheck();">
		<table id="duoRegTbl" align="center" border="1">
			<tr>
				<td colspan="2"><h3 style="color: white;">건의 수정하기</h3></td>
			</tr>
			<tr>
				<td class="duoreg-td01">사용자 이름</td>
				<td class="duoreg-td02" style="color: white;">${sessionScope.accountInfo.nickname }</td>
			</tr>
			<tr>
				<td class="duoreg-td01">머릿말</td>
				<td class="duoreg-td02">
					<select name="headname" style="width: 100%">
						<option value="youtuber">유튜버 추천</option>
						<option value="bug">버그신고</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="duoreg-td01">제목</td>
				<td class="duoreg-td02" style="color: white;"><input name="title"></td>
			</tr>
			<tr>
				<td class="duoreg-td01" colspan="2">작성란</td>
			</tr>
			<tr>
				<td class="duoreg-td02" colspan="2"><textarea name="text" style="width: 95%" rows="5" cols="30" placeholder="작성의 유의"></textarea> </td>
			</tr>
			<tr>
				<td class="duoreg-td01">삭제 비밀번호</td>
				<td class="duoreg-td02"><input name="pw" style="width: 95%"
					placeholder="4자리 숫자로 입력해주세요" type="password" maxlength="4"></td>
			</tr>
			<tr>
				<td class="duoreg-td01"><button class="btn_duocancel"
						onclick="history.go(-1)" type="button">취소</button></td>
				<td class="duoreg-td02"><button class="btn_duoreg02" id="btn_duoreg02" value="${param.no}" name="no">등록</button></td>
			</tr>
		</table>
	</form>
</body>
</html>