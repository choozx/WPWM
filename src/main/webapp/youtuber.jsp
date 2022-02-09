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

<form action="youtuberC" method="post">


<table id="youtuberTbl">
<tr>
<td>유튜버 이름</td>
<td><input name="youtuberName"></td>
</tr>
<tr>
<td>puuid</td>
<td><input name="puuid"></td>
</tr>
<tr>
<td>채널 URL</td>
<td><input name="channelURL"></td>
</tr>
<tr>
<td>채널 ID</td>
<td><input name="channelID"></td>
</tr>
<tr>
<td></td>
<td colspan="2"><button id="youtuberBtn">등록</button></td>
</tr>
</table>
</form>


<c:forEach items="${Youtubers }" var="y">
<table id="yTbl">
<tr>
<td class="yTblTd1">유튜버 이름</td>
<td class="yTblTd2">${y.youtuber}</td>
</tr>


<tr>
<td class="yTblTd1">puuid</td>
<td class="yTblTd2">${y.puuid}</td>
</tr>


<tr>
<td class="yTblTd1">채널 URL</td>
<td class="yTblTd2">${y.channelURL}</td>
</tr>


<tr>
<td class="yTblTd1">채널 ID</td>
<td class="yTblTd2">${y.channelID}</td>
</tr>


<tr>
<td><button onclick="location.href='YoutuberUpC?no=${y.no}'" class="yBtn">수정</button>
<button onclick="YoutuberDel(${y.no})" class="yBtn" >삭제</button></td>
</tr>
</table>

</c:forEach>







</body>
</html>