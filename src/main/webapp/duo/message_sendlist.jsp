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
	<div>
		<p class="text-center02">
			&nbsp;<span id="messnickname" style="background-color: gray">${sessionScope.accountInfo.nickname }</span>님의
			보낸쪽지함 <span><a class="message-menu02"
				href="MessageSendlistC?nickname=${sessionScope.accountInfo.nickname}">보낸쪽지</a></span>
			<span><a class="message-menu02"
				href="MessageRecvlistC?nickname=${sessionScope.accountInfo.nickname}">받은쪽지</a></span>
		</p>
		<div id="board_list_wrap1">
			<div id="board-list">
				<form action="MessageDelController?nickname=${sessionScope.accountInfo.nickname }"
					 onsubmit="return confirm('정말 삭제하시겠습니까?')" type="submit">
				<div id="board-list-head1">
					<div id="p2">받는이</div>
					<div id="n4">내용</div>
					<div id="t1">
						날짜
						<button style="float: right;" class="material-icons i_btn"
							name="nickname" value="${sessionScope.accountInfo.nickname }">delete</button>
					</div>
				</div>
				</form>


				<c:forEach var="m" items="${sendMessages }" varStatus="status">
						<form action="MessageDelController?no=${m.no}" onsubmit="return confirm('정말 삭제하시겠습니까?')" type="submit" >
						<div id="board-list-body">
							<c:choose>
								<c:when
									test="${sessionScope.accountInfo.nickname eq m.sendname}">
									<div id="item">
										<div id="n3">${m.recvname}<br>
											<button class="btn_n2" onclick="window.open('SearchC?name=${m.recvname}','정보조회',
								'width=900,height=730,left=1100,top=0,scrollbar=yes');" type="button">정보조회</button>
											<button class="btn_n2"
												onclick="window.open('MessageC?nickname=${sessionScope.accountInfo.nickname}&&name=${m.recvname}','쪽지보내기',
								'width=430,height=400,left=1100,top=0,scrollbar=yes');" type="button">쪽지보내기</button>
										</div>
										<div id="m4" onclick="location.href='MessageSendDetailC?no=${m.no}'">${m.content}</div>
										<div id="d1">
											<fmt:formatDate value="${m.date }" type="both"
												dateStyle="short" timeStyle="short" />
											<button style="float: right;" class="material-icons i_btn"
												name="no" value="${m.no}"
												>delete</button>
										</div>

									</div>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</div>
					</form>
				</c:forEach>
			</div>
		</div>
		<div align="center" style="background-color: #E1EEF5;">
			<button onclick="location.href='MessageSendC?nickname=${sessionScope.accountInfo.nickname}&&id=${sessionScope.accountInfo.id}'">새 쪽지 작성</button>
		</div>
	</div>
</body>
</html>