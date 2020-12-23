package dao;

import java.sql.Connection;
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
			
		} finally {
			conn.close(stmt);
			conn.close(rs);
			conn.close(connection);
		}
		
		
		return clients;
	}
	/*public Map<Integer, Apartamento> listarApartamentos() {
		
		Map<Integer, Apartamento> apartList = new TreeMap<Integer, Apartamento>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(
					"select a.codigo, a.nombre, a.propietario, a.direccion, a.año_construccion, a.año_reforma, "
							+ "a.cp, a.numero_visitas, r.fecha_inicio, r.fecha_fin "
							+ "from apartamento a join reserva_alquiler r on a.codigo = r.codigo_apartamento "
							+ "order by a.codigo;");
			Apartamento apartamento = null;
			while (rs.next()) {
				apartamento = new Apartamento();
				apartamento.setCodigo(rs.getInt(1));
				apartamento.setNombre(rs.getString(2));
				apartamento.setPropietario(rs.getString(3));
				apartamento.setDireccion(rs.getString(4));
				apartamento.setAnyoConstruccion(rs.getInt(5));
				apartamento.setAnyoReforma(rs.getInt(6));
				apartamento.setCp(rs.getString(7));
				apartamento.setNumeroVisitas(rs.getInt(8));
				Reserva reserva = new Reserva();
				reserva.setFechaInicio(rs.getDate(9));
				reserva.setFechaFin(rs.getDate(10));
				if (apartList.containsKey(rs.getInt(1))) {
					for (Integer key : apartList.keySet()) {
						if (apartList.containsKey(rs.getInt(1))) {
							if (apartamento.getCodigo() == apartList.get(key).getCodigo()) {
								apartList.get(key).getReservas().add(reserva);
							}
						}
					}

				} else {
					apartList.put(rs.getInt(1), apartamento);
					apartList.get(rs.getInt(1)).getReservas().add(reserva);
				}
			}
		
		} catch (SQLException sql) {
			System.out.print("Se ha producido un error al realizar la consulta");
			sql.printStackTrace(System.out);
		} finally {
			cx.close(stmt);
			cx.close(rs);
			cx.close(conexion);
		}
		return  apartList;
	}*/
	
}
