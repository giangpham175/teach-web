package info.webcanhan.dao;

import java.sql.*;

public class DBConnection {
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/comment";
	String user = "root";
	String password = "1234";

	public Connection KetNoi() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			/*
			 * if(conn != null) { System.out.println("Ket noi thanh cong"); }
			 */

		} catch (ClassNotFoundException e) {
			System.out.println("Load driver khong thanh cong");
		} catch (SQLException e) {
			System.out.println("Loi : " + e.getMessage());
		}
		return conn;
	}
}
