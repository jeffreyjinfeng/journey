package jinfeng.myservlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Javas.BookedFlight;
import Javas.DBClass;


public class Bank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
		
		String accountName = (String)request.getParameter("accountName");
		String accountNumber = (String)request.getParameter("accountNumber");
		String routingNumber = (String)request.getParameter("routingNumber");
		Boolean success = true;
		String error = "";
		Boolean errorAccount = false;
		Boolean errorBalance = false;
		
		
		HttpSession session = request.getSession();
		session.setAttribute("account", accountName);
		String niner_ID = (String)session.getAttribute("username");
		Double totalCost = (Double)session.getAttribute("TC");
		
		System.out.println("TOTAL  COST: "+totalCost);
		
		DBClass d = new DBClass();
		d.connectMeIn();
		
		String verifyAccount ="SELECT * FROM Accounts "+
		"WHERE ACCOUNTID ='"+accountNumber+"' and "+
		"HOLDERNAME ='"+accountName+"' and "+
		"ROUTINGNUMBER ="+routingNumber;
			
		ResultSet rs = d.execute(verifyAccount);
		
		double balance = 0;
		
		try{
			
			if(!rs.next()){
				System.out.println("Incorrect Account Data");
				success = false;
				errorAccount = true;
			}else{
				balance = rs.getDouble("BALANCE");
				System.out.println("The balance of account is: "+balance);
				if(balance < totalCost){
					success = false;
					errorBalance = true;
				}
			}
		}catch (SQLException e) {
			
			e.printStackTrace();
		}		
	
		    
			error = "Success: Your Purchase has been confirmed";
			
			
			
			
			if(errorAccount == true){
				error = "Failed: Incorrect Account Information";
				

			}
			if(errorBalance == true){
				error = "Failed: You do not have enough funds";
				
				
			}
			response.getWriter().write(error);
			
			session.setAttribute("Status", error);
			List<BookedFlight> bf =(List<BookedFlight>) session.getAttribute("bf");
			
		    
		
		    
				
			
	}*/

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	/*	System.out.println("J");
		String accountName = (String)request.getParameter("accountName");
		String accountNumber = (String)request.getParameter("accountNumber");
		String routingNumber = (String)request.getParameter("routingNumber");
		
		response.setContentType("text/HTML");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(accountName +' '+ accountNumber +' '+routingNumber); */
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
		
		String accountName = (String)request.getParameter("accountName");
		String accountNumber = (String)request.getParameter("accountNumber");
		String routingNumber = (String)request.getParameter("routingNumber");
		Boolean success = true;
		String error = "";
		Boolean errorAccount = false;
		Boolean errorBalance = false;
		
		
		HttpSession session = request.getSession();
		session.setAttribute("account", accountName);
		String niner_ID = (String)session.getAttribute("username");
		Double totalCost = (Double)session.getAttribute("TC");
		
		System.out.println("TOTAL  COST: "+totalCost);
		
		DBClass d = new DBClass();
		d.connectMeIn();
		
		String verifyAccount ="SELECT * FROM Accounts "+
		"WHERE ACCOUNTID ='"+accountNumber+"' and "+
		"HOLDERNAME ='"+accountName+"' and "+
		"ROUTINGNUMBER ="+routingNumber;
			
		ResultSet rs = d.execute(verifyAccount);
		
		double balance = 0;
		
		try{
			
			if(!rs.next()){
				System.out.println("Incorrect Account Data");
				success = false;
				errorAccount = true;
			}else{
				balance = rs.getDouble("BALANCE");
				System.out.println("The balance of account is: "+balance);
				if(balance < totalCost){
					success = false;
					errorBalance = true;
				}
			}
		}catch (SQLException e) {
			
			e.printStackTrace();
		}		
	
		    
			error = "1";
			
			if(errorAccount == true){
				error = "2";
				

			}
			if(errorBalance == true){
				error = "3";
				
				
			}
			response.getWriter().write(error);
			
			session.setAttribute("Status", error);
			List<BookedFlight> bf =(List<BookedFlight>) session.getAttribute("bf");
		
		
	}
}


