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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import recources.OverallMonitoringResource;

//start of service
@Path("/Monitoring")
public class OverallMonitoringService {
	
	//Consumption API type object
	OverallMonitoringResource monitoringobj = new OverallMonitoringResource();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readConsumption(){
		return monitoringobj.readOverallMonitoring();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOverallMonitoring(@FormParam("pay_ID") String pay_ID,@FormParam("power_consumption_ID") String power_consumption_ID,
			@FormParam("month") String month,@FormParam("comment") String comment){
	
		String output = monitoringobj.insertOverallMonitoring(pay_ID, power_consumption_ID, month, comment);
	
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMonitoring(String monitoringData){
		
		//Convert the input string to a JSON object
		JsonObject monitoringobject = new JsonParser().parse(monitoringData).getAsJsonObject();
	
		//Read the values from the JSON object
		int monitoring_ID = monitoringobject.get("monitoring_ID").getAsInt();
		int pay_ID = monitoringobject.get("pay_ID").getAsInt();
		int power_consumption_ID = monitoringobject.get("power_consumption_ID").getAsInt();
		String month = monitoringobject.get("month").getAsString();
		int units = monitoringobject.get("units").getAsInt();
		double balance = monitoringobject.get("balance").getAsDouble();
		String comment = monitoringobject.get("comment").getAsString();
		
		String output = monitoringobj.updateMonitoring(monitoring_ID, pay_ID, power_consumption_ID, month, units, balance, comment);
	
		return output;
	}
	
	@DELETE
	@Path("{monitoring_ID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(@PathParam ("monitoring_ID") String monitoring_ID)
	{
		return monitoringobj.deleteMonitoring(monitoring_ID);
	}	
	
	@GET
	@Path("/{month}")
	@Produces(MediaType.TEXT_HTML)
	public String readSpecificMonitoring(@PathParam("month") String month)
	{
		return monitoringobj.readSpecificMonitoring(month);
	}
	
	
}
