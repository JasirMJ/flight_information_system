package edu.kit.informatik;

/**
 * This class implements strukture of vertex.
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 1.0
 */
public class Vertex {
    private static int counter = 0;
    private final int id;
    private final String name;
    private final String iata;

    /**
     * Creates new vertex
     * 
     * @param name
     *            full name of airport
     * @param iata
     *            short name of airport
     */
    public Vertex(final String name, final String iata) {
        this.name = name;
        this.iata = iata;
        this.id = counter++;
    }

    /**
     * Return id of the airport.
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns full name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns iata.
     * 
     * @return the iata
     */
    public String getIata() {
        return iata;
    }

    @Override
    public String toString() {

        return String.format("%d:%s:%s", this.id, this.iata, this.name);
    }

}
