package model;

import java.util.ArrayList;

public class Payment {
	
		//Attributes
		private int paymentID;
		private String userID;
		private int billID;
		private double total_amount;
		private double paid_amount;
		private double balance;
		private String month;
		private String paid_Date;


		public Payment() {

			this.paid_Date = paid_Date;
		}
		
		
		public Payment(int paymentID, String userID, int billID, double total_amount, double paid_amount,
				double balance, String month, String paid_Date) {
			super();
			this.paymentID = paymentID;
			this.userID = userID;
			this.billID = billID;
			this.total_amount = total_amount;
			this.paid_amount = paid_amount;
			this.balance = balance;
			this.month = month;
			this.paid_Date = paid_Date;
		}
		
		public int getPaymentID() {
			return paymentID;
		}
		public void setPaymentID(int paymentID) {
			this.paymentID = paymentID;
		}
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public int getBillID() {
			return billID;
		}
		public void setBillID(int billID) {
			this.billID = billID;
		}
		public double getTotal_amount() {
			return total_amount;
		}
		public void setTotal_amount(double total_amount) {
			this.total_amount = total_amount;
		}
		public double getPaid_amount() {
			return paid_amount;
		}
		public void setPaid_amount(double paid_amount) {
			this.paid_amount = paid_amount;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public String getPaid_Date() {
			return paid_Date;
		}
		public void setPaid_Date(String paid_Date) {
			this.paid_Date = paid_Date;
		}


		
		
		
		

}
