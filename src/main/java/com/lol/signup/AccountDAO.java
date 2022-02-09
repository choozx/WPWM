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
			//로그인 실패 
			request.setAttribute("loginPage", "home.jsp");
		}else {
			// 로그인 성공
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
				System.out.println("등록성공");
				request.setAttribute("r", "등록성공");
			}else {
				request.setAttribute("r", "등록실패");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "db 서버문제");
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public static void login(HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("id");
        String userPw = request.getParameter("pw");
        
        // 업뎃 기능이 수행되면 값이 실려 있을거고 아니면 값이 없을꺼 
        String idd = (String)request.getAttribute("idd");
        String pww = (String)request.getAttribute("pww");

        if (idd != null) {
            userId = idd;
            userPw = pww;
        }
        
        // 2. DB랑 비교 (아직 개발이 안됨 .) - 가데이터 처리

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
                // 아이디 맞으면 pw검사
                if (userPw.equals(dbPW) ) {
                    // 값이 여러개니까 뭉쳐서
                    Account a = new Account();
                    a.setId(rs.getString("a_id"));
                    a.setPw(rs.getString("a_pw"));
                    a.setNickname(rs.getString("a_nickname"));
                    a.setPuuid(rs.getString("a_puuid"));
                    HttpSession hs = request.getSession();
                    hs.setAttribute("accountInfo", a);
                    hs.setMaxInactiveInterval(30*60);  //  세션 30분

                    // request.setAttribute("a", a);
                    response.setContentType("text/html; charset=euc-kr");
                    PrintWriter script = response.getWriter();
                    script.println("<script>");
                    script.println("alert('로그인 성공했습니다.');");
                    script.println("location.href = 'HC';");
                    script.println("</script>");
                    script.close();
                } else {
                    System.out.println("비밀번호 오류");
                    response.setContentType("text/html; charset=euc-kr");
                    PrintWriter out = response.getWriter();
                    String msg = "비밀번호가 틀립니다.";
                    String str = "";
                    str = "<script language='javascript'>";
                    str += "alert('" + msg + "');"; // 얼럿창 띄우기
                    str += "history.go(-1);"; // 이전페이지로 가기
                    str += "</script>";
                    out.print(str);
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
	
	

