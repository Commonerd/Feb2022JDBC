package jdbcex.day0209;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcEx4 {
//사원의 이름 검색 - 스캐너의 이름의 일부만 입력해도 해당 글자를 포함하는
//사원의 사번, 이름, 직급을 출력하세요. 
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		System.out.println("사원 이름 검색 >");
		String input = scan.nextLine();
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##scott";
		String password = "tiger";
		
		String sql = "select empno, ename, job from emp"
				+ " where ename like upper('%"+input+"%')";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url,user,password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				System.out.printf("사번 : %d 이름 : %s 직책 : %s",empno,ename,job);
				
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
		scan.close();
	
		
	}

}
