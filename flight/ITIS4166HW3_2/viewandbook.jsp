<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Javas.DBClass" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View and Book</title>


</head>


<% 
	
    if (session.getAttribute("LoggedIn") == Boolean.TRUE) {
    	request.getSession();
%>
<body><form action="ViewAndBook" method="POST"> 
<center><h1>Current User:  <%=session.getAttribute("User") %></h1></center><p><p>

<%
 	String resultNum = (String)session.getAttribute("resultNum");
	System.out.println("vb2 " + resultNum);
	
	int rN = Integer.parseInt(resultNum);
	System.out.println(rN);
	
	String SQL= "SELECT * FROM Flights WHERE FLIGHTNUMBER='"+rN+"'";
	DBClass d = new DBClass();
	d.connectMeIn();
	ResultSet rs = d.execute(SQL);
	
	
	rs.first();
	%>
	<%int flightnumber = rs.getInt("FLIGHTNUMBER"); %>
	<center>Flight #<b><%=flightnumber%></b></center>
	<p>Date of Flight: <%=rs.getString("DATE") %>
	<p>Departure Time:<%=rs.getString("DEPARTURE") %>
	<p>Arrival Time:<%=rs.getString("ARRIVAL") %>
	<p>Cost: $<%=rs.getDouble("COST") %>
	<p>Total Seats: <%=rs.getInt("SEATS_TOTAL") %>
	<p>Seats Taken: <%=rs.getInt("SEATS_TAKEN") %><p>


Select number of seats

					<select id='seats' name='seats'>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					</select>

<input type="hidden" name="flight" value=<%=rN%>>
<input type="hidden" name="dof" value=<%=rs.getString("date")%>>
<input type="hidden" name="seatsTotal" value=<%=rs.getInt("SEATS_TOTAL")%>>
<input type="hidden" name="seatsTaken" value=<%=rs.getInt("SEATS_Taken")%>>
<input type="hidden" name="cost" value=<%=rs.getDouble("COST")%>>
<input type="Submit" value="Select">
</form>
  <br>
 <form action="TravelPackages" method="post" target="_blank">
 <input type ="hidden" name="flightnumber" value=<%= flightnumber %>>
 <input type ="submit" value="Travel Packages">
 </form>

<p>
<input type="button" value="Back" onclick="window.history.back()" >  
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