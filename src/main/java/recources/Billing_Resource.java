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



