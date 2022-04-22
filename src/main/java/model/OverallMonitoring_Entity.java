package model;

import java.util.ArrayList;

public class OverallMonitoring_Entity {
	
	//Attributes
	private int monitoring_ID;
	private int pay_ID;
	private int power_consumption_ID;
	private String month;
	private int units;
	private double balance;
	private String comment;
	
	
	public int getMonitoring_ID() {
		return monitoring_ID;
	}
	public void setMonitoring_ID(int monitoring_ID) {
		this.monitoring_ID = monitoring_ID;
	}
	public int getPay_ID() {
		return pay_ID;
	}
	public void setPay_ID(int pay_ID) {
		this.pay_ID = pay_ID;
	}
	public int getPower_consumption_ID() {
		return power_consumption_ID;
	}
	public void setPower_consumption_ID(int power_consumption_ID) {
		this.power_consumption_ID = power_consumption_ID;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}



}
