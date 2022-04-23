package model;

import java.util.ArrayList;


public class Complaint {
	//Attributes
	private int compID;
	private String userID;
	private String des;
	private String answer;
	private String updatedDate;
	private String compDate;
	private String cstatus;
	
	public int getCompID() {
		return compID;
	}
	public void setCompID(int compID) {
		this.compID = compID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCompDate() {
		return compDate;
	}
	public void setCompDate(String compDate) {
		this.compDate = compDate;
	}
	public String getStatus() {
		return cstatus;
	}
	public void setStatus(String cstatus) {
		this.cstatus = cstatus;
	}
	
	

}
