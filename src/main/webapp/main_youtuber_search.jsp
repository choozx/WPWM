<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center">
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
	<form action="SearchC">
		<input type="hidden" name="name" value="${summorner.name }">
		<button>일반 검색</button>
	</form>
	<hr>
	<c:forEach begin="0" end="${matchCount }" var="i">
		<table id="youtuberSearchTbl" align="center" style="width: 500px;">
			<tr id="youtuberSearchTd">
				<td>
					<table>
						<tr>
							<c:set var="youtuberDTO" value="youtuberDTO${i }"></c:set>
							<c:forEach var="youtuberinfo" items="${requestScope[youtuberDTO] }">
								<td style="vertical-align: baseline;">
									<div id="youtuberImg">
										<img alt="" src="${youtuberinfo.img }">
									</div>
									<div>								
										<p class="youtuberP">${youtuberinfo.name }</p>
										<p class="youtuberP">${youtuberinfo.subscribers }명</p>
										<p class="youtuberP"><button onclick="location.href=' ${youtuberinfo.channel_url }'"><img id="forwardYoutubeBtn" src="file/searchImg/youtubeLogo.png"></button></p>
									</div>
								</td>
							</c:forEach>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="summornerTd">
				<td>
					<table style="line-height: 1">
					<c:set var="matchDTO" value="matchDTO${i }"></c:set>
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
					<c:forEach var="matchinfo" items="${requestScope[matchDTO] }" varStatus="status">
						<c:if test="${status.index eq 5}">
						<tr>
							<td colspan="5">
								<hr style="margin-top: 1px; margin-bottom: 1px;">
							</td>
						</tr>
						</c:if>
						<tr id="youtubermatchDTO">
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
				</td>			
			</tr>
		</table>	
	</c:forEach>
</body>
</html>