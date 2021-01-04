package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dto.Client;

public class ClientDAO {
	ConnectionDAO conn;

	public ClientDAO() {
		conn = new ConnectionDAO();
	}

	public ArrayList<Client> clientsList() {
		Connection connection = conn.getConexion();
		ArrayList<Client> clients = new ArrayList<Client>();
		Client client;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM clients";
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				client = new Client();
				client.setId(rs.getInt(1));
				client.setName(rs.getString(2));
				clients.add(client);
			}
		} catch (SQLException sql) {
			sql.printStackTrace(System.out);
		} finally {
			conn.close(stmt);
			conn.close(rs);
			conn.close(connection);
		}
		return clients;
	}

	public int insertClient(Client client) {
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		String query = "INSERT INTO clients (id_document, name, surname, address, phone, zipcode, city) VALUES (?,?,?,?,?,?,?)";
		int rows = 0;
		int inserted = -1;
		try {
			stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, client.getDocumentID());
			stmt.setString(2, client.getName());
			stmt.setString(3, client.getSurname());
			stmt.setString(4, client.getAddress());
			stmt.setString(5, client.getPhone());
			stmt.setInt(6, client.getZipcode());
			stmt.setString(7, client.getCity());
			// execute update
			rows = stmt.executeUpdate();

			if (rows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					inserted = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			inserted = -1;
		} finally {
			conn.close(stmt);
			conn.close(connection);
		}
		return inserted;
	}

	public boolean deleteClient(String documentID) {
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		int rows = 0;
		boolean deleted = false;
		try {
			stmt = connection.prepareStatement("DELETE FROM `clinica`.`clients` WHERE (`id_document` = ?)");
			stmt.setString(1, documentID);
			rows = stmt.executeUpdate();
			if (rows == 1) {
				deleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			deleted = false;
		} finally {
			conn.close(stmt);
			conn.close(connection);
		}
		return deleted;
	}

	public Client checkClient(String documentID) {
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Client client = null;
		try {
			stmt = connection.prepareStatement("Select * FROM `clinica`.`clients` WHERE (`id_document` = ?)");
			stmt.setString(1, documentID);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) { 
					client = new Client();
					client.setId(rs.getInt(1));
					client.setDocumentID(rs.getString(2));
					client.setAddress(rs.getString(5));
					client.setPhone(rs.getString(6));
					client.setZipcode(rs.getInt(7));
					client.setCity(rs.getString(8));
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace(System.out);
		} finally {
			conn.close(stmt);
			conn.close(rs);
			conn.close(connection);
		}
		return client;

	}
	public boolean editClient(Client client) {
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		int rows = 0;
		boolean updated = false;
		try {
			stmt = connection.prepareStatement("UPDATE `clinica`.`clients` SET `address` = ?, `phone` = ?, `zipcode` = ?, `city` = ? WHERE (`id` = ?)");
			stmt.setString(1, client.getAddress());
			stmt.setString(2, client.getPhone());
			stmt.setInt(3, client.getZipcode() );
			stmt.setString(4, client.getCity());
			stmt.setInt(5, client.getId());
			rows = stmt.executeUpdate();
			if (rows == 1) {
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			updated = false;
		} finally {
			conn.close(stmt);
			conn.close(connection);
		}
		return updated;
	}
}
