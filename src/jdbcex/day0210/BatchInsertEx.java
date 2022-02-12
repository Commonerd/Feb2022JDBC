package jdbcex.day0210;

import java.sql.*; 
public class BatchInsertEx { 
	

	public static void main(String args[]){ 
		ResultSet rs = null; 
		Connection con=null; 
		Statement stmt=null; 
		String sql = null; 
		boolean commit = false;//false : commit 안함 / true 커밋 


		try { 
			con = DriverManager.getConnection( 
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"c##scott", 
					"tiger"); 
			stmt = con.createStatement(); 
			sql = "create table test4(id varchar2(10) primary key, " + 
					"password varchar2(10))"; 
			//stmt.executeUpdate(sql); 

			con.setAutoCommit(false);//트랜잭션 시작: 이하 하나으 트랜잭션으로 묶음
			stmt.addBatch("INSERT INTO test4 " + 
					"VALUES('abc1011', '1111')"); 
			stmt.addBatch("INSERT INTO test4 " + 
					"VALUES('abc2022', '2222')"); 
			stmt.addBatch("INSERT INTO test4 " + 
					"VALUES('abc3033', '3333')");//중복이 되며에러남 
			stmt.addBatch("INSERT INTO test4 " + 
					"VALUES('abc4044', '4444')"); 

			int [] updateCounts = stmt.executeBatch();//일괄실행 
			commit = true; 
			con.commit(); 
			con.setAutoCommit(commit); 

			rs = stmt.executeQuery("SELECT * FROM test4"); 

			while (rs.next()) { 
				String id = rs.getString("id"); 
				String password = rs.getString("password"); 
				System.out.println("id : " + id + 
						" , password : "+password); 
			} 
		}catch(SQLException sqle) { 
			sqle.printStackTrace();


		}finally{ 
			try{ 
				if(!commit) con.rollback(); 
				if(rs != null) rs.close(); 
				if(stmt != null) stmt.close(); 
				if(con != null) con.close(); 
			}catch(SQLException sqle){ 
				sqle.printStackTrace(); 
			} 
		} 
	} 
} 
