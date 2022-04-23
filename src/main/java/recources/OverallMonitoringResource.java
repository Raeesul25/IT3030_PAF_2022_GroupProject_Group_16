package recources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBConnection;

public class OverallMonitoringResource {
	
	//DBConnection object to connect to database
	DBConnection dbConnect = new DBConnection();
	String dbErrorMessage = "Database Connection failed!!";
	
	//Insert
	public String insertOverallMonitoring(int monitoring_ID, int pay_ID, int power_consumption_ID, String month, int units, double balance, String comment){
		String output = "";
	
		try{

			Connection con = dbConnect.connectRoot();
			if (con == null){
				return "Error while connecting to the database for inserting."; 	
			}
			// create a prepared statement
			//String query = " insert into monitoring('monitoring_ID', 'pay_ID', 'power_consumption_ID', 'month', 'units', 'comment') VALUES (?, ?, ?, ?, ?, ?, ?)";
			String query = " insert into monitoring('monitoring_ID', 'pay_ID', 'power_consumption_ID', 'month', 'units', 'balance', 'comment') VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, pay_ID);
			preparedStmt.setInt(3, power_consumption_ID);
			preparedStmt.setString(4, month);
			preparedStmt.setInt(5, units);
			preparedStmt.setDouble(6, balance);
			preparedStmt.setString(7, comment);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			
		}catch (Exception e){
			output = "Error while inserting the Overall Monitoring.";
			System.err.println(e.getMessage());
		}
			
		return output;
		
	}
	
	//read
	public String readOverallMonitoring(){
		String output = "";
		
		try{
			Connection con = dbConnect.connectRoot();
			if (con == null){
				return "Error while connecting to the database for reading."; 	
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Monitoring ID</th><th>Payment ID</th>" +
			"<th>Power Consumption ID</th>" +
			"<th>month</th>" +
			"<th>units</th><th>balance</th><th>comment</th></tr>"+
			"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from monitoring";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()){
				String monitoring_ID = Integer.toString(rs.getInt("monitoring_ID"));
				String pay_ID = Integer.toString(rs.getInt("pay_ID"));
				String power_consumption_ID = Integer.toString(rs.getInt("power_consumption_ID"));
				String month = rs.getString("month");
				String units = Integer.toString(rs.getInt("units"));
				String balance = Double.toString(rs.getDouble("balance"));
				String comment = rs.getString("comment");
			
				// Add into the html table
				output += "<tr><td>" + monitoring_ID + "</td>";
				output += "<td>" + pay_ID + "</td>";
				output += "<td>" + power_consumption_ID + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + units + "</td>";
				output += "<td>" + balance + "</td>";
				output += "<td>" + comment + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" 
				+ "<td><form method='post' action='items.jsp'>" 
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='itemID' type='hidden' value='" + monitoring_ID + "'>" + "</form></td></tr>";
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		}catch (Exception e){
			output = "Error while reading the Overall Monitoring.";
			System.err.println(e.getMessage());	
		}
			
		return output;
		
	}
	
	//update
	public String updateMonitoring(int monitoring_ID, int pay_ID, int power_consumption_ID, String month, int units, double balance, String comment) {
		
		String output = "";
		try{
			
			Connection con = dbConnect.connectRoot();
			if (con == null){
				return "Error while connecting to the database for updating."; 	
			}
		
			// create a prepared statement
			String query = "UPDATE monitoring SET  pay_ID=?, power_consumption_ID=?, month=?, units=?, balance=?, comment=?,  WHERE monitoring_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			preparedStmt.setInt(1, pay_ID);
			preparedStmt.setInt(2, power_consumption_ID);
			preparedStmt.setString(3, month);
			preparedStmt.setInt(4, units);
			preparedStmt.setDouble(5, balance);
			preparedStmt.setString(6, comment); ///// why
			preparedStmt.setInt(7, monitoring_ID);
		
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
	
	//Delete
	public String deleteMonitoring(String monitoring_ID){
		String output = "";
		try{
			
			Connection con = dbConnect.connectRoot();
			if (con == null){
				return "Error while connecting to the database for deleting."; 	
			}
			// create a prepared statement
			String query = "DELETE FROM monitoring  WHERE monitoring_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, monitoring_ID);
			
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
	
	//read-given date report
	public String readSpecificMonitoring(String givenDate)
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
			output = "<table border='1'><tr><th>Monitoring ID</th>"
			+"<th>Payment ID</th>"
			+ "<th>month</th>"
			+ "<th>units</th>"
			+ "<th>balance</th>"
			+ "<th>comment</th>"
			+ "<th>Update</th></tr>";
			
			// Retrieving the concepts launched by a particular researcher
			//int monitoring_ID, int pay_ID, int power_consumption_ID, String month, int units, double balance, String comment
			String query = "select monitoring_ID, pay_ID, power_consumption_ID, month, units, balance, comment from consumption where userID = '"+givenDate+"' ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String monitoring_ID = rs.getString("monitoring_ID");
				String pay_ID = Integer.toString(rs.getInt("pay_ID"));
				String power_consumption_ID = Integer.toString(rs.getInt("power_consumption_ID"));
				String month = rs.getString("month");
				String units = Integer.toString(rs.getInt("units"));
				String balance = Double.toString(rs.getDouble("balance"));
				String comment = rs.getString("comment");
				
				
				// Add a row into the HTML table
				output += "<tr><td>" + monitoring_ID + "</td>";
				output += "<td>" + pay_ID + "</td>";
				output += "<td>" + power_consumption_ID + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + units + "</td>";
				output += "<td>" + balance + "</td>";
				output += "<td>" + comment + "</td>";
				
				// button for updating a consumption
				output += "<td><form method='post' action=''>"
				+ "<input name='btnUpdate' "
				+ " type='submit' value='Update' class='btn btn-secondary'>"
				+ "<input name='conID' type='hidden' "
				+ " value=' " + monitoring_ID + "'>"
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

}
