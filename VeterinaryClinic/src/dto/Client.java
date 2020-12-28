package dto;

public class Client {
	//attributes
	private int id;
	private String documentID;
	private String name;
	private String surname;
	private String address;
	private String phone;
	private int zipcode;
	private String city;
	public Client() {
		
	}
	
	public Client(String name, String documentID,String surname, String address, String phone, int zipcode, String city) {
		this.documentID= documentID;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.zipcode = zipcode;
		this.city = city;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getDocumentID() {
		return documentID;
	}

	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", phone="
				+ phone + ", zipcode=" + zipcode + ", city=" + city + "]";
	}
	
}
