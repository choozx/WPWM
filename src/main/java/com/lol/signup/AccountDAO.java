package com.lol.signup;

import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lol.signup.Account;
import com.lol.search.client.RiotClient;
import com.lol.search.Summoner;
import com.lol.search.client.Tier;
import com.lol.main.DBManager;

public class AccountDAO {
	
	private final static RiotClient riotClient = new RiotClient();
	
	public static void loginCheck(HttpServletRequest request) {

		HttpSession hs = request.getSession();
		Account a = (Account) hs.getAttribute("accountInfo");
		hs.setMaxInactiveInterval(30*60);
		
		if (a == null) {
			//�α��� ���� 
			request.setAttribute("loginPage", "home.jsp");
		}else {
			// �α��� ����
			request.setAttribute("nickName", a.getNickname());
		}
	}

	public static void reg(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into LOL_ACCOUNT values(?, ?, ?, ?)";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			String nickName = request.getParameter("nickname");
			System.out.println(nickName);
			
			Summoner summorner = riotClient.getUser(nickName);
			
			
			
			pstmt.setString(1, request.getParameter("id"));
			pstmt.setString(2, request.getParameter("pw"));
			pstmt.setString(3, nickName);
			pstmt.setString(4, summorner.getPuuid());
			
		
			System.out.println(summorner.getName());
			System.out.println(summorner.getAccountId());
			System.out.println(summorner.getLevel());
			System.out.println(summorner.getPuuid());
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("��ϼ���");
				request.setAttribute("r", "��ϼ���");
			}else {
				request.setAttribute("r", "��Ͻ���");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "db ��������");
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public static void login(HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("id");
        String userPw = request.getParameter("pw");
        
        // ���� ����� ����Ǹ� ���� �Ƿ� �����Ű� �ƴϸ� ���� ������ 
        String idd = (String)request.getAttribute("idd");
        String pww = (String)request.getAttribute("pww");

        if (idd != null) {
            userId = idd;
            userPw = pww;
        }
        
        // 2. DB�� �� (���� ������ �ȵ� .) - �������� ó��

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from lol_account where a_id = ?";//////////////////////
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPW = rs.getString("a_pw");
                String dbId = rs.getString("a_id");
                // ���̵� ������ pw�˻�
                if (userPw.equals(dbPW) ) {
                    // ���� �������ϱ� ���ļ�
                    Account a = new Account();
                    a.setId(rs.getString("a_id"));
                    a.setPw(rs.getString("a_pw"));
                    a.setNickname(rs.getString("a_nickname"));
                    a.setPuuid(rs.getString("a_puuid"));
                    HttpSession hs = request.getSession();
                    hs.setAttribute("accountInfo", a);
                    hs.setMaxInactiveInterval(30*60);  //  ���� 30��

                    // request.setAttribute("a", a);
                    response.setContentType("text/html; charset=euc-kr");
                    PrintWriter script = response.getWriter();
                    script.println("<script>");
                    script.println("alert('�α��� �����߽��ϴ�.');");
                    script.println("location.href = 'HC';");
                    script.println("</script>");
                    script.close();
                } else {
                    System.out.println("��й�ȣ ����");
                    response.setContentType("text/html; charset=euc-kr");
                    PrintWriter out = response.getWriter();
                    String msg = "��й�ȣ�� Ʋ���ϴ�.";
                    String str = "";
                    str = "<script language='javascript'>";
                    str += "alert('" + msg + "');"; // ��â ����
                    str += "history.go(-1);"; // ������������ ����
                    str += "</script>";
                    out.print(str);
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
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }

    }

	public static void logOut(HttpServletRequest request) {

		HttpSession hs = request.getSession();
		
			hs.setAttribute("accountInfo", null);
			
	}

	}
	
	

