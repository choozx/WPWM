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
	<form action="DuoUpdateC" method="post" name="duoUpdateForm" onsubmit="return duoUpdateCheck();">
		<table id="duoRegTbl" align="center" border="1">
			<tr>
				<td colspan="2"><h3 style="color: white;">소환사 수정하기</h3></td>
			</tr>
			<tr>
				<td class="duoupdate-td01" colspan="2">소환사 이름</td>
			</tr>
			<tr>
				<td class="duoupdate-td02" colspan="2" style="color: white;">${duo.name}</td>
			</tr>
			<tr>
				<td class="duoupdate-td01">주 포지션</td>
				<td class="duoupdate-td01">큐 타입</td>
			<tr>
				<td class="duoupdate-td02" style="color: white;">${duo.position}<select name="position"
					style="width: 100%">
						<option value="positionAll">포지션 전체</option>
						<option value="top">탑</option>
						<option value="jg">정글</option>
						<option value="mid">미드</option>
						<option value="adc">원딜</option>
						<option value="supp">서포터</option>
				</select></td>
				<td class="duoupdate-td02" style="color: white;">${duo.type}<select name="queue"
					style="width: 100%">
						<option value="solo">솔로랭크</option>
						<option value="free">자유랭크</option>
						<option value="normal">일반(비공개 선택)</option>
						<option value="etc">무작위 총력전</option>
				</select></td>
			</tr>
			<tr>
				<td class="duoupdate-td01" colspan="2">메모</td>
			</tr>
			<tr>
				<td class="duoupdate-td02" colspan="2"><textarea name="duoMemo"
						style="width: 100%";>${duo.memo}</textarea></td>
			</tr>
			<tr>
				<td class="duoupdate-td01">삭제 비밀번호</td>
				<td class="duoupdate-td02"><input name="duoPw" style="width: 95%"
					placeholder="4자리 숫자로 입력해주세요" type="password" maxlength="4"></td>
			</tr>
			<tr>
				<td class="duoupdate-td01"><button class="btn_duocancel"
						onclick="history.go(-1)" type="button">취소</button></td>
				<td class="duoupdate-td02"><button class="btn_duoreg02"
						value="${duo.no}" name="no">등록</button></td>
			</tr>
		</table>
	</form>
	<form action="DuoSearchC">
		<table id="duotblSelect">
			<tr id="selectTr">
				<td><select name="position" class="select-td">
						<option value="positionAll">포지션 전체</option>
						<option value="top">탑</option>
						<option value="jg">정글</option>
						<option value="mid">미드</option>
						<option value="adc">원딜</option>
						<option value="supp">서포터</option>
				</select> <select name="queue" class="select-td">
						<option value="solo">솔로랭크</option>
						<option value="free">자유랭크</option>
						<option value="normal">일반(비공개 선택)</option>
						<option value="etc">무작위 총력전</option>
				</select> <select name="tier" class="select-td">
						<option value="ALL">티어 전체</option>
						<option value="IRON">아이언</option>
						<option value="BRONZE">브론즈</option>
						<option value="SILVER">실버</option>
						<option value="GOLD">골드</option>
						<option value="PLATINUM">플래티넘</option>
						<option value="DIAMOND">다이아</option>
						<option value="MASTER">마스터</option>
						<option value="GRANDMASTER">그랜드마스터</option>
						<option value="CHALLENGER">챌린저</option>
				</select>
					<button class="btn_select" >검색</button>
					<button class="btn_select" onclick="history.go(-1)" type="button">돌아가기</button></td>
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
						onclick="location.href='DuoRegC?name=${sessionScope.accountInfo.nickname }'" type="button" value="${sessionScope.accountInfo.nickname }">소환사 등록하기</button></td>
				</c:otherwise>
			</c:choose>
			</tr>
		</table>
	</form>
	<div id="board-list-wrap">
		<div id="board-list">
			<div id="board-list-head">
				<div id="n">소환사 이름</div>
				<div id="p">주 포지션</div>
				<div id="t">티어</div>
				<div id="w">승률</div>
				
				<div id="m">메모</div>
				<div id="d">등록일시</div>
			</div>
			<c:forEach var="d" items="${duos }" varStatus="status">
				<div id="board-list-body">
					<div id="item">
						<div id="n2">${d.name}
				<c:choose>
					<c:when test="${sessionScope.accountInfo.nickname eq d.name }">
					</c:when>
					<c:otherwise>
							<br>
							<button class="btn_n2" onclick="window.open('SearchC?name=${d.name}','정보조회',
								'width=900,height=730,left=1100,top=0,scrollbar=yes');" type="button">정보조회</button>
							<button class="btn_n2"
								onclick="window.open('MessageC?no=${d.no}&&name=${d.name}','쪽지보내기',
								'width=530,height=730,left=1100,top=0,scrollbar=yes');">쪽지보내기</button>
					</c:otherwise>
				</c:choose>
						</div>
						<div id="p" onclick="location.href='DuoDetailC?no=${d.no}'">${d.position}</div>
						<div id="t" onclick="location.href='DuoDetailC?no=${d.no}'"> 
					    <c:if test="${d.tier eq 'IRONI' || d.tier eq 'IRONII' || d.tier eq 'IRONIII' || d.tier eq 'IRONIV'}"><img src="./file/tier_iron.jpg" style="width: 30px;"> ${d.tier}</c:if>
						<c:if test="${d.tier eq 'BRONZEI' || d.tier eq 'BRONZEII' || d.tier eq 'BRONZEIII' || d.tier eq 'BRONZEIV'}"><img src="./file/tier_bronze.jpg" style="width: 30px;"> ${d.tier}</c:if>
						<c:if test="${d.tier eq 'SILVERI' || d.tier eq 'SILVERII' || d.tier eq 'SILVERIII' || d.tier eq 'SILVERIV'}"><img src="./file/tier_silver.jpg" style="width: 30px;"> ${d.tier}</c:if>
						<c:if test="${d.tier eq 'GOLDI' || d.tier eq 'GOLDII' || d.tier eq 'GOLDIII' || d.tier eq 'GOLDIV'}"><img src="./file/tier_gold.jpg" style="width: 30px;"> ${d.tier}</c:if> 
						<c:if test="${d.tier eq 'PLATINUMI' || d.tier eq 'PLATINUMII' || d.tier eq 'PLATINUMIII' || d.tier eq 'PLATINUMIV'}"><img src="./file/tier_platinum.jpg" style="width: 30px;"> ${d.tier}</c:if> 
						<c:if test="${d.tier eq 'DIAMONDI' || d.tier eq 'DIAMONDII' || d.tier eq 'DIAMONDIII' || d.tier eq 'DIAMONDIV'}"><img src="./file/tier_diamond.jpg" style="width: 30px;"> ${d.tier}</c:if> 
						<c:if test="${d.tier eq 'MASTERI'}"><img src="./file/tier_master.jpg" style="width: 30px;"> ${d.tier}</c:if> 
						<c:if test="${d.tier eq 'GRANDMASTERI'}"><img src="./file/tier_grandmaster.jpg" style="width: 30px;"> ${d.tier}</c:if> 
						<c:if test="${d.tier eq 'CHALLENGERI'}"><img src="./file/tier_challenger.jpg" style="width: 30px;"> ${d.tier}</c:if> 
						
						</div>
						<div id="w" onclick="location.href='DuoDetailC?no=${d.no}'">
						 <c:set var="w" value="${d.win}"></c:set>
						 <c:set var="l" value="${d.lose}"></c:set>
						 <span style="color: #00BBA3">${w}승</span>&nbsp; &nbsp;<span style="color: #E84057">${l}패</span>
						 <br>
						 <c:choose>
						 <c:when test="${w >= l}">
						 <span style="color: #00BBA3"><fmt:formatNumber value="${(w/(w+l)) * 100.00}" pattern=".00"/>%</span>
						 </c:when>
						 <c:otherwise>
						 <span style="color: #E84057"><fmt:formatNumber value="${(w/(w+l)) * 100.00}" pattern=".00"/>%</span>
						 </c:otherwise>
						 </c:choose>
						</div>
						<div id="m2" onclick="location.href='DuoDetailC?no=${d.no}'">${d.memo}</div>
						<div id="d">${d.date}
							<button style="float: right;" class="material-icons i_btn"
								onclick="location.href='DuoUpdateC?no=${d.no}'" type="button">edit</button>
							<button style="float: right;" class="material-icons i_btn"
								onclick="location.href='DuoDelController?no=${d.no}&&name=${d.name}'"
								type="button">delete</button>
						</div>

					</div>
				</div>
			</c:forEach>
			<div id = "duoPaging">
					<c:choose>
						<c:when test="${curPageNo ne 1}">
						<a href="DuoPageController?p=1" class="duopaging-num">&lt&lt</a>&nbsp;&nbsp;
						<a href="DuoPageController?p=${curPageNo - 1}" class="duopaging-num">&lt</a>
						</c:when>
						<c:otherwise>
						<span class="duopaging-num">&lt&lt</span>&nbsp;&nbsp;
						<span class="duopaging-num">&lt</span>
						</c:otherwise>
					</c:choose>
					<c:forEach var="d" begin="1" end="${pageCount}">
					<a href="DuoPageController?p=${d}" class="duopaging-num">[${d}]</a>
					</c:forEach>
					<c:choose>
						<c:when test="${curPageNo eq pageCount}">
					<span class="duopaging-num">&gt</span>&nbsp;&nbsp;
					<span class="duopaging-num" >&gt&gt</span>
						</c:when>
						<c:otherwise>
					<a href="DuoPageController?p=${curPageNo + 1}" class="duopaging-num">&gt</a>&nbsp;&nbsp;
					<a href="DuoPageController?p=${pageCount}" class="duopaging-num" >&gt&gt</a>
						</c:otherwise>
					</c:choose>
				</div>
		</div>
	</div>
	<form action="DuoSearchC" method="post" name="duoSearchForm" onsubmit="return duoSearchCheck();">
				<p id ="searchNick">
					<input id="searchNickInput" placeholder="닉네임을 입력하세요" name="searchNick">
					<button id="btn_searchNick">검색</button>
				</p>
			</form>
	
</body>
</html>