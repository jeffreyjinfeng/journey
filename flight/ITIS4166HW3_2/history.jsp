<%@ page import="java.sql.ResultSet" %>
<%@ page import="Javas.DBClass" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking History</title>
</head>
<body>
<% 
	
    if (session.getAttribute("LoggedIn") == Boolean.TRUE) {
    	request.getSession();
%>


<center><h1>Booking History</h1></center>
<table border='1'>
<tr><th>User </th> 
<th>OrderNumber</th>
<th>Details</th>
<th>Date Purchased</th>
<th>Payment Account</th>
<th>Total Cost</th>

<%
	DBClass d = new DBClass();
	d.connectMeIn();
	String SQL = (String)session.getAttribute("bh");
	ResultSet rs = d.execute(SQL);
	
	rs.beforeFirst();
	while (rs.next()){
	String userID = rs.getString("niner_ID");
	int FBNUM = rs.getInt("FBNUM");
	String details = rs.getString("DETAILS");
	String date = rs.getString("DATE");
	String account = rs.getString("ACCOUNT");
	double totalCost = rs.getDouble("TOTALCOST"); 

	
%><tr>
<td><%=userID %></td>
<td><%=FBNUM %></td>
<td><%=details %></td> 
<td><%=date %></td> 
<td><%=account %></td> 
<td>$<%=totalCost %></td> 

</tr>
<% } %></table>
<p>
<input type="button" value="Home" onclick="window.location.assign('searchquery.jsp')" >






<% } else { %> You are not logged in<br/>
<a href="login.jsp">Please Login</a> <% } %>


</body>
</html>