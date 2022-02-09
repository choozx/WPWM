package com.lol.duo;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.lol.main.DBManager;
import com.lol.search.Summoner;
import com.lol.search.client.RiotClient;
import com.lol.search.client.Tier;


import oracle.net.aso.c;

public class ComplimentDAO {

	private final static RiotClient riotClient = new RiotClient();

	private ArrayList<Compliment> compliments;

	private static final ComplimentDAO CDAO = new ComplimentDAO();

	private ComplimentDAO() {
	}

	public static ComplimentDAO getCdao() {
		return CDAO;
	}

	public void paging(int page, HttpServletRequest request) {

		request.setAttribute("curPageNo", page); // int page 현재 머물러있는 페이지를 view로 넘길거임

		// 전체 페이지 수 계산
		int cnt = 10; // 한 페이지당 보여줄 데이터 개수
		int total = compliments.size(); // 총 데이터 개수

		// 페이지개수
		int pageCount = (int) Math.ceil((double) total / cnt);
		request.setAttribute("pageCount", pageCount);
		int pageCountSize = (int) Math.ceil((double) pageCount / 10);
		;
		request.setAttribute("pageCount2", pageCountSize);

		int start = total - (cnt * (page - 1)); // 최신글을 먼저 보려고

		int end = (page == pageCount) ? -1 : start - (cnt + 1);

		// 나머지는 데이터를 페이징에 실어주는 일
		ArrayList<Compliment> items = new ArrayList<Compliment>();
		try {
			for (int i = start - 1; i > end; i--) {
				// start-1 은 인덱스 번호 맞추게 하기 위하여

				items.add(compliments.get(i));
			}
			request.setAttribute("compliments", items);

		} catch (Exception IndexOutOfBoundsException) {
			request.setAttribute("c", "검색 결과가 없습니다");
		}

	}

	public void regCompliment(HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from lol_account where a_nickname=?";
		String sql2 = "insert into compliment" + " values(compliment_seq.nextval, ?, ?, ?, 0, ?, sysdate, ?)";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);

			String name = request.getParameter("name");
			Summoner summorner = riotClient.getUser(name);
			Tier tier = riotClient.getUserTier(summorner.getId());

			System.out.println(name);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbName = rs.getString("a_nickname");
				if (dbName.equals(name)) {

					pstmt = con.prepareStatement(sql2);
					String memo = request.getParameter("CMemo");
					int pw = Integer.parseInt(request.getParameter("CPw"));
					String username = request.getParameter("nickname");

					pstmt.setString(1, name);
					pstmt.setString(2, tier.getTier() + tier.getRank());
					pstmt.setString(3, memo);
					pstmt.setInt(4, pw);
					pstmt.setString(5, username);

					System.out.println(name);
					System.out.println(tier.getTier() + tier.getRank());
					System.out.println(memo);
					System.out.println(pw);
					System.out.println(username);
					if (pstmt.executeUpdate() == 1) {
						System.out.println("칭찬하기 등록 성공!");
						response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('칭찬하기 등록을 하였습니다.');");
						script.println("location.href = 'ComplimentC';");
						script.println("</script>");
						script.close();
					}

				}
			} else {
				response.setContentType("text/html; charset=euc-kr");
				PrintWriter out = response.getWriter();
				String msg = "존재하지 않는 회원입니다";
				String str = "";
				str = "<script language='javascript'>";
				str += "alert('" + msg + "');"; // 얼럿창 띄우기
				str += "history.go(-1);"; // 이전페이지로 가기
				str += "</script>";
				out.print(str);
				request.setAttribute("c", "존재하지 않는 회원입니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB서버 문제..");
			request.setAttribute("c", "DB서버 문제..");
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void getAllCompliment(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from compliment order by c_date ";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			compliments = new ArrayList<Compliment>();
			Compliment c = null;
			while (rs.next()) {
				c = new Compliment();
				c.setNo(rs.getInt("c_no"));
				c.setName(rs.getString("c_name"));
				c.setTier(rs.getString("c_tier"));
				c.setMemo(rs.getString("c_memo"));
				c.setLikecount(rs.getInt("c_likecount"));
				c.setPw(rs.getInt("c_pw"));
				c.setDate(rs.getDate("c_date"));
				c.setUsername(rs.getString("c_username"));
				compliments.add(c);
			}
			request.setAttribute("compliments", compliments);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void deleteCompliment(HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select c_pw from compliment where c_no=?";

		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			int no = Integer.parseInt(request.getParameter("no"));
			String pw = request.getParameter("userPw"); // ==>> 유저가 입력한 pw : null

			System.out.println(no);
			System.out.println(pw);

			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPw = rs.getString("c_pw"); // db 비번값
				if (pw.equals(dbPw)) { // null == db비번값
					sql = "delete compliment where c_no=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					if (pstmt.executeUpdate() == 1) {
						System.out.println("삭제 성공!");
						response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('해당 글을 삭제하였습니다.');");
						script.println("location.href = 'ComplimentC';");
						script.println("</script>");
						script.close();
					}
				} else {
					System.out.println("비밀번호를 확인하세요");
					request.setAttribute("c", "비밀번호를 확인하세요");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("c", "DB서버 문제..");
			System.out.println("DB서버 문제..");
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void getCompliment(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from compliment where c_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			System.out.println(request.getParameter("no"));

			rs = pstmt.executeQuery();

			Compliment c = null;
			if (rs.next()) {
				c = new Compliment();
				c.setNo(rs.getInt("c_no"));
				c.setName(rs.getString("c_name"));
				c.setTier(rs.getString("c_tier"));
				c.setMemo(rs.getString("c_memo"));
				;
				c.setLikecount(rs.getInt("c_likecount"));
				c.setPw(rs.getInt("c_pw"));
				c.setDate(rs.getDate("c_date"));
				c.setUsername(rs.getString("c_username"));
				request.setAttribute("compliment", c);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void updateCompliment(HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select c_pw from compliment where c_no=?";
		String sql2 = "update compliment set c_memo=? where c_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			// 값받기
			String no = request.getParameter("no");
			String pw = request.getParameter("CPw");
			System.out.println(no);
			System.out.println(pw);
			pstmt.setString(1, no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPw = rs.getString("c_pw");
				if (pw.equals(dbPw)) {
					pstmt = con.prepareStatement(sql2);
					String memo = request.getParameter("CMemo");
					no = request.getParameter("no");

					System.out.println(memo);
					System.out.println(no);

					pstmt.setString(1, memo);
					pstmt.setString(2, no);
					if (pstmt.executeUpdate() == 1) {
						System.out.println("수정 성공!");
						response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('해당 글을 수정하였습니다.');");
						script.println("location.href = 'ComplimentC';");
						script.println("</script>");
						script.close();
					}
				} else {
					System.out.println("비밀번호를 확인하세요");
					request.setAttribute("c", "비밀번호를 확인하세요");
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

	public void searchNickname(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			String sql = "select * from compliment where c_name like '%'||?||'%' order by c_date";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("searchNick"));
			System.out.println(request.getParameter("searchNick"));
			rs = pstmt.executeQuery();

			compliments = new ArrayList<Compliment>();
			Compliment c = null;
			while (rs.next()) {
				c = new Compliment();
				c.setNo(rs.getInt("c_no"));
				c.setName(rs.getString("c_name"));
				c.setTier(rs.getString("c_tier"));
				c.setMemo(rs.getString("c_memo"));
				c.setLikecount(rs.getInt("c_likecount"));
				c.setPw(rs.getInt("c_pw"));
				c.setDate(rs.getDate("c_date"));
				c.setUsername(rs.getString("c_username"));

				compliments.add(c);
			}
			request.setAttribute("compliments", compliments);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void updateLikey(HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql0 = "select * from likey where l_no = ?";
		

		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql0);
			
			String no = request.getParameter("no");
			System.out.println(no);
			pstmt.setString(1, no);
			
			rs = pstmt.executeQuery(); 
			// rs에 뭐 있으면 이미 추천, 없으면 추천 올리기(insert)
			// insert 분기 점을 나눠서 수행 - > select
			rs.next();
			System.out.println(no);
				if(rs.getString("l_no") == no) {
						response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('이미 추천을 누른 글입니다.');");
						script.println("location.href = 'ComplimentC';");
						script.println("</script>");
						script.close();		
				} else {
						String sql1 = "insert into likey values(?, ?)";
						pstmt = con.prepareStatement(sql1);
						no = request.getParameter("no");
					    String username = request.getParameter("nickname");
						System.out.println("insert : " + no);
						System.out.println("insert : " + username);
						pstmt.setString(1, username);
						pstmt.setString(2, no);
						
						if(pstmt.executeUpdate() == 1) {
							String sql2 = "update compliment set c_likecount = c_likecount + 1 where c_no = ?";
							pstmt = con.prepareStatement(sql2);
							
							no = request.getParameter("no");
							System.out.println("update : " + no);
							pstmt.setString(1, no);
							pstmt.executeUpdate();
							response.setContentType("text/html; charset=euc-kr");
							PrintWriter script = response.getWriter();
							script.println("<script>");
							script.println("alert('추천이 완료되었습니다.');");
							script.println("location.href = 'ComplimentC';");
							script.println("</script>");
							script.close();
							
						 }
					 }
			
		}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}


		}

	public void likeyList(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from compliment order by c_likecount";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, request.getParameter("nickname"));
//			System.out.println(request.getParameter("nickname"));
			rs = pstmt.executeQuery();
			
			compliments = new ArrayList<Compliment>();
			Compliment c = null;
			while (rs.next()) {
				c = new Compliment();
				c.setNo(rs.getInt("c_no"));
				c.setName(rs.getString("c_name"));
				c.setTier(rs.getString("c_tier"));
				c.setMemo(rs.getString("c_memo"));
				c.setLikecount(rs.getInt("c_likecount"));
				c.setPw(rs.getInt("c_pw"));
				c.setDate(rs.getDate("c_date"));
				c.setUsername(rs.getString("c_username"));
				compliments.add(c);
			}
				request.setAttribute("compliments", compliments);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
}
