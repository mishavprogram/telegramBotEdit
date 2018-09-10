package stalkerbotGUI.service.exception;

public class UserAlreadyExistException extends ServiceException {

    public UserAlreadyExistException(String messageKey) {
        super(messageKey);
    }

    public UserAlreadyExistException(Throwable cause, String messageKey) {
        super(cause, messageKey);
    }
}
