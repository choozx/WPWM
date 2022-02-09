package com.lol.youtube;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.lol.main.DBManager;

public class YoutuberDAO {

	public static void regYoutuber(HttpServletRequest request) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into youtuber_account values (youtuber_account_seq.nextval, ?,?,?,?)";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			String name = request.getParameter("youtuberName");
			String puuid = request.getParameter("puuid");
			String url = request.getParameter("channelURL");
			String id = request.getParameter("channelID"); 
			
			System.out.println(name);
			System.out.println(puuid);
			System.out.println(url);
			System.out.println(id);
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, puuid);
			pstmt.setString(3, url);
			pstmt.setString(4, id);
			
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "등록성공");
			}else {
				request.setAttribute("r", "등록실패");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "db 서버 문제");
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getAllYoutuber(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from youtuber_account order by a_no desc";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<Youtuber> Youtubers = new ArrayList<Youtuber>();

			Youtuber y = null;
			while (rs.next()) {
				y = new Youtuber();
				y.setNo(rs.getInt("a_no"));
				y.setYoutuber(rs.getString("a_id"));
				y.setPuuid(rs.getString("a_puuid"));
				y.setChannelURL(rs.getString("a_channelURL"));
				y.setChannelID(rs.getString("a_channelID"));
				Youtubers.add(y);
				
			}
			request.setAttribute("Youtubers", Youtubers);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void updateYoutuber(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update youtuber_account set a_id=?, a_puuid=?, a_channelURL=?, a_channelID=? where a_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			request.setCharacterEncoding("utf-8");
			pstmt.setString(1, request.getParameter("youtuberName"));
			pstmt.setString(2, request.getParameter("puuid"));
			pstmt.setString(3, request.getParameter("channelURL"));
			pstmt.setString(4, request.getParameter("channelID"));
			pstmt.setInt(5, Integer.parseInt(request.getParameter("no")));
			
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "수정 성공");
			}else {
				request.setAttribute("r", "수정 실패");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "db 서버문제");
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getYoutuber(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select * from youtuber_account where a_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			request.setCharacterEncoding("utf-8");
			pstmt.setInt(1, Integer.parseInt(request.getParameter("no"))); // no 값 받기 
			rs = pstmt.executeQuery();
			
			Youtuber y = null;
			if (rs.next()) {
				y = new Youtuber();
				y.setNo(rs.getInt("a_no"));
				y.setYoutuber(rs.getString("a_id"));
				y.setPuuid(rs.getString("a_puuid"));
				y.setChannelURL(rs.getString("a_channelURL"));
				y.setChannelID(rs.getString("a_channelID"));
			
				request.setAttribute("Youtube", y);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void delYoutuber(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete youtuber_account where a_no=?";
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("no")));
			
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "삭제 성공");
			}else {
				request.setAttribute("r", "삭제 실패");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "db 서버문제");
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	}
	


