package com.lol.suggestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.lol.main.DBManager;



public class SuggestionDAO {
	
	private ArrayList<Suggestion> suggestions;
	
	private static final SuggestionDAO DDAO = new SuggestionDAO();
	
	
	private SuggestionDAO() {
	
	}
	
	public static SuggestionDAO getDdao() {
		return DDAO;
	}

	public  void paging(int page, HttpServletRequest request) { 
		
		request.setAttribute("curPageNo", page); // int page 현재 머물러있는 페이지를 view로 넘길거임 
		
		// 전체 페이지 수 계산
		  int cnt = 10; // 한 페이지당 보여줄 데이터 개수
		  int total = suggestions.size(); // 총 데이터 개수
		  
		  // 페이지개수
		  int pageCount = (int)Math.ceil((double)total / cnt);
		  request.setAttribute("pageCount", pageCount);
		  int pageCountSize = (int)Math.ceil((double)pageCount / 10);;
		  request.setAttribute("pageCount2", pageCountSize);
		 
		  int start = total - (cnt * (page -1)); // 최신글을 먼저 보려고
		  
		  
		  int end = (page == pageCount) ? -1 : start - (cnt + 1);	
	  
		  // 나머지는 데이터를 페이징에 실어주는 일
		  ArrayList<Suggestion> items = new ArrayList<Suggestion>();
		  try {
			  for (int i = start-1; i > end; i--) {
				  // start-1 은 인덱스 번호 맞추게 하기 위하여
			  
				  items.add(suggestions.get(i));
			  }
			  request.setAttribute("suggestions",items);
		
		  } catch (Exception IndexOutOfBoundsException) {
			  request.setAttribute("d", "검색 결과가 없습니다");
		  }
		  	
	}
		  	
	public void regDuo(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into SUGGESTION values(SUGGESTION_seq.nextval, ?, ?, ?, ?, ?, sysdate)";
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			
			String headname = request.getParameter("headname");			
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String text = request.getParameter("text");
			String pw = request.getParameter("pw");
		
			System.out.println(headname);
			System.out.println(name);
			System.out.println(title);
			System.out.println(text);
			System.out.println(pw);
			
			pstmt.setString(1, headname);
			pstmt.setString(2, name);
			pstmt.setString(3, title);
			pstmt.setString(4, pw);
			pstmt.setString(5, text);
		
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("d", "등록 성공!");
				System.out.println("등록 성공!");
			} else {
				request.setAttribute("d", "등록 실패!");
				System.out.println("등록 실패!");
			}
			
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("d", "DB서버 문제..");
				System.out.println("DB서버 문제..");
			} finally {
				DBManager.close(con, pstmt, null);
			}
		
		}

	public void getAllDuo(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from suggestion order by s_date ";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			suggestions = new ArrayList<Suggestion>();
			Suggestion s = null;
			while (rs.next()) {
				s = new Suggestion();
				s.setNo(rs.getInt("s_no"));
				s.setName(rs.getString("s_name"));
				s.setPw(rs.getString("s_pw"));
				s.setHeadname(rs.getString("s_headname"));
				s.setTitle(rs.getString("s_title"));
				s.setText(rs.getString("s_text"));
				s.setDate(rs.getDate("s_date"));
				
				suggestions.add(s);
			}
			request.setAttribute("suggestions", suggestions);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public void updateDuo(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from suggestion where s_no=?";

		try {
			request.setCharacterEncoding("utf-8");
		    con = DBManager.connect();
		    pstmt = con.prepareStatement(sql);

		    String no = request.getParameter("no");
 		    String pw = request.getParameter("pw");
 		    System.out.println(no);
		    System.out.println(pw); 
		    pstmt.setString(1, no);
		   
		    rs = pstmt.executeQuery();
		    rs.next();
			String dbPw = rs.getString("s_pw");
			    if (pw.equals(dbPw)) {
			    	sql = "update suggestion set s_headname=?, s_title=?, s_text=? where s_no=?";
			    	pstmt = con.prepareStatement(sql);
				    String headname = request.getParameter("headname");
				    String title = request.getParameter("title");
				    String text = request.getParameter("text");
			
				    pstmt.setString(1, headname);
				    pstmt.setString(2, title);
				    pstmt.setString(3, text);
				    pstmt.setString(4, no);
				    if(pstmt.executeUpdate() == 1) {
				    	System.out.println("수정 성공!");
				    	request.setAttribute("d", "수정 성공!");
				
				    } else {	
				    	System.out.println("비밀번호를 확인하세요");
				    	request.setAttribute("d", "비밀번호를 확인하세요");
				    }
			    }
			
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public void getDuo(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		String sql = "select * from suggestion where s_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));


			rs = pstmt.executeQuery();
			
 			Suggestion s = null;
 			rs.next();
 				s = new Suggestion();
 				s.setNo(rs.getInt("s_no"));
 				s.setName(rs.getString("s_name"));
 				s.setPw(rs.getString("s_pw"));
 				s.setHeadname(rs.getString("s_headname"));
 				s.setTitle(rs.getString("s_title"));
 				s.setText(rs.getString("s_text"));
 				s.setDate(rs.getDate("s_date"));
 				request.setAttribute("suggestion", s);
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(con, pstmt, rs);
			}
		
		}

	public void deleteDuo(HttpServletRequest request) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select s_pw from suggestion where s_no = ?";
				
		
		try {
			request.setCharacterEncoding("utf-8");
		    con = DBManager.connect();
		    pstmt = con.prepareStatement(sql);
		    int no = Integer.parseInt(request.getParameter("no"));
		    String pw = request.getParameter("userPw");  // ==>> 유저가 입력한 pw : null
		   
		    System.out.println(no);
		    System.out.println(pw);
		   
		    pstmt.setInt(1, no);
		    rs = pstmt.executeQuery();
		  
		    rs.next();
		    String dbPw = rs.getString("s_pw"); // db 비번값 
			if (pw.equals(dbPw) ) { // null == db비번값 
			    sql = "delete suggestion where s_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);				
				if(pstmt.executeUpdate() == 1) {
					System.out.println("삭제 성공!");
					request.setAttribute("d", "삭제 성공!");
				} 
			} else {
				System.out.println("비밀번호를 확인하세요");
				request.setAttribute("d", "비밀번호를 확인하세요");
		}	
		   

		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		}finally {
			DBManager.close(con, pstmt, rs);
		}
	
	}

	public void searchSuggestion(HttpServletRequest request) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			
			if(request.getParameter("headname").equals("All")) {
				String sql = "select * from suggestion order by s_date";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			} else {
				String sql = "select * from suggestion where s_headname like ? order by s_date";	
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("headname"));
				rs = pstmt.executeQuery();
			}
			
			suggestions = new ArrayList<Suggestion>();
			Suggestion s = null;
			while (rs.next()) {
				s = new Suggestion();
				s.setNo(rs.getInt("s_no"));
				s.setName(rs.getString("s_name"));
				s.setPw(rs.getString("s_pw"));
				s.setHeadname(rs.getString("s_headname"));
				s.setTitle(rs.getString("s_title"));
				s.setText(rs.getString("s_text"));
				s.setDate(rs.getDate("s_date"));
				suggestions.add(s);
			}
			request.setAttribute("suggestions", suggestions);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		   
		   
	}

}
	       

	
	

			
		
		
	
		
		
