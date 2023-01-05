package academy.belhard.lms.service.exception;

public class LmsException extends RuntimeException {
    public LmsException() {
    }

    public LmsException(String message) {
        super(message);
    }

    public LmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LmsException(Throwable cause) {
        super(cause);
    }
}
