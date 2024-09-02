package exceptions;

public class TicketNotGeneratedException extends Exception {
    public TicketNotGeneratedException(String message) {
        super(message);
    }
}
