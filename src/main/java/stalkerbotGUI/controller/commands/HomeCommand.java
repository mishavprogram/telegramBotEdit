package stalkerbotGUI.controller.commands;

import stalkerbotGUI.model.entity.enums.RoleType;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand extends CommandExecutor {

    public HomeCommand(){
        super(PagesPath.WELCOME_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        RoleType roleType = RoleType.valueOf(request.getSession().getAttribute(Attributes.USER_ROLE).toString());

        if (roleType.equals(RoleType.USER))
            return PagesPath.USER_HOME;
        else if (roleType.equals(RoleType.ADMIN))
            return PagesPath.ADMIN_HOME;
        else return PagesPath.WELCOME_PAGE;

    }
}
