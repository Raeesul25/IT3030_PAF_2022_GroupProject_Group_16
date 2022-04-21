package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

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
	public String readAllConsumption()
	{
		return consumptionobj.readConsumption();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(@FormParam("userID") String userID, 
			@FormParam("month") String month,@FormParam("premonread") int premonread,
			@FormParam("curmonread") int curmonread){
	
		String output = consumptionobj.insertConsumption(userID, month, premonread, 
				curmonread);
	
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
		String conID = consumptionobject.get("conID").getAsString();
		String userID = consumptionobject.get("userID").getAsString();
		String month = consumptionobject.get("month").getAsString();
		String premonread = consumptionobject.get("premonread").getAsString();
		String curmonread = consumptionobject.get("curmonread").getAsString();
		String conunits = consumptionobject.get("conunits").getAsString();
		
		String output = consumptionobj.updateConsumption(conID, userID, month, premonread, 
				curmonread, conunits);
	
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteConceptDetails(String consumptionData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser());
			
		//Read the value from the element <conID>
		String conID = doc.select("conID").text();
		String output = consumptionobj.deleteConsumption(conID);
			
		return output;
	}
	
}
