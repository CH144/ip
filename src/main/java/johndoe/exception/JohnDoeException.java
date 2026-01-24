package johndoe.exception;

/**
 * Represents exceptions of the {@code JohnDoe} app.
 */
public class JohnDoeException extends Exception {
    /**
     * Creates a new {@code JohnDoeException} with the error message to be displayed.
     */
    public JohnDoeException(String errorMessage) {
        super(errorMessage);
    }
}
