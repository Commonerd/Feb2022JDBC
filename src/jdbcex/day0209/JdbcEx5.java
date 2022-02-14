package jdbcex.day0209;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcEx5 {
//부서번호 입력시 
//해당 부서의 부서번호, 최고 급여, 최소 급여, 급여 평균(소수점 없음) 출력
//함수를 사용하는 경우 컬럼명이 없다!! -> 별칭을 사용하거나 인덱스를 사용한다.	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("부서번호를 입력하세요 > ");
		int input = scan.nextInt();
				
		String sql = "select deptno, max(sal) as max, min(sal), round(avg(sal)) "
				+ "from emp "
				+ "group by deptno "
				+ "having deptno ="+input;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##scott";
		String password = "tiger";

		Connection con = null;
		Statement stmt = null; 
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
		
			if(rs.next()) {
				int deptno = rs.getInt("deptno");
				int max = rs.getInt("max");
				int min = rs.getInt(3);
				int avg = rs.getInt(4);
				
				System.out.printf("부서 : %d 최대 : %d  최소 : %d 평균 : %d"
						,deptno,max,min,avg);
			}else {
				System.out.println(input+" 부서 없음"); 
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