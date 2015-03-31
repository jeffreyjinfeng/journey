package servelets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import Javas.Users;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        
        

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uN = request.getParameter("username");
		out.println(uN);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uN = request.getParameter("username");
		String pW = request.getParameter("password");
		String eM = request.getParameter("Email");
		String dB = request.getParameter("DOB");
		System.out.println("POST METHOD");
		
		Users obj = new Users(uN, pW, eM, dB);
		
		if(obj.checkUsername() == false){
			
			
			
			out.println("Congratulations, your account has been registered <p>" +
			"<a href='http://localhost:8080/ITIS4166HW3_2/login.jsp'>Continue</a>");
			
			
		}
		
		else{
			out.println("This account name is already in use, try a new account name <p>" +
					"<a href='http://localhost:8080/ITIS4166HW3_2/registration.jsp'>Continue</a>");
		}
		
		
		
	}
	

}
