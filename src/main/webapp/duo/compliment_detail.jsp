<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<span id="attributeValue">${c}</span>
	
	<form action="ComplimentRegC?nickname=${sessionScope.accountInfo.nickname }" method="post" onsubmit="return duoRegCheck();" name="duoRegForm">
		<table id="duoRegTbl" align="center" border="1">
			<tr>
				<td colspan="2"><h3 style="color: white;">상세정보</h3></td>
			</tr>
			<tr>
				<td class="duoreg-td01">소환사 이름</td>
				<td class="duoreg-td01">추천</td>
			</tr>
			<tr>
				<td class="duoreg-td02" style="color: white;">${compliment.name}</td>
				<td class="duoreg-td02" style="color: white;">${compliment.likecount}</td>
			</tr>
			<tr>
				<td class="duoreg-td01" colspan="2">메모</td>
			</tr>
			<tr>
				<td class="duoreg-td02" colspan="2" style="color: white; text-align: left">${compliment.memo}</td>
			</tr>
		</table>
	</form>
	<form action="DuoSearchC">
		<table id="duotblSelect">
			<tr id="selectTr">
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
				<td colspan="7" align="right"><button class="btn_duoreg"
						onclick="location.href='ComplimentRegC?nickname=${sessionScope.accountInfo.nickname}'" type="button" value="${sessionScope.accountInfo.nickname }">칭찬하기</button></td>
				</c:otherwise>
			</c:choose>
			</tr>
		</table>
	</form>
	<div id="board-list-wrap">
		<div id="board-list">
			<div id="board-list-head">
				<div id="n5">소환사 이름</div>
				<div id="t5">티어</div>
				<div id="m6">메모</div>
				<div id="w5">추천<a style="color: green" href="ComplimentLikeListC?nickname=${sessionScope.accountInfo.nickname}">(추천순으로)</a></div>
				
				<div id="d5">등록일시</div>
			</div>
			<c:forEach var="c" items="${compliments }" varStatus="status">
				<form action="" onsubmit="" >
				<div id="board-list-body">
					<div id="item">
						<div id="n2">${c.name}
				<c:choose>
					<c:when test="${sessionScope.accountInfo.nickname eq c.name }">
					</c:when>
					<c:otherwise>
							<br>
							<button class="btn_n2" onclick="window.open('SearchC?name=${c.name}','정보조회',
								'width=900,height=730,left=1100,top=0,scrollbar=yes');" type="button">정보조회</button>
							<button class="btn_n2"
								onclick="window.open('MessageC?no=${c.no}&&name=${c.name}','쪽지보내기',
								'width=530,height=730,left=1100,top=0,scrollbar=yes');" type="button">쪽지보내기</button>
					</c:otherwise>
				</c:choose>
						</div>
						<div id="t5" onclick="location.href='ComplimentDetailC?no=${c.no}'"> 
					    <c:if test="${c.tier eq 'IRONI' || c.tier eq 'IRONII' || c.tier eq 'IRONIII' || c.tier eq 'IRONIV'}"><img src="./file/tier_iron.jpg" style="width: 30px;"> ${c.tier}</c:if>
						<c:if test="${c.tier eq 'BRONZEI' || c.tier eq 'BRONZEII' || c.tier eq 'BRONZEIII' || c.tier eq 'BRONZEIV'}"><img src="./file/tier_bronze.jpg" style="width: 30px;"> ${c.tier}</c:if>
						<c:if test="${c.tier eq 'SILVERI' || c.tier eq 'SILVERII' || c.tier eq 'SILVERIII' || c.tier eq 'SILVERIV'}"><img src="./file/tier_silver.jpg" style="width: 30px;"> ${c.tier}</c:if>
						<c:if test="${c.tier eq 'GOLDI' || c.tier eq 'GOLDII' || c.tier eq 'GOLDIII' || c.tier eq 'GOLDIV'}"><img src="./file/tier_gold.jpg" style="width: 30px;"> ${c.tier}</c:if> 
						<c:if test="${c.tier eq 'PLATINUMI' || c.tier eq 'PLATINUMII' || c.tier eq 'PLATINUMIII' || c.tier eq 'PLATINUMIV'}"><img src="./file/tier_platinum.jpg" style="width: 30px;"> ${c.tier}</c:if> 
						<c:if test="${c.tier eq 'DIAMONDI' || c.tier eq 'DIAMONDII' || c.tier eq 'DIAMONDIII' || c.tier eq 'DIAMONDIV'}"><img src="./file/tier_diamond.jpg" style="width: 30px;"> ${c.tier}</c:if> 
						<c:if test="${c.tier eq 'MASTERI'}"><img src="./file/tier_master.jpg" style="width: 30px;"> ${c.tier}</c:if> 
						<c:if test="${c.tier eq 'GRANDMASTERI'}"><img src="./file/tier_grandmaster.jpg" style="width: 30px;"> ${c.tier}</c:if> 
						<c:if test="${c.tier eq 'CHALLENGERI'}"><img src="./file/tier_challenger.jpg" style="width: 30px;"> ${c.tier}</c:if> 
						
						</div>
						<div id="m5" onclick="location.href='ComplimentDetailC?no=${c.no}'">${c.memo}</div>
						
						<div id="w5">${c.likecount } </div>
						
						<div id="d5">${c.date}
							<button style="float: right;" class="material-icons i_btn"
								onclick="location.href='ComplimentUpdateC?no=${c.no}'" type="button">edit</button>
							<button style="float: right;" class="material-icons i_btn"
								onclick="location.href='ComplimentDelC?no=${c.no}&&name=${c.name} '"
								type="button">delete</button>
						</div>

					</div>
				</div>
				</form>
			</c:forEach>
			<div id = "duoPaging">
					<c:choose>
						<c:when test="${curPageNo ne 1}">
						<a href="ComplimentPageController?p=1" class="duopaging-num">&lt&lt</a>&nbsp;&nbsp;
						<a href="ComplimentPageController?p=${curPageNo - 1}" class="duopaging-num">&lt</a>
						</c:when>
						<c:otherwise>
						<span class="duopaging-num">&lt&lt</span>&nbsp;&nbsp;
						<span class="duopaging-num">&lt</span>
						</c:otherwise>
					</c:choose>
					<c:forEach var="c" begin="1" end="${pageCount}">
					<a href="ComplimentPageController?p=${c}" class="duopaging-num">[${c}]</a>
					</c:forEach>
					<c:choose>
						<c:when test="${curPageNo eq pageCount}">
					<span class="duopaging-num">&gt</span>&nbsp;&nbsp;
					<span class="duopaging-num" >&gt&gt</span>
						</c:when>
						<c:otherwise>
					<a href="ComplimentPageController?p=${curPageNo + 1}" class="duopaging-num">&gt</a>&nbsp;&nbsp;
					<a href="ComplimentPageController?p=${pageCount}" class="duopaging-num" >&gt&gt</a>
						</c:otherwise>
					</c:choose>
				</div>
		</div>
	</div>
			<form action="ComplimentSearchC" method="post" name="duoSearchForm" onsubmit="return duoSearchCheck();">
				<p id ="searchNick">
					<input id="searchNickInput" placeholder="닉네임을 입력하세요" name="searchNick">
					<button id="btn_searchNick">검색</button>
				</p>
			</form>
</body>
</html>