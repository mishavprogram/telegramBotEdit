package stalkerbotGUI.controller.commands;

import stalkerbotGUI.model.entity.enums.RoleType;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleType roleType = RoleType.valueOf(request.getSession().getAttribute(Attributes.USER_ROLE).toString());

        System.out.println("roleType = "+roleType);

        if (roleType.equals(RoleType.USER))
            return PagesPath.USER_HOME_PAGE;
        else if (roleType.equals(RoleType.ADMIN))
            return PagesPath.ADMIN_HOME_PAGE;
        else return PagesPath.WELCOME_PAGE;
    }
}
