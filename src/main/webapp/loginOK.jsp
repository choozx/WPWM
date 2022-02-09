<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${r }<p>
${sessionScope.accountInfo.nickName }
<button onclick="location.href='LoginController'">로그아웃</button>
<button onclick="location.href='InfoAccountC'">회원정보</button>
</body>
</html>