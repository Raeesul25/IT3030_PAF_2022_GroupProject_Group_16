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
	
	
	/**********************
	
	public String viewBill()
	{
		String output = "";
		try
		{
			Connection con = dbConnect.connect();
			if (con == null)
			{
				return dbErrorMessage;
			}
			
			// Displaying the read concepts
			output = "<table border='1'><tr><th>Consumption ID</th>"
			+"<th>User ID</th><th>Month</th><th>Previous Month Reading</th>"
			+ "<th>Current Month Reading</th><th>Consumption units</th></tr>";
			
			// retrieving all the concept details
			String query = "select * from consumption ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// Iterate through the rows in the result set
			while (rs.next())
			{
				String conID = Integer.toString(rs.getInt("conID"));
				String userID = rs.getString("userID");
				String month = rs.getString("month");
				String premonread = Integer.toString(rs.getInt("premonread"));
				String curmonread = rs.getString("curmonread");
				String conunits = rs.getString("conunits");
				
			    
				// Add a row into the HTML table
				output += "<tr><td>" + conID + "</td>";
				output += "<td>" + userID + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + premonread + "</td>";
				output += "<td>" + curmonread + "</td>";
				output += "<td>" + conunits + "</td>";
				
				// button for backing a concept
				output += "<td><form method='post' action=''>"
				+ "<input name='btnBacks' "
				+ " type='submit' value='Back the project' class='btn btn-secondary'>"
				+ "<input name='conceptID' type='hidden' "
				+ " value=' " + conID + "'>"
				+ "</form></td></tr>";
				
				}
				con.close();
				
				// Completion of the HTML table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while retrieving the consumptions!";
				System.err.println(e.getMessage());
			}
			return output;
	}
	
	***********/
	
	// 	RETRIEVE
	
	public static List<Billing_Entity> viewBills(){
		
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection con;
		ArrayList<Billing_Entity> bill = new ArrayList<>();
		
		try {
			
			con = dbConnect.connect();
			pst = con.prepareStatement("SELECT * FROM electrogrid.billing b WHERE b.bill_ID = ?");
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
	
	
	
	
	

}// End of CLass



