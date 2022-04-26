package recources;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import art_Gallery.DatabaseUtilizer;
import model.Billing_Entity;
import util.DBConnection;

public class Billing_Resource {
	
	//DBConnection object to connect to database
	static DBConnection dbConnect = new DBConnection();
	String dbErrorMessage = "Database Connection failed!!";
	
	
	
	// 	RETRIEVE
	
	public String viewAllBills(){
		String output = "";
		
		try{
			Connection con = dbConnect.connectRoot();
			if (con == null){
				return "Error while connecting to the database for reading."; 	
			}
			
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Bill ID</th><th>Consumption ID</th><th>User Name</th>" +
			"<th>NIC</th>" +
			"<th>Address</th>" +
			"<th>Month </th> <th>Monthly Units</th> <th>Rate per Unit</th> <th>Bill Amount</th> </tr>";
			
			String query = "select * from billing";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()){
				String bill_ID = Integer.toString(rs.getInt("bill_ID"));
				String power_consumption_ID = rs.getString("power_consumption_ID");
				String User_Name = rs.getString("User_Name");
				String NIC = rs.getString("NIC");
				String address = rs.getString("address");
				String month = rs.getString("month");
				String monthly_units = Integer.toString(rs.getInt("monthly_units"));
				String rate = Integer.toString(rs.getInt("rate"));
				String amount = Double.toString(rs.getDouble("amount"));
			
				// Add into the html table
				output += "<tr><td>" + bill_ID + "</td>";
				output += "<td>" + power_consumption_ID + "</td>";
				output += "<td>" + User_Name + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + monthly_units + "</td>";
				output += "<td>" + rate + "</td>";
				output += "<td>" + amount + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" 
				+ "<td><form method='post' action='items.jsp'>" 
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='itemID' type='hidden' value='" + bill_ID + "'>" + "</form></td></tr>";
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		}catch (Exception e){
			output = "Error while reading the Bill Records.";
			System.err.println(e.getMessage());	
		}
			
		return output;
		
	}
	
	
	// Retrieve SINGLE USER BILLS
	
	public String viewBill(String userID)
	{
		String output = "";
		try
		{
			Connection con = dbConnect.connectRoot();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
						
			// Displaying the read concepts
			output = "<table border='1'><tr><th>Bill ID</th><th>Consumption ID</th><th>User Name</th>" +
					"<th>NIC</th>" +
					"<th>Address</th>" +
					"<th>Month </th> <th>Monthly Units</th> <th>Rate per Unit</th> <th>Bill Amount</th> </tr>";
			
			
			// Retrieving a single bill for a user
			String query = "select bill_ID, power_consumption_ID, User_Name, NIC, address, b.month, monthly_units, rate, amount  from billing b, consumption c WHERE b.power_consumption_ID = c.conID AND c.userID ='"+userID+"' ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String bill_ID = Integer.toString(rs.getInt("bill_ID"));
				String power_consumption_ID = rs.getString("power_consumption_ID");
				String User_Name = rs.getString("User_Name");
				String NIC = rs.getString("NIC");
				String address = rs.getString("address");
				String month = rs.getString("month");
				String monthly_units = Integer.toString(rs.getInt("monthly_units"));
				String rate = Integer.toString(rs.getInt("rate"));
				String amount = Double.toString(rs.getDouble("amount"));
			
				count++;			
				
				// Add into the html table
				output += "<tr><td>" + bill_ID + "</td>";
				output += "<td>" + power_consumption_ID + "</td>";
				output += "<td>" + User_Name + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + monthly_units + "</td>";
				output += "<td>" + rate + "</td>";
				output += "<td>" + amount + "</td>";
				
				// button for updating a consumption
				output += "<td><form method='post' action=''>"
				+ "<input name='btnUpdate' "
				+ " type='submit' value='Update' class='btn btn-secondary'>"
				+ "<input name='bill_ID' type='hidden' "
				+ " value=' " + bill_ID + "'>"
				+ "</form></td></tr>";
				
				}
				con.close();
				
				// Completion of the HTML table
				output += "</table>";
				
				// if user is not in database
				if (count == 0)
				{
					return "User does NOT exist.";
				}
				
			}
			catch (Exception e)
			{
				output = "Error while retrieving the user bill.";
				System.out.println(e.getMessage());
			}
			return output;
	}

	
	// INSERT
	
	public String insertBill(String power_consumption_ID, String User_Name, String NIC, String address, String month, String monthly_units, String rate){
		
		String output = "";
	
		try{

			Connection con = dbConnect.connectRoot();
			
			if (con == null){
				return "Error while connecting to the database for inserting."; 	
			}
			
			
			// create a prepared statement
			String query = " insert into billing(bill_ID, power_consumption_ID, User_Name, NIC, address, month, monthly_units, rate, amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(power_consumption_ID));
			preparedStmt.setString(3, User_Name);
			preparedStmt.setString(4, NIC);
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, month);
			
			//preparedStmt.setDouble(7, Double.parseDouble(amount));
			preparedStmt.setInt(7, Integer.parseInt(monthly_units));
			preparedStmt.setInt(8, Integer.parseInt(rate));
			
			double  tot_amount;
			tot_amount = (Integer.valueOf(monthly_units) * Integer.valueOf(rate));
			preparedStmt.setDouble(9, tot_amount);
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "New Bill Record inserted successfully";
			
		}catch (Exception e){
			output = "Error while inserting the New Bill Record.";
			System.err.println(e.getMessage());
		}
			
		return output;
		
	}
	
	// UPDATE
	
	public String updateBill(String bill_ID, String rate) {
		
		//boolean rs = false;
		String output = "";
		
		try{
			
			Connection con = dbConnect.connectRoot();
			
			if (con == null){
				return "Error while connecting to the database for updating."; 	
			}
		
			// create a prepared statement
			String query = "UPDATE electrogrid.billing SET rate=?, amount=? WHERE bill_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			/*
			String query2 = "select monthly_units  from billing WHERE bill_ID ='"+bill_ID+"' ";
			Statement stmt = con.createStatement();
			ResultSet rs2 = stmt.executeQuery(query2);
			String monthly_units = Integer.toString(rs2.getInt("monthly_units"));
			
			double  tot_amount;
			tot_amount = (Integer.valueOf(monthly_units) * Integer.valueOf(rate));
			preparedStmt.setDouble(2, tot_amount);
		
			preparedStmt.setInt(3, Integer.parseInt(bill_ID));
			
			*/
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(rate));			
			preparedStmt.setInt(2, Integer.parseInt(bill_ID));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Bill Record updated successfully";
		
		
		}catch (Exception e){
			output = "Error while updating the Bill Record.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
		
	
	// DELETE
	
	public String deleteBill(String bill_ID){
		
		String output = "";
		
		try{
			
			Connection con = dbConnect.connectRoot();
			
			if (con == null){
				return "Error while connecting to the database for deleting."; 	
			}
			
			
			// create a prepared statement
			String query = "DELETE FROM billing WHERE bill_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(bill_ID));
			
			// execute the statement
			preparedStmt.execute();

			con.close();
			output = "Bill Record deleted successfully";
			
		}catch (Exception e){
			
			output = "Error while deleting the Bill Record.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	

	

}// End of CLass



