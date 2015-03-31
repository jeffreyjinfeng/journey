package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Javas.DBClass;


public class SearchQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String source;
    String destination;
    String date;
    String nseat;
    String flightclass;
    DBClass d;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get five items above from html form
		source = request.getParameter("source");
		destination = request.getParameter("destination");
		date = request.getParameter("date");
		nseat = request.getParameter("nseat");
		
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//get flight items with SQL query -> a new table
		String SQL= "SELECT * FROM Flights WHERE SOURCE='"+source+"' AND DESTINATION='"+destination+"' AND DATE='"+date+"'";// based on source, destination, select from table flight
		d = new DBClass();
		d.connectMeIn();
		ResultSet rs = d.execute(SQL);// store data required into a resultset rs
		
		request.setAttribute("rs", rs);
		session.setAttribute("date", date);
	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/searchresult.jsp");
	    
		//test
		try{
			while (rs.next())
		{
			int flightnumber = rs.getInt("FLIGHTNUMBER");
			System.out.println("FLIGHTNUMBER:  "+flightnumber);
		}
		}catch( SQLException e){
			e.printStackTrace();
		}catch( NullPointerException n){
			System.out.println("Nothing in database");
			
		}		
		
		rd.forward(request, response);

	}

}

