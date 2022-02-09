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
<script>

function leave(){
	
	let ok = confirm('정말 탈퇴하시겠습니까?');
	let deleteInput = document.deleteForm.pw;
	if(isEmpty(deleteInput)){
		alert('비밀번호를 확인하세요')
		
		
		deleteInput.focus;
		deleteInput.value == "";
		return false;
	} 
	
	if(ok){
		
	return true;

	}else{
		return false;
	}
	}
			



</script>



<form action="leave" method="post" onsubmit="return leave();" name="deleteForm">
				<table style="margin-top: 190px;" id="reg3Tbl">
					<tr class="">
						<td><a href="edit">개인정보 관리</a></td>
						<td><a href="changePassword">비밀번호 변경</a></td>
						<td><a href="leave">회원탈퇴</a></td>
					</tr>
					
					<tr>
						<td colspan="3" class="leaveTd">회원탈퇴 전에 반드시 유의사항을 확인하고 진행해 주세요.</td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd" ><h4>개인정보 및 서비스 이용 기록 삭제</h4></td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd">이메일 ID에 소셜 계정을 연결한 경우 탈퇴 시 연결 정보도 함께 삭제됩니다.</td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd"><h4>커뮤니티 서비스 등록 게시물 유지</h4></td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd">회원가입 이후 등록하신 게시물들은 회원탈퇴 후에도 삭제 되지 않고 유지됩니다. 삭제를 원하시는 경우에는 직접 삭제하신 후 회원탈퇴를 진행하시기 바랍니다.</td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd"><h4>개인정보 보관</h4></td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd">회원 탈퇴 시 일부 개인정보는 개인정보처리방침에 따라 탈퇴일로부터 30일간 보관되며, 그 이후 관계법령에 필요한 경우에는 별도 보관합니다.</td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd"><h4>탈퇴 후 제한</h4></td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd">탈퇴 처리된 ID는 30일동안 재가입이 불가합니다.</td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd"><input placeholder="비밀번호를 입력하세요" name="pw" ></td>
					</tr>
					<tr>
						<td colspan="3" class="leaveTd"><button class="loginBtn" value="${sessionScope.accountInfo.id }" name="id">동의</button></td>
					</tr>
						
					
			
		
				</table>
	</form>
</body>
</html>