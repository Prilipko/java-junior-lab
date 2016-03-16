package likeAShop.dao.exception;

/**
 * Created by Alexander on 20.05.2015.
 */
public class DaoBusinessException extends DaoException {
    public DaoBusinessException(String message) {
        super(message);
    }

    public DaoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
