<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/reg.css">
<link rel="stylesheet" href="css/index.css">
<script>
function fn_submit() {
    var f = document.userInfo;

    if (f.pw2.value != f.pw3.value) {
        alert("비밀번호가 같지않습니다.");
        f.pw2.focus();
        f.pw2.value == "";
        return false;
    }
    if(isEmpty(f.pw2)){
        alert('신규 비밀번호를 입력하세요');
        f.pw2.focus();
        f.pw2.value == "";
        return false; // 동작이 안넘어가게
    }
    if (f.pw.value == f.pw2.value || f.pw.value == f.pw3.value ) {
        alert("기존 비밀번호와 같습니다.");
        f.pw2.focus();
        f.pw2.value == "";
        return false;
    }
}
</script>
</head>
<body>
	<form action="RegUpdate" name="userInfo" onsubmit="return fn_submit();" method="post">
		<article style="margin-top: 200px;" class="container2">
			<div class="regTbl1">
				<a href="edit">개인정보 관리</a>
			</div>
			<div class="regTbl2">
				<a href="changePassword">비밀번호 변경</a>
			</div>
			<div class="regTbl3">
				<a href="leave">회원탈퇴</a>
			</div>
			</article>
		<table>
		<tr>
			<td class="regfont1" >현재 비밀번호</td>
			<td><input type="password"
				value="${sessionScope.accountInfo.pw }" name="pw" class="reg2Input"></td>
			</tr>
				<tr>
			<td class="regfont1" >신규 비밀번호</td>
			<td><input type="password" name="pw2" class="reg2Input"></td>
			</tr>
				<tr>
			<td class="regfont1" >비밀번호 확인</td>
			<td><input type="password" name="pw3" class="reg2Input"></td>
				</tr>
				<tr>
			<td><button name="id" value="${sessionScope.accountInfo.id }"
					id="cPwbtn">확인</button></td>
					</tr>
			<!-- 아이디는 고정이라  버튼에다 준거 -->
		</table>
	</form>
</body>
</html>