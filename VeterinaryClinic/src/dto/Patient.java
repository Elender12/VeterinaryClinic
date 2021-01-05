package dto;

import java.util.ArrayList;

public class Patient {
	private int id;
	private int ownerId;
	private String name;
	private float weight;
	private int age;
	private String type;
	private String breed;
	private ArrayList<Treatment> treatments;
	public Patient() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}
	public void setTreatments(ArrayList<Treatment> treatments) {
		this.treatments = treatments;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", ownerId=" + ownerId + ", name=" + name + ", weight=" + weight + ", age=" + age
				+ ", breed=" + breed + "]";
	}
	
	
}
