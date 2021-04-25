<%@page import="model.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    if (request.getParameter("title") != null)
    {
    	Project projectObj = new Project();
    String stsMsg = projectObj.insertProject(request.getParameter("title"), request.getParameter("description"));
    
    session.setAttribute("statusMsg", stsMsg);
    request.setAttribute("value", 2);

    } 

%>

<%
		if(request.getParameter("prId") != null){
			Project projectUpdate = new Project();
			String stMsg = projectUpdate.updateProject(request.getParameter("prId"), request.getParameter("tit"), request.getParameter("desc"));
			session.setAttribute("statusMessage", stMsg);
		}
%>

<%
		if(request.getParameter("projectID") != null){
			Project projectDel = new Project();
			String deleteMsg = projectDel.deleteProject(request.getParameter("projectID"));
    		session.setAttribute("deleteStatus", deleteMsg);
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Management</title>
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
 		<div class='form-row'>
		<h1>Project Management</h1>
		<form method="post" action="Order.jsp">
			<input name="order" type="submit" value="Order Service" class="btn btn-info" style="margin-left:560%;">
		</form>
		</div>

	<%
		if(request.getParameter("id") != null){
			Project projectGet = new Project();
			out.print(projectGet.getProject(request.getParameter("id")));
		}	
		else{
			out.print("<form method='post' action='ResearcherDashboard.jsp'>"
					+ "Title : <input name='title' type='text' class='form-control'><br>"
					+ "Descriprion : <textarea name='description' type='text' class='form-control' row='10'></textarea><br>"
					+ "<input name='btnSubmit' type='submit' value='Save' class='btn btn-primary'>" + "</form>");
		}
	
	%>


	<div class="alert alert-success">
	<%

		if (request.getParameter("title") != null){
 			out.print(session.getAttribute("statusMsg"));
		}
		else if(request.getParameter("prId") != null){
			out.print(session.getAttribute("statusMessage"));
		}
		else if(request.getParameter("projectID") != null){
			out.print(session.getAttribute("deleteStatus"));
		}
	
	%>
	</div>
	
	
	<div class="tbs">
	<%
 		Project projectObj = new Project();
 		out.print(projectObj.readProjects());
	%>
	</div>
		</div>
	</div>
</div>
</body>
</html>