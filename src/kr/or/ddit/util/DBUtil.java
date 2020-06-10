package kr.or.ddit.util;

import java.sql.*;

import oracle.net.aso.r;

public class DBUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료");
			
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"KB",
					"java");
			
		} catch (SQLException e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
			return null;
		}
		
	}

}
