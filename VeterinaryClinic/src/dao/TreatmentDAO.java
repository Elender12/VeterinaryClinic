package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import dto.Treatment;
import dto.Patient;

public class TreatmentDAO {
	
	final static double IVA = 0.21;
	
	ConnectionDAO conn;
	public TreatmentDAO() {
		conn = new ConnectionDAO();
	}
	
	
	/**
	 * 
	 * @param ownerID
	 * @return lista con nombre de la mascota, ultimo tratamiento recibido, descripcion y precio con IVA
	 */
	public Map<String, Treatment> getLastTreatment(String ownerID) {
		Map<String, Treatment> lastTreatment = new TreeMap<String, Treatment>();
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;;
		try {
			stmt = connection.prepareStatement(
						"select p.name, t.description, t.price + (t.price * "+IVA+"), max(treatment_date) from applied_treatments as ap inner join patients as p on ap.id_patient = p.id inner join clients as c on c.id= p.owner_id inner join treatments as t on ap.id_treatment= t.id where c.id_document = ? group by p.name");
			stmt.setString(1, ownerID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Treatment treatment = new Treatment();
				String name = rs.getString(1);
				treatment.setDescription(rs.getString(2));
				treatment.setPrice(rs.getDouble(3));
				treatment.setTreatmentDate(rs.getDate(4));
				lastTreatment.put(name, treatment);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			
		}finally {
			conn.close(stmt);
			conn.close(rs);
			conn.close(connection);
		}
		return lastTreatment;
	}
	/**
	 * 
	 * @param ownerID
	 * @return lista con todos los tratamientos recibidos por cada mascota que pertenece a un usuario
	 */
	public Map<String, ArrayList<Treatment>> getPetTreatments(String ownerID) {
		Map<String, ArrayList<Treatment>> vaccinesList = new TreeMap<String, ArrayList<Treatment>>();
		Connection connection = conn.getConexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;;
		try {
			stmt = connection.prepareStatement(
						"select p.name, t.description, t.isVaccine, treatment_date, t.price from applied_treatments as ts inner join patients as p on ts.id_patient = p.id inner join clients as c on c.id= p.owner_id inner join treatments as t on ts.id_treatment= t.id where c.id_document = ?");
			stmt.setString(1, ownerID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Treatment treatment = new Treatment();
				Patient patient = new Patient();
				patient.setName(rs.getString(1));
				treatment.setDescription(rs.getString(2));
				treatment.setVaccine(rs.getBoolean(3));
				treatment.setTreatmentDate(rs.getDate(4));
				treatment.setPrice(rs.getDouble(5));
				if(vaccinesList.containsKey(rs.getString(1))) {
					vaccinesList.get(rs.getString(1)).add(treatment);
				}else {
					vaccinesList.put(patient.getName(),new ArrayList<Treatment>());
					vaccinesList.get(rs.getString(1)).add(treatment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			
		}finally {
			conn.close(stmt);
			conn.close(rs);
			conn.close(connection);
		}
			return vaccinesList;
	}
}
