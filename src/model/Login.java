package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {

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
	
	public String insertLogin(String username, String password)  {
		String output = "";


		try {
			Connection con = connect();

			if (con == null)
			{
				return "Error while connecting to the database";
			}
		
			// create a prepared statement
			String query = " insert into login(`cID`,`username`,`password`)" + " values (?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, password);
			
			
			
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
	
	
	
	
	
	
	
}
