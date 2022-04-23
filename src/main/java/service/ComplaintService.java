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

import recources.ComplaintResource;


//start of service
@Path("/Complaint")
public class ComplaintService {
	//Complain API type object
		ComplaintResource complaintobj = new ComplaintResource();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readComplaint(){
			return complaintobj.readComplaint();
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertComplaint(@FormParam("compID") int compID,@FormParam("userID") String userID,
				@FormParam("des") String des,@FormParam("answer") String answer,
				@FormParam("updatedDate") String updatedDate, @FormParam("compDate") String compDate, @FormParam("cstatus") String cstatus){
		
			String output = complaintobj.insertComplaint(compID, userID, des, answer, 
					updatedDate, compDate, cstatus);
		
			return output;
		}
		

		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateComplaint(String complaintData){
			
			//Convert the input string to a JSON object
			JsonObject complaintobject = new JsonParser().parse(complaintData).getAsJsonObject();
		
			//Read the values from the JSON object
			int compID = complaintobject.get("compID").getAsInt();
			String userID = complaintobject.get("userID").getAsString();
			String des = complaintobject.get("des").getAsString();
			String answer = complaintobject.get("answer").getAsString();
			String updatedDate = complaintobject.get("updatedDate").getAsString();
			String compDate = complaintobject.get("compDate").getAsString();
			String cstatus = complaintobject.get("cstatus").getAsString();

			
			String output = complaintobj.updateComplaint(compID, userID, des, answer, updatedDate,
					compDate, cstatus);
		
			return output;
		}

}
