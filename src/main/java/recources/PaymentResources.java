package recources;

import java.sql.*;

import util.DBConnection;


public class PaymentResources {

		//DBConnection object to connect to database
		DBConnection dbConnect = new DBConnection();
		String dbErrorMessage = "Database Connection failed!!";
	
		
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
						
						//query to get all the payment details
						String query = "select * from payment ";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						// binding values

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
		
		
		
		//Insert new payment details to the table
		public String insertPayment(String userID,String billID,  String paid_amount,String payment_type,String card_no){
			
			String output = "";		
			int bill_ID = Integer.parseInt(billID);
			
			//check whether the input fields are empty or not
			if(userID.isEmpty() && billID.isEmpty() && paid_amount.isEmpty()  && payment_type.isEmpty() && card_no.isEmpty())
				return "Fields cannot be empty";
			
			else {
					try{
		
						Connection con = dbConnect.connect();
						if (con == null){
							return "Error while connecting to the database for inserting."; 	
						}
						
						// create a prepared statement
						String query = " insert into payment(paymentID,userID,billID,total_amount,paid_amount,balance,month,payment_type,card_no,paid_Date) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?)";						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						
						//get month and amount of bill
						String q1 = "SELECT * from billing where bill_ID = '"+ bill_ID +"'";
						Statement stmt2 = con.createStatement();
						ResultSet rs1 = stmt2.executeQuery(q1);
						rs1.next();
						
						String month =rs1.getString(6);
						String total_amount =rs1.getString(9);
		
						System.out.println(month+" "+total_amount);
						
						
						// binding values for payment table
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, userID);
						preparedStmt.setInt(3, bill_ID);
						preparedStmt.setDouble(4, Double.parseDouble(total_amount));
						
						
						//get month and amount of bill
						String q2 = "SELECT paid_amount,balance from payment where billID = '"+ bill_ID +"'order by paymentID desc limit 1";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(q2);
						
						//check whether a payment record has happened before to the given bill id
						if(rs.next() == false) {
							System.out.println("-------------------------");
							// binding values for payment table
							preparedStmt.setDouble(5, Double.parseDouble(paid_amount));
							
							//get balance (if user paid less than the total amount, balance will be a negative value) 
							double balance =  Double.parseDouble(paid_amount) - Double.parseDouble(total_amount);
							preparedStmt.setDouble(6, balance);
							
							
							
						}
						else{//if the payment is doing for a new bill which has not done any payment
						double pamount = Double.parseDouble(rs.getString(1))+Double.parseDouble(paid_amount);
						double pbalance = pamount - Double.parseDouble(total_amount);
		
						preparedStmt.setDouble(5, pamount);
						preparedStmt.setDouble(6, pbalance);
						
						}
						
		
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
						output = "Payment Inserted successfully"; 
						//Successful message when inserting payment
						
						
					}catch (Exception e){
						output = "Error while inserting the Payment to the table.";
						System.err.println(e.getMessage());
					}
						
					return output;
			}
		}
		
		
		//Update payment details
		public String updatePayment(String payID,String billID,String month,String payment_type,String card_no,String total_amount,String new_paid,String new_balance,String paid_Date) {
			
			
			
			//check whether the input fields are empty or not
			if(payID.isEmpty() && billID.isEmpty() && month.isEmpty()  && payment_type.isEmpty() && card_no.isEmpty() && total_amount.isEmpty()&& new_paid.isEmpty()&& new_balance.isEmpty()&& paid_Date.isEmpty())
				return "Fields cannot be empty";
			
			
			else {
				String output = "";
				int paymentID = Integer.parseInt(payID);
				int bID = Integer.parseInt(billID);
				double paid_amount =Double.parseDouble(new_paid) ;
				double balance = Double.parseDouble(new_balance) ;
				double tot_amount = Double.parseDouble(total_amount) ;
				
				
				try{
					
					Connection con = dbConnect.connect();
					if (con == null){
						return "Error while connecting to the database for updating."; 	
					}
		
					// create a update statement
					String query ="Update payment set paid_amount='"+paid_amount+"',month='"+month+"',balance='"+balance+"',paid_Date='"+ paid_Date+"',payment_type='"+payment_type+"',total_amount='"+ tot_amount+"',billID='"+ bID 
							+"',card_no='"+ card_no+"'"+"where paymentID='"+paymentID+"'";
			
					//create statement
					Statement st = con.createStatement();
					st.executeUpdate(query);
				
					con.close();
					output = "Payment updated successfully";
				
				}catch (Exception e){
					output = "Error while updating the payment.";
					System.err.println(e.getMessage());
				}
				
				return output;
				
			}
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
					//Successful message when deleting payment row
				}catch (Exception e){
					output = "Error while deleting the payment record.";
					System.err.println(e.getMessage());
				}
				
				return output;
		}
		
		
}
