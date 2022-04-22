package recources;

import java.sql.*;
import java.util.ArrayList;

import model.Payment;
import util.DBConnection;

public class PaymentResources {

	//DBConnection object to connect to database
		DBConnection dbConnect = new DBConnection();
		String dbErrorMessage = "Database Connection failed!!";
	
		
		//Insert new payment details to the table
		public String insertPayment(String userID,String billID, String total_amount, String paid_amount, String month){
			
			String output = "";
		
			try{

				Connection con = dbConnect.connect();
				if (con == null){
					return "Error while connecting to the database for inserting."; 	
				}
				// create a prepared statement
				String query = " insert into payment(paymentID,userID,billID,total_amount,paid_amount,balance,month,paid_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, userID);
				preparedStmt.setInt(3, Integer.parseInt(billID));
				preparedStmt.setDouble(4, Double.parseDouble(total_amount));
				preparedStmt.setDouble(5, Double.parseDouble(paid_amount));
				
				double balance = Double.parseDouble(total_amount) - Double.parseDouble(paid_amount);
				preparedStmt.setDouble(6, balance);
				preparedStmt.setString(7, month);
				
				//get current date
				long currentDate=System.currentTimeMillis();  
				java.sql.Date paid_Date=new java.sql.Date(currentDate);
				preparedStmt.setString(8, paid_Date.toString());
				
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Payment Inserted successfully"; //Successful message when inserting payment
				
			}catch (Exception e){
				output = "Error while inserting the Payment to the table.";
				System.err.println(e.getMessage());
			}
				
			return output;
			
		}
		
		//Update payment details
		public String updatePayment(String paymentID,String userID,String billID, String total_amount, String p_amount,String obalance, String month,String old_paid_Date,String new_paid) {
			
			String output = "";
			try{
				
				Connection con = dbConnect.connect();
				if (con == null){
					return "Error while connecting to the database for updating."; 	
				}
				
	
				// create a update statement
				String query = "UPDATE payment SET  paid_amount=?, balance=?, paid_Date=?, curMonReading=?, conUnits=?, WHERE paymentID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(paymentID));
				preparedStmt.setString(2, userID);
				preparedStmt.setInt(3, Integer.parseInt(billID));
				preparedStmt.setDouble(4, Double.parseDouble(total_amount));
				
				double paid_amount =Double.parseDouble(p_amount);
				paid_amount=paid_amount + Double.parseDouble(new_paid);
				preparedStmt.setDouble(5, (paid_amount));
				
				double balance =Double.parseDouble(obalance);
				balance = balance - Double.parseDouble(new_paid);
				preparedStmt.setDouble(6, balance);
				preparedStmt.setString(7, month);
				
				//get current date
				long currentDate=System.currentTimeMillis();  
				java.sql.Date paid_Date=new java.sql.Date(currentDate);
				preparedStmt.setString(8, paid_Date.toString());
			
				// execute the statement
				preparedStmt.execute();
			
				con.close();
				output = "Payment updated successfully";
			
			}catch (Exception e){
				output = "Error while updating the payment.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//Delete payment
		public String deletePayment(String paymentID){
			String output = "";
			try{
				
				Connection con = dbConnect.connect();
				if (con == null){
					return "Error while connecting to the database for deleting."; 	
				}
				// create a prepared statement
				String query = "DELETE FROM payment WHERE paymentID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(paymentID));
				
				// execute the statement
				preparedStmt.execute();

				con.close();
				output = "Deleted successfully";
			}catch (Exception e){
				output = "Error while deleting the payment.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//get one payment
		public ArrayList<model.Payment> read_one_payment(int paymentID){

			//store details of the user in a ArrayList			
			ArrayList<model.Payment> payment = new ArrayList<>();
			
			try{
				Connection con = dbConnect.connect();
				if (con == null){
					System.err.println("Error while connecting to the database for reading.");
					return null;
				}
				
				
				//statement to get data from payment table
				String query1 ="SELECT * from shop where paymentID = '"+ paymentID+"'";
			
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query1);
				
				// get details from table
				
					int pID = rs.getInt(1);
					String userID = rs.getString(2);
					int billID = rs.getInt(3);
					double total_amount = rs.getDouble(4);
					double paid_amount = rs.getDouble(5);
					double balance = rs.getDouble(6);
					String month = rs.getString(7);
					String paid_Date = rs.getString(8);

					
					//create object from Payment class
					model.Payment p1 = new Payment(pID, userID, billID, total_amount, paid_amount, balance, month, paid_Date);//new Payment(pID,userID,billID,total_amount,paid_amount,balance,month,paid_Date);
					
					//add details of payment to arraylist
					payment.add(p1);
				
				
				con.close();
				
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}
				
			return payment;
			
		}
	/*	
		public ArrayList<model.> read_one_payment(int paymentID){

			//store details of the user in a ArrayList			
			ArrayList<model.Payment> payment = new ArrayList<>();
			
			try{
				Connection con = dbConnect.connect();
				if (con == null){
					System.err.println("Error while connecting to the database for reading.");
					return null;
				}
				
				
				//statement to get data from payment table
				String query1 ="SELECT * from shop where paymentID = '"+ paymentID+"'";
			
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query1);
				
				// get details from table
				
					int pID = rs.getInt(1);
					String userID = rs.getString(2);
					int billID = rs.getInt(3);
					double total_amount = rs.getDouble(4);
					double paid_amount = rs.getDouble(5);
					double balance = rs.getDouble(6);
					String month = rs.getString(7);
					String paid_Date = rs.getString(8);

					
					//create object from Payment class
					model.Payment p1 = new Payment(pID, userID, billID, total_amount, paid_amount, balance, month, paid_Date);//new Payment(pID,userID,billID,total_amount,paid_amount,balance,month,paid_Date);
					
					//add details of payment to arraylist
					payment.add(p1);
				
				
				con.close();
				
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}
				
			return payment;
			
		}
		*/
		
		//get all payment details based on the user id
		public String readPayment(String u_ID){
			String output = "";
			try{
				Connection con = dbConnect.connect();
				if (con == null){
					return "Error while connecting to the database for reading."; 	
				}
				// Prepare the table to display payment details
				output = "<table border='1'><tr><th>Payment ID</th><th>User ID</th>" +
				"<th>Bill ID</th>" +
				"<th>Toatl Amount</th>" +
				"<th>Paid Amount</th>" +
				"<th>Balance</th>" +
				"<th>Month</th>" +
				"<th>Paid Date</th>" +
				"<th>Update</th><th>Delete</th></tr>";
				
				String query = "select * from payment WHERE userID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(2,u_ID);
				ResultSet rs = preparedStmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next()){
					String paymentID = Integer.toString(rs.getInt("paymentID"));
					String userID = rs.getString("userID");
					String billID = rs.getString("billID");
					String total_amount = Double.toString(rs.getDouble("total_amount"));
					String paid_amount = Double.toString(rs.getDouble("paid_amount"));
					String balance = Double.toString(rs.getDouble("balance"));
					String month =rs.getString("month");
					String paid_Date =rs.getString("paid_Date");
				
					// Add into the html table
					output += "<tr><td>" + paymentID + "</td>";
					output += "<td>" + userID + "</td>";
					output += "<td>" + billID + "</td>";
					output += "<td>" + total_amount + "</td>";
					output += "<td>" + paid_amount + "</td>";
					output += "<td>" + balance + "</td>";
					output += "<td>" + month + "</td>";
					output += "<td>" + paid_Date + "</td>";
				
					// button for backing a concept
					 output += "<td><input name='btnUpdate' " 
					 + " type='button' value='Update'></td>"
					 + "<td><form method='post' action=''>"
					 + "<input name='btnRemove' " 
					 + " type='submit' value='Delete'>"
					 + "<input name='paymentID' type='hidden' " 
					 + " value='" + paymentID + "'>" + "</form></td></tr>"; 
					 } 

				con.close();
				
				// Complete the html table
				output += "</table>";
				
			}catch (Exception e){
				output = "Error while reading the items.";
				System.err.println(e.getMessage());	
			}
				
			return output;
			
		}
	
}
