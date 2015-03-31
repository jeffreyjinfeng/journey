<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<style type="text/css">
body {background-color: rgb(255,255,153);align=center;}
</style>
</head>
<body>

<script>
 function signin()
 {
 
 var iChars = "[!#$%^&*()+=\\-\\[\\]\\\';,/{}|\":<>?]{}";
	var space=" ";
	// check for username
	var u = document.forms["myForm"]["username"].value;
if(u==""){
 alert("username Cannot Be Empty");
 return false;
}

for (var i = 0; i < u.length; i++)
{
if (iChars.indexOf(u.charAt(i)) != -1)
{
  alert ("username has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
  return false;
}
}

for (var i = 0; i < u.length; i++){
if(space.indexOf(u.charAt(i)) != -1){
	
	
 alert("White Spaces are not Allowed");
 return false;
}
}
// check for password
var p = document.forms["myForm"]["password"].value;
if(p==""){
	 alert("password Cant Be Empty");
	 return false;
	}

	for (var i = 0; i < p.length; i++)
	{
	if (iChars.indexOf(p.charAt(i)) != -1)
	{
	  alert ("password has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
	  return false;
	}
	}

	for (var i = 0; i < p.length; i++){
	if(space.indexOf(p.charAt(i)) != -1){
		
		
	 alert("White Spaces are not Allowed");
	 return false;
	}
}

}
</script>

<%
Cookie cookie = new Cookie("lastuser",
        "");
cookie.setMaxAge(5000000);
response.addCookie(cookie);
%>

<%@ page import="jinfeng.myservlets.login" %>
<%@ page import="javax.servlet.http.HttpSession" %>





<!--  Example on declarations -->
<%! 

String generateMsg()
{
	return "User name can't start with a number";
	
}
%>

<%
   
    java.util.Date date = new java.util.Date();
	
	Cookie[] cookies = request.getCookies(); 
	String lastuser = "";
	boolean userCookie = false;
	for(int i = 0; i < cookies.length; i++){
	Cookie c = cookies[i];
	
	if (c.getName().equals("lastuser")){
	lastuser = c.getValue();
	userCookie = true;
	}
}  
    
%>

<p>
<!--  The use of expressions to display the date variable processed by the above scriptlet -->
Hello!  The time is now <%= date %>

<!--  Get vs. Post
Go to http://www.w3schools.com/tags/ref_httpmethods.asp
 -->
<table>
<tr>
<td><img src="/ITIS4166HW2/images/logo.png" width="700" ></td>
<td rowspan="2">
<H3 ALIGN="CENTER">Please Login</H3>

<FORM name="myForm" ACTION="login"  onsubmit="return signin()" method="POST">
<center> Username:</center> <br><center> <INPUT TYPE="TEXT" NAME="username" VALUE=<%= lastuser%>><BR></center>
 <center>Password:</center> <br><center> <INPUT TYPE="TEXT" NAME="password"><BR></center>
 <input type="checkbox" name="checkbox" value="check" >Remember Me<br>
  <CENTER><INPUT TYPE="SUBMIT" value="Log In"></CENTER>
</FORM>
<br>
Do not have an account?
<input type="button" value="REGISTER" onclick="window.location.assign('registration.jsp')">
<p > <font size="3" color="red"><%= generateMsg() %></font>
<br>

</td>

</tr>
<tr>
<td width="700" ><img src="/ITIS4166HW2/images/plane.jpg" width="700" ></td>
</tr>
</table>




</body>
</html>