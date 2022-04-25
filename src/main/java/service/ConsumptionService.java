package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import recources.ConsumptionResource;

//start of service
@Path("/Consumptions")
public class ConsumptionService {
	
	//Consumption API type object
	ConsumptionResource conAPI = new ConsumptionResource();
	
	/*** Viewing All Consumption details (HTTP Verb : GET) by an HTML table ***/
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllConsumption()
	{
		return conAPI.readConsumption();
	}
	
	/*** Insert Function(HTTP Verb : POST) by getting the input values through forms and produces a plain text as output ***/
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(@FormParam("userID") String userID, 
			@FormParam("month") String month,@FormParam("premonread") String premonread,
			@FormParam("curmonread") String curmonread){
	
		String output = conAPI.insertConsumption(userID, month, premonread, 
				curmonread);
	
		return output;
	}
	
	/*** Update Function(HTTP Verb : PUT) Using JSON and produces a plain text as output result***/
	@PUT
	@Path("/update/{conID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConsumption(@PathParam ("conID") String conID, String consumptionData){
		
		//Convert the input string to a JSON object
		JsonObject consumptionobject = new JsonParser().parse(consumptionData).getAsJsonObject();
	
		//Read the values from the JSON object
		String userID = consumptionobject.get("userID").getAsString();
		String month = consumptionobject.get("month").getAsString();
		String premonread = consumptionobject.get("premonread").getAsString();
		String curmonread = consumptionobject.get("curmonread").getAsString();
		
		String output = conAPI.updateConsumption(conID, userID, month, premonread, 
				curmonread);
	
		return output;
	}
	
	/*** DELETE FUNCTION (HTTP Verb : DELETE) using XML and produces plain text as output results ***/
	@DELETE
	@Path("/delete/{conID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteConceptDetails(@PathParam ("conID") String conID)
	{
		return conAPI.deleteConsumption(conID);
	}
	
	/*** Viewing Consumption details as a user(HTTP Verb : GET) by accepting researcher ID as input and produces an HTML Table ***/
	@GET
	@Path("/{userID}")
	@Produces(MediaType.TEXT_HTML)
	public String readSpecificUserConsumption(@PathParam("userID") String userID)
	{
		return conAPI.readSpecificUserConsumption(userID);
	}
	
	
	/************************************** INTER SERVICE COMMUNICATION ********************************************/
	//Method to get the user ID from user service
	
	/***************************** CONSUMPTION MONITORING SERVICE AS SERVER FOR INTER SERVICE COMMUNICATION **********************************/
	//Method to send consumption ID to billing  service 
    @GET
    @Path("/conID/")
    @Produces(MediaType.APPLICATION_XML)
    public String readConsumptionID(@QueryParam("month")String month) {

	    return conAPI.getconID(month);
    }
    
}
