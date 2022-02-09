<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/message.css">
<script type="text/javascript" src="js/message.js"></script>
<script type="text/javascript" src="js/validCheck.js"></script>
<body>
	<div class="container bootdey">
				<p class="text-center">&nbsp;<span id="messnickname" style="background-color: gray" >${sessionScope.accountInfo.nickname }</span>님의 쪽지함
				<span style="text-align: right"><a class="message-menu" href="MessageSendlistC?nickname=${sessionScope.accountInfo.nickname}" >보낸쪽지</a></span>
				<span style="text-align: right"><a class="message-menu" href="MessageRecvlistC?nickname=${sessionScope.accountInfo.nickname }">받은쪽지</a></span>
				</p>
				<form>
					<div class="form-row mb-3" >
						<label for="to" class="col-2 col-sm-1 col-form-label">- 보내는사람 &nbsp; &nbsp;: ${sessionScope.accountInfo.nickname }  </label>
					</div>
					<div class="form-row mb-3">
						<label for="cc" class="col-2 col-sm-1 col-form-label">- 받는사람 &nbsp; &nbsp; &nbsp; : ${param.name }  </label>
					</div>
				</form>
				<p></p>
				<form action="MessageC?nickname=${sessionScope.accountInfo.nickname}&&name=${param.name}" method="post" onsubmit="return confirmMessage()" name="messageform">
						<div class="form-group mt-4">
							<textarea class="form-control" id="message" name="message" rows="12"
								placeholder="메시지를 입력하세요(100자)" maxlength="100" required="required"></textarea>
						</div>
						<div class="form-group">
							<button class="btn btn-success">쪽지보내기</button>
							<button onClick="window.close()" type="button" value="창닫기">취소</button>
						</div>
					</div>
				</form>
				
</body>
</html>