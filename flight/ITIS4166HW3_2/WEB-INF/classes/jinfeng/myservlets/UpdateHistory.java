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

/**
 * Servlet implementation class TransactionConfirmation
 */
public class UpdateHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateHistory() {
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

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
		
		String accountName = (String)request.getParameter("accountName");
		String accountNumber = (String)request.getParameter("accountNumber");
		String routingNumber = (String)request.getParameter("routingNumber");


		
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
		try {
			rs.next();
			balance = rs.getDouble("BALANCE");
		} catch (SQLException e1) {
			System.out.println("Did not get balance");
		}
		
			String count ="SELECT COUNT(*) FROM BookedFlights";
			
			int fNum = 0;
			rs = d.execute(count);
			  try {
				rs.next();
				fNum = rs.getInt(1);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			String seatUpdate;
			String details ="";
	
			List<BookedFlight> bf =(List<BookedFlight>) session.getAttribute("bf");
			
			for (Iterator<BookedFlight> iter = bf.iterator(); iter.hasNext(); ) {
				BookedFlight display = iter.next();

				seatUpdate = "UPDATE Flights" +
						" SET SEATS_TAKEN=SEATS_TAKEN+"+display.getWantedSeats()+
						" WHERE FlightNumber ="+display.getFlightNumber();
				details = details +"Flight: " +display.getFlightNumber()+ " " + display.getWantedSeats()+ " Seats \n";
				
				d.insert(seatUpdate);
			}
			
			String update = "UPDATE Accounts"
							+" SET BALANCE="+(balance-totalCost)
							+" WHERE ACCOUNTID="+accountNumber;
			d.insert(update);
		
			
			
			System.out.println(count +"   "+fNum);
			String SQL = "INSERT INTO BookedFlights(niner_id, FBNUM, DETAILS, DATE, ACCOUNT, TOTALCOST) " +
					"Values('"+niner_ID+"',"+fNum+",'"+details+"','"+dateFormat.format(date)+"','"+accountName+"',"+totalCost+")";
			d.insert(SQL);
			
		
	
			
		}
	}


