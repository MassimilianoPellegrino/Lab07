package it.polito.tdp.poweroutages.model;

public class PowerOutage {
	
	int id;
	int nerc_id;
	int customers_affected;
	int year;
	double  hours;
	
	public PowerOutage(int id, int nerc_id, int customers_affected, int year, double hours) {
		super();
		this.id = id;
		this.nerc_id = nerc_id;
		this.customers_affected = customers_affected;
		this.year = year;
		this.hours = hours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNerc_id() {
		return nerc_id;
	}

	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}

	public int getCustomers_affected() {
		return customers_affected;
	}

	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id = " + id + ", nerc_id = " + nerc_id + ", customers_affected = " + customers_affected
				+ ", year = " + year + ", hours = " + hours;
	}
	
	

}
