package stalkerbotGUI.controller.commands;

import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();

        response.sendRedirect(PagesPath.LOGIN);
        return PagesPath.REDIRECT;
    }
}
