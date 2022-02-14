package jdbcex.sample;

import java.sql.*;

public class PerformanceEx {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	public static void main(String[] args) {

		long start;
		long end;
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##scott", "tiger");
			stmt = con.createStatement();
			String sql = "create table performance(id varchar(10)," + "password varchar(10))";
			stmt.executeUpdate(sql);
//currentTimeMillis(): 시스템상의 현재시간을 밀리세컨드로 반환 
			start = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++) {
				stmt.executeUpdate("insert into performance values" + "('" + i + "','" + i + "')");
			}

			end = System.currentTimeMillis();
			System.out.println("Statement process time = " + (end - start));

			sql = "insert into performance values(?,?)";
			pstmt = con.prepareStatement(sql);

			start = System.currentTimeMillis();
			for (int j = 0; j < 10000; j++) {
				pstmt.setString(1, "" + j);
				pstmt.setString(2, "" + j);
				pstmt.executeUpdate();
			}
			end = System.currentTimeMillis();

			System.out.println("PreparedStatement process time = " + (end - start));
			stmt.executeUpdate("drop table performance");
		} catch (SQLException ee) {
			ee.printStackTrace();
		} finally {
			try {
				if (pstmt != null) //nullpointerexception
					pstmt.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
	}
}
