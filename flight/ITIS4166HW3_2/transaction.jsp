<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complete your transaction</title>
<%@ page import="java.util.List" %>
<%@ page import="Javas.BookedFlight" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Javas.DBClass" %>
<script src="http://code.jquery.com/jquery-latest.js">   
</script>
<script type="text/javascript">

function getData(){
	
	var aName = $("#accountName").val();
	var aNumber  = $("#accountNumber").val();
	var rNum = $("#routingNumber").val();
	var print = "<form name = \"myForm\" action= \"Print\" method=\"post\" onsubmit= \"return check()\" target=\"_blank\">" +
		  "Success!<br>" +		
		"Name:<input type=\"text\" name = \"name\"><p>" +
		"Age:<input type=\"text\" name = \"age\"><p>" +
		 "<input type=\"radio\" name=\"sex\" value=\"male\" checked> Male<br>"+
		    "<input type=\"radio\" name=\"sex\" value=\"female\"> Female<br>"+
		"<input type=\"submit\" value=\"PRINT\">"+
		"</form>";
	
	$.post("Bank", {accountName:aName, accountNumber:aNumber, routingNumber:rNum}, function(data){
		
		
		if(data == 1){
			$.post("UpdateHistory", {accountName:aName, accountNumber:aNumber, routingNumber:rNum}, function(data){
				$("#name").html("Success: Your Flights have been confirmed");
				$("#input").html(print);
				
			});
		}
		if(data == 2){
			$("#name").html("Failed: Incorrect Account Info");
		}
		if(data == 3){
			$("#name").html("Failed: Incorrect Insufficient Funds");
		}
	
	});
}

</script>
</head>

<script>
function validatebanking()
{
	var iChars = "[!@#$%^&*()+=\\-\\[\\]\\\';,./{}|\":<>?]{1}";
	var space=" ";
	
var x = document.forms["myForm"]["accountName"].value;
var y =document.forms["myForm"]["accountNumber"].value;
var z =document.forms["myForm"]["routingNumber"].value;

if(x==""){
    alert("accountName Cant Be Empty");
    return false;
}

for (var i = 0; i < x.length; i++)
{
  if (iChars.indexOf(x.charAt(i)) != -1)
  {
     alert ("File name has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
     return false;
  }
}

for (var i = 0; i < x.length; i++){
if(space.indexOf(x.charAt(i)) != -1){
	
	
    alert("White Spaces are not Allowed");
    return false;
}
}


if(y==""){
    alert("accountNumber Cant Be Empty");
    return false;
}
for (var i = 0; i < y.length; i++){
	if(space.indexOf(y.charAt(i)) != -1){
		
		
	    alert("White Spaces are not Allowed");
	    return false;
	}
	}

if(isNaN(y)){
    alert("Only Numbers Allowed");
    return false;
}



if(z==""){
    alert("accountNumber Cant Be Empty");
    return false;
}
for (var i = 0; i < z.length; i++){
	if(space.indexOf(z.charAt(i)) != -1){
		
		
	    alert("White Spaces are not Allowed");
	    return false;
	}
	}

if(isNaN(z)){
    alert("Only Numbers Allowed");
    return false;
}





}

</script>


<body>

<% 
	request.getSession();
    if (session.getAttribute("LoggedIn") == Boolean.TRUE && session.getAttribute("inOrder") == Boolean.TRUE){

%>

<center><h1>Current User:  <%=session.getAttribute("User") %></h1></center><p><p>

<b><center>Final Order</center></b><p>
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
	<p>Departure City:<%=rs.getString("SOURCE") %>
	<p>Arrival Time:<%=rs.getString("ARRIVAL") %>
	<p>Arrival City:<%=rs.getString("DESTINATION") %>
		
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
<% session.setAttribute("TC", totalCost); %>
<p><b>Final Cost: $</b><%= totalCost%>

<p><p>
<center>Enter Payment Information</center><p>

<div id ="input">
Account Name<input type="text" name="accountName" id="accountName" value = " "><p>
Account Number<input type="text" name="accountNumber" id="accountNumber" value = " "><p>
Routing Number<input type="text" name="routingNumber" id="routingNumber" value = " "><p>
</div>

<input type="button" id="BankCheck" value="Confirm" onClick="getData()"/>
<div id="name"> </div>


<input type="button" value="Cancel" onclick="window.location.assign('searchquery.jsp')" >


<form action ="Logout" method="POST" >
<br>
<input type="submit" value="Log Out"> </form>
<br><br>

<form action ="BookingHistory" method="POST" >
<input type="Submit" value="Booking History"> </form>


    <%} else { if(session.getAttribute("LoggedIn") == Boolean.FALSE){%> You are not logged in<br/>
<a href="login.jsp">Please Login</a> <% } else {%> You are not in a transaction <a href="searchquery.jsp">Home</a> <% }} %>

</body>
</html>