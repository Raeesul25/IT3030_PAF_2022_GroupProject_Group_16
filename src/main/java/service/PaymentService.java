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
import javax.ws.rs.core.MediaType;

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
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(@FormParam("userID") String userID, 
			@FormParam("billID") String billID,
			@FormParam("paid_amount") String paid_amount,@FormParam("payment_type") String payment_type,@FormParam("card_no") String card_no){
	
		String output = Paymnetsobj.insertPayment(userID,billID, paid_amount,payment_type,card_no);
	
		return output;
	}
	
	
	@PUT
	@Path("/update/{billID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(@PathParam ("billID") String billID, String consumptionData){
		
		//Convert the input string to a JSON object
		JsonObject PaymnetsobjJ = new JsonParser().parse(consumptionData).getAsJsonObject();
	
		//Read the values from the JSON object
		String paymentID = PaymnetsobjJ.get("paymentID").getAsString();
		String billID1 = PaymnetsobjJ.get("billID").getAsString();
		String new_paid = PaymnetsobjJ.get("new_paid").getAsString();
		String payment_type = PaymnetsobjJ.get("payment_type").getAsString();
		String card_no = PaymnetsobjJ.get("card_no").getAsString();
		
		String output = Paymnetsobj.updatePayment(paymentID,billID1,payment_type,card_no,new_paid);
	
		return output;
	}
	
	
	@DELETE
	@Path("/delete/{paymentID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePaymentRecord(@PathParam ("paymentID") String paymentID)
	{
		return Paymnetsobj.deletepayment(paymentID);
	}
	
	@GET
	@Path("/{userID}")
	@Produces(MediaType.TEXT_HTML)
	public String readOneUserPayment(@PathParam("userID") String userID)
	{
		return Paymnetsobj.readOneUserPayment(userID);
	}

}
