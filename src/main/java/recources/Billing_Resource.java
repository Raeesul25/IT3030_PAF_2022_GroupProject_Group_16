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
	/*
	public static List<Billing_Entity> viewBill(){
		
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection con;
		ArrayList<Billing_Entity> bill = new ArrayList<>();
		
		try {
			
			con = dbConnect.connect();
			pst = con.prepareStatement("SELECT * FROM electrogrid.billing");
			//b WHERE b.bill_ID = ?
			//pst.setString(1, UIDConverted);
			resultSet = pst.executeQuery();
			
			while(resultSet.next()) {
    			
    			int bill_ID = resultSet.getInt(1);
    			int power_consumption_ID = resultSet.getInt(2);
    			String User_Name = resultSet.getString(3);
    			String NIC = resultSet.getString(4);
    			
    			String address = resultSet.getString(5);
    			String month = resultSet.getString(6);
    			double amount = resultSet.getDouble(7);
    			int monthly_units = resultSet.getInt(8);
    			int rate = resultSet.getInt(9);
    			
    			Billing_Entity objectBill = new Billing_Entity(bill_ID, power_consumption_ID, User_Name, NIC, address, month, amount, monthly_units, rate); 
    			//Billing_Entity objectBill = new Billing_Entity();
    			bill.add(objectBill);
    			
    		} // end of While
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return bill;
	} // end of RETRIEVE
	*/
	
	
	public String viewBill(){
		String output = "";
		
		try{
			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for reading."; 	
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Bill ID</th><th>Consumption ID</th><th>User Name</th>" +
			"<th>NIC</th>" +
			"<th>Address</th>" +
			"<th>Month </th> <th>Bill Amount</th> <th>Monthly Units</th> <th>Rate per Unit</th></tr>"+
			"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from consumption";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()){
				String conID = Integer.toString(rs.getInt("conID"));
				String userID = rs.getString("userID");
				String month = rs.getString("month");
				String prevMonRead = Integer.toString(rs.getInt("preMonRead"));
				String curMonRead = Integer.toString(rs.getInt("curMonRead"));
				String conUnits = Integer.toString(rs.getInt("conUnits"));
			
				// Add into the html table
				output += "<tr><td>" + conID + "</td>";
				output += "<td>" + userID + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + prevMonRead + "</td>";
				output += "<td>" + curMonRead + "</td>";
				output += "<td>" + conUnits + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" 
				+ "<td><form method='post' action='items.jsp'>" 
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='itemID' type='hidden' value='" + conID + "'>" + "</form></td></tr>";
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
	
	
	
	
	// INSERT
	
	public String insertBill(String power_consumption_ID, String User_Name, String NIC, String address, String month, String amount, String monthly_units, String rate){
		
		String output = "";
	
		try{

			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for inserting."; 	
			}
			// create a prepared statement
			String query = " insert into electrogrid.billing(bill_ID, power_consumption_ID, User_Name, NIC, address, month, amount, monthly_units, rate) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(power_consumption_ID));
			preparedStmt.setString(3, User_Name);
			preparedStmt.setString(4, NIC);
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, month);
			//preparedStmt.setDouble(7, Double.parseDouble(amount));
			preparedStmt.setInt(8, Integer.parseInt(monthly_units));
			preparedStmt.setInt(9, Integer.parseInt(rate));
			
			double  tot_amount;
			tot_amount = (Integer.valueOf(monthly_units) * Integer.valueOf(rate));
			preparedStmt.setDouble(7, tot_amount);
			
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
	
	public String updateConsumption(String conId, String userID, String month, String premonreading, String curmonreading, String conunits) {
		
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
			preparedStmt.setInt(5, Integer.parseInt(conunits));
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
	
	
	

}// End of CLass



