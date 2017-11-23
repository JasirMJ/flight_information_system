package edu.kit.informatik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class implements reading(line by line) from the input file.(Input part)
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 1.0
 */
public final class Reader {
    private static int vertexCnt = 0;
    // by the reading of first area,the vertices comes here temporally
    private static ArrayList<Vertex> vertices = new ArrayList<>();
    private static Graph graph;

    // Private constructor to avoid object generation.
    private Reader() {

    }

    /**
     * This is main read method.
     * 
     * @param args
     *            command line argument
     * @throws IOException
     *             i/o exceptions by reading
     * @throws IllegalArgumentException
     *             wrong format
     */
    public static void readCla(final String[] args) throws IOException, IllegalArgumentException {
        // check path to file
        checkClaLength(args.length);
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        String line = reader.readLine();

        while (line != null) {

            if (line.split(";").length == 2 && !line.equals("--")) {
                checkLineFirstArea(line);
                // count the number of vertices
                vertexCnt++;

                handleFirstArea(line);
            } else if (line.equals("--")) {

                // create graph with new size and add all vertices from first area
                addToGraph();

            }

            else if (line.split(";").length == 3 && !line.equals("--")) {
                checkLineSecondArea(line);
                handleSecondArea(line, graph);
            } else {
                throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PARSING);
            }

            line = reader.readLine();
        }
        reader.close();
    }

    /**
     * Returns graph.
     * 
     * @return graph
     */
    public static Graph getGraph() {
        return graph;
    }

    private static void checkClaLength(final int size) throws IllegalArgumentException {
        if (size != 1) {
            throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PATH + "(size:" + size + ")");
        }
    }

    private static void checkLineFirstArea(final String line) throws IllegalArgumentException {
        String[] splittedLine = line.split(";");
        String errMsg = "";
        if (splittedLine.length != 2) {
            errMsg = "(size:" + splittedLine.length + ")";
            throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PARSING + errMsg);
        }
        // this is my precondition, in this area graph can be created,it will be created ONLY after "--"!
        if (graph != null) {
            throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PARSING);
        }
        boolean checkIATA = splittedLine[0].matches("[A-Z]{3}");

        boolean checkName = splittedLine[1].matches("[a-zA-Z_-]+");
        if (!checkIATA) {
            errMsg = "(" + splittedLine[0] + ", but expected [A-Z]{3}!)";
        }
        if (!checkName) {
            errMsg = "(" + splittedLine[1] + ", but expected [a-zA-Z_-]+!)";
        }

        if (!(checkIATA && checkName)) {
            throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PARSING + errMsg);
        }
    }

    private static void checkLineSecondArea(final String line) throws IllegalArgumentException, NumberFormatException {
        String[] splittedLine = line.split(";");
        String errMsg = "";
        if (splittedLine.length != 3) {
            errMsg = "(size:" + splittedLine.length + ")";
            throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PARSING + errMsg);
        }
        // this is my precondition,in this area graph can not be null,because in this area it will be filled with edges
        if (graph == null) {
            throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PARSING);
        }
        boolean checkFirstAirport = splittedLine[0].matches("[A-Z]{3}");
        boolean checkSecondAirport = splittedLine[1].matches("[A-Z]{3}");
        boolean checkEqualityAirports = !splittedLine[0].equals(splittedLine[1]);
        if (!checkFirstAirport) {
            errMsg = "(" + splittedLine[0] + ", but expected [A-Z]{3})";
        }
        if (!checkSecondAirport) {
            errMsg = "(" + splittedLine[1] + ", but expected [A-Z]{3})";
        }
        if (!checkEqualityAirports) {
            errMsg = "(" + splittedLine[0] + " and " + splittedLine[1] + " are equals)";
        }

        if (!(checkSecondAirport && checkFirstAirport && checkEqualityAirports)) {
            throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PARSING + errMsg);
        }

        int distance = Integer.parseInt(splittedLine[2]);
        if (distance <= 0) {
            errMsg = "(" + distance + " is negativ!)";
            throw new IllegalArgumentException(Messages.ERR_MSG_CLA_PARSING + errMsg);
        }

    }

    private static void handleFirstArea(final String line) {
        String[] splittedLine = line.split(";");
        String iata = splittedLine[0];
        String airportName = splittedLine[1];
        // create new vertices
        vertices.add(new Vertex(airportName, iata));

    }

    private static void handleSecondArea(final String line, final Graph graph) {
        String[] splittedLine = line.split(";");

        Vertex firstAirport = graph.getVertexFromMap(splittedLine[0]);
        Vertex secondAirport = graph.getVertexFromMap(splittedLine[1]);

        int distance = Integer.parseInt(splittedLine[2]);
        // add to the strukture of graph

        graph.addToGraph(firstAirport, secondAirport, distance);

    }

    private static void addToGraph() {
        graph = new Graph(vertexCnt);
        for (int i = 0; i < vertices.size(); i++) {

            graph.addToMap(vertices.get(i));

        }
    }

}
