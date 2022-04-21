package model;

import java.util.ArrayList;

public class Billing_Entity {
	
	private int bill_ID;
	private int power_consumption_ID;
	private String User_Name;
	private int NIC;
	private String address;
	private String month;
	private double amount;
	private int monthly_units;
	private int rate;
	
	
	// Constructor
	
	public Billing_Entity(int bill_ID, int power_consumption_ID, String user_Name, int nIC, String address,
			String month, double amount, int monthly_units, int rate) {
		super();
		this.bill_ID = bill_ID;
		this.power_consumption_ID = power_consumption_ID;
		User_Name = user_Name;
		NIC = nIC;
		this.address = address;
		this.month = month;
		this.amount = amount;
		this.monthly_units = monthly_units;
		this.rate = rate;
	}
	
	
	/*
	public Billing_Entity() {
		super();
	}	
	*/
	
	// Getters
	
	public int getBill_ID() {
		return bill_ID;
	}

	public int getPower_consumption_ID() {
		return power_consumption_ID;
	}
	public String getUser_Name() {
		return User_Name;
	}
	public int getNIC() {
		return NIC;
	}
	public String getAddress() {
		return address;
	}
	public String getMonth() {
		return month;
	}
	public double getAmount() {
		return amount;
	}
	public int getMonthly_units() {
		return monthly_units;
	}
	public int getRate() {
		return rate;
	}
	
	
	// Setters
	
	public void setBill_ID(int bill_ID) {
		this.bill_ID = bill_ID;
	}
	public void setPower_consumption_ID(int power_consumption_ID) {
		this.power_consumption_ID = power_consumption_ID;
	}
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}
	public void setNIC(int nIC) {
		NIC = nIC;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setMonthly_units(int monthly_units) {
		this.monthly_units = monthly_units;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	

} // End of class
