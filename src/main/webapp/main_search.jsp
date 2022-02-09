<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style >
#searchMain{
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>

<body>
<table id="searchMain" align="center">
	<tr>
		<td>
			<img id="tierIcon" alt="" src="file/lolIcon/${tier.tier }.png">
		</td>
		<td>
			${tier.tier} ${tier.rank} ${tier.leaguePoints}<p>
			${summorner.name }<p>
			Lv.${summorner.level }<p>
			승/패 ${tier.wins } / ${tier.losses }
		</td>
	</tr>
</table>
	<hr>
	<form action="YoutuberSearchC">
		<input type="hidden" name="name" value="${summorner.name }">
		<button>유튜버 검색</button>
	</form>
	<hr>
	<c:forEach var="matchDTO" items="${matchAll }">
		<table id="summornerTd" align="center" style="width: 500px;">
			<tr>
				<td class="matchText">
					닉네임
				</td>
				<td class="matchText">
					챔피언
				</td>
				<td class="matchText">
					KDA
				</td>
				<td class="matchText">
					데미지
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<hr style="margin-top: 1px; margin-bottom: 1px; color: #ADD8E6">
				</td>
			</tr>
			<c:forEach var="matchinfo" items="${matchDTO }" varStatus="status">
				<c:if test="${status.index eq 5}">
					<tr>
						<td colspan="5">
							<hr style="margin-top: 1px; margin-bottom: 1px;">
						</td>
					</tr>
				</c:if>
					<tr>
					<td width="160px">
						${matchinfo.sumornerName }
					</td>
					<td width="100px">
						${matchinfo.championName }
					</td>
					<td width="140px">
						${matchinfo.kda } &nbsp					
						${matchinfo.kill }/${matchinfo.deaths }/${matchinfo.assists }
					</td>
					<td width="100px">
						${matchinfo.damage }
					</td>
				</tr>
			</c:forEach>
		</table>	
	</c:forEach>
</body>
</html>