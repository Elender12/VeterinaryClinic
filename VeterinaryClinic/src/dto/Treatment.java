package dto;

public class Treatment {
	private int id;
	private String description;
	private double price;
	private boolean isVaccine;
	public Treatment() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isVaccine() {
		return isVaccine;
	}
	public void setVaccine(boolean isVaccine) {
		this.isVaccine = isVaccine;
	}
	@Override
	public String toString() {
		return "Treatment [id=" + id + ", description=" + description + ", price=" + price + ", isVaccine=" + isVaccine
				+ "]";
	}
	
}
