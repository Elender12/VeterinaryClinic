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
	final static int DELETE = 3;
	final static int EDIT = 2;
	final static int REGISTER = 1;
	final static int MANAGE_CLIENT_OPTION = 1;
	final static int MANAGE_PET_OPTION = 2;
	final static int CONSUT_TREATMENT_INFO = 3;

	public static void main(String[] args) {
		String documentID = "";
		ClientDAO clientDAO = new ClientDAO();
		PatientDAO patientDAO = new PatientDAO();
		Client client = null;
		ArrayList<Patient> pets = null;
		int opcion = Leer.pedirEntero("1-Manage a CLIENT  \n" + "2- Manage a PET \n" + "3-Manage a TREATMENT \n"
				+ "4-Consult information\n" + "0-Exit");
		while (opcion != 0) {
			switch (opcion) {
			case MANAGE_CLIENT_OPTION:
				int opt = Leer.pedirEntero("Press " + REGISTER + " to register a client, " + EDIT
						+ " to edit a client or " + DELETE + " to delete one.");
				switch (opt) {
				case REGISTER:
					System.out.println("Complete the following information in order to register a new client: ");
					Client newClient = gatherClientData(REGISTER, null);
					int insertedID = clientDAO.insertClient(newClient);
					showResult(insertedID != NOT_VALID);
					break;
				case EDIT:
					documentID = Leer.pedirCadena("Insert client\'s document id: ");
					client = new Client();
					client = clientDAO.checkClient(documentID);
					if (client != null) {
						Client editedClient = gatherClientData(EDIT, client);
						showResult(clientDAO.editClient(editedClient));
					} else {
						System.out.println("Client was not updated.");
					}
					break;
				case DELETE:
					documentID = Leer.pedirCadena("Insert client\'s document id: ");
					client = clientDAO.checkClient(documentID);
					if (client != null) {
						boolean deleted = clientDAO.deleteClient(documentID);
						showResult(deleted);
					} else {
						System.out.println("Client was not found");
					}
					break;
				}
				break;
			case MANAGE_PET_OPTION:
				documentID = Leer.pedirCadena("Insert client\'s document id: ");
				client = new Client();
				client = clientDAO.checkClient(documentID);
				if (client != null) {
					int petOption = 0;
					petOption = Leer.pedirEntero("Press " + REGISTER + " to register a pet, " + EDIT
							+ " to edit a pet or " + DELETE + " to delete one.");
					switch (petOption) {
					case REGISTER:
						Patient newPatient = getPatientData(REGISTER, client.getId(), null);
						boolean res = patientDAO.insertPatient(newPatient);
						showResult(res);
						break;
					case EDIT:
						pets = new ArrayList<Patient>();
						pets = showAllPets(client, patientDAO);
						if (pets.size() >= 1) {
							int numberPet = Leer.pedirEntero("Insert number of pet you want to edit ");
							System.out.println("number pet" + numberPet);
							while (numberPet > pets.size() + 1 || numberPet < 0) {
								numberPet = Leer.pedirEntero("Insert again the number of pet you want to edit: ");
							}
							if (numberPet <= pets.size() + 1 && numberPet >= 1) {
								Patient patientToUpdate = pets.get(numberPet - 1);
								Patient updatedPatient = getPatientData(EDIT, client.getId(), patientToUpdate);
								boolean resAction = patientDAO.updatePatient(updatedPatient);
								showResult(resAction);
							} else {
								System.out.println("Not a valid option ");
							}

						} else {
							System.out.println("There are no pets for this client.");
						}
						break;
					case DELETE:
						pets = new ArrayList<Patient>();
						pets = showAllPets(client, patientDAO);
						if (pets.size() >= 1) {
							int number = Leer.pedirEntero("Insert number of pet you want to delete: ");
							while (number > pets.size() + 1 || number < 0) {
								number = Leer.pedirEntero("Insert again the number of pet you want to edit: ");
							}
							if (number <= pets.size() + 1 && number >= 1) {
								boolean deletedPet = patientDAO.deletePatient(pets.get(number - 1).getId());
								showResult(deletedPet);
							}
						} else {
							System.out.println("There are no pets for this client.");
						}
						break;
					default:
						break;
					}
				} else {
					System.out.println("Client was not found");
				}
				break;

			case CONSUT_TREATMENT_INFO:

				break;
			default:
				Leer.mostrarEnPantalla("Please insert a valid option.");
				break;
			}
			opcion = Leer.pedirEntero("Please choose another option: ");
		}

	}

	public static void showResult(boolean result) {
		if (result) {
			Leer.mostrarEnPantalla("The action was completed successfully");
		} else {
			Leer.mostrarEnPantalla("There was en error. Please try again.");
		}
	}

	public static ArrayList<Patient> showAllPets(Client client, PatientDAO patientDAO) {
		ArrayList<Patient> pets = patientDAO.getAllPatients(client.getId());
		if (pets != null) {
			for (int i = 0; i < pets.size(); i++) {
				Leer.mostrarEnPantalla((i + 1) + " " + pets.get(i).getName());
			}
			return pets;
		}
		return null;
	}

	public static Patient getPatientData(int option, int ownerID, Patient patient) {
		if (option == REGISTER) {
			Patient newPatient = new Patient();
			newPatient.setOwnerId(ownerID);
			newPatient.setName(Leer.pedirCadena("Pet's name: "));
			newPatient.setWeight(Leer.pedirFloat("Pet's weight: "));
			newPatient.setAge(Leer.pedirEntero("Pet's age: "));
			newPatient.setType(Leer.pedirCadena("Pet type: "));
			newPatient.setBreed(Leer.pedirCadena("Pet's breed: "));
			return newPatient;
		}
		if (option == EDIT) {
			patient.setWeight(Leer.pedirFloat("Pet's weight: "));
			patient.setAge(Leer.pedirEntero("Pet's age: "));
			return patient;
		}
		return null;
	}

	public static Client gatherClientData(int option, Client client) {
		if (option == REGISTER) {
			Client newClient = new Client();
			newClient.setDocumentID(Leer.pedirCadena("Client\'s document id: "));
			newClient.setName(Leer.pedirCadena("Client\'s name: "));
			newClient.setSurname(Leer.pedirCadena("Client\'s surname: "));
			newClient.setAddress(Leer.pedirCadena("Client\'s address: "));
			newClient.setPhone(Leer.pedirCadena("Client\'s phone: "));
			newClient.setZipcode(Leer.pedirEntero("Client\'s zipcode: "));
			newClient.setCity(Leer.pedirCadena("Client\'s city: "));
			return newClient;
		}
		if (option == EDIT) {
			client.setAddress(Leer.pedirCadena("Client\'s address: "));
			client.setPhone(Leer.pedirCadena("Client\'s phone: "));
			client.setZipcode(Leer.pedirEntero("Client\'s zipcode: "));
			client.setCity(Leer.pedirCadena("Client\'s city: "));
			return client;
		}
		return null;
	}

}
