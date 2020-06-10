package kr.or.ddit.basic;

import java.sql.*;

public class T03_JdbcTest {
	/*
	 * lprod_id : 101, lprod_gu : N101, lprod_nm : 농산물 lprod_id : 102, lprod_gu :
	 * N102, lprod_nm : 수산물 lprod_id : 103, lprod_gu : N103, lprod_nm : 축산물
	 * 
	 */

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB에 접속(Connection객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "KB";
			String password = "java";

			conn = DriverManager.getConnection(url, userId, password);

			// 3. Statement객체 생성 => Connection객체를 이용한다.
//			stmt = conn.createStatement();
			
			String sql = 	" insert into lprod " + 
							" (lprod_id, lprod_gu, lprod_nm) " + 
							" values(?,?,?)";
			
			// PreparedStatement객체를 생성할 떄 SQL문을 넣어서 생성한다.
			pstmt = conn.prepareStatement(sql);
			
			//쿼리문의 물음표 자리에 들어갈 데이터를 셋팅한다.
			pstmt.setInt(1,101);
			pstmt.setString(2,"N101");
			pstmt.setString(3,"농산물");
			
			int cnt = pstmt.executeUpdate();
			
			pstmt.setInt(1,102);
			pstmt.setString(2,"N102");
			pstmt.setString(3,"수산물");
			
			
			pstmt.setInt(1,103);
			pstmt.setString(2,"N103");
			pstmt.setString(3,"축산물");
			
			
			
			//SQL문이 select문이 아닐 경우에는 excuteUpdate()메서드를 사용한다.
			//executeUpdate()메서드는 실행에 성공한 레코드 수를 반환한다.
			cnt = stmt.executeUpdate(sql);
			System.out.println("첫번째 반환값 : " + cnt);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
}
