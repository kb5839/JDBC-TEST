package kr.or.ddit.basic;

import java.sql.*;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

import kr.or.ddit.util.DBUtil;

/*
 * LPROD 테이블에 새로운 데이터 추가하기
 * 
 * lprod_gu와 lprod_nm은 직접 입력받아 처리하고,
 * lprod_id는 현재의 lprod_id들 중 제일 큰 값보다 1증가된 값으로 한다.
 * (기타사항 : lprod_gu도 중복되는지 검사한다.)
 */

public class T04_JdbcTest {
	

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
	try {
//		// 1. 드라이버 로딩
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//
//		// 2. DB에 접속(Connection객체 생성)
//		String url = "jdbc:oracle:thin:@localhost:1521/xe";
//		String userId = "KB";
//		String password = "java";
//
//		conn = DriverManager.getConnection(url, userId, password);
		conn = DBUtil.getConnection();

		// 3. Statement객체 생성 => Connection객체를 이용한다.
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("입력");
		int input1 = s.nextInt();
		String input2 = s.nextLine();
		
		// 4. SQL문을 Statement객체를 이용해 실행하고 실행결과를 ResultSet에 저장한다
		String sql = " insert into lprod " + 
				" (lprod_id, lprod_gu, lprod_nm) " + 
				" values(?,?,?)"; 

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,101);
		pstmt.setString(2,"N101");
		pstmt.setString(3,"농산물");
		
		// SQL문이 select일 경우에는 excuteQuery()메서드 이용하고,
		// insert, update, delete일 경우에는 executeUpdate()메서드 이용함
		rs = pstmt.executeQuery(sql);

		// 5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여
		// 차례로 읽어와 처리한다.
		System.out.println("실행한 쿼리문 : " + sql);
		System.out.println("=== 쿼리문 실행결과 ===");

		// rs.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로
		// 이동시키고, 그곳에 자료가 있으면 true, 없으면 false를
		// 반환한다.
		while (rs.next()) {
			// 컬럼의 자료를 가져오는 방법
			// 방법1) rs.get자료형이름("컬럼명")
			// 방법2) rs.get자료형이름(컬럼번호) => 컬럼번호는 1부터 시작
			System.out.println("lprod_id : " + rs.getInt("lprod_id"));
			System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
			System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
			System.out.println("--------------------------------------");
		}
		System.out.println("출력 끝 ...");

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		// 6. 종료 (사용했던 자원을 모두 반납한다.)
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
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
