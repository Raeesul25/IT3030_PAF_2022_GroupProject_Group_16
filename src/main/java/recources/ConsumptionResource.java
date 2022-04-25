package recources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBConnection;

public class ConsumptionResource {
	
	//DBConnection object to connect to database
	DBConnection dbConnect = new DBConnection();
	
	
	// ---Method to insert the consumption details---
	public String insertConsumption(String userID, String month, String premonreading, String curmonreading){
		String output = "";
	
		try{

			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for inserting."; 	
			}
			// create a prepared statement
			String query = " insert into consumption(conID,userID,month,preMonReading,curMonReading,conUnits) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 1);
			preparedStmt.setString(2, userID);
			preparedStmt.setString(3, month);
			preparedStmt.setInt(4, Integer.parseInt(premonreading));
			preparedStmt.setInt(5, Integer.parseInt(curmonreading));
			
			int conunits;
			conunits = (Integer.valueOf(curmonreading) - Integer.valueOf(premonreading));
			preparedStmt.setInt(6, conunits);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Consumption Inserted successfully";
			
		}catch (Exception e){
			output = "Error while inserting the consumption.";
			System.err.println(e.getMessage());
		}
			
		return output;
		
	}
	
	// ---Method to update a consumption details---
	public String updateConsumption(String conId, String userID, String month, String premonreading, String curmonreading) {
		
		String output = "";
		try{
			
			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for updating."; 	
			}
		
			// create a prepared statement
			String query = "UPDATE consumption SET userID=?, month=?, preMonReading=?, curMonReading=?, conUnits=? WHERE conID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			preparedStmt.setString(1, userID);
			preparedStmt.setString(2, month);
			preparedStmt.setInt(3, Integer.parseInt(premonreading));
			preparedStmt.setInt(4, Integer.parseInt(curmonreading));
			
			int conunits;
			conunits = (Integer.valueOf(curmonreading) - Integer.valueOf(premonreading));
			preparedStmt.setInt(5, conunits);
			preparedStmt.setInt(6, Integer.parseInt(conId));
		
			// execute the statement
			preparedStmt.execute();
		
			con.close();
			output = "Consumption Details Updated successfully";
		
		}catch (Exception e){
			output = "Error while updating the consumption.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	// ---Method to delete a consumption detail---
	public String deleteConsumption(String conID){
		String output = "";
		try{
			
			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for deleting."; 	
			}
			// create a prepared statement
			String query = "DELETE FROM consumption WHERE conID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(conID));
			
			// execute the statement
			preparedStmt.execute();

			con.close();
			output = "Consumption Deleted successfully";
		}catch (Exception e){
			output = "Error while deleting the Consumption.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	// ---Method to read all Consumption---
	public String readConsumption(){
		String output = "";
		try{
			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for reading."; 	
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Consumption ID</th><th>User ID</th>" +
			"<th>Month</th>" +
			"<th>Previous Month Reading</th>" +
			"<th>Current Month Reading</th>" +
			"<th>Consumption units</th>" +
			"<th>Update</th><th>Delete</th></tr>";
			String query = "select * from consumption";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()){
				String conID = Integer.toString(rs.getInt("conID"));
				String userID = rs.getString("userID");
				String month = rs.getString("month");
				String premonread = Integer.toString(rs.getInt("preMonReading"));
				String curmonread = Integer.toString(rs.getInt("curMonReading"));
				String conunits = Integer.toString(rs.getInt("conUnits"));
			
				// Add into the html table
				output += "<tr><td>" + conID + "</td>";
				output += "<td>" + userID + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + premonread + "</td>";
				output += "<td>" + curmonread + "</td>";
				output += "<td>" + conunits + "</td>";
			
				// button for updating a consumption
				output += "<td><form method='post' action=''>"
				+ "<input name='btnUpdate' "
				+ " type='submit' value='Update' class='btn btn-secondary'>"
				+ "<input name='conID' type='hidden' "
				+ " value=' " + conID + "'>"
				+ "</form></td>";
				
				// button for deleting a consumption
				output += "<td><form method='post' action=''>"
						+ "<input name='btnDelete' "
						+ " type='submit' value='Delete' class='btn btn-secondary'>"
						+ "<input name='conID' type='hidden' "
						+ " value=' " + conID + "'>"
						+ "</form></td>";

			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		}catch (Exception e){
			output = "Error while reading the consumptions.";
			System.err.println(e.getMessage());	
		}
			
		return output;
			
	}
	
	// -- Method to read specific user's consumption---
	public String readSpecificUserConsumption(String userID)
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
			output = "<table border='1'><tr><th>Consumption ID</th>"
			+"<th>Month</th>"
			+ "<th>Previous Month Reading</th>"
			+ "<th>Current Month Reading</th>"
			+ "<th>Consumption Units</th>"
			+ "<th>Update</th></tr>";
			
			// Retrieving the concepts launched by a particular researcher
			String query = "select conID, month, preMonReading, curMonReading, conUnits from consumption where userID = '"+userID+"' ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String conID = Integer.toString(rs.getInt("conID"));
				String month = rs.getString("month");
				String premonread = Integer.toString(rs.getInt("preMonReading"));
				String curmonread = Integer.toString(rs.getInt("curMonReading"));
				String conunits = Integer.toString(rs.getInt("conUnits"));
				
				
				// Add a row into the HTML table
				output += "<tr><td>" + conID + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + premonread + "</td>";
				output += "<td>" + curmonread + "</td>";
				output += "<td>" + conunits + "</td>";
				
				// button for updating a consumption
				output += "<td><form method='post' action=''>"
				+ "<input name='btnUpdate' "
				+ " type='submit' value='Update' class='btn btn-secondary'>"
				+ "<input name='conID' type='hidden' "
				+ " value=' " + conID + "'>"
				+ "</form></td></tr>";
				
				}
				con.close();
				
				// Completion of the HTML table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while retrieving user consumption details!!";
				System.out.println(e.getMessage());
			}
			return output;
	}


	// -- Method to read specific month's consumption---
	public String SpecificMonthConsumption(String month)
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
			output = "<table border='1'><tr><th>Consumption ID</th>"
			+"<th>User ID</th>"
			+ "<th>Previous Month Reading</th>"
			+ "<th>Current Month Reading</th>"
			+ "<th>Consumption Units</th>"
			+ "<th>Update</th></tr>";
			
			// Retrieving the concepts launched by a particular researcher
			String query = "select conID, userID, preMonReading, curMonReading, conUnits from consumption where month = '"+month+"' ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String conID = Integer.toString(rs.getInt("conID"));
				String userID = rs.getString("month");
				String premonread = Integer.toString(rs.getInt("preMonReading"));
				String curmonread = Integer.toString(rs.getInt("curMonReading"));
				String conunits = Integer.toString(rs.getInt("conUnits"));
				
				
				// Add a row into the HTML table
				output += "<tr><td>" + conID + "</td>";
				output += "<td>" + userID + "</td>";
				output += "<td>" + premonread + "</td>";
				output += "<td>" + curmonread + "</td>";
				output += "<td>" + conunits + "</td>";
				
				// button for updating a consumption
				output += "<td><form method='post' action=''>"
				+ "<input name='btnUpdate' "
				+ " type='submit' value='Update' class='btn btn-secondary'>"
				+ "<input name='conID' type='hidden' "
				+ " value=' " + conID + "'>"
				+ "</form></td></tr>";
				
				}
				con.close();
				
				// Completion of the HTML table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while retrieving user consumption details!!";
				System.out.println(e.getMessage());
			}
			return output;
	}

	/**************************** METHODS REQUIRED AS A SERVER FOR INTER SERVICE COMMUNICATION ************************/
	
	//Method to get Consumption ID when giving the month
	public String getconID(String month) {

		String output = "";
		try{
	
			Connection con = dbConnect.connect();
		
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
		
			// Retrieving the concepts launched by a particular researcher
			String query = "SELECT conID "
			+ "FROM consumption "
			+ "WHERE month = '"+month+"' ";
		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		
			String conID = null;
		
			// iterate through the rows in the result set
			while (rs.next()){
			conID = rs.getString("conID");
			}
		
			con.close();
		
			output += conID;
	
		}
		catch (Exception e){
			output = "Error while retrieving consumption ID!!";
			System.err.println(e.getMessage());
	}
		return output;
	}
	    
}
