package jdbcex.day0209;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcEx2 {

	public static void main(String[] args) {

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##scott";
			String password = "tiger";
			
			String sql = "insert into dept values(50,'개발','서울')";
			//dept
			// + " values.." values 앞에 공백이 하나 있어야 한다.없으면 붙어버려서,의외로 에러 자주남.
			Connection con = null;
			Statement stmt = null;
			//레코드를 가져올 일이 없다.
			try {
				con = DriverManager.getConnection(url, user, password);
				stmt = con.createStatement();
				int i = stmt.executeUpdate(sql);
				System.out.println(i);
				System.out.println("50번 부서가 저장되었습니다.");
			} catch (SQLException e) {
				e.printStackTrace();//스캇계정으로 db접속이 완료됨. 
			} finally {
				try {
					stmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}

}
