package com;
import model.Researcher; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Researcher")
public class ResearcherService {

	Researcher researcher = new Researcher();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProjects() { 
		 return researcher.readResearcher(); 
	}
	
	//To insert
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearcher(@FormParam("Name") String Name,@FormParam("ResearcherID") String ResearcherID,
			@FormParam("Email") String Email,@FormParam("Phone") String Phone,@FormParam("Department") String Department) { 
		String output = researcher.insertResearcher(Name,ResearcherID,Email,Phone,Department); 
		return output; 
	}
	
	//To Update
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearcher(String researcherData) { 
		//Convert the input string to a JSON object 
		JsonObject researcherObject = new JsonParser().parse(researcherData).getAsJsonObject();
	 
		//Read the values from the JSON object
		String RID = researcherObject.get("rID").getAsString(); 
		String Name= researcherObject.get("nm").getAsString(); 
		String ResearcherID= researcherObject.get("gr").getAsString(); 
		String Phone= researcherObject.get("pn").getAsString(); 
		String Email= researcherObject.get("em").getAsString(); 
		String Department= researcherObject.get("cy").getAsString(); 
		
	 
		String output = researcher.updateResearcher(RID,Name,ResearcherID,Phone,Email,Department); 
		return output; 
	}
	
	
	//To Delete
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearcher(String researcherData) { 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <itemID>
		String RID = doc.select("RID").text(); 
		String output = researcher.deleteResearcher(RID); 
		return output; 
	}

}
