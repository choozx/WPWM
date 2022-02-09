<%@page import="com.lol.main.DBManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String id = request.getParameter("id");
if(id == null ){ 
%>

<script>
 alert("잘못된 경로로의 접근입니다!");
 self.close(); //팝업창 닫힘
</script>	
	
<%
return; //jsp 종료
}

id = id.trim(); //trim :아이디 앞뒤 공백일경우 제거 

if(id.length() < 4 || id.length() > 12){ //trim :아이디 앞뒤 공백일경우 제거 
%>

<script>
 alert("잘못된 아이디 입니다.");
 self.close(); //팝업창 닫힘
</script>	
	
<%
return; //jsp 종료
}

Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
String sql = "select count(*) as cnt from lol_account where a_id ='"+id+"'  ";
	   
	   con = DBManager.connect();
	   pstmt = con.prepareStatement(sql);
	   rs = pstmt.executeQuery();
	  
	   rs.next();
	   
	   int cnt = rs.getInt("cnt");
	  rs.close();
	  pstmt.close();
	  con.close();
%>
	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body{
width: 320px;
height:400px;
overflow-x: hidden;
overflow-y: hidden;
font-size: 20px;
}
#div1{
	width: 100%;
	height: 10px;
	background: #344a72;
	text-align: center;
	vertical-align: middle; 
	font-size: 20px;
	font-weight: bold;
	line-height: 10.0;
}
#div2 {
	text-align: center;
	vertical-align: middle; 
	font-size: 18px;
	font-weight: bold;
	line-height: 10.0;
}
#cPwbtn{
	width: 100px;
	height: 35px;
	margin-top: 20px;
	border: none;
	background-color: #49c1a2;
	color: white;
	font-size: 18px;
	border-radius: 6px;
	position: relative;
	left: 5px
}
</style>
<body>
<div id="div1">
<span id="div2">
<%
if( cnt == 0){
	out.print("사용 가능한 아이디입니다.");
	%>
<script>

opener.document.frm.chk.value = "1";
</script>	
	
	<%
}else{
	out.print("이미 사용중인 아이디입니다.");
%>
<script>
opener.document.frm.chk.value = "0";
</script>	
	<%
}
%>
</span>
<br>
<button type="button" onclick="self.close()" id="cPwbtn">닫기</button>
</div>
</body>
</html>