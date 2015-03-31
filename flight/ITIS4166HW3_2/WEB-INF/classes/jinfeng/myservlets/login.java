package jinfeng.myservlets;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Javas.BookedFlight;
import Javas.Users;


public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean checkboxchecker = false;
	public String lastusername=""; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uN = request.getParameter("username");
		String pW = request.getParameter("password");
		String cB = request.getParameter("checkbox");
		Users obj = new Users(uN, pW);
		
		System.out.println(cB);
		HttpSession session = request.getSession();
		session.setAttribute("User", obj.getUsername());
		
		
		if(obj.check() == true)
		{
			session.setAttribute("username", uN);//store userinforamtion 
			session.setAttribute("checkBox", Boolean.FALSE);
			session.setAttribute("cB", cB);
			session.setAttribute("inOrder", Boolean.FALSE);
			
		    System.out.println(cB);
		    
		  
		    if (session.getAttribute("cB") != null){
		    	checkboxchecker = true;
		    	System.out.println("Check If accessed");	    	
		    	Cookie cookie = new Cookie("lastuser", (String)session.getAttribute("username"));
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
		    	
		    	
		    }
			List<BookedFlight> bf = new ArrayList<BookedFlight>();
			session.setAttribute("bf", bf);
			session.setAttribute("LoggedIn", Boolean.TRUE );
			response.sendRedirect("http://localhost:8080/ITIS4166HW3_2/searchquery.jsp");
		  }
			
		
		else
		{
			
		
			System.out.println("Incorrect login/password combination, sending to registration page");
			out.print("<html><head><title>Incorrect Login</title></head><body>"+
					
					"Incorrect Password/Login Combination go to the following link to register<p><a href='registration.jsp'>Register</a>"
					+"<p><a href='login.jsp'>Return to Login page</a>"+
					"</body></html>");
			
			//response.sendRedirect("http://localhost:8080/ITIS4166HW2/registration.jsp");
					
		}
		
		

	}
	
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
			 
			        PrintWriter out = res.getWriter();
			        res.setContentType("text/plain");
			 
			        Enumeration<String> headerNames = req.getHeaderNames();
			 
			        while (headerNames.hasMoreElements()) {
			 
			            String headerName = headerNames.nextElement();
			            out.write(headerName);
			            out.write("\n");
			 
			            Enumeration<String> headers = req.getHeaders(headerName);
			            while (headers.hasMoreElements()) {
			                String headerValue = headers.nextElement();
			                out.write("t" + headerValue);
			                out.write("\n");
			            }
			 
			        }
			 
			        out.close();
			 
			    }	
	

}
