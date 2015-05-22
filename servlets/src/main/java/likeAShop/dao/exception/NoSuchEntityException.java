package likeAShop.dao.exception;

/**
 * Created by Alexander on 20.05.2015.
 */
public class NoSuchEntityException extends DaoBusinessException {
    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
