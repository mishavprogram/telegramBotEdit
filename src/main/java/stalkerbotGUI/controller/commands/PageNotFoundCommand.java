package stalkerbotGUI.controller.commands;

import stalkerbotGUI.controller.validators.Errors;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.MessageKeys;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageNotFoundCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Errors errors = new Errors();
        errors.addError(Attributes.ERROR, MessageKeys.URL_NOT_FOUND);
        request.setAttribute(Attributes.ERRORS, errors);
        return PagesPath.ERROR_PAGE;
    }
}