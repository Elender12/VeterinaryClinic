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
	/**
	 * 
	 * @param patient
	 * @return resultado de la operacion
	 */
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
	/**
	 * 
	 * @param petID
	 * @return resultado de la operacion
	 */
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

	/**
	 * 
	 * @param patient
	 * @return resultado de la operacion
	 */
	public boolean updatePatient(Patient patient) {
		boolean updated = false;
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			stmt = connection.prepareStatement(
						"UPDATE `clinica`.`patients` SET `weight` = ?, `age` = ? WHERE (`id` = ?)");
			stmt.setFloat(1, patient.getWeight());
			stmt.setInt(2, patient.getAge());
			stmt.setInt(3, patient.getId());
			rows = stmt.executeUpdate();
			if(rows == 1 ) {
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			updated = false;
		}finally {
			conn.close(stmt);
			conn.close(connection);
		}
		return updated;
	}
	
	/**
	 * 
	 * @param ownerID
	 * @return lista con todos las mascotas
	 */
	public ArrayList<Patient> getAllPatients(int ownerID){
		ArrayList<Patient> pets = null;
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;;
		try {
			stmt = connection.prepareStatement(
						"SELECT * FROM `clinica`.`patients` WHERE (`owner_id` = ?)");
			stmt.setInt(1, ownerID);
			rs = stmt.executeQuery();
			pets = new ArrayList<Patient>();
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getInt(1));
				patient.setOwnerId(rs.getInt(2));
				patient.setName(rs.getString(3));
				patient.setWeight(rs.getFloat(4));
				patient.setAge(rs.getInt(5));
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
