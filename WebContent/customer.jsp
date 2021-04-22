<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    if (request.getParameter("name") != null)
    {
    	Customer CustomerObj = new Customer();
    String stsMsg = CustomerObj.insertCustomer(request.getParameter("name"),request.getParameter("gender"),request.getParameter("phone"),request.getParameter("email"),request.getParameter("city"),request.getParameter("region"));
    
    session.setAttribute("statusMsg", stsMsg);
    } 

%>

<%
		if(request.getParameter("cId") != null){
			Customer CustomerUpdate = new Customer();
			String stMsg = CustomerUpdate.updateCustomer(request.getParameter("cId"), request.getParameter("nm"), request.getParameter("gr"), request.getParameter("pn"),request.getParameter("em"),request.getParameter("cy"),request.getParameter("rn"));
			session.setAttribute("statusMessage", stMsg);
		}
%>

<%
		if(request.getParameter("cID") != null){
			Customer CustomerDelete = new Customer();
			String deleteMsg = CustomerDelete.deleteCustomer(request.getParameter("cID"));
    		session.setAttribute("deleteStatus", deleteMsg);
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">

<style>
.avatar {
  vertical-align: middle;
  width: 65px;
  height: 50px;
  border-radius: 50%;
}
</style>
</head>
<body>

<div class="container">
	<div class="row">
 		<div class="col">
 		<br><br>
 		<div class="form-row">
 		<br><br>
 		
 		<input name="cname" type="text" value="GadgetBadget" readonly="readonly">
 		</div>
 		<br><br>
		<h1>GadgetBadget Management Registration</h1><br>



	<%
		if(request.getParameter("id") != null){
			Customer CustomerGet = new Customer();
			out.print(CustomerGet.getCustomer(request.getParameter("id")));
		}	
		else{
			out.print("<form method='post' action='customer.jsp'>"
					+ "Name : <input name='name' type='text' class='form-control'><br>"
					+ "Gender : <input name='gender' type='text' class='form-control'><br>"
					+ "Phone : <input name='phone' type='text' class='form-control'><br>"
					+ "Email : <input name='email' type='email' class='form-control'><br>"
					+ "City : <input name='city' type='text' class='form-control'><br>"
					+ "Region : <input name='region' type='text' class='form-control'><br>"
					+ "<input name='btnSubmit' type='submit' value='submit' class='btn btn-primary'>" + "</form>");
		}
	
	%>


	<div class="alert alert-success">
	<%

		if (request.getParameter("name") != null){
 			out.print(session.getAttribute("statusMsg"));
		}
		else if(request.getParameter("cId") != null){
			out.print(session.getAttribute("statusMessage"));
		}
		else if(request.getParameter("cID") != null){
			out.print(session.getAttribute("deleteStatus"));
		}
	
	%>
	</div>
	
	<br>
	<%
	Customer CustomerObj = new Customer();
 		out.print(CustomerObj.readCustomer());
	%>
	
		</div>
	</div>
</div>
</body>
</html>