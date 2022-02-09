<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/signup.css">
<script>
function fn_submit() {
	var f = document.userInfo;
	
	if (f.id.value == "") {
		alert("아이디를 입력해주세요");
		f.id.focus();
		return false;
	}
	if (f.chk.value == "0") {
		alert("아이디 중복확인을 해주세요.");
		return false;
	}
	if (f.pw.value == "") {
		alert("비밀번호를 입력해주세요");
		f.pw.focus();
		return false;
	}
	if (f.pw.value != f.pwcheck.value) {
		alert("비밀번호가 같지않습니다.");
		f.pwcheck.focus();
		return false;
	}
	if (f.nickname.value == "") {
		alert("소환사이름을 입력해주세요");
		f.nickname.focus();
		return false;
	}
}
	
	function fn_onload() {
		document.frm.id.focus();
	}
	function fn_idCheck() {
		
		var id = document.userInfo.id.value;
		if (id == "") {
			alert("아이디를 입력해주세요.");
			document.frm.id.focus();
			return false;		
		}
		if(id.length < 4 || id.length > 12){//아이디의 길이
			alert("아이디는 4~12자 사이로 해주세요")
			document.frm.id.focus();
			return false;
		}
		var url = "idCheck.jsp?id="+id;
		window.open(url,"중복아이디체크","width=300,height=300,left=1100px,scrollbar=no");
	}


</script>
</head>
<body>
	<div class="signup-box">
		<h1>Sign Up</h1>
		<h4>It's free and only takes a minute</h4>

		<form action="RegAccountC" name="userInfo" onsubmit="return fn_submit()">
			<input type="hidden" name="chk" value="0"> <label>ID</label>
			<input type="text" placeholder="4~12자의 영문 대소문자와 숫자로만 입력" name="id"
				id="id" maxlength="50">

			<button type="button" onclick="fn_idCheck()" name="chk">중복확인</button>
			
			<label>PW</label> <input type="password"
				placeholder="4~12자의 영문 대소문자와 숫자로만 입력" name="pw" id="password1"
				maxlength="50"> <label>Confirm PW</label> <input
				type="password" name="pwcheck" id="password2" maxlength="50">
			<label>NickName</label> <input type="text" placeholder=""
				name="nickname">
			<!-- <input type="button" value="Submit">  -->
			<button class="loginBtn">Submit</button>

		</form>
	</div>

	<p class="para-2">
		Already have an account?<a href="login.jsp">Login here</a>
	</p>
</body>
</html>