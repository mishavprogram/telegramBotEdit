package stalkerbotGUI.controller.commands.common;

import stalkerbotGUI.controller.commands.Command;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        return PagesPath.INFO_PAGE;
    }
}
