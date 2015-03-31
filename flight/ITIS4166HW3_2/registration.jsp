<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>

 <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>



</head>

<script>
function checkregistration()
{
	// check for username
	var iChars = "[!#$%^&*()+=\\-\\[\\]\\\';,/{}|\":<>?]{}";
	var space=" ";
	
var x = document.forms["myForm"]["username"].value;
if(x==""){
    alert("Username Cannot Be Empty");
    return false;
}

for (var i = 0; i < x.length; i++)
{
  if (iChars.indexOf(x.charAt(i)) != -1)
  {
     alert ("username has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
     return false;
  }
}

for (var i = 0; i < x.length; i++){
if(space.indexOf(x.charAt(i)) != -1){
	
	
    alert("White Spaces are not Allowed");
    return false;
}
}


//check for password
var y = document.forms["myForm"]["password"].value;
var p = document.forms["myForm"]["password2"].value;
if (y != p){
    alert("password confimation error. Please confirm your password again");
    return false;
}

if(y==""){
    alert("password Cannot Be Empty");
    return false;
}

for (var i = 0; i < y.length; i++)
{
  if (iChars.indexOf(y.charAt(i)) != -1)
  {
     alert ("password has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
     return false;
  }
}

for (var i = 0; i < y.length; i++){
if(space.indexOf(y.charAt(i)) != -1){
	
	
    alert("White Spaces are not Allowed");
    return false;
}
}

//check for email
var z = document.forms["myForm"]["email"].value;
if(z==""){
    alert("email Cant Be Empty");
    return false;
}

for (var i = 0; i < z.length; i++)
{
  if (iChars.indexOf(z.charAt(i)) != -1)
  {
     alert ("Email has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
     return false;
  }
}

for (var i = 0; i < z.length; i++){
if(space.indexOf(z.charAt(i)) != -1){
	
	
    alert("White Spaces are not Allowed");
    return false;
}
}

// check for DOB
var h = document.forms["myForm"]["DOB"].value;
if(h==""){
    alert("Date of Birth Cant Be Empty");
    return false;
}
}

</script>

<body>
<h1 align=center>Registration</h1>
<center>Please fill in the required information</center>
<center>User names and passwords must be 8-12 characters long.</center>
<FORM ACTION="Registration" name="myForm" onsubmit="return checkregistration()" method="POST">
<p>
Username: <INPUT TYPE="TEXT" NAME="username"><BR>
Password: <INPUT TYPE="password" NAME="password"><BR>
Password: <INPUT TYPE="password" NAME="password2"><BR>
Email: <INPUT TYPE="TEXT" NAME="email"><BR>

DOB:<input type="text" name="DOB" id="datepicker"><BR>
<CENTER><INPUT TYPE="SUBMIT" VALUE="Register"></CENTER>
<br>
Already have an account? 
<input type="button" value="LOGIN" onclick="window.location.assign('login.jsp')">
</FORM>

</body>
</html>