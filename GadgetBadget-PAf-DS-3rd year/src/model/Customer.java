package model;
import java.sql.*;

public class Customer {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget","root", "");
			
			//for testing
			System.out.print("Successfully connected"); 
		}catch(Exception e) {
			 e.printStackTrace(); 
		}
		//System.out.print("done");
		return con;	
	}
	
	public String insertCustomer(String cname, String gender,String phone,String email,String city, String region)  {
		String output = "";


		try {
			Connection con = connect();

			if (con == null)
			{
				return "Error while connecting to the database";
			}
		
			// create a prepared statement
			String query = " insert into customer(`cID`,`cname`,`gender`,`phone`,`email`,`city`,`region`)" + " values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cname);
			preparedStmt.setString(3, gender);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, city);
			preparedStmt.setString(7, region);
			
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Customer detail inserted successfully";
			
		}catch (Exception e){
			 output = "Error while inserting";
			 System.err.println(e.getMessage());
		} 
		return output;
		
	}
	
	public String readCustomer(){
		String result = "";
		
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
				
				
				String query = "select * from customer";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next()){
					
					String cname = rs.getString("cname");
					String gender =rs.getString("gender");
					String email = rs.getString("email");
					
					String city = rs.getString("city");
					String phone= Integer.toString(rs.getInt("phone"));
					String region= rs.getString("region");
					String cID= Integer.toString(rs.getInt("cID"));
					
					// Add a row into the html table
					result += "<div class='card'><h5 class='card-header'>" + cname + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + cID+ "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + gender+ "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + phone + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + email + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + city + "</h5>";
					result += "<div class='card'><h5 class='card-header'>" + region + "</h5>";
					
					// buttons
					result += "<div class='form-row'><form method='post' action='customer.jsp'>" 
							+ "<input name='btnGet' type='submit' value='Update' class='btn btn-success'>"
							+ "<input name='id' type='hidden'" + " value='" + cID + "'>" + "</form>&nbsp&nbsp&nbsp&nbsp&nbsp" 
							+ "<form method='post' action='customer.jsp'>" 
							+ "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>" 
							+ "<input name='cID' type='hidden' " + " value='" + cID + "'>" + "</form></div></div></div><br>";
				}
				con.close();
				// Complete the html table
			
		}catch (Exception e){
			result = "Error while reading the customer data.";
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public String getCustomer(String cID) {
		String result = "";		
		
		try {
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
			
			String query = " select * from customer where cID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(cID));
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				result = "<form method='post' action='customer.jsp'>"
						+ "Name : <input name='nm' type='text' value = '"+rs.getString("cname")+"' class='form-control'><br>"
						+ "Gender: <input name='gr' type='text' value = '"+rs.getString("gender")+"' class='form-control' row='10'><br>"
						+ "Phone: <input name='pn' type='text' value = '"+Integer.toString(rs.getInt("phone"))+"' class='form-control' row='10'><br>"
						+ "Email: <input name='em' type='text' value = '"+rs.getString("email")+"' class='form-control' row='10'><br>"
						+ "City: <input name='cy' type='text' value = '"+rs.getString("city")+"' class='form-control' row='10'><br>"		
						+ "Region: <input name='rn' type='text' value = '"+rs.getString("region")+"' class='form-control' row='10'><br>"		
						+ "<form method='post' action='customer.jsp'>"
						+ "<input name='btnUpdate' type='submit' value='Update' class='btn btn-info'>"
						+ "<input name='cId' type='hidden' " + " value='" + cID + "'>" + "</form><br>";

			}
			
			con.close();
		}catch(Exception e) {
			result = "Error while geting the customer data.";
			System.err.println(e.getMessage());
		}
		return result;		
	}
	
	public String updateCustomer(String cID,String cname, String gender,String phone,String email,String city, String region) {
		String result = "";
		
		try {
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
			
			String query = " update customer set cname=?,gender=?, phone=?, email=?, city=?,region=? where cID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, cname);
			preparedStmt.setString(2, gender);
			preparedStmt.setInt(3, Integer.parseInt(phone));
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, city);
			preparedStmt.setString(6, region);
			preparedStmt.setInt(7, Integer.parseInt(cID));


			preparedStmt.execute();
			con.close();
			
			result = "Updated successfully";

		}catch(Exception e) {
			result = "Error while updating the customer data.";
			System.err.println(e.getMessage());
		}		
		
		return result;
	}
	
	public String deleteCustomer(String cID) {
		String result = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the Database";
			}
			
			String query = " delete from customer where cID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(cID));
			preparedStmt.execute();
			
			con.close();
			result = "Record has been Deleted Successfully";

		}catch(Exception e) {
			result = "Error while Deleting the customer detail.";
			System.err.println(e.getMessage());
		}
		
		return result;	
	}
}
