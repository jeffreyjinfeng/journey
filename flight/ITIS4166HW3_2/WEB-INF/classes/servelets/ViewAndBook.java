package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Javas.BookedFlight;

/**
 * Servlet implementation class viewandbook
 */
public class ViewAndBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAndBook() {
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String flightNumber = (String)session.getAttribute("resultNum");
		String date = request.getParameter("dof");
		String wantedSeats = request.getParameter("seats");
		String cost = request.getParameter("cost");
		String takenSeats = request.getParameter("seatsTaken");
		String totalSeats = request.getParameter("seatsTotal");
		
		
		Double cS = Double.parseDouble(cost);
		int wS = Integer.parseInt(wantedSeats);
		int kS = Integer.parseInt(takenSeats);
		int tS = Integer.parseInt(totalSeats);
		int fN = Integer.parseInt(flightNumber);
		
		double costFinal = cS * wS;
		
		
		session.setAttribute("fN", flightNumber);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/transaction.jsp");
		
		if(wS > (tS - kS)){
			
			out.println("<html><head><title>Seat Request Error</title></head><body>" +
					"You have requested more seats than what is available. Click the following link to search for another flight.<p>" +
					"<a href='searchquery.jsp'>Continue</a></body></html>"
					
			);
			
		}else{
			out.println("<html><head><title>Flight Added</title></head><body>" +
					"Your Flight has been added to your order" +
					"<p><input type='button' value='Add More Flights' onclick=window.location.assign('searchquery.jsp') >"+
					"<p><input type='button' value='Complete Order' onclick=window.location.assign('transaction.jsp') >"
			);
			session.setAttribute("inOrder", Boolean.TRUE);
			List<BookedFlight> bf =(List<BookedFlight>) session.getAttribute("bf");
			BookedFlight newBF = new BookedFlight(fN, wS, costFinal);
			bf.add(newBF);
			session.setAttribute("bf", bf);
		}
		
		
	}

}
