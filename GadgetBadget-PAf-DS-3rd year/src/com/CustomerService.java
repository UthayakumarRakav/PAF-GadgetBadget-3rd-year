package com;
import model.Customer; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Customer")
public class CustomerService {

	Customer customer = new Customer();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProjects() { 
		 return customer.readCustomer(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCustomer(@FormParam("cname") String cname, @FormParam("cID") String cID,@FormParam("phone") String phone,
			@FormParam("email") String email,@FormParam("city") String city) { 
		String output = customer.insertCustomer(cname, cID,phone,email,city); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String customerData) { 
		//Convert the input string to a JSON object 
		JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
	 
		//Read the values from the JSON object
		String cID = customerObject.get("prId").getAsString(); 
		String cname= customerObject.get("nm").getAsString(); 
		String phone= customerObject.get("pn").getAsString(); 
		String email= customerObject.get("em").getAsString(); 
		String city= customerObject.get("cy").getAsString(); 
	 
		String output = customer.updateCustomer(cID, cname, phone,email,city); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProject(String customerData) { 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <itemID>
		String cID = doc.select("cID").text(); 
		String output = customer.deleteCustomer(cID); 
		return output; 
	}

}
