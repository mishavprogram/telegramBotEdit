package stalkerbotGUI.controller.commands;

import stalkerbotGUI.controller.validators.Errors;
import stalkerbotGUI.utils.InfoPageUtils;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageNotFoundCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InfoPageUtils.prepareInfoForInfoPage(request, Attributes.ERROR, Attributes.ERRORS);
        return PagesPath.INFO_PAGE;
    }
}