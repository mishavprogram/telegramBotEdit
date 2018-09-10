package stalkerbotGUI.dao.exception;

import stalkerbotGUI.exception.ApplicationException;

public class DaoException extends ApplicationException {
    public DaoException(String messageKey) {
        super(messageKey);
    }

    public DaoException(Throwable cause, String messageKey) {
        super(cause, messageKey);
    }

    public DaoException(String message, String messageKey) {
        super(message, messageKey);
    }

    @Override
    public String getMessageKey() {
        return super.getMessageKey();
    }
}
