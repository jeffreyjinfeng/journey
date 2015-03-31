<!-- Flight Search Query jsp page 
o The page contains a set of fields an user can fill before clicking the search button. 
The fields are for: the source, destination, date of travel, number of seats and 
class. The class field shall be a drop down box with the following values: 
(economy, business, first class). The rest are text fields. 
o The page must have a search button which when clicked must lead to the Flight 
Search results page. 
o The page should have a Booking History button that leads to the Booking history 
page -->


<%@page import="Javas.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search for a flight</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>


<script>
 function checking()
 {
 
 var iChars = "[!#$%^&*()+=\\-\\[\\]\\\';,/{}|\":<>?]{}";
	var space=" ";
	// check for source
	var u = document.forms["myForm"]["source"].value;
if(u==""){
 alert("source Cannot Be Empty");
 return false;
}

for (var i = 0; i < u.length; i++)
{
if (iChars.indexOf(u.charAt(i)) != -1)
{
  alert ("source has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
  return false;
}
}

for (var i = 0; i < u.length; i++){
if(space.indexOf(u.charAt(i)) != -1){
	
	
 alert("White Spaces are not Allowed");
 return false;
}
}
// check for destiniation
var p = document.forms["myForm"]["destination"].value;
if(p==""){
	 alert("destination Cant Be Empty");
	 return false;
	}

	for (var i = 0; i < p.length; i++)
	{
	if (iChars.indexOf(p.charAt(i)) != -1)
	{
	  alert ("destination has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
	  return false;
	}
	}

	for (var i = 0; i < p.length; i++){
	if(space.indexOf(p.charAt(i)) != -1){
		
		
	 alert("White Spaces are not Allowed");
	 return false;
	}
}
	
	var d = document.forms["myForm"]["date"].value;
	if(d==""){
		 alert("Date Cant Be Empty");
		 return false;
		}

}
</script>

</head>
<body>

<% 
	
    if (session.getAttribute("LoggedIn") == Boolean.TRUE) {
    	request.getSession();
%>


<center><h1>Current User:  <%=session.getAttribute("User") %></h1></center>
<p>
<p>
<p>
<h1 align=center>SEARCH FLIGHT</h1>
<form name="myForm" action="SearchQuery" onsubmit="return checking()" method="POST">
<label>Source:  <input type="text" name="source"></label><br>
<label>Destination: <input type="text" name="destination"></label><br>
<label>Date:<input type="text" name="date" id="datepicker"></label><br>
<p>
<br>
<input type="Submit" value="Search"  >
</form>

<form action ="Logout" method="POST" >
<br>
<input type="submit" value="Log Out">
<br><br><br>

</form>


<br><br><br>
<form action ="BookingHistory" method="POST" >
<input type="Submit" value="Booking History"> </form>
<% } else { %> You are not logged in<br/>
<a href="login.jsp">Please Login</a> <% } %>


</body>
</html>






