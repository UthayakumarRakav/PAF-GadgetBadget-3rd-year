<%@page import="model.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    if (request.getParameter("Username") != null)
    {
    	Login login= new Login();
    String stsMsg = login.insertLogin(request.getParameter("Username"),request.getParameter("password"));
    
    session.setAttribute("statusMsg", stsMsg);
    } 

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Here!</title>
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
		<h1>GadgetBadget Management Login</h1><br>



	<%
		if(request.getParameter("cID") != null){
			Login LoginGet = new Login();
			out.print(LoginGet.getLogin(request.getParameter("cID")));
		}	
		else{
			out.print("<form method='post' action='customer.jsp'>"
					+ "UserName : <input name='Username' type='text' class='form-control'><br>"
					+ "Password : <input name='password' type='password' class='form-control'><br>"
					
					+ "<input name='btnSubmit' type='submit' value='Login' class='btn btn-primary'>" + "</form>");
		}
	
	%>


	<div class="alert alert-success">
	<%

		if (request.getParameter("un") != null){
 			out.print(session.getAttribute("statusMsg"));
		}
		else if(request.getParameter("cID") != null){
			out.print(session.getAttribute("statusMessage"));
		}
		else if(request.getParameter("cID") != null){
			out.print(session.getAttribute("deleteStatus"));
		}
	
	%>
	</div>
	
	<br>
	
	
		</div>
	</div>
</div>
</body>
</html>