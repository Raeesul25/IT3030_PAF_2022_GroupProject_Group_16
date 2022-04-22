package model;

import java.util.ArrayList;

public class Consumption {
	
	//Attributes
	private int conID;
	private String userID;
	private String month;
	private int preMonReading;
	private int curMonReading;
	private int conUnits;
	private ArrayList<Consumption> summary;
	// Test Comment
	
	
	public Consumption() {
		
	}


	public int getConID() {
		return conID;
	}


	public void setConID(int conID) {
		this.conID = conID;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public int getPreMonReading() {
		return preMonReading;
	}


	public void setPreMonReading(int preMonReading) {
		this.preMonReading = preMonReading;
	}


	public int getCurMonReading() {
		return curMonReading;
	}


	public void setCurMonReading(int curMonReading) {
		this.curMonReading = curMonReading;
	}


	public int getConUnits() {
//		conUnits = (curMonReading - preMonReading);
		return conUnits;
	}


	public void setConUnits(int conUnits) {
		this.conUnits = conUnits;
	}


	public ArrayList<Consumption> getSummary() {
		return summary;
	}


	public void setSummary(ArrayList<Consumption> summary) {
		this.summary = summary;
	}
	
	
	
	
}
