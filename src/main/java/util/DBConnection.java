package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:8090/Consumption_Monitoring_Service/","root", "user");
					
			//Testing the connection
			System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
