package edu.kit.informatik;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class implement graph strukture,which comprises {@link Vertex} vertices.
 * Distance and adjacency Matrices implements edges and value (distance) of
 * edges.
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 2.0
 */
public class Graph {
	private final int size;
	// vertex,id vertex collection
	private Map<Vertex, Integer> vertices;
	private int[][] adjacencyMatrix;
	private long[][] distanceMatrix;

	/**
	 * Construktor initializes adjacency and distance Matrix with the fixed
	 * size(count of vertices).
	 * 
	 * @param size -count of vertices(airports).
	 */
	public Graph(final int size) {
		this.size = size;
		this.vertices = new HashMap<>();
		this.adjacencyMatrix = new int[this.size][this.size];
		this.distanceMatrix = new long[this.size][this.size];
		// set infinity value (Integer.MAX_VALUE)
		initDistanceMatrix();
	}

	/**
	 * Add new vertices {@link Vertex} to the strukture(matrices).
	 * 
	 * @param vertexStart  start airport(iata).
	 * @param vertexTarget arrival airport(iata).
	 * @param distance     between the airports.Can not be negativ or 0.
	 */
	public void addToGraph(final Vertex vertexStart, final Vertex vertexTarget, final int distance) {
		addToAdjacencyMatrix(vertexStart, vertexTarget);
		addToDistanceMatrix(vertexStart, vertexTarget, distance);
	}

	/**
	 * Search and return the vertex through iata from storage of vertices .
	 * 
	 * @param iata international air transport association, short name of the
	 *             airport.
	 * @return null if not found, else the vertex with aforecited iata name.
	 * @throws NullPointerException vertex was not found.
	 * 
	 */
	public Vertex getVertexFromMap(final String iata) throws NullPointerException {
		if (iata.equals("FRA")) {
			System.out.println("FRA");
		}

		Collection<Vertex> vertices = this.vertices.keySet();
		Iterator<Vertex> iterator = vertices.iterator();
		while (iterator.hasNext()) {
			Vertex temp = iterator.next();
			if (temp.getIata().equals(iata)) {
				return temp;
			}
		}
		return null;

	}

	/**
	 * This method adds vertex to the storage.
	 * 
	 * @param vertex to be added.
	 */
	public void addToMap(final Vertex vertex) {
		this.vertices.put(vertex, vertex.getId());

	}

	/**
	 * 
	 * @param iata1 dasd
	 * @param iata2 adas
	 * @return dasd
	 */
	public int isConnected(final String iata1, final String iata2) {
		int idFirstAirport = getIndex(iata1);
		int idSecondAirport = getIndex(iata2);
		if (idFirstAirport == -1 || idSecondAirport == -1) {
			return -1;
		}

		return this.adjacencyMatrix[idFirstAirport][idSecondAirport];
	}

	/**
	 * sada
	 * 
	 * @param iata1 international air transport association, short name of the first
	 *              airport.
	 * @param iata2 international air transport association, short name of the
	 *              second airport.
	 * @return 0 if airports were not found or iata was false, else distance between
	 *         two airports
	 */
	public long getDistance(final String iata1, final String iata2) {
		int idFirstAirport = getIndex(iata1);
		int idSecondAirport = getIndex(iata2);
		// check airport name(iata)
		if (idFirstAirport == -1 || idSecondAirport == -1) {
			return 0;
		}
		return this.distanceMatrix[idFirstAirport][idSecondAirport];
	}

	/**
	 * This method return full name of the airport through short iata name.
	 * 
	 * @param iata international air transport association, short name of the
	 *             airport.
	 * @return full name of the airport, if not found or iata false return null.
	 * @throws NullPointerException if not found(airport) or iata false.
	 */
	public String getName(final String iata) throws NullPointerException {
		Iterator<Vertex> iterator = vertices.keySet().iterator();
		Vertex tempVertex = null;
		while (iterator.hasNext()) {
			tempVertex = iterator.next();
			if (tempVertex.getIata().equals(iata)) {
				return tempVertex.getName();
			}

		}
		return null;
	}

	/**
	 * This method calcs the shortest way from one airport to the second.
	 * 
	 * @param iata1 international air transport association, short name of the first
	 *              airport.
	 * @param iata2 international air transport association, short name of the
	 *              second airport.
	 * @return the shorest distance,else -1 if the iata was false.
	 */
	public long getShortest(final String iata1, final String iata2) {
		long[][] shortestMatrix = GraphAlgorithm.findShortestPath(this.distanceMatrix);
		int idFirstAirport = getIndex(iata1);
		int idSecondAirport = getIndex(iata2);
		if (idFirstAirport == -1 || idSecondAirport == -1) {
			return -1;
		}
		return shortestMatrix[idFirstAirport][idSecondAirport];
	}

	/**
	 * 
	 * @param iata1 international air transport association, short name of the
	 *              airport.
	 * @param iata2 international air transport association, short name of the
	 *              second airport.
	 * @param stops the number of stops between the start and target aiports.
	 * @return the number of routes with the said over number of stops, else -1 if
	 *         the input was false.
	 */
	public int getRoutes(final String iata1, final String iata2, final int stops) {
		int[][] adjMatrixPow = GraphAlgorithm.matrixPow(this.adjacencyMatrix, stops);
		int idFirstAirport = getIndex(iata1);
		int idSecondAirport = getIndex(iata2);
		if (idFirstAirport == -1 || idSecondAirport == -1 || adjMatrixPow == null) {
			return -1;
		}
		return adjMatrixPow[idFirstAirport][idSecondAirport];
	}

	/**
	 * Returns adjacency matrix.
	 * 
	 * @return the adjacency matrix
	 */
	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	/**
	 * Returns distance matrix.
	 * 
	 * @return the distance matrix
	 */
	public long[][] getDistanceMatrix() {
		return distanceMatrix;
	}

	/**
	 * Getter return vertices.
	 * 
	 * @return the vertices.
	 */
	public Map<Vertex, Integer> getVertices() {
		return vertices;
	}

	/**
	 * Return the number of vertices(airports).
	 * 
	 * @return the size of the matrix
	 */
	public int getSize() {
		return size;
	}

	// -1 if not found
	private int getIndex(final String iata) {
		Iterator<Vertex> iterator = vertices.keySet().iterator();
		Vertex tempVertex = null;
		while (iterator.hasNext()) {
			tempVertex = iterator.next();
			if (tempVertex.getIata().equals(iata)) {
				return tempVertex.getId();
			}

		}
		return -1;
	}

	// if connected 1
	private void addToAdjacencyMatrix(final Vertex vertexStart, final Vertex vertexTarget) {
		int start = vertexStart.getId();
		int target = vertexTarget.getId();

		this.adjacencyMatrix[start][target] = 1;
	}

	// if connected distance value else inf
	private void addToDistanceMatrix(final Vertex vertexStart, final Vertex vertexTarget, final int distance) {
		int start = vertexStart.getId();
		int target = vertexTarget.getId();
		this.distanceMatrix[start][target] = distance;
	}

	// init inf value
	private void initDistanceMatrix() {
		for (int oY = 0; oY < distanceMatrix.length; oY++) {
			for (int oX = 0; oX < distanceMatrix.length; oX++) {
				this.distanceMatrix[oY][oX] = Integer.MAX_VALUE;
			}
		}
	}

	// 0 distance = inf
	@Override
	public String toString() {
		String tempAdjacency = "";
		String tempDistance = "";
		for (int oY = 0; oY < adjacencyMatrix.length; oY++) {
			tempAdjacency += "\n";
			tempDistance += "\n";
			for (int oX = 0; oX < adjacencyMatrix.length; oX++) {

				tempAdjacency += " " + this.adjacencyMatrix[oY][oX];
				tempDistance += " " + this.distanceMatrix[oY][oX];
			}
		}
		return tempAdjacency + "\n" + tempDistance;
	}
	
    private void emptySynchronized() {
        synchronized (this) {
            // Forgot implementation
        }
    }
 

    private void sleepInSynchronized() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(5000);
        }

}
