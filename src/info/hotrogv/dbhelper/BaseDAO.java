package info.hotrogv.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {

	protected static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotrogv?useSSL=false", "root", "1234");
			con = DriverManager.getConnection("jdbc:mysql://mysql22999-hotrogv.jl.serv.net.mx/hotrogv", "root", "hvxdVxjCCs");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
