package dto;

import java.sql.Date;

public class Treatment {
	private int id;
	private String description;
	private double price;
	private boolean isVaccine;
	private Date treatmentDate;
	
	public Treatment() {
		
	}
	
	public Date getTreatmentDate() {
		return treatmentDate;
	}

	public void setTreatmentDate(Date treatmentDate) {
		this.treatmentDate = treatmentDate;
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
		if(isVaccine) {
			return  description + ", price " + price
					+ "€ on " + treatmentDate+ " and it was a vaccine";
		}else {
			return  description + ", price " + price
					+ "€ on " + treatmentDate;
		}

	}

	
}
