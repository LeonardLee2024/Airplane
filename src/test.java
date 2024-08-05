import java.io.*;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import base.DijkstraA;

//import com.google.gson.Gson;

import base.Airport;
import base.Flight;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("cc test");

		// Path to your JSON file (replace with your actual path)
		String filePath = "src/args2.json"; // airplan/src/args.json

	    // Read JSON file content
	    FileReader reader = new FileReader(filePath);
	    StringBuilder contentBuilder = new StringBuilder();
	    int c;
	    while ((c = reader.read()) != -1) {
	      contentBuilder.append((char) c);
	    }
	    reader.close();

	    String jsonContent = contentBuilder.toString();

	    // Parse JSON data
	    JSONObject jsonData = new JSONObject(jsonContent);

	    // Extract airports and flights data
	    List<Airport> airports = new ArrayList<>();
	    List<Flight> flights = new ArrayList<>();

	    // Parse airports data
	    JSONObject airportData = jsonData.getJSONObject("airports");
	    for (String key : airportData.keySet()) {
	      String code = key;
	      JSONObject airportDetails = airportData.getJSONObject(key);
	      String name = airportDetails.getString("name");
	      String city = airportDetails.getString("city");

	      Airport airport = new Airport();
	      airport.code = code;
	      airport.name = name;
	      airport.city = city;
	      airports.add(airport);
	    }

	    // Parse flights data
	    JSONArray flightData = jsonData.getJSONArray("flights");
	    for (int i = 0; i < flightData.length(); i++) {
	      JSONObject flightMap = flightData.getJSONObject(i);
	      String from = flightMap.getString("from");
	      String to = flightMap.getString("to");
	      int distance = flightMap.getInt("distance"); // Handle potential casting issues
	      String duration = flightMap.getString("duration");

	      Flight flight = new Flight();
	      flight.from = from;
	      flight.to = to;
	      flight.distance = distance;
	      flight.duration = duration;
	      flights.add(flight);
	    }

	    // Print airports information
	    System.out.println("Airports:");
	    for (Airport airport : airports) {
	      System.out.println(airport.code + ": " + airport.name + " (" + airport.city + ")");
	    }

	    // Print flights information
	    System.out.println("\nFlights:");
	    for (Flight flight : flights) {
	      System.out.println(flight.from + " -> " + flight.to + " (Distance: " + flight.distance + ", Duration: " + flight.duration + ")");
	    }
	    DijkstraA obj = new DijkstraA();
	    
	    String A, F;
	    while (true) {
		    Scanner sc = new Scanner(System.in);
		    System.out.print("Enter your departure location: ");
		    A = sc.nextLine();
		    System.out.print("Enter your destination: ");
		    F = sc.nextLine();
		    if (A.equals("A") || A.equals("B") || A.equals("C") || A.equals("D") || A.equals("E") || A.equals("F")) {
		    	if (F.equals("A") || F.equals("B") || F.equals("C") || F.equals("D") || F.equals("E") || F.equals("F")) {
			    	break;
			    }
		    }
//		    Integer locations[] = {Integer.parseInt(A), Integer.parseInt(F)};
//		    if (Integer.valueOf(Integer.toHexString(locations[0])) >= 41 && Integer.valueOf(Integer.toHexString(locations[0])) <= 46) {
//		    	if (Integer.valueOf(Integer.toHexString(locations[1])) >= 41 && Integer.valueOf(Integer.toHexString(locations[1])) <= 46) {
//		    		break;
//		    	}
//		    }
	    }
	    List<String> ls = obj.dijkstra(flights, A, F);
	    
	    for (String i : ls) {
	    	System.out.println(i);
	    }
	  }
	}
