package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.JdbcConnection;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = JdbcConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from department");

			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.closeConnection();
			JdbcConnection.closeStatement(stmt);
			JdbcConnection.closeResultSet(rs);
		}
	}
}
