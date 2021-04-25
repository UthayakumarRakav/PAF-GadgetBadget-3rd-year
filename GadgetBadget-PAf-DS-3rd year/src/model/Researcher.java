package model;
import java.sql.*;

public class Researcher {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher","root", "");
			
			//for testing
			System.out.print("Successfully connected"); 
		}catch(Exception e) {
			 e.printStackTrace(); 
		}
		//System.out.print("done");
		return con;	
	}
	
	//To Insert
	public String insertResearcher(String Name, String ResearcherID,String Email,String Phone,String Department)  {
		String output = "";


		try {
			Connection con = connect();

			if (con == null)
			{
				return "Error while connecting to the database";
			}
		
			// create a prepared statement
			String query = " insert into rdata(`RID`,`Name`,`ResearcherID`,`Email`,`Phone`,`Department`)" + " values (?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Name);
			preparedStmt.setString(3, ResearcherID);
			preparedStmt.setString(4, Email);
			preparedStmt.setString(5, Phone);
			preparedStmt.setString(6, Department);
			
			
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Researcher detail inserted successfully";
			
		}catch (Exception e){
			 output = "Error while inserting";
			 System.err.println(e.getMessage());
		} 
		return output;
		
	}
	
	public String readResearcher(){
		String result = "";
		
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
				
				
				String query = "select * from rdata";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next()){
					
					String Name = rs.getString("Name");
					String ResearcherID= rs.getString("ResearcherID");
					String Email = rs.getString("Email");
					String Phone= Integer.toString(rs.getInt("Phone"));
					String Department = rs.getString("Department");
					
					
					String RID= Integer.toString(rs.getInt("RID"));
					
					// Add a row into the html table
					result += "<div class='card'><h5 class='card-header'>" + Name + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + ResearcherID + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + Email + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + Phone + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + Department + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + RID+ "</h5>";
					
					
					// buttons
					result += "<div class='form-row'><form method='post' action='researcher.jsp'>" 
							+ "<input name='btnGet' type='submit' value='Update' class='btn btn-success'>"
							+ "<input name='id' type='hidden'" + " value='" + RID + "'>" + "</form>&nbsp&nbsp&nbsp&nbsp&nbsp" 
							+ "<form method='post' action='researcher.jsp'>" 
							+ "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>" 
							+ "<input name='RID' type='hidden' " + " value='" + RID + "'>" + "</form></div></div></div><br>";
				}
				con.close();
				// Complete the html table
			
		}catch (Exception e){
			result = "Error while reading.";
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public String getResearcher(String RID) {
		String result = "";		
		
		try {
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
			
			String query = " select * from rdata where RID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(RID));
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				result = "<form method='post' action='researcher.jsp'>"
						+ "Researcher Name : <input name='nm' type='text' value = '"+rs.getString("Name")+"' class='form-control'><br>"
						+ "Researcher ID: <input name='gr' type='text' value = '"+rs.getString("ResearcherID")+"' class='form-control' row='10'><br>"
						+ "Researcher Email: <input name='em' type='text' value = '"+rs.getString("Email")+"' class='form-control' row='10'><br>"
						+ "Researcher Phone: <input name='pn' type='text' value = '"+Integer.toString(rs.getInt("Phone"))+"' class='form-control' row='10'><br>"
						+ "Researcher Department: <input name='cy' type='text' value = '"+rs.getString("Department")+"' class='form-control' row='10'><br>"		
						+ "<form method='post' action='researcher.jsp'>"
						+ "<input name='btnUpdate' type='submit' value='Update' class='btn btn-info'>"
						+ "<input name='rID' type='hidden' " + " value='" + RID + "'>" + "</form><br>";

			}
			
			con.close();
		}catch(Exception e) {
			result = "Error while geting .";
			System.err.println(e.getMessage());
		}
		return result;		
	}
	
	//To Update
	public String updateResearcher(String RID,String Name, String ResearcherID,String Phone,String Email,String Department) {
		String result = "";
		
		try {
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
			
			String query = " update rdata set Name=?,ResearcherID=?, Phone=?, Email=?, Department=? where RID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, Name);
			preparedStmt.setString(2, ResearcherID);
			preparedStmt.setInt(3, Integer.parseInt(Phone));
			preparedStmt.setString(4, Email);
			preparedStmt.setString(5, Department);
			
			preparedStmt.setInt(6, Integer.parseInt(RID));


			preparedStmt.execute();
			con.close();
			
			result = "Updated successfully";

		}catch(Exception e) {
			result = "Error while updating.";
			System.err.println(e.getMessage());
		}		
		
		return result;
	}
	
	//To Delete
	public String deleteResearcher(String RID) {
		String result = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the Database";
			}
			
			String query = " delete from rdata where RID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(RID));
			preparedStmt.execute();
			
			con.close();
			result = "Record has been Deleted Successfully";

		}catch(Exception e) {
			result = "Error while Deleting .";
			System.err.println(e.getMessage());
		}
		
		return result;	
	}
}
