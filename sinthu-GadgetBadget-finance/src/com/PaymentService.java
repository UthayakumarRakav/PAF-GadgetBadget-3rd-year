package com;

import model.Payment; 

import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;
import com.google.gson.*; 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/payment") 
public class PaymentService {

	Payment payObj = new Payment(); 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertDetails(@FormParam("cname") String cname,      
							@FormParam("cphone") String cphone,    
							@FormParam("ccard") String ccard,      
							@FormParam("cvv") String cvv)
	{  
		String output = payObj.insertDetails(cname, cphone, ccard, cvv);  
		return output; 
	} 
	 
	 @GET  
	 @Path("/")  
	 @Produces(MediaType.TEXT_HTML) 
	public String readDetails()  
	 {   
		 return payObj.readDetails();
		 
	 } 
	 
	 
	 @PUT 
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String updateDetails(String paymentData)
	 {  
		 
		 JsonObject payObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
		 
		 String ID = payObject.get("ID").getAsString();  
		 String cname = payObject.get("cname").getAsString();  
		 String cphone = payObject.get("cphone").getAsString();  
		 String ccard= payObject.get("ccard").getAsString();  
		 String cvv= payObject.get("cvv").getAsString(); 
		 
		 String output = payObj.updateDetails(ID, cname, cphone, ccard, cvv); 
		 
		 return output; 
		 
	 } 
	 
	 
	 @DELETE 
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_XML) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String deleteDetails(String payData)
	 {   
		 Document doc = Jsoup.parse(payData, "", Parser.xmlParser());     
		 String payID = doc.select("payID").text(); 
		 
		 String output = payObj.deleteDetails(payID);
		 return output; } 
	 
	 
}
