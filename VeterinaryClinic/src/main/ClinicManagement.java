package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ClientDAO;
import dao.PatientDAO;
import dto.Client;
import dto.Patient;
import utils.Leer;


public class ClinicManagement {
	final static int OPTION_YES = 0;
	final static int OPTION_NO = 1;
	final static int OPTION_CLIENT = 2;
	final static int OPTION_PET = 3;
	final static int NOT_VALID = -1;
	
	public static void main(String[] args) {
		ClientDAO clientDAO = new ClientDAO();
		PatientDAO patientDAO = new PatientDAO();
		ArrayList<Client> clients = new ArrayList<Client>();
		int opcion = Leer.pedirEntero("1-Register a client or a pet \n" 
				+ "2-Delete a client or a pet \n"
				+ "3-Edit a  client or pet\n"
				+ "4-Consult information\n"
				+ "0-Exit");
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				System.out.println("Enter the information in order to register a new client: ");
				Client client = getClientData();
				//Client clientTest= new Client("Daniela","6784510Y","Amargos","C/ Anonima", "976585858", 50007,"Zaragoza");
				int insertedID = clientDAO.insertClient(client);
				if (insertedID != -1) {
					Leer.mostrarEnPantalla("The client was registered");
					int registerPetOption = Leer.pedirEntero("Would you like to register a pet for this client? \n Insert "+ OPTION_YES+" for YES and "+OPTION_NO+" for NO.");
					if(registerPetOption == 1) {
						Patient patient = getPatientData();
						patient.setOwnerId(insertedID);
						//insert  method here
						boolean res = patientDAO.insertPatient(patient);
						if(res) {
							Leer.mostrarEnPantalla("The pet was registered");
						}
					}else {
						Leer.mostrarEnPantalla("Thank you for using this service.");
					}
				}else
					Leer.mostrarEnPantalla("The client was NOT registered. Please try again."); 
				break;
			case 2:
				String documentID = Leer.pedirCadena("Insert client\'s document id: ");
				int clientID = clientDAO.checkClient(documentID);
				if(clientID != NOT_VALID) {
					int option = Leer.pedirEntero("Would you like to delete this client or the pet that owns? Press " +OPTION_CLIENT+" to delete a CLIENT and "+OPTION_PET+" to delete a PET ");
					while(option != OPTION_CLIENT || option != OPTION_PET) {
						option = Leer.pedirEntero("Insert a correct option, please");
					}
					switch(option) {
					case OPTION_CLIENT:
						boolean deleted = clientDAO.deleteClient(documentID);
						if(deleted) {
							Leer.mostrarEnPantalla("Client deleted successfully");
						}
						break;
					case OPTION_PET:
						ArrayList<Patient> pets = patientDAO.getAllPatients(clientID);
						for (int i = 0; i < pets.size(); i++) {
							Leer.mostrarEnPantalla(i+" "+pets.get(i).getName());
						}
						int number= Leer.pedirEntero("Insert number of pet you want to delete ");
						
						boolean deletedPet = patientDAO.deletePatient(pets.get(number).getId());
						if(deletedPet) {
							Leer.mostrarEnPantalla("Patient deleted");
						}
						break;
					default:
						System.out.println("Insert a valid option, please");
					
					}
					
				}else {
					Leer.mostrarEnPantalla("Client not found!");
				}
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
	public static Patient getPatientData() {
		Patient patient = new Patient();
		patient.setName(Leer.pedirCadena("Pet's name: "));
		patient.setWeight(Leer.pedirFloat("Pet's weight: "));
		patient.setAge(Leer.pedirEntero("Pet's age: "));
		patient.setType(Leer.pedirCadena("Pet type: "));
		patient.setBreed(Leer.pedirCadena("Pet's breed: "));
		return patient;
	}
	public static Client getClientData() {
		Client client = new Client();
		client.setDocumentID(Leer.pedirCadena("Client\'s document id: "));
		client.setName(Leer.pedirCadena("Client\'s name: "));
		client.setSurname(Leer.pedirCadena("Client\'s surname: "));
		client.setAddress(Leer.pedirCadena("Client\'s address: "));
		client.setPhone(Leer.pedirCadena("Client\'s phone: "));
		client.setZipcode(Leer.pedirEntero("Client\'s zipcode: "));
		client.setCity(Leer.pedirCadena("Client\'s city: ")); 
		return client;
	}

}
