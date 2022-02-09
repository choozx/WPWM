<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="YoutuberUpC" method="post">


<table id="youtuberTbl2">
<tr>
<td>번호</td>
<td>${Youtube.no }</td>
</tr>

<tr>
<td>유튜버 이름</td>
<td><input name="youtuberName" value="${Youtube.youtuber }"></td>
</tr>

<tr>
<td>puuid</td>
<td><input name="puuid" value="${Youtube.puuid }"></td>
</tr>

<tr>
<td>채널 URL</td>
<td><input name="channelURL" value="${Youtube.channelURL }"></td>
</tr>

<tr>
<td>채널 ID</td>
<td><input name="channelID" value="${Youtube.channelID }"></td>
</tr>

<tr>
<td colspan="2"><button id="youtuberBtn" value="${Youtube.no }" name="no">수정</button></td>
</tr>
</table>



</form>
</body>
</html>