<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/signup.css">
<link rel="stylesheet" href="css/youtube.css">
<link rel="stylesheet" href="css/reg.css">
<link rel="stylesheet" href="css/search/noSearch.css">
<link rel="stylesheet" href="css/search/searchMain.css">
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/validCheck.js"></script>
<script type="text/javascript" src="js/youtube.js"></script>
</head>
<body>
	<div id="topMenu1">
		<ul>
			<li><a href="HC" style="text-decoration: none;">WPWM</a></li>
			<li><img
				src="https://opgg-gnb.akamaized.net/static/images/icons/img-navi-duo-gray.svg"
				id="topmenu_logo" onclick="location.href='DuoHC'"><a
				href="DuoHC" style="text-decoration: none;">Duo(듀오찾기)</a></li>
			<li><a href="SuggestionHC" style="text-decoration: none;">건의사항</a></li>
			<li><c:choose>
					<c:when test="${sessionScope.accountInfo == null}">
						<!-- 로그인 안됨 -->
					</c:when>

					<c:otherwise>
						<c:set var="id" value="${sessionScope.accountInfo.id }"></c:set>
						<c:if test="${id eq 'admin' }">
							<button onclick="location.href='youtuberC'" id="btn_youtuber">
								<span style="font-size: 12">Youtuber</span>
							</button>
						</c:if>
						<button
							onclick="window.open('MessageRecvlistC?nickname=${sessionScope.accountInfo.nickname }','쪽지보내기',
								'width=530,height=730,left=1100,top=0,scrollbar=yes');"
							id="btn_message">
							<span style="font-size: 12">쪽지함</span>
						</button>
					</c:otherwise>
				</c:choose></li>
			<li><c:choose>
					<c:when test="${sessionScope.accountInfo == null}">
						<!-- 로그인 안됨 -->
						<a href="login.jsp">로그인</a>
					</c:when>

					<c:otherwise>
						<button onclick="location.href='login'" id="btn_logout">
							<span  style="font-size: 12">로그아웃</span>
						</button>
						<button class="btn btn-primary mar dropdown-toggle" type="button"
							data-toggle="dropdown">${nickName }</button>
						<div class="dropdown-menu">
							<a href="regC" class="dropdown-item">개인정보 관리</a>
						</div>


					</c:otherwise>
				</c:choose></li>
		</ul>
	</div>
				<table id="conTbl">
					<tr>
						<td><jsp:include page="${contentPage }"></jsp:include></td>
					</tr>
				</table>
			
</body>
</html>