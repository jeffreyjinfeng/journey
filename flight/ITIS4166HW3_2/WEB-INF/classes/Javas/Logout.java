package Javas;

import java.sql.*;
import java.util.List;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import Javas.Users;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        
        

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uN = request.getParameter("username");
		out.println(uN);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		
		session.getAttribute("bf");
		List<BookedFlight> bf =(List<BookedFlight>) session.getAttribute("bf");
		bf.clear();
		
		if((Boolean)session.getAttribute("checkBox") == true){ 
			    	 
					System.out.println("Cookie Made");
					String lastuser = (String)session.getAttribute("lastuser");
					Cookie cookie = new Cookie("lastuser", lastuser);
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
		}		
			
				
		
		session.setAttribute("LoggedIn", Boolean.FALSE);
		session.invalidate();
		response.sendRedirect("http://localhost:8080/ITIS4166HW3_2/login.jsp");
		
		
		
	}
	

}
