package recources;

import java.sql.*;
import java.util.ArrayList;

import model.Billing_Entity;
import model.Payment;
import util.DBConnection;

public class PaymentResources {

	//DBConnection object to connect to database
		DBConnection dbConnect = new DBConnection();
		String dbErrorMessage = "Database Connection failed!!";
	
		
		//Insert new payment details to the table
		public String insertPayment(String userID,String billID,  String paid_amount,String payment_type,String card_no){
			
			String output = "";		
			int bill_ID = Integer.parseInt(billID);
			try{

				Connection con = dbConnect.connect();
				if (con == null){
					return "Error while connecting to the database for inserting."; 	
				}
				
				//get month and amount of bill
				String q1 = "SELECT * from billing where bill_ID = '"+ bill_ID +"'";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(q1);
				rs.next();
				
				String month =rs.getString(6);
				String total_amount =rs.getString(9);

				System.out.println(month+" "+total_amount);
				
				// create a prepared statement
				String query = " insert into payment(paymentID,userID,billID,total_amount,paid_amount,balance,month,payment_type,card_no,paid_Date) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?)";						
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values for payment table
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, userID);
				preparedStmt.setInt(3, Integer.parseInt(billID));
				preparedStmt.setDouble(4, Double.parseDouble(total_amount));
				preparedStmt.setDouble(5, Double.parseDouble(paid_amount));
				
				//get balance (if user paid less than the total amount, balance will be a negative value) 
				double balance =  Double.parseDouble(paid_amount) - Double.parseDouble(total_amount);
				preparedStmt.setDouble(6, balance);
				preparedStmt.setString(7, month);
				preparedStmt.setString(8, payment_type);
				preparedStmt.setString(9, card_no);
				
				//get current date
				long currentDate=System.currentTimeMillis();  
				java.sql.Date paid_Date=new java.sql.Date(currentDate);
				preparedStmt.setString(10, paid_Date.toString());
				
				
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
		public String updatePayment(String payID,String billID, String payment_type,String card_no,String new_paid) {
			
			String output = "";
			
			return output;
		}
		
		//Delete payment by payment id
		public String deletepayment(String paymentID){
			String output = "";
			try{
				
				Connection con = dbConnect.connect();
				if (con == null){
					return "Error while connecting to the database for deleting."; 	
				}
				// create a prepared statement
				String query = "DELETE FROM payment WHERE paymentID='"+paymentID+"'";
				Statement preparedStmt = con.createStatement();
				
				// execute the statement
				preparedStmt.execute(query);

				con.close();
				output = "Payment record Deleted successfully";
			}catch (Exception e){
				output = "Error while deleting the payment record.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		

	
		
		//get all payment details based on the user id
		public String readPayment(){
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
				"<th>Payment Type</th>" +
				"<th>Card no.</th>" +
				"<th>Paid Date</th>" +
				"<th>Update</th><th>Delete</th></tr>";
				
				String query = "select * from payment ";//WHERE userID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				//preparedStmt.setString(2,u_ID);
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
					String payment_type =rs.getString("payment_type");
					String card_no =rs.getString("card_no");
					String paid_Date =rs.getString("paid_Date");
				
					// Add into the html table
					output += "<tr><td>" + paymentID + "</td>";
					output += "<td>" + userID + "</td>";
					output += "<td>" + billID + "</td>";
					output += "<td>" + total_amount + "</td>";
					output += "<td>" + paid_amount + "</td>";
					output += "<td>" + balance + "</td>";
					output += "<td>" + month + "</td>";
					output += "<td>" + payment_type + "</td>";
					output += "<td>" + card_no + "</td>";
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
		
		//read one specific user's payment details
		public String readOneUserPayment(String user_ID)
		{
			String output = "";
			try
			{
				Connection con = dbConnect.connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				
				// Displaying the read concepts
				output = "<table border='1'><tr><th>Payment ID</th>" +
						"<th>Bill ID</th>" +
						"<th>Toatl Amount</th>" +
						"<th>Paid Amount</th>" +
						"<th>Balance</th>" +
						"<th>Month</th>" +
						"<th>Payment Type</th>" +
						"<th>Card no.</th>" +
						"<th>Paid Date</th>" +
						"<th>Update</th><th>Delete</th></tr>";
				
				// Retrieving the concepts launched by a particular researcher
				String query ="SELECT * from payment where userID = '"+ user_ID+"'";
				Statement createStmt = con.createStatement();
				
				ResultSet rs = createStmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String payment_ID = Integer.toString(rs.getInt("paymentID"));
					String userID = rs.getString("userID");
					String billID = rs.getString("billID");
					String total_amount = Double.toString(rs.getDouble("total_amount"));
					String paid_amount = Double.toString(rs.getDouble("paid_amount"));
					String balance = Double.toString(rs.getDouble("balance"));
					String month =rs.getString("month");
					String payment_type =rs.getString("payment_type");
					String card_no =rs.getString("card_no");
					String paid_Date =rs.getString("paid_Date");
				
					// Add into the html table
					output += "<tr><td>" + payment_ID + "</td>";
					output += "<td>" + billID + "</td>";
					output += "<td>" + total_amount + "</td>";
					output += "<td>" + paid_amount + "</td>";
					output += "<td>" + balance + "</td>";
					output += "<td>" + month + "</td>";
					output += "<td>" + payment_type + "</td>";
					output += "<td>" + card_no + "</td>";
					output += "<td>" + paid_Date + "</td>";
				
					// button for backing a concept
					 output += "<td><input name='btnUpdate' " 
					 + " type='button' value='Update'></td>"
					 + "<td><form method='post' action=''>"
					 + "<input name='btnRemove' " 
					 + " type='submit' value='Delete'>"
					 + "<input name='userID' type='hidden' " 
					 + " value='" + userID + "'>" + "</form></td></tr>"; 
					 } 

				con.close();
				
				// Complete the html table
				output += "</table>";
				}
				catch (Exception e)
				{
					output ="Error while retrieving payment details!!";
					System.out.println(e.getMessage());
				}
				return output;
		}
		
		
	
}
