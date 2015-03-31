package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Javas.BookedFlight;
import Javas.DBClass;

/**
 * Servlet implementation class TravelPackagesServlet
 */
public class TravelPackagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int packageid;
       String destination;
       String description;
       double cost;
       DBClass d;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelPackagesServlet() {
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
		packageid = Integer.parseInt(request.getParameter("packageid"));
		HttpSession session = request.getSession();
		session.setAttribute("packageid", packageid);
		try{
		//add the package to the shopping cart arraylist
		d = new DBClass();
		d.connectMeIn();
		String SQL ="SELECT * FROM PACKAGES WHERE PACKAGEID='"+packageid+"' ";
		ResultSet rs = d.execute(SQL);
		while (rs.next()){
		destination = rs.getString("DESTINATION");
		description = rs.getString("DESCRIPTION");
		cost = rs.getDouble("COST");
		}
		
		List<BookedFlight> bf =(List<BookedFlight>) session.getAttribute("bf");
		BookedFlight pk = new BookedFlight(destination, description, cost, packageid);
		bf.add(pk);
		session.setAttribute("bf", bf);
		
		PrintWriter out = response.getWriter();
		System.out.println("booking packages successfully");
		out.println("Book package successfully!/n Please keep booking your flight!");
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
