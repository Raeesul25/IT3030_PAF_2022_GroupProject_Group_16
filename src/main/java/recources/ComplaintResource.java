package recources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBConnection;

public class ComplaintResource {
	
	//DBConnection object to connect to database
	DBConnection dbConnect = new DBConnection();
	String dbErrorMessage = "Database Connection failed!!";
	


	
	public String insertComplaint(int compID, String userID, String des, String answer, String updatedDate, String compDate, String cstatus ){
		String output = "";
	
		try{

			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for inserting."; 	
			}
			// create a prepared statement
			String query = " insert into complaint('compID', 'userID', 'des', 'answer', 'updatedDate', 'compDate', 'cstatus') VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userID);
			preparedStmt.setString(3, des);
			preparedStmt.setString(4, answer);
			preparedStmt.setString(5, updatedDate);
			preparedStmt.setString(6, compDate);
			preparedStmt.setString(7, cstatus);
			
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
	
	
	public String readComplaint(){
		String output = "";
		
		try{
			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for reading."; 	
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Complaint ID</th><th>User ID</th>" +
			"<th>Description</th>" +
			"<th>Answer</th>" +
			"<th>Updated Date</th><th>Complained Date</th><tr>Status</tr></tr>"+
			"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from complain";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()){
				String compID = Integer.toString(rs.getInt("compID"));
				String userID = rs.getString("userID");
				String des = rs.getString("des");
				String answer = rs.getString("answer");
				String updatedDate = rs.getString("updatedDate");
				String compDate = rs.getString("compDate");
				String cstatus = rs.getString("cstatus");
			
				// Add into the html table
				output += "<tr><td>" + compID + "</td>";
				output += "<td>" + userID + "</td>";
				output += "<td>" + des + "</td>";
				output += "<td>" + answer + "</td>";
				output += "<td>" + updatedDate + "</td>";
				output += "<td>" + compDate + "</td>";
				output += "<td>" + cstatus + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" 
				+ "<td><form method='post' action='items.jsp'>" 
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='itemID' type='hidden' value='" + compID + "'>" + "</form></td></tr>";
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
	
	public String updateComplaint(int compID, String userID, String des, String answer, String updatedDate, String compDate, String cstatus) {
		
		String output = "";
		try{
			
			Connection con = dbConnect.connect();
			if (con == null){
				return "Error while connecting to the database for updating."; 	
			}
		
			// create a prepared statement
			String query = "UPDATE complaint SET  userID=?, des=?, answer=?, updatedDate=?, compDate=?, cstatus=?, WHERE compID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			preparedStmt.setString(1, userID);
			preparedStmt.setString(2, des);
			preparedStmt.setString(3, answer);
			preparedStmt.setString(4, updatedDate);
			preparedStmt.setString(5, compDate);
			preparedStmt.setString(6, cstatus);
			preparedStmt.setInt(7, compID);
		
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
	
	public String deleteComplaint(int conID){
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
	
	public String readAllComplaint()
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
			output = "<table border='1'><tr><th>Complaint ID</th><th>User ID</th>" +
			"<th>Description</th>" +
			"<th>Answer</th>" +
			"<th>Updated Date</th><th>Complained Date</th><tr>Status</tr></tr>"+
			"<th>Update</th><th>Remove</th></tr>";
			
			// retrieving all the concept details
			String query = "select * from complaint";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// Iterate through the rows in the result set
			while (rs.next())
			{
				String compID = Integer.toString(rs.getInt("compID"));
				String userID = rs.getString("userID");
				String des = rs.getString("des");
				String answer = rs.getString("answer");
				String updatedDate = rs.getString("updatedDate");
				String compDate = rs.getString("compDate");
				String cstatus = rs.getString("cstatus");
				
			    
				// Add a row into the HTML table
				output += "<tr><td>" + compID + "</td>";
				output += "<td>" + userID + "</td>";
				output += "<td>" + des + "</td>";
				output += "<td>" + answer + "</td>";
				output += "<td>" + updatedDate + "</td>";
				output += "<td>" + compDate + "</td>";
				output += "<td>" + cstatus + "</td>";
				
				// button for backing a concept
				output += "<td><form method='post' action=''>"
				+ "<input name='btnBacks' "
				+ " type='submit' value='Back the project' class='btn btn-secondary'>"
				+ "<input name='conceptID' type='hidden' "
				+ " value=' " + compID + "'>"
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
	
}


