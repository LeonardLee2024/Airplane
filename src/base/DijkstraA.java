package base;
import java.util.*;


public class DijkstraA {
	public static List<String> dijkstra(List<Flight> flights, String source, String destination) {

	    // Create a map to store distances from source to each node
	    Map<String, Integer> distances = new HashMap<>();

	    // Create a set to store processed nodes
	    Set<String> processed = new HashSet<>();

	    // Initialize distances for all nodes as infinity (except source)
	    for (Flight flight : flights) {
	        distances.put(flight.from, Integer.MAX_VALUE);
	        distances.put(flight.to, Integer.MAX_VALUE);
	    }
	    distances.put(source, 0);

	    // Use a priority queue to prioritize unprocessed nodes with shortest tentative distances
	    PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.distance - b.distance);
	    queue.add(new Node(source, 0));

	    while (!queue.isEmpty()) {
	        Node current = queue.poll();
	        processed.add(current.city);

	        // Relax edges (update distances if shorter path found)
	        for (Flight flight : flights) {
	            if (flight.from.equals(current.city) && !processed.contains(flight.to)) {
	                int newDistance = distances.get(current.city) + flight.distance;
	                if (newDistance < distances.get(flight.to)) {
	                    distances.put(flight.to, newDistance);
	                    queue.add(new Node(flight.to, newDistance));
	                }
	            }
	        }
	    }

	    // Reconstruct path if destination is reachable
	    if (distances.get(destination) == Integer.MAX_VALUE) {
	        return null; // Destination unreachable
	    }

	    List<String> path = new ArrayList<>();
	    path.add(destination);
	    String currentCity = destination;
	    while (!currentCity.equals(source)) {
	        for (Flight flight : flights) {
	            if (flight.to.equals(currentCity) && distances.get(currentCity) - flight.distance == distances.get(flight.from)) {
	                path.add(flight.from);
	                currentCity = flight.from;
	                break;
	            }
	        }
	    }

	    // Reverse path to get order from source to destination
	    Collections.reverse(path);
	    return path;
	}
}
