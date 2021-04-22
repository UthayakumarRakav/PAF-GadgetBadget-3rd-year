package com;
import model.Login;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Customer;
@Path("/login")
public class LoginService {

	Login login= new Login();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readLogin() { 
		 return login.readLogin(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertLogin(@FormParam("username") String username, @FormParam("password") String password) { 
		String output = login.insertLogin(username, password); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatelogin(String loginData) { 
		//Convert the input string to a JSON object 
		JsonObject loginobj = new JsonParser().parse(loginData).getAsJsonObject();
	 
		//Read the values from the JSON object
		String cID = loginobj.get("cID").getAsString(); 
		String username= loginobj.get("username").getAsString(); 
		String password= loginobj.get("password").getAsString(); 
		
	 
		String output = login.updateLogin(cID, username,password); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletelogin(String loginData) { 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(loginData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <itemID>
		String cID = doc.select("cID").text(); 
		String output = login.deleteLogin(cID); 
		return output; 
	}

}
