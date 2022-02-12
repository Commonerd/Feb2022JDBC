package jdbcex.day0209;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class JdbcEx3 {
//사원번호를 스캐너로 입력 받아서 해당 사원의 사원번호, 이름, 입사일 출력
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("사원번호를 입력하세요 > ");
		int input = scan.nextInt();

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##scott";
		String password = "tiger";

		String sql = "select empno, ename, hiredate from emp" + " where empno=" + input;
		// + " where ename 이라면...?
		// =>+ " where ename='"+input+"'";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {// 가져오는 레코드가 한 줄이라면 이렇게..
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				Timestamp h_date = rs.getTimestamp("hiredate");// getDate는 자바.sql패키지임. 사용하지 않음. 다른애로 써야함.
				// 타임스탬프 임포트할 때 sql로 불러오도록!! 이상한 것 불러오지
				Date hiredate = new Date(h_date.getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
				System.out.printf("사번 : %d 이름 : %s 입사일 : %s", empno, ename, sdf.format(hiredate));
			} else {
				System.out.println(input + " 사원은 없습니다.");
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
