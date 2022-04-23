package service;

import javax.websocket.server.PathParam;
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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import recources.PaymentResources;

//start of service
@Path("/Payments")
public class PaymentService {
	
	//Payment API type object
	PaymentResources Paymnetsobj = new PaymentResources();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllPayment()
	{
		return Paymnetsobj.readPayment();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(@FormParam("userID") String userID, 
			@FormParam("billID") String billID, @FormParam("total_amount") String total_amount,
			@FormParam("paid_amount") String paid_amount,@FormParam("month") String month,@FormParam("payment_type") String payment_type,@FormParam("card_no") String card_no){
	
		String output = Paymnetsobj.insertPayment(userID,billID, total_amount, paid_amount, month,payment_type,card_no);
	
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String consumptionData){
		
		//Convert the input string to a JSON object
		JsonObject consumptionobject = new JsonParser().parse(consumptionData).getAsJsonObject();
	
		//Read the values from the JSON object
		String paymentID = consumptionobject.get("paymentID").getAsString();
		String userID = consumptionobject.get("userID").getAsString();
		String billID = consumptionobject.get("billID").getAsString();
		String total_amount = consumptionobject.get("total_amount").getAsString();
		String p_amount = consumptionobject.get("p_amount").getAsString();
		String obalance = consumptionobject.get("obalance").getAsString();
		String month = consumptionobject.get("month").getAsString();
		String old_paid_Date = consumptionobject.get("old_paid_Date").getAsString();
		String new_paid = consumptionobject.get("new_paid").getAsString();
		String payment_type = consumptionobject.get("payment_type").getAsString();
		String card_no = consumptionobject.get("card_no").getAsString();
		
		String output = Paymnetsobj.updatePayment(paymentID,userID,billID, total_amount, p_amount,obalance, month,old_paid_Date,new_paid,payment_type,card_no);
	
		return output;
	}
	
	
	@DELETE
	@Path("/delete/{paymentID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePaymentRecord(@PathParam ("paymentID") String paymentID)
	{
		return Paymnetsobj.deletePayment(paymentID);
	}

}
