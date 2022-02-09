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
	<span id="suggestionDetailTitle" style=" color: white;">${suggestion.title }</span>
	<table id="suggestionDetailTbl" align="center" border="1">
		<tr>
			<td class="suggestionDetailtd1">글 번호</td>
			<td class="suggestionDetailtd2" align="left" style="color: white;">${suggestion.no}</td>
		</tr>
		<tr>
			<td class="suggestionDetailtd1">작성자 이름</td>
			<td class="suggestionDetailtd2" align="left" style="color: white;">${suggestion.name}</td>
		</tr>
		<tr>
			<td id="suggestionDetailText" colspan="2" style="color: white;">${suggestion.text}</td>
		</tr>
		<tr>
			<td class="suggestionDetailtd1">등록일시</td>
			<td class="suggestionDetailtd2" style="color: white;">${suggestion.date }</td>
		</tr>
	</table>
</body>
</html>		