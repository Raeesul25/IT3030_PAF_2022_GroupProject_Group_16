package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import recources.Billing_Resource;


//start of service
@Path("/Billing")
public class Billing_Service {
	
	//Consumption API type object
	Billing_Resource objectBill = new Billing_Resource();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewAllBills(){
		
		return objectBill.viewAllBills();
	}
	
	// View single bill
	
	@GET
	@Path("{userID}")
	@Produces(MediaType.TEXT_HTML)
	public String viewBill(@PathParam("userID") String userID)
	{
		return objectBill.viewBill(userID);
	}
	

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(@FormParam("power_consumption_ID") String power_consumption_ID, 
			@FormParam("User_Name") String User_Name,@FormParam("NIC") String NIC, @FormParam("address") String address, @FormParam("month") String month, @FormParam("monthly_units") String monthly_units, 
			@FormParam("rate") String rate){
	
		String output = objectBill.insertBill(power_consumption_ID, User_Name, NIC, address, month, monthly_units, rate);
	
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData){
		
		//Convert the input string to a JSON object
		JsonObject objectBillData = new JsonParser().parse(billData).getAsJsonObject();
	
		//Read the values from the JSON object

		String bill_ID = objectBillData.get("bill_ID").getAsString();
		String rate = objectBillData.get("rate").getAsString();
		
		String output = objectBill.updateBill(bill_ID, rate);
	
		return output;
	}
	
	
	//   "/{name}"
	@DELETE
	@Path("{bill_ID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(@PathParam ("bill_ID") String bill_ID)
	{
		return objectBill.deleteBill(bill_ID);
	}
	

	
}
