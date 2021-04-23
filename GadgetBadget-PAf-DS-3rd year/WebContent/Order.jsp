<%@page import="model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    if (request.getParameter("emailX") != null)
    {
    	Order orderObj = new Order();
    String stsMsg = orderObj.insertOrder(request.getParameter("emailX"),request.getParameter("product_id"),request.getParameter("quantity"),request.getParameter("address"),request.getParameter("city"),request.getParameter("mobileNumber"),request.getParameter("paymentMethod"),request.getParameter("transactionId"));
    
    session.setAttribute("statusMsg", stsMsg);
    

    } 

%>

<%
		if(request.getParameter("uID") != null){
			Order orderObj = new Order();
			String stMsg = orderObj.updateOrder(request.getParameter("uID"),request.getParameter("email"),request.getParameter("product_id"),request.getParameter("quantity"),request.getParameter("address"),request.getParameter("city"),request.getParameter("mobileNumber"),request.getParameter("paymentMethod"),request.getParameter("transactionId"));
			session.setAttribute("statusMessage", stMsg);
		}
%>

<%
		if(request.getParameter("oID") != null){
			Order orderObj = new Order();
			String deleteMsg = orderObj.deleteOrder(request.getParameter("oID"));
    		session.setAttribute("deleteStatus", deleteMsg);
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">

<style>
.avatar {
  vertical-align: middle;
  width: 65px;
  height: 50px;
  border-radius: 50%;
  }
.tbs{
    width: 100%;
    height:350px;
  	overflow: auto; 
  }
</style>
</head>
<body>

<div class="container">
	<div class="row">
 		<div class="col">
 		<br><br><br><br><br>
 		
		<h1>Make Your Order Here</h1><br>



	<%
		if(request.getParameter("id") != null){
			Order orderObj = new Order();
			out.print(orderObj.getOrder(request.getParameter("id")));
		}	
		else{
			out.print( "<form method='post' action='Order.jsp'>"
					+ "Enter Your Email : <input name='emailX' type='text'class='form-control'><br>"
					+ "Enter project_id : <input name='product_id' type='text' class='form-control'><br>"
					+ "How many project you want : <input name='quantity' type='text' class='form-control'><br>"
					+ "Address : <input name='address' type='text' class='form-control' ><br>"
					+ "City : <input name='city' type='text' class='form-control'><br>"
					+ "Mobile number : <input name='mobileNumber' type='text' class='form-control'><br>"					
					+ "Your paymentmethod : <input name='paymentMethod' type='text' class='form-control'><br>"
				    + "transactionId : <input name='transactionId' type='text' class='form-control'><br>"
					+ "<input name='btnSubmit' type='submit' value='Save' class='btn btn-primary'>" + "</form>");

					
		}
	
	%>


	<div class="alert alert-success">
	<%

		if (request.getParameter("emailX") != null){
 			out.print(session.getAttribute("statusMsg"));
		}
		else if(request.getParameter("uID") != null){
			out.print(session.getAttribute("statusMessage"));
		}
		else if(request.getParameter("oID") != null){
			out.print(session.getAttribute("deleteStatus"));
		}
	
	%>
	</div>
	
	
	<div class="tbs">
	<%
 		Order orderObj = new Order();
 		out.print(orderObj.readOrderProjects());
	%>
	</div>
		</div>
	</div>
</div>
</body>
</html>