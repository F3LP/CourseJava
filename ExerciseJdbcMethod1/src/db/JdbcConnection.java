package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {

	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			if (conn == null) {
				String url = "jdbc:mysql://localhost:3306/coursejdbc";

				Properties props = new Properties();
				props.put("user", "developer");
				props.put("password", "xxxxx");
				props.put("useSSL", "false");
				conn = DriverManager.getConnection(url, props);
				System.out.println("Connection open");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Connection close");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
