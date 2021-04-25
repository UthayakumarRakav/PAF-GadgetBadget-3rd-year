package model;

import java.sql.*;

public class Payment {

	private Connection connect()  {   
		
		Connection con = null; 
	 
	  try   
	  {     
		  Class.forName("com.mysql.jdbc.Driver");              
		  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");   
		  
	  }   
	  catch (Exception e)   
	  {e.printStackTrace();} 
	 
	  return con;  
	  } 
	
	public String insertDetails(String name, String phone, String card, String cvv) 
	{   
		String output = ""; 
	 try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return "Error while connecting to the database for inserting."; } 
	 
	      String query = " insert into paymentdetails (`ID`,`cname`,`cphone`,`ccard`,`cvv`)"     
	    		  + " values (?, ?, ?, ?, ?)"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      preparedStmt.setInt(1, 0);   
	      preparedStmt.setString(2, name);   
	      preparedStmt.setString(3, phone);    
	      preparedStmt.setString(4, card);   
	      preparedStmt.setString(5, cvv); 
	   
	 
	   
	      preparedStmt.execute();    
	      con.close(); 
	 
	   output = "Inserted successfully";   
	   
	  }catch (Exception e)   
	  	
	 	{    
		   output = "Error while inserting the Details";    
		   System.err.println(e.getMessage());   
		} 
	 return output;  
	}
	
	
public String readDetails()  
	{   
		String output = ""; 

		try   
		{   
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
			
	 
	     output = "<table border=\"1\"><tr><th>Customer Name</th><th>Phone</th><th>Card</th><th>CVVv</th><th>Update</th><th>Remove</th></tr>"; 
	 
	   
	     String query = "select * from paymentdetails";    
	     Statement stmt = con.createStatement();    
	     ResultSet rs = stmt.executeQuery(query); 
	 
	     while (rs.next())    
	     {     
	    	 String ID = Integer.toString(rs.getInt("ID"));     
	    	 String cname = rs.getString("cname");     
	    	 String cphone = rs.getString("cphone");     
	    	 String ccard= rs.getString("ccard");     
	    	 String cvv= rs.getString("cvv"); 
	     
	 
	         output += "<tr><td>" + cname + "</td>";     
	         output += "<td>" + cphone + "</td>";     
	         output += "<td>" + ccard + "</td>";     
	         output += "<td>" + cvv + "</td>"; 
	 
	         
	         
	         output +="<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
        			 + "<td><form method='post' action='Payment.jsp'>"
        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
        			 + "<input name='ID' type='hidden' value='" + ID + "'> </form></td></tr>";
	         } 
	
	     con.close(); 
	 
	     output += "</table>";   
	     }   
		catch (Exception e)  
		{    
			output = "Error while reading the Details.";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	  } 
	
	public String updateDetails( String ID, String name, String phone, String card, String cvv)  
	{   
		String output = ""; 
	 
		try   
		{    
				Connection con = connect(); 
	 
				if (con == null)   
				{
					return "Error while connecting to the database for updating."; 
				} 
	 
	   String query = "UPDATE paymentdetails SET cname=?,cphone=?,ccard=?,cvv=? WHERE ID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setString(1, name);    
	   preparedStmt.setString(2, phone);      
	   preparedStmt.setString(3, card);    
	   preparedStmt.setString(4, cvv);
	   preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
	   preparedStmt.execute();   
	   con.close(); 
	 
	   output = "Updated successfully";   
	}   
		catch (Exception e)   
	{    
			output = "Error while updating the Details.";    
			System.err.println(e.getMessage());   
			
	} 
	 
	  return output;  
	  
	}
	
	public String deleteDetails(String ID)  
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return "Error while connecting to the database for deleting."; } 
	 
	   String query = "delete from paymentdetails where ID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setInt(1, Integer.parseInt(ID)); 
	 
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Deleted successfully"; 
	   
	  }   
	  catch (Exception e)   
	  {    
		  output = "Error while deleting the Details.";    
		  System.err.println(e.getMessage());   } 
	 
	  return output;  }
	
	
}
