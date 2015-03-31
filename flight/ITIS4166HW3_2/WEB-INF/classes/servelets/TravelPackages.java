package servelets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Javas.DBClass;

/**
 * Servlet implementation class TravelPackages
 */
public class TravelPackages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int packageid;
       String destination;
       String description;
       double cost;
       
       int flightnumber;
       
       DBClass d;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelPackages() {
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
		d = new DBClass();
		d.connectMeIn();
		flightnumber = Integer.parseInt(request.getParameter("flightnumber"));// from the form
		String SQL1 ="SELECT * FROM Flights WHERE FLIGHTNUMBER="+flightnumber;
		ResultSet r = d.execute(SQL1);
		try{
		while (r.next())
		{
			destination = r.getString("DESTINATION");
			System.out.println(destination);//test
		}
		String SQL2= "SELECT * FROM PACKAGES WHERE DESTINATION='"+destination+"' ";// based on source, destination, select from table flight
		
		ResultSet rs = d.execute(SQL2);
		HttpSession session = request.getSession();
		session.setAttribute("pi", rs);
		
		//System.out.println("value of rs: "+ rs.next());//test
		
		//System.out.println("packageid is: "+rs.getInt("PACKAGEID"));// TEST
		response.sendRedirect("http://localhost:8080/ITIS4166HW3_2/travelpackages.jsp");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
