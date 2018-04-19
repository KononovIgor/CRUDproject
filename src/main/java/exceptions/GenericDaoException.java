package exceptions;

public class GenericDaoException extends RuntimeException {
    public GenericDaoException(String reason) {
        super(reason);
    }

    public GenericDaoException(String reason, Exception exception) {
        super(reason, exception);
    }
}
