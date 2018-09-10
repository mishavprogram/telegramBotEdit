package stalkerbotGUI.controller.exception;

import stalkerbotGUI.exception.ApplicationException;

/**
 * this class represents custom exception for controller layer
 */
public class ControllerException extends ApplicationException {
    public ControllerException(String messageKey) {
        super(messageKey);
    }

    public ControllerException(Throwable cause, String messageKey) {
        super(cause, messageKey);
    }
}
