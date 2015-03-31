

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>

</head>


<%@ page import="Javas.DBClass" %>
<%@ page import="java.sql.ResultSet" %>

<body>
<% 
	
    if (session.getAttribute("LoggedIn") == Boolean.TRUE) {
    	request.getSession();
%>

<center><h1>Current User:  <%=session.getAttribute("User") %></h1></center><p><p>
<h1 align=center>SEARCH RESULT</h1>
<table border='1'>

<tr><th>Flight Number: </th> 
<th>Flight Date:</th>
<th>Departure time</th>
<th>Arrival Time</th>
<th>Cost</th>
<th>Cost</th>


<%
ResultSet rs = (ResultSet)request.getAttribute("rs");
String date = (String)session.getAttribute("date");

String SQL;
int flightnumber;
String dTime;
String aTime;
double cost;
String flightNumberSearch;// based on source, destination, select from table flight


rs.beforeFirst();
while (rs.next()){
	flightnumber = rs.getInt("FLIGHTNUMBER");
	dTime = rs.getString("DEPARTURE");
	aTime = rs.getString("ARRIVAL");
	cost = rs.getDouble("COST"); 
	date = rs.getString("DATE"); 
	
%><tr>
<td><%=flightnumber %></td>
<td><%=date %></td>
<td><%=dTime %></td>
<td><%=aTime %></td>
<td>$<%=cost %></td>
<td>
	<form action="FlightSearchResults" method="POST">
	<input type="hidden" name="flight" value=<%=flightnumber%>>
	<input type="hidden" name="dof" value=<%=date%>>
	<input type="submit" value="View and Book">
	</form>
</td>



<%

}

%>
</tr>


</table>

<p><p>
<input type="button" value="Home" onclick="window.location.assign('searchquery.jsp')" > 
<form action ="Logout" method="POST" >
<br>
<input type="submit" value="Log Out"> </form>
<br><br>

<form action ="BookingHistory" method="POST" >
<input type="Submit" value="Booking History"> </form>

<% } else { %> You are not logged in<br/>
<a href="login.jsp">Please Login</a> <% } %>

</body>
</html>