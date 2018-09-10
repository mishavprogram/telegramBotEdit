package stalkerbotGUI.controller.commands.login;

import stalkerbotGUI.controller.commands.Command;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return PagesPath.LOGIN_PAGE;
    }
}
