package com.lol.reg;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import com.lol.main.DBManager;
import com.lol.signup.AccountDAO;


public class RegDAO {

	public static void getAllInfo(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from lol_account";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			Reg r = null;
			if (rs.next()) {
				r = new Reg();
				r.setId(rs.getString("a_id"));
				r.setPw(rs.getString("a_pw"));
				r.setNickname(rs.getString("a_nickname"));
				r.setPuuid(rs.getString("a_puuid"));
			}
			request.setAttribute("reg", r);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void updateInfo(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update lol_account set a_pw=? where a_id=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			String pw = request.getParameter("pw");
			String pw2 = request.getParameter("pw2");
			String id = request.getParameter("id");

			System.out.println(pw);
			System.out.println(pw2);
			System.out.println(id);

			String pw3 = "";
			if (pw2.length() == 0) { // 패스워드 가 null 이면 입력안함 =>기존값
				pw3 = pw;

			} else {
				pw3 = pw2;

			}
			pstmt.setString(1, pw3);

			pstmt.setString(2, id);

			request.setAttribute("idd", request.getParameter("id"));
			request.setAttribute("pww", pw3);
			System.out.println(pw3);

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "수정성공");
				System.out.println("수정성공");
			} else {
				request.setAttribute("r", "수정실패");
				System.out.println("수정실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db 서버문제");
			request.setAttribute("r", "db 서버문제");

		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getPassword(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from lol_account where a_id=?";
		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("id"));
			rs = pstmt.executeQuery();

			Reg r = null;
			if (rs.next()) {
				r = new Reg();
				r.setId(rs.getString("a_id"));
				r.setPw(rs.getString("a_pw"));
				r.setNickname(rs.getString("a_nickname"));
				r.setPuuid(rs.getString("a_puuid"));
			}
			request.setAttribute("reg", r);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void delete(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select a_pw from lol_account where a_id=?";
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			System.out.println(id);
			System.out.println(pw); // 탈퇴할때 적는 pw
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				String dbPw = rs.getString("a_pw"); // db 비번값
				if (dbPw.equals(pw)) {
					sql = "delete lol_account where a_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id); 
					
					if (pstmt.executeUpdate() == 1) {
						System.out.println("삭제성공");
						request.setAttribute("r", "삭제성공");
						AccountDAO.logOut(request);
					}
				}else {
					System.out.println("비밀번호를 확인해라");
					request.setAttribute("r", "비밀번호 확인");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "db 서버 문제");
			System.out.println("db 서버 문제");
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

}
