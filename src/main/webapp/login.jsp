<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/signup.css">
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/validCheck.js"></script>
</head>
<body>
	<div class="login-box">
		<h1>Login</h1>
		<form action="login" method="post" onsubmit="return regCheck();" name="loginForm">
			<label>ID</label>
			<input type="text" placeholder="아이디" id="i1" name="id"> 
			<label>PW</label>
			<input type="password" placeholder="비밀번호" name="pw">
			<button class="loginBtn" type="submit">Submit</button>
		</form>
	</div>
	<p class="para-2">회원이 아니십니까? <a href="SignUp">Sign Up Here</a></p>
</body>
</html>