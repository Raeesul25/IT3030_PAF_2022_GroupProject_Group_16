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
    			int NIC = resultSet.getInt(4);
    			
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
	
	
	// INSERT
	
	public String insertBill(String power_consumption_ID, String User_Name, String NIC, String address, String month, String amount, String monthly_units, String rate){
		
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
			preparedStmt.setInt(1, 0);
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



