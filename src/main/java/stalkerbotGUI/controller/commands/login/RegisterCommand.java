package stalkerbotGUI.controller.commands.login;

import stalkerbotGUI.controller.commands.Command;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return PagesPath.REGISTER_PAGE;
    }
}
