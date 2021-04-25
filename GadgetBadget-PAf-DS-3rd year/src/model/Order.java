
package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

import javax.servlet.http.Part;

public class Order {


	

		public static Connection connect() {
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
		
		public String insertOrder(String email, String product_id,String quantity,String address,String city,String mobileNumber,String paymentMethod,String transactionId) {
			String output = "";


			try {
				Connection con = connect();

				if (con == null)
				{
					return "Error while connecting to the database";
				}
			
				// create a prepared statement
				String query = " insert into orderproject(`id`,`email`,`product_id`,`quantity`,`address`,`city`,`mobileNumber`,`paymentMethod`,`transactionId`)" + " values ( ?, ?, ?,?, ?, ?,?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, email);
				preparedStmt.setInt(3, Integer.parseInt(product_id));
				preparedStmt.setInt(4, Integer.parseInt(quantity));
				preparedStmt.setString(5, address);
				preparedStmt.setString(6, city);
				preparedStmt.setInt(7, Integer.parseInt(mobileNumber));		
				preparedStmt.setString(8, paymentMethod);
				preparedStmt.setString(9, transactionId);
				//execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Order Inserted successfully";

				
			}catch (Exception e){
				 output = "Error while inserting";
				 System.err.println(e.getMessage());
			} 
			return output;
			
		}
		
		public String readOrderProjects(){
			String result = "";
			
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for reading.";
				}
					
					
					String query = "select * from orderproject";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next()){
						String id = Integer.toString(rs.getInt("id"));
						String email= rs.getString("email");
						String product_id=Integer.toString(rs.getInt("product_id"));
						String quantity=Integer.toString(rs.getInt("quantity"));
						String address=rs.getString("address");
						String city=rs.getString("city");
						String mobileNumber=Integer.toString(rs.getInt("mobileNumber"));						
						String paymentMethod=rs.getString("paymentMethod");
						String transactionId=rs.getString("transactionId");

						// Add a row into the html table
						result += "<div class='card'><h5 class='card-header'>" + email + "</h5>";
						result += "<div class='card'><h5 class='card-header'>" + product_id + "</h5>";
						result += "<div class='card'><h5 class='card-header'>" + quantity+ "</h5>";
						result += "<div class='card-body'><p class='card-text'>" + address + "</p>";
						result += "<div class='card'><h5 class='card-header'>" + city + "</h5>";
						result += "<div class='card'><h5 class='card-header'>" + mobileNumber + "</h5>";						
						result += "<div class='card'><h5 class='card-header'>" + paymentMethod + "</h5>";
						result += "<div class='card'><h5 class='card-header'>" + transactionId + "</h5>";
						
						
						
						// buttons
						result += "<div class='form-row'><form method='post' action='Order.jsp'>" 
								+ "<input name='btnGet' type='submit' value='Update' class='btn btn-success'>"
								+ "<input name='id' type='hidden'" + " value='" + id + "'>" + "</form>&nbsp&nbsp&nbsp&nbsp&nbsp" 
								+ "<form method='post' action='Order.jsp'>" 
								+ "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>" 
								+ "<input name='oID' type='hidden' " + " value='" + id + "'>" + "</form></div></div></div><br>";
					}
					con.close();
					// Complete the html table
				
			}catch (Exception e){
				result = "Error while reading the Orders.";
				System.err.println(e.getMessage());
			}
			return result;
		}
		
		public String getOrder(String id) {
			String result = "";		
			
			try {
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for reading.";
				}
				
				String query = " select * from orderproject where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, Integer.parseInt(id));
				ResultSet rs = preparedStmt.executeQuery();
				
				while(rs.next()) {
					result = "<form method='post' action='Order.jsp'>"
							+ "email : <input name='email' type='text' value = '"+rs.getString("email")+"' class='form-control'><br>"
							+ "product_id : <input name='product_id' type='text' value = '"+rs.getString("product_id")+"' class='form-control'><br>"
							+ "quantity : <input name='quantity' type='text' value = '"+rs.getString("quantity")+"' class='form-control'><br>"
							+ "address : <input name='address' type='text' value = '"+rs.getString("address")+"' class='form-control' row=10><br>"
							+ "city : <input name='city' type='text' value = '"+rs.getString("city")+"' class='form-control'><br>"
							+ "mobileNumber : <input name='mobileNumber' type='text' value = '"+rs.getString("mobileNumber")+"' class='form-control'><br>"							
							+ "paymentMethod : <input name='paymentMethod' type='text' value = '"+rs.getString("paymentMethod")+"' class='form-control'><br>"
						    + "transactionId : <input name='transactionId' type='text' value = '"+rs.getString("transactionId")+"' class='form-control'><br>"
							+ "<form method='post' action='Order.jsp'>"
							+ "<input name='btnUpdate' type='submit' value='Update' class='btn btn-primary'>"
							+ "<input name='uID' type='hidden' " + " value='" + id + "'>" + "</form><br>";

				}
				
				con.close();
			}catch(Exception e) {
				result = "Error while geting the orders.";
				System.err.println(e.getMessage());
			}
			return result;		
		}
		
		public String updateOrder(String id, String email, String product_id,String quantity,String address,String city,String mobileNumber,String paymentMethod,String transactionId) {
			String result = "";
			
			try {
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for reading.";
				}
				
				String query = " update orderproject set email=?,product_id=?,quantity=?,address=?,city=?, mobileNumber=?,paymentMethod=?,transactionId=? where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
		
				preparedStmt.setString(1, email);
				preparedStmt.setInt(2, Integer.parseInt(product_id));
				preparedStmt.setInt(3, Integer.parseInt(quantity));
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, city);
				preparedStmt.setInt(6, Integer.parseInt(mobileNumber));
				preparedStmt.setString(7, paymentMethod);
				preparedStmt.setString(8, transactionId);
				preparedStmt.setInt(9, Integer.parseInt(id));
				
				preparedStmt.execute();
				con.close();
				
				result = "Updated successfully";

			}catch(Exception e) {
				result = "Error while updating the orders.";
				System.err.println(e.getMessage());
			}		
			
			return result;
		}
		
		public String deleteOrder(String id) {
			String result = "";
			
			try {
				Connection con = connect();
				
				if(con == null) {
					return "Error while connecting to the Database";
				}
				
				String query = " delete from orderproject where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, Integer.parseInt(id));
				preparedStmt.execute();
				
				con.close();
				result = "Record has been Deleted Successfully";

			}catch(Exception e) {
				result = "Error while Deleting the Order details";
				System.err.println(e.getMessage());
			}
			
			return result;	
		}
	}

	
	

