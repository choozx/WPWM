<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="css/message.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	type="text/css" rel="stylesheet" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<body style="background-color: #E1EEF5;">
 <table border="1"style="width: 100%; height: 730px;">
 	<tr>
 		<td><h2 style="text-align: center">쪽지정보</h2></td>
 	</tr>
 	<tr>
 		<td style="font-size: 20px;">받는이 : ${sendMessage.recvname}</td>
 	</tr>
 	<tr>
 		<td style="font-size: 20px;">날짜 : <fmt:formatDate value="${sendMessage.date}" type="both"
		dateStyle="short" timeStyle="short" /></td>
 	</tr>
 	<tr>
 		<td style="font-size: 20px; vertical-align: top;">&lt;내용&gt;  <br>  ${sendMessage.content}</td>
 	</tr>
 	<tr>
 		<td align="center"><button onclick="location.href='MessageSendlistC'" style="text-align: center">목록으로</button></td>
 	</tr>
 </table>
	
</body>
</html>