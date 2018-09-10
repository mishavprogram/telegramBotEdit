package stalkerbotGUI.controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interface, which represents execution of certain logic
 * on user request
 */
@FunctionalInterface
public interface Command {

    /**
     * this method perform some logic on user requests
     *
     * @param request  request from client
     * @param response response to client
     * @return
     * @throws ServletException
     * @throws IOException
     */
    String execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;
}