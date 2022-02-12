package jdbcex.day0209;

import java.sql.*;
public class jdbcEx {

	public static void main(String[] args) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//접속
		//커넥션은 인터페이스라서 new를 통한 객체 못만듦.
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = DriverManager.getConnection(//예외처리
					"jdbc:oracle:thin:@localhost:1521:xe","c##scott","tiger"); //SQL문 전송
			stmt = con.createStatement();
			String sql = "select * from dept";
			rs = stmt.executeQuery(sql);//rs: ResultSet 뽑아온 게 있구나. 
			
			while(rs.next()) {//next, next, next 한줄씩 뽑아온다.
			int deptno = rs.getInt("deptno");
			String dname = rs.getString("dname");//컬럼의 인덱스 =2
			String loc = rs.getString(3);//=컬럼의 인덱스 => "loc"
			System.out.printf("%d번 부서 %s의 근무지는 %s입니다.\n"
					,deptno,dname,loc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}
	}
}


