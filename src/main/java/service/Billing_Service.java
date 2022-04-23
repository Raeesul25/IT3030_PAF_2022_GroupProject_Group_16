package service;

import java.util.List;

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

import model.Billing_Entity;
import recources.Billing_Resource;


//start of service
@Path("/Billing")
public class Billing_Service {
	
	//Consumption API type object
	Billing_Resource objectBill = new Billing_Resource();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewBill(){
		
		return objectBill.viewBill();
	}

/*	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(@FormParam("userID") String userID, 
			@FormParam("month") String month,@FormParam("premonread") String premonread,
			@FormParam("curmonread") String curmonread){
	
		String output = consumptionobj.insertConsumption(userID, month, premonread, 
				curmonread);
	
		return output;
	}
	
	
	@PUT
	@Path("/update/{conID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConsumption(@PathParam ("conID") String conID, String consumptionData){
		
		//Convert the input string to a JSON object
		JsonObject consumptionobject = new JsonParser().parse(consumptionData).getAsJsonObject();
	
		//Read the values from the JSON object
//		String conID = consumptionobject.get("conID").getAsString();
		String userID = consumptionobject.get("userID").getAsString();
		String month = consumptionobject.get("month").getAsString();
		String premonread = consumptionobject.get("premonread").getAsString();
		String curmonread = consumptionobject.get("curmonread").getAsString();
		String conunits = consumptionobject.get("conunits").getAsString();
		
		String output = consumptionobj.updateConsumption(conID, userID, month, premonread, 
				curmonread, conunits);
	
		return output;
	}
	
	*/
	//   "/{name}"
	@DELETE
	@Path("{bill_ID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(@PathParam ("bill_ID") String bill_ID)
	{
		return objectBill.deleteBill(bill_ID);
	}
	
	
	
}
