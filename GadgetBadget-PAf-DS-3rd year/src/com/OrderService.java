package com;
import model.Order;


import javax.servlet.http.Part;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;
import com.mysql.cj.jdbc.Blob;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Orders") 

public class OrderService {
	
Order order = new Order();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readOrderProjects() { 
		 return order.readOrderProjects(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertOrder(@FormParam("email") String email, @FormParam("product_id") String product_id, @FormParam("quantity") String quantity,@FormParam("address") String address, @FormParam("address") String city,@FormParam("mobileNumber") String mobileNumber, @FormParam("paymentMethod") String paymentMethod, @FormParam("address") String transactionId) { 
		String output = order.insertOrder(email, product_id,quantity,address, city,mobileNumber,paymentMethod,transactionId); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateOrder(String orderData) { 
		//Convert the input string to a JSON object 
		JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject();
	 
		//Read the values from the JSON object
		String id = orderObject.get("id").getAsString(); 
		String email = orderObject.get("email").getAsString(); 
		String product_id = orderObject.get("product_id").getAsString(); 
		String quantity = orderObject.get("quantity").getAsString(); 
		String address = orderObject.get("address").getAsString(); 
		String city = orderObject.get("city").getAsString(); 
		String mobileNumber = orderObject.get("mobileNumber").getAsString(); 		 
		String paymentMethod = orderObject.get("email").getAsString(); 
		String transactionId = orderObject.get("quantity").getAsString(); 
		
	 
		String output = order.updateOrder(id,email, product_id,quantity,address, city,mobileNumber,paymentMethod,transactionId); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteOrder(String orderData) { 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(orderData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <itemID>
		String id = doc.select("id").text(); 
		String output = order.deleteOrder(id); 
		return output; 
	}
	

}
