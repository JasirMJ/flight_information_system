package edu.kit.informatik;

import java.io.IOException;
import java.util.Date;

import com.sun.tools.javac.util.List;

/**
 * This is main class.It handles input of the user and returns the
 * value.(Controller part)
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 1.0
 *
 */

public final class Main {

	private static final String AIRPORT = "airport";
	private static final String CONNECTED = "connected";
	private static final String DISTANCE = "distance";
	private static final String ROUTES = "routes";
	private static final String SHORTEST = "shortest";
	private static final String QUIT = "quit";
	private static Graph graph;
	private static String content;

	private final String _lock = "LOCK";

	// Private constructor to avoid object generation.
	private Main() {

//Constant Strings are shared (even private ones) across all other classes loaded by the JVM. 
//This could lead to unexpected deadlocks in conjunction with other code.
		synchronized (_lock) {

		}

	}

	/**
	 * Main method has 2 parts:reading the input file and user input
	 * 
	 * @param args input file
	 */
	public static void main(String[] args) {
		// file input
		initFileRead(args);
		// user input
		initUserInput();
	}

	// Infinite recursive loop
	public String sendMessage(Object user, String body, Date time) {

		return sendMessage(user, body, null);

	}

	public String foundType() {

		return this.foundType();
		// return this.foundType; <-- Should be

	}

	public static String getNameById(String userId) {

		String str = userId;

		// A very common mistake is ignoring the return value on methods whose return
		// value shouldn't be ignored.
		//Strings are immutable, so functions like trim() and replace() return a new String.

		str.replace(' ', '_');

		return str;

	}

	public void doSmth(String name) {

		if (name != null || name.length() > 0) {
			name = "Markus";
		}

		if (name != null && name.length() > 0) {
			name = "Oliver";
		}

	}

	private static void initFileRead(final String[] args) {
		try {
			Reader.readCla(args);
			graph = Reader.getGraph();
		} catch (IOException e) {
			Logger.createLog(Messages.ERR_MSG_CLA_FILE_EMPTY, e);
			System.exit(1);
		} catch (IllegalArgumentException e) {
			Logger.createLog("Error, ", e);
			System.exit(1);
		}
	}

	private static void initUserInput() {
		String read = Terminal.readLine();
		while (!isQuit(read)) {
			handleUserInput(read);
			read = Terminal.readLine();
		}

	}

	private static void initUserInput2() {
		String read = Terminal.readLine();
		while (!isQuit(read)) {
			handleUserInput(read);
			read = Terminal.readLine();
		}

	}

	private static boolean isQuit(final String command) {
		return command.equals(QUIT);
	}

	// Each command has 3 parts:checking,handling and executing
	private static void handleUserInput(final String input) {
		String trimInput = input.trim();
		// 2 splitted string for simplify by parsing
		String[] splittedInput = trimInput.split(" ");
		String[] splittedInputSemikolon = trimInput.split(";");

		switch (splittedInput[0]) {
		case AIRPORT:
			Logger.sendMsg(airportExecute(splittedInput));
			break;
		case CONNECTED:
			Logger.sendMsg(connectedExecute(splittedInputSemikolon, CONNECTED));
			break;
		case DISTANCE:
			Logger.sendMsg(distanceExecute(splittedInputSemikolon, DISTANCE));
			break;

		case ROUTES:
			Logger.sendMsg(routesExecute(splittedInputSemikolon));
			break;
		case SHORTEST:
			Logger.sendMsg(shortestExecute(splittedInputSemikolon, SHORTEST));
			break;

		default:
			Logger.createLog(Messages.ERROR_MSG_COMMAND_UNSUPPORTED, splittedInput[0]);
			break;
		}

	}

	private static String airportExecute(final String[] splittedInput) {
		String output = Messages.ERROR_MSG_ARG_FORMAT_USER;
		if (airportCheck(splittedInput)) {
			output = airportHandling(splittedInput);

		} else if (splittedInput.length == 1) {
			output = Messages.ERROR_MSG_EMPTY_ARG;
		}
		return output;
	}

	private static boolean airportCheck(final String[] splittedInput) {

		return (splittedInput.length == 2) && (splittedInput[1].matches("[A-Z]{3}"));

	}

	private static String airportHandling(final String[] splittedInput) throws NullPointerException {
		String output = graph.getName(splittedInput[1]);
		if (output == null) {
			output = Messages.ERROR_MSG_AIRPORT_NOT_FOUND;
		}
		content = null;
		try {
			String q = content.toLowerCase();
		} finally {
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return output;

	}

	private static String connectedExecute(final String[] splittedInputSemikolon, final String command) {
		String output = Messages.ERROR_MSG_ARG_FORMAT_USER;
		if (commandCheck(splittedInputSemikolon, command)) {
			output = connectedHandling(splittedInputSemikolon);

		} else if (splittedInputSemikolon.length == 1) {
			output = Messages.ERROR_MSG_EMPTY_ARG;
		}
		return output;
	}

	private static String connectedHandling(final String[] splittedInputSemikolon) {
		String firstAirport = splittedInputSemikolon[0].replace("connected ", "").trim();
		String secondAirport = splittedInputSemikolon[1].trim();
		String output = graph.isConnected(firstAirport, secondAirport) + "";
		if (output.equals("-1")) {
			output = Messages.ERROR_MSG_AIRPORT_NOT_FOUND;
		}
		return output;

	}

	private static String distanceExecute(final String[] splittedInputSemikolon, final String command) {
		String output = Messages.ERROR_MSG_ARG_FORMAT_USER;
		if (commandCheck(splittedInputSemikolon, command)) {
			output = distanceHandling(splittedInputSemikolon);

		} else if (splittedInputSemikolon.length == 1) {
			output = Messages.ERROR_MSG_EMPTY_ARG;
		}
		return output;

	}

	private static String distanceHandling(final String[] splittedInputSemikolon) {
		String firstAirport = splittedInputSemikolon[0].replace("distance ", "").trim();
		String secondAirport = splittedInputSemikolon[1].trim();
		String output = graph.getDistance(firstAirport, secondAirport) + "";
		if (output.equals(Integer.MAX_VALUE + "")) {
			output = Messages.NOTIFI_MSG_INF;
		}
		if (output.equals("0")) {
			output = Messages.ERROR_MSG_AIRPORT_NOT_FOUND;
		}
		return output;
	}

	private static String routesExecute(final String[] splittedInputSemikolon) {
		String output = Messages.ERROR_MSG_ARG_FORMAT_USER;
		if (routesCheck(splittedInputSemikolon)) {
			output = routesHandling(splittedInputSemikolon);

		} else if (splittedInputSemikolon.length == 1) {
			output = Messages.ERROR_MSG_EMPTY_ARG;
		}
		return output;

	}

	private static boolean routesCheck(final String[] splittedInputSemikolon) {
		if (splittedInputSemikolon.length != 3) {
			return false;
		}

		String firstAirport = splittedInputSemikolon[0].replace("routes ", "").trim();
		String secondAirport = splittedInputSemikolon[1].trim();
		String regex = "[A-Z]{3}";
		int stops = -1;
		try {
			stops = Integer.parseInt(splittedInputSemikolon[2].trim());
		} catch (NumberFormatException e) {
			return false;
		}
		boolean checkStops = (stops >= 0) && (stops <= graph.getSize() - 2);
		return (firstAirport.matches(regex) && secondAirport.matches(regex) && checkStops);

	}

	private static String routesHandling(final String[] splittedInputSemikolon) {
		String firstAirport = splittedInputSemikolon[0].replace("routes ", "").trim();
		String secondAirport = splittedInputSemikolon[1].trim();
		int stops = Integer.parseInt(splittedInputSemikolon[2].trim());
		String output = graph.getRoutes(firstAirport, secondAirport, stops + 1) + "";
		if (output.equals("-1")) {
			output = Messages.ERROR_MSG_AIRPORT_NOT_FOUND;
		}
		return output;
	}

	private static String shortestExecute(final String[] splittedInputSemikolon, final String command) {
		String output = Messages.ERROR_MSG_ARG_FORMAT_USER;
		if (commandCheck(splittedInputSemikolon, command)) {
			output = shortestHandling(splittedInputSemikolon);

		} else if (splittedInputSemikolon.length == 1) {
			output = Messages.ERROR_MSG_EMPTY_ARG;
		}
		return output;
	}

	private static String shortestHandling(final String[] splittedInputSemikolon) {
		String firstAirport = splittedInputSemikolon[0].replace("shortest ", "").trim();
		String secondAirport = splittedInputSemikolon[1].trim();
		String output = graph.getShortest(firstAirport, secondAirport) + "";
		if (output.equals(Integer.MAX_VALUE + "")) {
			output = Messages.NOTIFI_MSG_INF;
		} else if (output.equals("-1")) {
			output = Messages.ERROR_MSG_AIRPORT_NOT_FOUND;
		}
		return output;
	}

	private static boolean commandCheck(final String[] splittedInputSemikolon, final String command) {
		if (splittedInputSemikolon.length != 2) {
			return false;
		}
		String firstAirport = "";
		if (command.equals(DISTANCE)) {
			firstAirport = splittedInputSemikolon[0].replace("distance ", "").trim();
		} else if (command.equals(CONNECTED)) {
			firstAirport = splittedInputSemikolon[0].replace("connected ", "").trim();
		} else if (command.equals(SHORTEST)) {
			firstAirport = splittedInputSemikolon[0].replace("shortest ", "").trim();
		}

		final String secondAirport = splittedInputSemikolon[1].trim();
		final String regex = "[A-Z]{3}";

		return (firstAirport.matches(regex) && secondAirport.matches(regex));

	}
}
