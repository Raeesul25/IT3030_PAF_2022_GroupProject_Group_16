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
	
	/*** Viewing All payment details (HTTP Verb : GET) by an HTML table ***/
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllPayment()
	{
		// calling the read method 
		return Paymnetsobj.readPayment();
	}
	
	/*** Viewing one user payment details (HTTP Verb : GET) by an HTML table ***/
	@GET
	@Path("/{userID}")
	@Produces(MediaType.TEXT_HTML)
	public String readOneUserPayment(@PathParam("userID") String userID)
	{
		// calling the read method of one user
		return Paymnetsobj.readOneUserPayment(userID);
	}
	
	/*** Insert Function(HTTP Verb : POST) by getting the input values through forms and produces a plain text as output ***/
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("userID") String userID, 
			@FormParam("billID") String billID,
			@FormParam("paid_amount") String paid_amount,@FormParam("payment_type") String payment_type,@FormParam("card_no") String card_no){
	
		// calling the insert method
		String output = Paymnetsobj.insertPayment(userID,billID, paid_amount,payment_type,card_no);
	
		return output;
	}
	
	/*** Update Function(HTTP Verb : PUT) Using JSON and produces a plain text as output result***/
	@PUT
	@Path("/update/{paymentID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(@PathParam ("paymentID") String paymentID, String paymentData){
		
		//Convert the input string to a JSON object
		JsonObject PaymnetsobjJ = new JsonParser().parse(paymentData).getAsJsonObject();
	
		//Read the values from the JSON object
		String paytID = PaymnetsobjJ.get("paymentID").getAsString();
		String billID = PaymnetsobjJ.get("billID").getAsString();
		String new_paid = PaymnetsobjJ.get("new_paid").getAsString();
		String payment_type = PaymnetsobjJ.get("payment_type").getAsString();
		String card_no = PaymnetsobjJ.get("card_no").getAsString();
		String new_balance = PaymnetsobjJ.get("new_balance").getAsString();
		String total_amount = PaymnetsobjJ.get("total_amount").getAsString();
		String paid_Date = PaymnetsobjJ.get("paid_Date").getAsString();
		String month = PaymnetsobjJ.get("month").getAsString();
		
		// calling the update method
		String output = Paymnetsobj.updatePayment(paytID,billID,month,payment_type,card_no,total_amount,new_paid,new_balance,paid_Date);
	
		return output;
	}
	
	/*** DELETE FUNCTION (HTTP Verb : DELETE) using XML and produces plain text as output results ***/
	@DELETE
	@Path("/delete/{billID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePaymentRecords(@PathParam ("billID") String billID)
	{
		// calling the delete method
		return Paymnetsobj.deletepayment(billID);
	}
	

}
