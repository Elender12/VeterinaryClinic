package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Client;
import dto.Patient;

public class PatientDAO {
	ConnectionDAO conn;
	public PatientDAO() {
		conn = new ConnectionDAO();
	}
	
	public boolean  insertPatient(Patient patient) {
		Connection connection = conn.getConexion();
		PreparedStatement	stmt = null;
		String query = "INSERT INTO patients (owner_id, name, weight, age, type, breed) VALUES (?,?,?,?,?,?)";
		int rows = 0;
		boolean inserted = false;
		try {
			stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, patient.getOwnerId());
			stmt.setString(2, patient.getName());
			stmt.setFloat(3,  patient.getWeight());
			stmt.setInt(4, patient.getAge());
			stmt.setString(5, patient.getType());
			stmt.setString(6, patient.getBreed());
			//execute update
			rows = stmt.executeUpdate();
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
	public boolean deletePatient(int petID) {
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		int rows = 0;
		boolean deleted = false;
		try {
			stmt = connection.prepareStatement(
						"DELETE FROM `clinica`.`patients` WHERE (`id` = ?)");
			stmt.setInt(1, petID);
			rows = stmt.executeUpdate();
			if(rows == 1 ) {
				deleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			deleted = false;
		}finally {
			conn.close(stmt);
			conn.close(connection);
		}
		return deleted;
		
	}
	
	public ArrayList<Patient> getAllPatients(int ownerID){
		ArrayList<Patient> pets = new ArrayList<Patient>();
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int rows = 0;
	
		try {
			stmt = connection.prepareStatement(
						"SELECT id, name FROM `clinica`.`patients` WHERE (`owner_id` = ?)");
			stmt.setInt(1, ownerID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt(1));
				patient.setName(rs.getString(2));
				pets.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			
		}finally {
			conn.close(stmt);
			conn.close(rs);
			conn.close(connection);
		}
		
		return pets;
		
	}
}
