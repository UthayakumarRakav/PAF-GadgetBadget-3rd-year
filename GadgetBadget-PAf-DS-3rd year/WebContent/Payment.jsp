<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="model.Payment" %>
<%

if (request.getParameter("cname") != null)
{
	if (request.getParameter("cname") != null)
	 {
		Payment PaymentServiceObj = new Payment();
	 String stsMsg = PaymentServiceObj.insertDetails( request.getParameter("cname"),
	 request.getParameter("cphone"),
	 request.getParameter("ccard"),
	 request.getParameter("ccvv"));
	 session.setAttribute("statusMsg", stsMsg);
	 } 
}

//Delete item----------------------------------
if (request.getParameter("ID") != null)
{
	Payment PaymentServiceObj = new Payment();
String stsMsg = PaymentServiceObj.deleteDetails(request.getParameter("ID"));
session.setAttribute("statusMsg", stsMsg);
} 

%>

<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<meta charset="ISO-8859-1">


<title>Payment Management</title>
</head>

<body>

<h1>Payment Management</h1>
<form method="post" action="Payment.jsp">
<div class="container">
 <div class="row">
 <div class="col">

 Customer Name: <input name="cname" type="text"  class="form-control"><br>
 Customer Phone: <input name="cphone" type="text"  class="form-control"><br>
 Card NUmber: <input name="ccard" type="text"  class="form-control"><br>
 Cvv: <input name="ccvv" type="text"  class="form-control"><br>
 <input name="btnSubmit" type="submit" value="Save"class="btn btn-primary">

 </div>
 </div>
</div>
</form>
<div class="alert alert-success">
 <% out.print(session.getAttribute("statusMsg"));%>
</div>
<br>
<%
Payment PaymentServiceObj = new Payment();
 out.print(PaymentServiceObj.readDetails());
%>

</body>
</html>