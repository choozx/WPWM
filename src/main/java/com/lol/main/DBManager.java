// AOP작업 할 거임
// DB관련 작업을 할 때 매번 반복해서 했던 작업 : 연결코드
// 그걸 AOP 하자는거
package com.lol.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	// db작업시에는 어쨋든 연결을 해줘야함
	public static Connection connect() throws SQLException {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			return DriverManager.getConnection(url, "yjh", "yjh");  // 이코드 타입이 Connection임
				
	}
	// 닫을게 많은데 한번에 닫기
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if (rs != null) {   // null이 아닐 때 닫아줘야하므로 
				rs.close(); 
			}
		} catch (SQLException e) {			
		}
		try {
			if (pstmt != null) {   // null이 아닐 때 닫아줘야하므로 
				pstmt.close(); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
