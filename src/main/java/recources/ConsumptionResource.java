package recources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBConnection;

public class ConsumptionResource {
	
	//DBConnection object to connect to database
	DBConnection dbConnect = new DBConnection();
	String dbErrorMessage = "Database Connection failed!!";
	
	public String insertConsumption(int conId, String userID, String month, int premonreading, int curmonreading, int conunits){
		String output = "";
	
		try{

			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for inserting."; 	
			}
			// create a prepared statement
			String query = " insert into consumption('conID', 'userID', 'month', 'preMonReading', 'curMonReading', 'conUnits') VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userID);
			preparedStmt.setString(3, month);
			preparedStmt.setInt(4, premonreading);
			preparedStmt.setInt(5, curmonreading);
			preparedStmt.setInt(6, conunits);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			
		}catch (Exception e){
			output = "Error while inserting the consumption.";
			System.err.println(e.getMessage());
		}
			
		return output;
		
	}
	
	public String updateConsumption(int conId, String userID, String month, int premonreading, int curmonreading, int conunits) {
		
		String output = "";
		try{
			
			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for updating."; 	
			}
		
			// create a prepared statement
			String query = "UPDATE consumption SET  userID=?, month=?, preMonReading=?, curMonReading=?, conUnits=?, WHERE conID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			preparedStmt.setString(1, userID);
			preparedStmt.setString(2, month);
			preparedStmt.setInt(3, premonreading);
			preparedStmt.setInt(4, curmonreading);
			preparedStmt.setInt(5, (conunits));
			preparedStmt.setInt(6, conId);
		
			// execute the statement
			preparedStmt.execute();
		
			con.close();
			output = "Updated successfully";
		
		}catch (Exception e){
			output = "Error while updating the consumption.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteConsumption(int conID){
		String output = "";
		try{
			
			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for deleting."; 	
			}
			// create a prepared statement
			String query = "DELETE FROM consumption  WHERE conID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, conID);
			
			// execute the statement
			preparedStmt.execute();

			con.close();
			output = "Deleted successfully";
		}catch (Exception e){
			output = "Error while deleting the Consumption.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
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
			"<th>Update</th><th>Remove</th></tr>";
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
			
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" 
				+ "<td><form method='post' action='items.jsp'>" 
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='conID' type='hidden' value='" + conID + "'>" + "</form></td></tr>";
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
