<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.ResultSet" %>
    <%@ page import="Javas.DBClass" %>
    <%@ page import="java.util.List" %>
    <%@ page import="Javas.BookedFlight" %>
    <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<script>
function myFunction()
{
window.print();
}
</script>
<body>

<button onclick="myFunction()">Print this page</button>

<br>


<jsp:useBean  id="key" class="Javas.Bean" scope="request"/>
Name:<jsp:getProperty name="key" property="name" /> <br>

Age:  <jsp:getProperty name="key" property="age" /><br>

Sex: <jsp:getProperty name="key" property="sex" /><br>



<%
	
	
	session.getAttribute("bf");
	List<BookedFlight> bf =(List<BookedFlight>) session.getAttribute("bf");
	int listSize = bf.size();
	double totalCost = 0;
	
	
	DBClass d = new DBClass();
	d.connectMeIn();
	String SQL = null;
	ResultSet rs = null;

for(int index = 0; index < listSize;index++ ){ BookedFlight display = bf.get(index);%>
	
	<%if (display.getFlightNumber() != 0){ %>
		
		<p>
		Flight# <%= display.getFlightNumber()%> x<%= display.getWantedSeats()%> Seats<p>
		Cost: $<%= display.getCost()%><p>
		<%
		SQL= "SELECT * FROM Flights WHERE FLIGHTNUMBER='"+display.getFlightNumber()+"'";
		rs = d.execute(SQL);
		rs.first();
		%>
		
		<center>Flight #<b><%=rs.getInt("FLIGHTNUMBER") %></b></center>
	<p>Date of Flight: <%=rs.getString("DATE") %>
	<p>Departure Time:<%=rs.getString("DEPARTURE") %>
	<p>Departure Time:<%=rs.getString("SOURCE") %>
	<p>Arrival Time:<%=rs.getString("ARRIVAL") %>
	<p>Departure Time:<%=rs.getString("DESTINATION") %>
		
		<% totalCost = totalCost + display.getCost();%>
		------- 	
		<%} else { %>
		<br>
		<p>
		Package#: <%=display.getPackageid() %> <br> 
		Description: <%=display.getDescription() %><br>
		Destination: <%= display.getDestination() %><br>
		Cost: $<%= display.getCost()%><p>
		
		
		<% totalCost = totalCost + display.getCost();%>
		-------
		
		
		
		
		
		
		<%} %>
<%
	}	
%>

</body>
</html>