<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>건의</title>
<link rel="stylesheet" href="css/suggestion.css">
</head>
<body>
	<span id="attributeValue">${d}</span>
	
	<form action="SuggestionSearchC">
		<table>
			<tr>
				<td>
					<select name="headname" class="select-td">
						<option value="All">전체</option>
						<option value="youtuber">유튜버 추천</option>
						<option value="bug">버그신고</option>
					</select>
					<button class="btn_select" >검색</button>
				</td>
 				<c:choose>
			 		<c:when test="${sessionScope.accountInfo == null}">
					<% 	PrintWriter script = response.getWriter(); 
						script.println("<script>");
						script.println("alert('로그인을 하세요')");
						script.println("location.href='login.jsp'");
						script.println("</script>");
					%>
					</c:when>
					<c:otherwise>
						<td align="right"><button class="btn_duoreg"
							onclick="location.href='SuggestionRegC?name=${sessionScope.accountInfo.nickname }'" type="button" value="${sessionScope.accountInfo.nickname }">글쓰기</button>
						</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</table>
	</form>
	<table style="width: 1200px; color: white;">
		<tr id="suggestionTR">
			<td class="suggestionNo">글 목록</td>
			<td class="suggestionHead">말머리</td>
			<td class="suggestionTitle">제목</td>
			<td class="suggestionUser">글쓴이</td>				
			<td class="suggestionDate">등록일시</td>
		</tr>
		<c:forEach var="s" items="${suggestions }" varStatus="status">
			<tr>
				<td class="suggestionNo">${s.no}</td>
				<td class="suggestionHead">${s.headname}</td>
				<td class="suggestionTitle"  onclick="location.href='SuggestionDetailC?no=${s.no}'">${s.title }</td>
				<td class="suggestionUser">${s.name }</td>
				<td class="suggestionDate">${s.date}
					<button style="float: right;" class="material-icons i_btn"
						onclick="location.href='SuggestionUpdateC?no=${s.no}'" type="button">edit</button>
					<button style="float: right;" class="material-icons i_btn"
						onclick="location.href='SuggestionDelController?no=${s.no}&&name=${s.name}'"
						type="button">delete</button>
				</td>				
			</tr>
		</c:forEach>
	</table>
	<table align="center">
		<tr id = "duoPaging">
			<td>
			<a href="SuggestionPageController?p=1" class="duopaging-num">&lt&lt</a>&nbsp;&nbsp;
			<a href="SuggestionPageController?p=${curPageNo - 1}" class="duopaging-num">&lt</a>
			<c:forEach var="d" begin="1" end="${pageCount}">
				<a href="SuggestionPageController?p=${d}" class="duopaging-num">[${d}]</a>
			</c:forEach>
			<a href="SuggestionPageController?p=${curPageNo + 1}" class="duopaging-num">&gt</a>&nbsp;&nbsp;
			<a href="SuggestionPageController?p=${pageCount}" class="duopaging-num" >&gt&gt</a>
			</td>
		</tr>
	</table>
</body>
</html>