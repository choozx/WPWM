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
		
		request.setAttribute("curPageNo", page); // int page ���� �ӹ����ִ� �������� view�� �ѱ���� 
		
		// ��ü ������ �� ���
		  int cnt = 10; // �� �������� ������ ������ ����
		  int total = suggestions.size(); // �� ������ ����
		  
		  // ����������
		  int pageCount = (int)Math.ceil((double)total / cnt);
		  request.setAttribute("pageCount", pageCount);
		  int pageCountSize = (int)Math.ceil((double)pageCount / 10);;
		  request.setAttribute("pageCount2", pageCountSize);
		 
		  int start = total - (cnt * (page -1)); // �ֽű��� ���� ������
		  
		  
		  int end = (page == pageCount) ? -1 : start - (cnt + 1);	
	  
		  // �������� �����͸� ����¡�� �Ǿ��ִ� ��
		  ArrayList<Suggestion> items = new ArrayList<Suggestion>();
		  try {
			  for (int i = start-1; i > end; i--) {
				  // start-1 �� �ε��� ��ȣ ���߰� �ϱ� ���Ͽ�
			  
				  items.add(suggestions.get(i));
			  }
			  request.setAttribute("suggestions",items);
		
		  } catch (Exception IndexOutOfBoundsException) {
			  request.setAttribute("d", "�˻� ����� �����ϴ�");
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
				request.setAttribute("d", "��� ����!");
				System.out.println("��� ����!");
			} else {
				request.setAttribute("d", "��� ����!");
				System.out.println("��� ����!");
			}
			
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("d", "DB���� ����..");
				System.out.println("DB���� ����..");
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
				    	System.out.println("���� ����!");
				    	request.setAttribute("d", "���� ����!");
				
				    } else {	
				    	System.out.println("��й�ȣ�� Ȯ���ϼ���");
				    	request.setAttribute("d", "��й�ȣ�� Ȯ���ϼ���");
				    }
			    }
			
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB���� ����..");
			System.out.println("DB���� ����..");
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
		    String pw = request.getParameter("userPw");  // ==>> ������ �Է��� pw : null
		   
		    System.out.println(no);
		    System.out.println(pw);
		   
		    pstmt.setInt(1, no);
		    rs = pstmt.executeQuery();
		  
		    rs.next();
		    String dbPw = rs.getString("s_pw"); // db ����� 
			if (pw.equals(dbPw) ) { // null == db����� 
			    sql = "delete suggestion where s_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);				
				if(pstmt.executeUpdate() == 1) {
					System.out.println("���� ����!");
					request.setAttribute("d", "���� ����!");
				} 
			} else {
				System.out.println("��й�ȣ�� Ȯ���ϼ���");
				request.setAttribute("d", "��й�ȣ�� Ȯ���ϼ���");
		}	
		   

		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB���� ����..");
			System.out.println("DB���� ����..");
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
	       

	
	

			
		
		
	
		
		
