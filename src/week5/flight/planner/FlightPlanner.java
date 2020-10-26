package week5.flight.planner;

import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;

public class FlightPlanner extends ConsoleProgram {

	//private static final String file = "flights.txt";

	public void run() {
		println("Welcome to the Flight Planner!");
		readTxtFile("flights");
		readTxtFile("flights.txt");
		println("Here's a list of all the cities in our database: ");
		printCities(cities);
		
		println("Let's plan a round-trip route!");
		
	//	System.out.println();

	}

	private void printCities(ArrayList<String> cityList) {
		for (int i = 0; i < cityList.size(); i++) {
			String city = cityList.get(i);
			println(" " + city);

		}
	}

	private void readTxtFile(String file) {
		flights = new HashMap<String, ArrayList<String>>();
		cities = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				if (line.length() != 0) {
					readFlightEntry(line);
				}
			}
			
			br.close();
		} catch (IOException ex) {
			System.out.println("You broke it.");
			throw new ErrorException(ex);
			
		}

	}
	
	
	// Method to break 'fromCity' -> 'toCity' and places it into Hash Map & ArrayList
	
	private void readFlightEntry(String line) {
		int arrow = line.indexOf("->");
		
		if (arrow == -1) {
			throw new ErrorException("You've entered the wrong flight: " + line);
		}
		
		String fromCity = line.substring(0, arrow).trim(); // .trim rids trailing/leading spaces
		String toCity = line.substring(arrow + 2).trim(); 
		
		// Call 'defineCity' to populate the entered Flight 
		// Adds into the HashMap(flights) and Array(cities)
		defineCity(fromCity);
		defineCity(toCity);
		
		getDestinations(fromCity).add(toCity);				
	}
	
	
	
	
	
	// Returns the fromCity from the HashMap
	private ArrayList<String> getDestinations(String fromCity) {
		return flights.get(fromCity);
	}

	private void defineCity(String cityName) {
		if (!cities.contains(cityName)) {
			cities.add(cityName);
			flights.put(cityName, new ArrayList<String>());
		}

	}

	// iVars
	private HashMap<String, ArrayList<String>> flights;
	private ArrayList<String> cities;
}
