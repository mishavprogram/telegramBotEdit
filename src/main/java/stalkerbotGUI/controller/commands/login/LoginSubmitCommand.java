package stalkerbotGUI.controller.commands.login;

import static stalkerbotGUI.utils.LoginUtils.clearLoginDataFromRequest;
import static stalkerbotGUI.utils.LoginUtils.saveLoginDataToRequest;
import static stalkerbotGUI.utils.constants.Attributes.PARAM_EMAIL;
import static stalkerbotGUI.utils.constants.Attributes.PARAM_PASSWORD;

import org.apache.log4j.Logger;
import stalkerbotGUI.controller.commands.CommandExecutor;
import stalkerbotGUI.model.entity.enums.RoleType;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.service.GeneralUserService;
import stalkerbotGUI.service.impl.DefaultGeneralUserService;
import stalkerbotGUI.service.impl.DefaultUserService;
import stalkerbotGUI.utils.HashUtils;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginSubmitCommand extends CommandExecutor {

    private GeneralUserService userService = new DefaultUserService();

    private static Logger logger = Logger.getLogger(LoginSubmitCommand.class);

    public LoginSubmitCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        saveLoginDataToRequest(request);
        String pageToGo = PagesPath.LOGIN;
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);

        //String passwordHash = HashUtils.getMD5Hash(password);

        Optional<User> user = userService.login(email, password);
        if (user.isPresent()) {
            logger.debug("user is present");
            User person = user.get();
            pageToGo = getResultPageByUserRole(person);
            request.getSession().setAttribute(Attributes.USER_ID, person.getId());
            request.getSession().setAttribute(Attributes.USER_ROLE, person.getRole());
            logger.debug("user role : "+person.getRole());
            request.getSession().setAttribute(Attributes.USER_NAME, person.getName());
            request.getSession().setAttribute(Attributes.USER_SURNAME, person.getSurname());
        } else logger.warn("user with email: "+email+", and password: "+password+" not exist in database");
        clearLoginDataFromRequest(request);
        logger.debug("page to go : "+pageToGo);
        return pageToGo;
    }

    private String getResultPageByUserRole(User user) {
        String result = PagesPath.USER_HOME;
        if (user.getRole() == RoleType.ADMIN) {
            result = PagesPath.ADMIN_HOME;
        }
        return result;
    }

}