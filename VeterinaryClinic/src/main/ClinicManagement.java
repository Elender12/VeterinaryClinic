package main;

import java.util.ArrayList;

import dao.ClientDAO;
import dto.Client;

public class ClinicManagement {

	public static void main(String[] args) {
		System.out.println("Testing");
		ClientDAO cl = new ClientDAO();
		ArrayList<Client> clients = new ArrayList<Client>();
		clients = cl.clientsList();
		for (Client client : clients) {
			System.out.println(client);
			
		}
		
		
	}

}
