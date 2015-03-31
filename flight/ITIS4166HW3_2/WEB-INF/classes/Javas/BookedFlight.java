package Javas;



public class BookedFlight {
	int flightNumber;
	int wantedSeats;
	double cost;
	int packageid;
	
	String destination;
	String description;
	
	
	//constructor of fllight
	public BookedFlight(int fN, int wS, double c){
		
		flightNumber = fN;
		cost = c;
		wantedSeats = wS;
		}
	
	// constructor of package
public BookedFlight(String dest, String desc, double c, int pi){
		destination = dest;
		description = desc;
		cost = c;
		packageid = pi;
		}
	
	public int getFlightNumber(){
		return flightNumber;
	}
	public int getWantedSeats(){
		return wantedSeats;
	}
	
	public double getCost(){
		return cost;
	}
	
	public int getPackageid(){
		return packageid;
	}

	public String getDestination(){
		return destination;
	}
	
	public String getDescription(){
		return description;
	}
}