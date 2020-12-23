package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDAO {
	private static ConnectionProperties properties;

	public ConnectionDAO() {
		properties = new ConnectionProperties();
		properties.readConnectionData();
	}

	public Connection getConexion() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getServer() + properties.getNameBD(),
					properties.getUser(), properties.getPassword());
		} catch (SQLException e) {
			System.out.print("There was en error with the connection");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.print("There was en error with the connection");
			e.printStackTrace();
		}
		return connection;
	}

	public void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public void close(PreparedStatement stmt) {
		try {
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

}
