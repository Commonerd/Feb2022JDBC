package jdbcex.day0209;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JDBCEx6 {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##scott";
		String password = "tiger";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "insert into dept values(?,?,?)";
			pstmt = con.prepareStatement(sql);//객체만들기 
			pstmt.setInt(1, 60);
			pstmt.setString(2, "디자인팀");
			pstmt.setString(3, "부산");
			
			int i = pstmt.executeUpdate();
		
			if(i >= 1) {
				System.out.println("부서 정보 저장");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}


