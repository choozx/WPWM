// AOP�۾� �� ����
// DB���� �۾��� �� �� �Ź� �ݺ��ؼ� �ߴ� �۾� : �����ڵ�
// �װ� AOP ���ڴ°�
package com.lol.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	// db�۾��ÿ��� ��¶�� ������ �������
	public static Connection connect() throws SQLException {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			return DriverManager.getConnection(url, "[db_id]", "[db_pw]");  // ���ڵ� Ÿ���� Connection��
				
	}
	// ������ ������ �ѹ��� �ݱ�
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if (rs != null) {   // null�� �ƴ� �� �ݾ�����ϹǷ� 
				rs.close(); 
			}
		} catch (SQLException e) {			
		}
		try {
			if (pstmt != null) {   // null�� �ƴ� �� �ݾ�����ϹǷ� 
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
