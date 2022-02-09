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

		request.setAttribute("curPageNo", page); // int page ���� �ӹ����ִ� �������� view�� �ѱ����

		// ��ü ������ �� ���
		int cnt = 10; // �� �������� ������ ������ ����
		int total = compliments.size(); // �� ������ ����

		// ����������
		int pageCount = (int) Math.ceil((double) total / cnt);
		request.setAttribute("pageCount", pageCount);
		int pageCountSize = (int) Math.ceil((double) pageCount / 10);
		;
		request.setAttribute("pageCount2", pageCountSize);

		int start = total - (cnt * (page - 1)); // �ֽű��� ���� ������

		int end = (page == pageCount) ? -1 : start - (cnt + 1);

		// �������� �����͸� ����¡�� �Ǿ��ִ� ��
		ArrayList<Compliment> items = new ArrayList<Compliment>();
		try {
			for (int i = start - 1; i > end; i--) {
				// start-1 �� �ε��� ��ȣ ���߰� �ϱ� ���Ͽ�

				items.add(compliments.get(i));
			}
			request.setAttribute("compliments", items);

		} catch (Exception IndexOutOfBoundsException) {
			request.setAttribute("c", "�˻� ����� �����ϴ�");
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
						System.out.println("Ī���ϱ� ��� ����!");
						response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('Ī���ϱ� ����� �Ͽ����ϴ�.');");
						script.println("location.href = 'ComplimentC';");
						script.println("</script>");
						script.close();
					}

				}
			} else {
				response.setContentType("text/html; charset=euc-kr");
				PrintWriter out = response.getWriter();
				String msg = "�������� �ʴ� ȸ���Դϴ�";
				String str = "";
				str = "<script language='javascript'>";
				str += "alert('" + msg + "');"; // ��â ����
				str += "history.go(-1);"; // ������������ ����
				str += "</script>";
				out.print(str);
				request.setAttribute("c", "�������� �ʴ� ȸ���Դϴ�");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB���� ����..");
			request.setAttribute("c", "DB���� ����..");
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
			String pw = request.getParameter("userPw"); // ==>> ������ �Է��� pw : null

			System.out.println(no);
			System.out.println(pw);

			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPw = rs.getString("c_pw"); // db �����
				if (pw.equals(dbPw)) { // null == db�����
					sql = "delete compliment where c_no=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					if (pstmt.executeUpdate() == 1) {
						System.out.println("���� ����!");
						response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('�ش� ���� �����Ͽ����ϴ�.');");
						script.println("location.href = 'ComplimentC';");
						script.println("</script>");
						script.close();
					}
				} else {
					System.out.println("��й�ȣ�� Ȯ���ϼ���");
					request.setAttribute("c", "��й�ȣ�� Ȯ���ϼ���");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("c", "DB���� ����..");
			System.out.println("DB���� ����..");
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
			// ���ޱ�
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
						System.out.println("���� ����!");
						response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('�ش� ���� �����Ͽ����ϴ�.');");
						script.println("location.href = 'ComplimentC';");
						script.println("</script>");
						script.close();
					}
				} else {
					System.out.println("��й�ȣ�� Ȯ���ϼ���");
					request.setAttribute("c", "��й�ȣ�� Ȯ���ϼ���");
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
			// rs�� �� ������ �̹� ��õ, ������ ��õ �ø���(insert)
			// insert �б� ���� ������ ���� - > select
			rs.next();
			System.out.println(no);
				if(rs.getString("l_no") == no) {
						response.setContentType("text/html; charset=euc-kr");
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('�̹� ��õ�� ���� ���Դϴ�.');");
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
							script.println("alert('��õ�� �Ϸ�Ǿ����ϴ�.');");
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
