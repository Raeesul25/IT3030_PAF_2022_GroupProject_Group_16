package service;

import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import recources.ConsumptionResource;

//start of service
@Path("/Consumptions")
public class ConsumptionService {
	
	//Consumption API type object
	ConsumptionResource consumptionobj = new ConsumptionResource();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readConsumption(){
		return consumptionobj.readConsumption();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(@FormParam("conID") int conID,@FormParam("userID") String userID,
			@FormParam("month") String month,@FormParam("premonread") int premonread,
			@FormParam("curmonread") int curmonread, @FormParam("conunits") int conunits){
	
		String output = consumptionobj.insertConsumption(conID, userID, month, premonread, 
				curmonread, conunits);
	
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConsumption(String consumptionData){
		
		//Convert the input string to a JSON object
		JsonObject consumptionobject = new JsonParser().parse(consumptionData).getAsJsonObject();
	
		//Read the values from the JSON object
		int conID = consumptionobject.get("conID").getAsInt();
		String userID = consumptionobject.get("userID").getAsString();
		String month = consumptionobject.get("month").getAsString();
		int premonread = consumptionobject.get("premonread").getAsInt();
		int curmonread = consumptionobject.get("curmonread").getAsInt();
		int conunits = consumptionobject.get("conunits").getAsInt();
		
		String output = consumptionobj.updateConsumption(conID, userID, month, premonread, curmonread,
				conunits);
	
		return output;
	}
	
}