package com.lol.duo;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.lol.main.DBManager;
import com.lol.search.Summoner;
import com.lol.search.client.RiotClient;
import com.lol.search.client.Tier;
import com.sun.jdi.connect.Transport;
import com.sun.net.httpserver.Authenticator;

import oracle.net.jdbc.TNSAddress.Address;


public class DuoDAO {
	
	private final static RiotClient riotClient = new RiotClient();

	private ArrayList<Duo> duos;
	
	private static final DuoDAO DDAO = new DuoDAO();
	
	
	private DuoDAO() {
	
	}
	
public static DuoDAO getDdao() {
		return DDAO;
	}


public  void paging(int page, HttpServletRequest request) { 
		
		request.setAttribute("curPageNo", page); // int page 현재 머물러있는 페이지를 view로 넘길거임 
		
		// 전체 페이지 수 계산
		  int cnt = 10; // 한 페이지당 보여줄 데이터 개수
		  int total = duos.size(); // 총 데이터 개수
		  
		  // 페이지개수
		  int pageCount = (int)Math.ceil((double)total / cnt);
		  request.setAttribute("pageCount", pageCount);
		  int pageCountSize = (int)Math.ceil((double)pageCount / 10);;
		  request.setAttribute("pageCount2", pageCountSize);
		 
		  int start = total - (cnt * (page -1)); // 최신글을 먼저 보려고
		  
		  
		  int end = (page == pageCount) ? -1 : start - (cnt + 1);	
	  
	  // 나머지는 데이터를 페이징에 실어주는 일
	  ArrayList<Duo> items = new ArrayList<Duo>();
	  try {
		  for (int i = start-1; i > end; i--) {
			  // start-1 은 인덱스 번호 맞추게 하기 위하여
			  
			  items.add(duos.get(i));
		  }
		  request.setAttribute("duos",items);
		
	} catch (Exception IndexOutOfBoundsException) {
		request.setAttribute("d", "검색 결과가 없습니다");
	}
		  	
		  	}
		  
	


	public void regDuo(HttpServletRequest request, HttpServletResponse response) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into duo values(duo_seq.nextval,?, ?, ?, ?, ?, sysdate, ?, ? ,?)";
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			
	   String name = request.getParameter("name");
	   Summoner summorner = riotClient.getUser(name);
	   Tier tier = riotClient.getUserTier(summorner.getId());	
			
		String position = request.getParameter("position");
		String queue = request.getParameter("queue");
		String memo = request.getParameter("duoMemo");
		int pw = Integer.parseInt(request.getParameter("duoPw"));
		
			
		pstmt.setString(1, name);
		pstmt.setString(2, position);
		pstmt.setString(3, queue);
		pstmt.setString(4, memo);
		pstmt.setInt(5, pw);
		pstmt.setString(6, tier.getTier() + tier.getRank());
		pstmt.setLong(7, tier.getWins());
		pstmt.setLong(8, tier.getLosses());
		
		System.out.println(name);
		System.out.println(position);
		System.out.println(queue);
		System.out.println(memo);
		System.out.println(pw);
		System.out.println(tier.getTier() + tier.getRank());
		System.out.println(tier.getWins());
		System.out.println(tier.getLosses());
		
		if (pstmt.executeUpdate() == 1) {
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('소환사 등록이 완료되었습니다.');");
			script.println("location.href ='DuoHC'");
			script.println("</script>");
			script.close();
		} else {
			request.setAttribute("d", "듀오 등록 실패!");
			System.out.println("듀오 등록 실패!");
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
		
		String sql = "select * from duo order by d_date ";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			duos = new ArrayList<Duo>();
			Duo d = null;
			while (rs.next()) {
				d = new Duo();
				d.setNo(rs.getInt("d_no"));
				d.setName(rs.getString("d_name"));
				d.setPosition(rs.getString("d_position"));
				d.setType(rs.getString("d_type"));
				d.setMemo(rs.getString("d_memo"));
				d.setPw(rs.getInt("d_pw"));
				d.setDate(rs.getDate("d_date"));
				d.setTier(rs.getString("d_tier"));
				d.setWin(rs.getLong("d_win"));
				d.setLose(rs.getLong("d_lose"));
				
				duos.add(d);
			}
			request.setAttribute("duos", duos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	}

	public void updateDuo(HttpServletRequest request, HttpServletResponse response) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select d_pw from duo where d_no=?";
		String sql2 = "update duo set d_position=?, d_type=?, d_memo=? where d_no=?";
		try {
		   request.setCharacterEncoding("utf-8");
		   con = DBManager.connect();
		   pstmt = con.prepareStatement(sql);
		// 값받기   
		   String no = request.getParameter("no");
		   String pw = request.getParameter("duoPw");
		   System.out.println(no);
		   System.out.println(pw); 
		   pstmt.setString(1, no);
		   
		   rs = pstmt.executeQuery();
		   if(rs.next()) {
			   String dbPw = rs.getString("d_pw");
			   if (pw.equals(dbPw) ) {
				  pstmt = con.prepareStatement(sql2);
				  String position = request.getParameter("position");
				  String type = request.getParameter("queue");
				  String memo = request.getParameter("duoMemo");
				  no = request.getParameter("no");
				  
				  
				  System.out.println(position);
				  System.out.println(type);
				  System.out.println(memo);
				  System.out.println(no);
				  
				  pstmt.setString(1, position);
				  pstmt.setString(2, type);
				  pstmt.setString(3, memo);
				  pstmt.setString(4, no);
				 if(pstmt.executeUpdate() == 1) {
					 System.out.println("수정 성공!");
					 response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('해당 글을 수정하였습니다');");
						script.println("location.href = 'DuoHC';");
						script.println("</script>");
						script.close();
				 } 
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
		
		String sql = "select * from duo where d_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));

					
			
			
			rs = pstmt.executeQuery();
			
 			Duo d = null;
		if (rs.next()) {
			d = new Duo();
			d.setNo(rs.getInt("d_no"));
			d.setName(rs.getString("d_name"));
			d.setPosition(rs.getString("d_position"));
			d.setType(rs.getString("d_type"));
			d.setMemo(rs.getString("d_memo"));
			d.setPw(rs.getInt("d_pw"));
			d.setDate(rs.getDate("d_date"));
			d.setTier(rs.getString("d_tier"));
			d.setWin(rs.getLong("d_win"));
			d.setLose(rs.getLong("d_lose"));
			request.setAttribute("duo", d);
		}	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}

	public void deleteDuo(HttpServletRequest request, HttpServletResponse response) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		String sql = "select d_pw from duo where d_no=?";
				
		
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
		  
		   if (rs.next()) {
			   String dbPw = rs.getString("d_pw"); // db 비번값 
			   if (pw.equals(dbPw) ) { // null == db비번값 
				    sql = "delete duo where d_no=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);				
					 if(pstmt.executeUpdate() == 1) {
					 System.out.println("삭제 성공!");
					 response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('해당 글을 삭제하였습니다');");
						script.println("location.href = 'DuoHC';");
						script.println("</script>");
						script.close();
				} 
				} else {
					System.out.println("비밀번호를 확인하세요");
					request.setAttribute("d", "비밀번호를 확인하세요");
			}	
		   }
		
		   
		}	catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("d", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
}

	public void searchDuo(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			
			if(request.getParameter("tier").equals("ALL")) {
				System.out.println(request.getParameter("position"));
				System.out.println(request.getParameter("queue"));
				System.out.println(request.getParameter("tier"));
				String sql = "select * from duo where d_position like '%'||?||'%' AND d_type like '%'||?||'%' order by d_date";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("position"));
				pstmt.setString(2, request.getParameter("queue"));
				rs = pstmt.executeQuery();
			} else {
				String sql = "select * from duo where d_position like '%'||?||'%' AND d_type like '%'||?||'%' AND d_tier like '%'||?||'%' order by d_date";	
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("position"));
				pstmt.setString(2, request.getParameter("queue"));
				pstmt.setString(3, request.getParameter("tier"));
				rs = pstmt.executeQuery();
			}
			
			
			duos = new ArrayList<Duo>();
			Duo d = null;
			while (rs.next()) {
				d = new Duo();
				d.setNo(rs.getInt("d_no"));
				d.setName(rs.getString("d_name"));
				d.setPosition(rs.getString("d_position"));
				d.setType(rs.getString("d_type"));
				d.setMemo(rs.getString("d_memo"));
				d.setPw(rs.getInt("d_pw"));
				d.setDate(rs.getDate("d_date"));
				d.setTier(rs.getString("d_tier"));
				d.setWin(rs.getLong("d_win"));
				d.setLose(rs.getLong("d_lose"));
				duos.add(d);
			}
			request.setAttribute("duos", duos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		   
		   
	}
	
	public void searchNickname(HttpServletRequest request) {
		
		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "select * from duo where d_name like '%'||?||'%' order by d_date";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("searchNick"));
			System.out.println(request.getParameter("searchNick"));
			rs = pstmt.executeQuery();
		
			
			
			duos = new ArrayList<Duo>();
			Duo d = null;
			while (rs.next()) {
				d = new Duo();
				d.setNo(rs.getInt("d_no"));
				d.setName(rs.getString("d_name"));
				d.setPosition(rs.getString("d_position"));
				d.setType(rs.getString("d_type"));
				d.setMemo(rs.getString("d_memo"));
				d.setPw(rs.getInt("d_pw"));
				d.setDate(rs.getDate("d_date"));
				d.setTier(rs.getString("d_tier"));
				d.setWin(rs.getLong("d_win"));
				d.setLose(rs.getLong("d_lose"));
				duos.add(d);
			}
			request.setAttribute("duos", duos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		   
		   
	}


}
