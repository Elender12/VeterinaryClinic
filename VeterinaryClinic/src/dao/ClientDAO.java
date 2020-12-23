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

	public ArrayList<Client> clientsList(){
		Connection connection = conn.getConexion();
		ArrayList<Client> clients = new ArrayList<Client>();
		Client client;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM clients";
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				client = new Client();
				client.setId(rs.getInt(1));
				client.setName(rs.getString(2));
				clients.add(client);
			}
		}catch(SQLException sql) {
			sql.printStackTrace(System.out);
		} finally {
			conn.close(stmt);
			conn.close(rs);
			conn.close(connection);
		}
		return clients;
	}
	public boolean insertClient(Client client) {
		Connection connection = conn.getConexion();
		PreparedStatement	stmt = null;
		String query = "INSERT INTO clients (name, surname, address, phone, zipcode, city) VALUES (?,?,?,?,?,?)";
		int rows = 0;
		boolean inserted = false;
		try {
			stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, client.getName());
			stmt.setString(2, client.getSurname());
			stmt.setString(3, client.getAddress());
			stmt.setString(4, client.getPhone());
			stmt.setInt(5, client.getZipcode());
			stmt.setString(6, client.getCity());
			//execute update
			rows = stmt.executeUpdate();
			//rows= stmt.executeUpdate(query);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()){
			    int id =rs.getInt(1);
			    System.out.println("id es: "+id);
			}
			if(rows == 1) {
				inserted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			inserted = false;
		}finally {
			conn.close(stmt);
			conn.close(connection);
		}
		return inserted;
	}
	public boolean insertPet(Client client) {
		boolean inserted = false;
		
		return inserted;
		
	}
}
