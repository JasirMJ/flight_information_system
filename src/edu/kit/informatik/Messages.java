package edu.kit.informatik;

/**
 * This (static)class contains all error,notification messages of the game.
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 1.0
 */
public final class Messages {
    // CLA msg errors
    /**
     * Error, your path to the file is wrong!
     */
    public static final String ERR_MSG_CLA_PATH = "your path to the file is wrong!";
    /**
     * Error,your input is false!
     */
    public static final String ERR_MSG_CLA_PARSING = " your input is false!";
    /**
     * Error, file is not found!
     */
    public static final String ERR_MSG_CLA_FILE_EMPTY = "Error, file is not found! ";

    /**
     * Error, your command has wrong format!
     */

    public static final String ERROR_MSG_FORMAT_USER = "Error, your command has wrong format!";
    /**
     * Error, your command has wrong format(size)!
     */
    public static final String ERROR_MSG_SIZE_FORMAT_USER = "Error, your command has wrong format(size)!";
    /**
     * Error, your command has wrong argument!
     */
    public static final String ERROR_MSG_ARG_FORMAT_USER = "Error, your command has wrong argument!";
    /**
     * Error, your command is not supported!
     */
    public static final String ERROR_MSG_COMMAND_UNSUPPORTED = "Error, your command  is not supported!";
    /**
     * Error, your comands argument is empty!
     */
    public static final String ERROR_MSG_EMPTY_ARG = "Error, your comands argument is empty!";
    /**
     * Error, airport is not found!
     */
    public static final String ERROR_MSG_AIRPORT_NOT_FOUND = "Error, airport is not found!";
    /**
     * inf
     */
    public static final String NOTIFI_MSG_INF = "inf";

    // Private constructor to avoid object generation.
    private Messages() {
    }
}
