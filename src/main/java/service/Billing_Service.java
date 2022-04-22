package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public List<Billing_Entity> viewBill(){
		
		return objectBill.viewBill();
	}

}
