package stalkerbotGUI.controller;

import org.apache.log4j.Logger;
import stalkerbotGUI.controller.commands.Command;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.model.entity.enums.RoleType;
import stalkerbotGUI.service.GeneralUserService;
import stalkerbotGUI.service.exception.UserAlreadyExistException;
import stalkerbotGUI.service.impl.DefaultGeneralUserService;
import stalkerbotGUI.service.impl.DefaultUserService;
import stalkerbotGUI.utils.HashUtils;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * main servlet which intercept all user requests
 */
public class FrontController extends HttpServlet {
    private AtomicInteger countOfEnter = new AtomicInteger(0);

    private static final Logger logger = Logger.getLogger(FrontController.class);
    /**
     * class which contains all commands
     */
    private static transient CommandHolder commandHolder = new CommandHolder();

    @Override
    public void init() throws ServletException {
        GeneralUserService userService = new DefaultUserService();

        String passwordHash = HashUtils.getMD5Hash("456");

        User user = new User.Builder()
            .setEmail("mishavprogram@ukr.net")
            .setName("Misha")
            .setSurname("Vinnichuk")
            .setRole(RoleType.ADMIN)
            .setPasswordHash(passwordHash)
            .getInstance();

        try {
            userService.create(user);
        }catch (UserAlreadyExistException ex){
            //to nothing!
        }
        catch (Exception ex){
            logger.error("init() exception by creating admin");
        }

        passwordHash = HashUtils.getMD5Hash("123");
        user.setEmail("mishav@ukr.net");
        user.setPasswordHash(passwordHash);
        user.setRole(RoleType.USER);

        try{
            userService.create(user);
        }catch (UserAlreadyExistException ex){
            //do nothing
        }
        catch (Exception ex){
            logger.error("init() exception by creating user");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get");
        String path = processRequest(request, response);
        if (!path.equals(PagesPath.REDIRECT)) {
            System.out.println("робимо перехід по сторінці");
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    /**
     * after all post requests it is necessary to perform redirect
     * accordingly with Post-Redirect-Get pattern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post");
        String path = processRequest(request, response);
        if (!path.equals(PagesPath.FORWARD)) {
            response.sendRedirect(path);
        }
    }

    /**
     * this method search necessary command and perform it
     *
     * @param request
     * @param response
     * @return page url
     * @throws ServletException
     * @throws IOException
     */
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();
        String key = method + ":" + path;
        logger.debug(method + " Uri: " + path);
        Command command = commandHolder.findCommand(key);
        logger.debug("command = "+command);
        return command.execute(request, response);
    }

    public static void setCommandHolder(CommandHolder commandHolder) {
        FrontController.commandHolder = commandHolder;
    }
}