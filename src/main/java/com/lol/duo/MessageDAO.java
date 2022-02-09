package com.lol.duo;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.main.DBManager;

public class MessageDAO {

	public static void regMessage(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into message values(message_seq.nextval, ?, ?, ?, sysdate)";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			String sendname = request.getParameter("nickname");
			String recvname = request.getParameter("name");
			String message = request.getParameter("message");
		
			System.out.println(sendname);
			System.out.println(recvname);
			System.out.println(message);
			
			pstmt.setString(1, sendname);
			pstmt.setString(2, recvname);
			pstmt.setString(3, message);
			if(pstmt.executeUpdate() == 1) {
				sql = "insert into sendmessage values(sendmessage_seq.nextval, ?, ?, ?, sysdate)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sendname);
				pstmt.setString(2, recvname);
				pstmt.setString(3, message);
				pstmt.executeUpdate();
			}
			
			
		} catch (Exception SQLIntegrityConstraintViolationException) {
			SQLIntegrityConstraintViolationException.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		}	finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
	}
	
	public static void regMessage2(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from lol_account where a_nickname=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			String recvname = request.getParameter("recvN");
			System.out.println(recvname);
			pstmt.setString(1, recvname);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbName = rs.getString("a_nickname");
			 if(recvname.equals(dbName)) {
				 sql = "insert into message values(message_seq.nextval, ?, ?, ?, sysdate)";
				 pstmt = con.prepareStatement(sql);
				 String sendname = request.getParameter("nickname"); 
				 recvname = request.getParameter("recvN"); 
				 String message = request.getParameter("message");
						 
						 
				 System.out.println(sendname);
				 System.out.println(recvname);
				 System.out.println(message);
						 
				 pstmt.setString(1, sendname);
				 pstmt.setString(2, recvname);
				 pstmt.setString(3, message);
				
				 if (pstmt.executeUpdate() == 1) {
					 sql = "insert into sendmessage values(sendmessage_seq.nextval, ?, ?, ?, sysdate)";
					 pstmt = con.prepareStatement(sql);
					 pstmt.setString(1, sendname);
					 pstmt.setString(2, recvname);
					 pstmt.setString(3, message);
					 pstmt.executeUpdate();
					 response.setContentType("text/html; charset=euc-kr");
					 PrintWriter out = response.getWriter(); 
						String msg = "쪽지 보내기 성공";
						   String str="";
						   str = "<script language='javascript'>";
						   str += "alert('"+ msg + "');";   //얼럿창 띄우기
						   str += "</script>";
						   out.print(str);
						
						  str="";
						   str = "<script language='javascript'>";
						   str += "self.close();";   // 창닫기
						    str += "</script>";
						   out.print(str);
					  }
				  } 
				 }  else {
					 System.out.println("존재하지 않는 회원입니다");
					 response.setContentType("text/html; charset=euc-kr");
					 PrintWriter out = response.getWriter(); 
						String msg = "존재하지 않는 회원입니다";
						   String str="";
						   str = "<script language='javascript'>";
						   str += "alert('"+ msg + "');";   //얼럿창 띄우기
						   str += "history.go(-1);";    //이전페이지로 가기
						   str += "</script>";
						   out.print(str);
						
						  
		  }
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		}	finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
	}
	
	public static void getRecvMessage(HttpServletRequest request) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from message order by m_sendtime desc";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<Message> messages = new ArrayList<Message>();
			Message m = null;
			while (rs.next()) {
				m = new Message();
				m.setNo(rs.getInt("m_no"));
				m.setSendname(rs.getString("m_sendnick"));
				m.setRecvname(rs.getString("m_recvnick"));
				m.setContent(rs.getString("m_content"));
				m.setDate(rs.getDate("m_sendtime"));
				
				messages.add(m);
			}
			request.setAttribute("Recvmessages", messages);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		

		
	}

	public static void getSendMessage(HttpServletRequest request) {
		
				
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select * from sendmessage order by sm_sendtime desc";
			try {
				request.setCharacterEncoding("utf-8");
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				ArrayList<Message2> messages = new ArrayList<Message2>();
				Message2 m = null;
				while (rs.next()) {
					m = new Message2();
					m.setNo(rs.getInt("sm_no"));
					m.setSendname(rs.getString("sm_sendnick"));
					m.setRecvname(rs.getString("sm_recvnick"));
					m.setContent(rs.getString("sm_content"));
					m.setDate(rs.getDate("sm_sendtime"));
					
					messages.add(m);
				}
				request.setAttribute("sendMessages", messages);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("d", "조회 실패!");
				System.out.println("조회 실패!");
			} finally {
				DBManager.close(con, pstmt, rs);
			}
			
			
			
			

			
		}

	public static void deleteRecvMessage(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
									
		String sql = "delete message where m_no = ?";
		try {
		request.setCharacterEncoding("utf-8");
		   con = DBManager.connect();
		   pstmt = con.prepareStatement(sql);
		   String no = request.getParameter("no");
		   pstmt.setString(1, no);
		   System.out.println(no);
			
			
		if (pstmt.executeUpdate() == 1) {
			request.setAttribute("d", "쪽지 삭제 성공!");
			System.out.println("쪽지 삭제 성공");
		} else {
			request.setAttribute("d", "쪽지 삭제 실패!");
			System.out.println("쪽지 삭제 실패");
		}
			
			
			
	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
		
	}
	
public static void deleteSendMessage(HttpServletRequest request,  HttpServletResponse response) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
									
		String sql = "delete sendmessage where sm_no = ?";
		try {
		request.setCharacterEncoding("utf-8");
		   con = DBManager.connect();
		   pstmt = con.prepareStatement(sql);
		   String no = request.getParameter("no");
		   pstmt.setString(1, no);
		   System.out.println(no);
			
			
		if (pstmt.executeUpdate() == 1) {
			request.setAttribute("d", "쪽지 삭제 성공!");
			System.out.println("쪽지 삭제 성공");
			
		} else {
			request.setAttribute("d", "쪽지 삭제 실패!");
			System.out.println("쪽지 삭제 실패");
		}
			
			
			
	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
		
	}

	public static void deleteAllRecvMessage(HttpServletRequest request,  HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
									
		String sql = "delete message where m_recvnick = ?";
		try {
		request.setCharacterEncoding("utf-8");
		   con = DBManager.connect();
		   pstmt = con.prepareStatement(sql);
		   String recvnick = request.getParameter("nickname");
		   pstmt.setString(1, recvnick);
		   System.out.println(recvnick);
			
			
		if (pstmt.executeUpdate() >= 1) {
			System.out.println("쪽지 삭제 성공");
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('받은 쪽지 모두를 삭제했습니다.');");
			script.println("location.href = 'MessageRecvlistC';");
			script.println("</script>");
			script.close();
		} else {
			request.setAttribute("d", "쪽지 삭제 실패!");
			System.out.println("쪽지 삭제 실패");
		}
			
			
			
	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void deleteAllSendMessage(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
									
		String sql = "delete sendmessage where sm_sendnick = ?";
		try {
		request.setCharacterEncoding("utf-8");
		   con = DBManager.connect();
		   pstmt = con.prepareStatement(sql);
		   String recvnick = request.getParameter("nickname");
		   pstmt.setString(1, recvnick);
		   System.out.println(recvnick);
			
			
		if (pstmt.executeUpdate() >= 1) {
			System.out.println("쪽지 삭제 성공");
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('보낸 쪽지 모두를 삭제했습니다.');");
			script.println("location.href = 'MessageSendlistC';");
			script.println("</script>");
			script.close();
		
		
		} else {
			request.setAttribute("d", "쪽지 삭제 실패!");
			System.out.println("쪽지 삭제 실패");
		}
			
			
			
	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
		
		
		
	}
	public static void detailRecvMessage(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		String sql = "select * from message where m_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));

					
			
			
			rs = pstmt.executeQuery();
			
 			Message m = null;
		if (rs.next()) {
			m = new Message();
			m.setNo(rs.getInt("m_no"));
			m.setSendname(rs.getString("m_sendnick"));
			m.setRecvname(rs.getString("m_recvnick"));
			m.setContent(rs.getString("m_content"));
			m.setDate(rs.getDate("m_sendtime"));
			request.setAttribute("message", m);
		}	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
	}
	public static void detailSendMessage(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		String sql = "select * from sendmessage where sm_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));

					
			
			
			rs = pstmt.executeQuery();
			
 			Message2 m = null;
		if (rs.next()) {
			m = new Message2();
			m.setNo(rs.getInt("sm_no"));
			m.setSendname(rs.getString("sm_sendnick"));
			m.setRecvname(rs.getString("sm_recvnick"));
			m.setContent(rs.getString("sm_content"));
			m.setDate(rs.getDate("sm_sendtime"));
			request.setAttribute("sendMessage", m);
		}	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
	}
		
	}

