<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Javas.DBClass" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP page count</title>
</head>
<body>

<%
//get the value "destination" from other pages

// search in the table to get the primary key.
// display item with forms
// pass primary key to other pages

%>


<% 
	
    if (session.getAttribute("LoggedIn") == Boolean.TRUE) {
    	request.getSession();
%>

<center><h1>Current User:  <%=session.getAttribute("User") %></h1></center><p><p>
<h1 align=center>SEARCH RESULT</h1>
<table border='1'>

<tr><th>PACKAGEID: </th> 
<th>DESTINATION:</th>
<th>ITEM DESCRIPTION</th>
<th>COST</th>


<%
ResultSet rs = (ResultSet)session.getAttribute("pi");
String date = (String)session.getAttribute("date");

String SQL;
int packageid;
String destination;
String description;
double cost;

//System.out.println("value of rs.next is travelpackages.jsp" + rs.next());
rs.beforeFirst();
while (rs.next()){
	packageid = rs.getInt("PACKAGEID");
	destination = rs.getString("DESTINATION");
	description = rs.getString("DESCRIPTION");
	cost = rs.getDouble("COST"); 
	 
	
%><tr>
<td><%=packageid %></td>
<td><%=destination %></td>
<td><%=description %></td>
<td><%=cost %></td>
<td>
	<form action="TravelPackagesServlet" method="POST">
	<input type="hidden" name="packageid" value=<%=packageid%>>
	<input type="hidden" name="dof" value=<%=date%>>
	<input type="submit" value="View and Book">
	</form>
</td>



<%

}

%>
</tr>


</table>

<form action ="Logout" method="POST" >
<br>
<input type="submit" value="Log Out">
<br><br><br>

<% } else { %> You are not logged in<br/>
<a href="login.jsp">Please Login</a> <% } %>






























<%! static int hit=0; %>
<%
if (hit == 0) 
{
%>
<h4><%
hit ++;
out.println("Welcome Visitor"); %> <br> 
<% out.println("Visitor Number : ");%> <%=hit %>.</h4>
<% 
hit++;
}
else
{
%>
<h4><% out.print("Welcome Visitor, Visitor Number : "); %> <%=hit %>.</h4>
<%
hit++;
}
%>
</body>
</html>