package edu.kit.informatik;

/**
 * This class is shell class of {@code Terminal}. It implements primitive and minial logger, which displays the
 * messages.(Output part)
 * 
 * @author Oleh Kuzmin<uvdxz@student.kit.edu>
 * @version 1.0
 */
public final class Logger {
    // Private constructor to avoid object generation.
    private Logger() {

    }

    /**
     * Displays a notification.
     * 
     * @param msg
     *            notification.
     */
    public static void sendMsg(final String msg) {
        Terminal.printLine(String.format("%s", msg));
    }

    /**
     * This method displays a notification or error message.
     * 
     * @param msg
     *            error message
     * @param description
     *            reason of error or notifikation
     */
    public static void createLog(final String msg, final String description) {
        printLog(String.format("%s(%s)", msg, description));

    }

    /**
     * This method displays a notification or error message.
     * 
     * @param descriptiom
     *            reason of error or notifikation
     * @param exception
     *            reason of error via Exception
     */
    public static void createLog(final String descriptiom, final Exception exception) {
        printLog(String.format("%s%s", descriptiom, exception.getMessage()));
    }

    // displays message
    private static void printLog(final String msg) {
        Terminal.printLine(msg);
    }
}
