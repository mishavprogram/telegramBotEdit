package stalkerbotGUI.controller.commands;

import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        request.getSession().invalidate();

        Enumeration enumeration = request.getParameterNames();

        System.out.println("Elements in request : ");
        while (enumeration.hasMoreElements()){
            Object element = enumeration.nextElement();
            System.out.println(element + " : "+request.getParameter(element.toString()));
        }

        response.sendRedirect(PagesPath.LOGIN);
        return PagesPath.REDIRECT;
    }
}
