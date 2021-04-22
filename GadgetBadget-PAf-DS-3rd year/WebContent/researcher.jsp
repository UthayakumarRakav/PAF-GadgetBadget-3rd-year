<%@page import="model.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    if (request.getParameter("Name") != null)
        {
        	Researcher ResearcherObj = new Researcher();
        String stsMsg = ResearcherObj.insertResearcher(request.getParameter("Name"),request.getParameter("ResearcherID"),request.getParameter("Email"),request.getParameter("Phone"),request.getParameter("Department"));
        
        session.setAttribute("statusMsg", stsMsg);
        }
    %>

<%
if(request.getParameter("rID") != null){
	Researcher ResearcherUpdate = new Researcher();
	String stMsg = ResearcherUpdate.updateResearcher(request.getParameter("rID"), request.getParameter("nm"), request.getParameter("gr"), request.getParameter("pn"),request.getParameter("em"),request.getParameter("cy"));
	session.setAttribute("statusMessage", stMsg);
		}
%>

<%
if(request.getParameter("RID") != null){
	Researcher ResearcherDelete = new Researcher();
	String deleteMsg = ResearcherDelete.deleteResearcher(request.getParameter("RID"));
    		session.setAttribute("deleteStatus", deleteMsg);
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Management</title>
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
 		
 		<input name="Name" type="text" value="GadgetBadget" readonly="readonly">
 		</div>
 		<br><br>
		<h1>GadgetBadget Management Registration</h1><br>



	<%
	if(request.getParameter("id") != null){
		Researcher ResearcherGet = new Researcher();
		out.print(ResearcherGet.getResearcher(request.getParameter("id")));
			}	
			else{
		out.print("<form method='post' action='researcher.jsp'>"
				+ "Researcher Name : <input name='Name' type='text' class='form-control'><br>"
				+ "Researcher ID : <input name='ResearcherID' type='text' class='form-control'><br>"
				+ "Researcher Email : <input name='Email' type='email' class='form-control'><br>"
				+ "Researcher Phone : <input name='Phone' type='text' class='form-control'><br>"
				+ "Researcher Department : <input name='Department' type='text' class='form-control'><br>"
				+ "<input name='btnSubmit' type='submit' value='submit' class='btn btn-primary'>" + "</form>");
			}
	%>


	<div class="alert alert-success">
	<%
	if (request.getParameter("Name") != null){
	 			out.print(session.getAttribute("statusMsg"));
			}
			else if(request.getParameter("rID") != null){
		out.print(session.getAttribute("statusMessage"));
			}
			else if(request.getParameter("RID") != null){
		out.print(session.getAttribute("deleteStatus"));
			}
	%>
	</div>
	
	<br>
	<%
	Researcher ResearcherObj = new Researcher();
	 		out.print(ResearcherObj.readResearcher());
	%>
	
		</div>
	</div>
</div>
</body>
</html>