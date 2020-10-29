package week5.flight.planner;

import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;

public class FlightPlanner extends ConsoleProgram {

	//private static final String file = "flights.txt";

	public void run() {
		flightPlanner();
	}
	
	public void flightPlanner() {
		println("Welcome to Flight Planner! \n" + "Here's a list of all the cities in our database: \n"); 
		readTxtFile("flights.txt"); //readTxtFile("flights");
		printCities(cities); // prints the cities 
		println("Let's plan a round-trip route!\n");
		String start = readLine("Enter the starting city: ");
		ArrayList<String> route = new ArrayList<String>(); // New 'route' list to add each city to
		route.add(start); // starting city is added to the ArrayList "route"
		String currentCity = start; 
		while (true) {
			String nextCity = getNextCity(currentCity);
			route.add(nextCity);
			if (nextCity.equals(start)) // if the next city in the list is the same as input, the loops stop
				break;
			currentCity = nextCity;
		}
		printRoute(route);
	}
		
	public void theRoute(String destination) { 
		String start = readLine("Enter the starting city: ");
		ArrayList<String> route = new ArrayList<String>(); // New 'route' list to add each city to
		route.add(start); // starting city is added to the ArrayList "route"
		String currentCity = start; 
		
		while (true) {
			String nextCity = getNextCity(currentCity);
			route.add(nextCity);
			if (nextCity.equals(start)) // if the next city in the list is the same as input, the loops stop
				break;
			currentCity = nextCity;
		}
		printRoute(route);
	}
	
	
	// Loops through array to get the indexed value of the cities
	private void printCities(ArrayList<String> cityList) {
		for (int i = 0; i < cityList.size(); i++) {
			String city = cityList.get(i);
			println(" " + city);
		}
	}

	// BR to read the file and display content
	private void readTxtFile(String fileFlights) {
		flights = new HashMap<String, ArrayList<String>>();
		cities = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileFlights));
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
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
	
	// Receives user input to go to the next city
	public String getNextCity(String city) {
		ArrayList<String> fList = getDestinations(city);
		String nextCity = null;
		while (true) {
			println("\nFrom " + city + " you can fly directly to: ");
			printCities(fList);
			nextCity = readLine("Where do you want to go from " + city + "? ");
			if (fList.contains(nextCity)) // checks if the list has the user's input
				break;
				println("\nYou can't get to" + nextCity + " by a direct flight. Please select a different flight.");
		}
		return nextCity;
	}
	
	// Return values of the cities with the arrow added to each city en route 
	private void printRoute(ArrayList<String> route) {
		println("The route you've chosen is: ");
		for (int i = 0; i < route.size() - 1; i++) {
			if (i > 0)
			print(route.get(i) + " -> ");
		}
		print(route.get(route.size() - 1));
		println();
	}	
	
	// Method to break 'fromCity' -> 'toCity' and places it into Hash Map & ArrayList
	private void readFlightEntry(String line) {
		if (!line.contains("->")) {
			throw new ErrorException("Invalid data entered for flight and destination");
		}
		
		String[] array = line.split("->");
		
		String fromCity = array[0].trim(); // .trim rids trailing/leading spaces
		String toCity = array[1].trim(); 
		
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
	private static ArrayList<String> cities;
	
}
