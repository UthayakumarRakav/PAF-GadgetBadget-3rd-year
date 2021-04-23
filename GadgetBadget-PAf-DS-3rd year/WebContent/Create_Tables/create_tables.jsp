  <%@ page import="model.Order" %>
<%@ page import="java.sql.*" %>

<% 
	try{
		Connection con = model.Order.connect();
		Statement st =con.createStatement();
		String q1="create table orderproject(id int,email varchar(100),product_id int, quantity int,address varchar(100),city varchar(100),mobileNumber int,paymentMethod varchar(100),transactionId varchar(100))";
		
		System.out.println(q1);
		st.execute(q1);
		System.out.print("You have benn created a table");
				
				
	}catch(Exception e){
		System.out.print(e);

	}
%>