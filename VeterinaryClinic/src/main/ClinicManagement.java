package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ClientDAO;
import dto.Client;
import utils.Leer;


public class ClinicManagement {

	public static void main(String[] args) {
		ClientDAO clientDAO = new ClientDAO();
		ArrayList<Client> clients = new ArrayList<Client>();
		clients = clientDAO.clientsList();
		for (Client client : clients) {
			System.out.println(client);

		}
		int opcion = Leer.pedirEntero("1-Register a client or a pet \n" 
				+ "2-Delete a client or a pet \n"
				+ "3-Edit a  client or pet\n"
				+ "4-Consult information\n"
				+ "0-Exit");
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				Client client = new Client();
				System.out.println("Enter the information in order to register a new client: ");
				/*client.setName(Leer.pedirCadena("Client\'s name: "));
				client.setSurname(Leer.pedirCadena("Client\'s surname: "));
				client.setAddress(Leer.pedirCadena("Client\'s address: "));
				client.setPhone(Leer.pedirCadena("Client\'s phone: "));
				client.setZipcode(Leer.pedirEntero("Client\'s zipcode: "));
				client.setCity(Leer.pedirCadena("Client\'s city: ")); */
				
				Client clientTest= new Client("Daniela","Amargos","C/ Anonima", "976585858", 50007,"Zaragoza");
				boolean res = clientDAO.insertClient(client);
				if (res) {
					Leer.mostrarEnPantalla("The client was registered");
					Leer.mostrarEnPantalla("Would you like to register a pet for this client?");
				}else
					Leer.mostrarEnPantalla("The client was registered. Please try again."); 
				break;
				
			case 2:
				/*Map<Integer, Apartamento> apartamentos = apartamentoDAO.listarApartamentos();
				System.out.println("Listado de los apartamentos y sus alquileres: ");
				for (Integer key : apartamentos.keySet()) {
					System.out.println(apartamentos.get(key));
				} */
				break;
			case 3:

				/*List<Inquilino> inquilinos = inquilinoDAO.consultarInquilinosActuales();
				for (Inquilino i : inquilinos) {
					System.out.println("El inquilino consultado es: " + i);
				}
				UtilidadesFicheros.escribirInquilinosXML(inquilinos);*/
				break;

			default:
				break;
			}
			opcion = Leer.pedirEntero("Escribe la siguiente opción");
		}

	}

}
