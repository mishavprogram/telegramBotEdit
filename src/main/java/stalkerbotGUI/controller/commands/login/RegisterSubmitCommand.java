package stalkerbotGUI.controller.commands.login;

import static stalkerbotGUI.utils.LoginUtils.clearLoginDataFromRequest;
import static stalkerbotGUI.utils.LoginUtils.saveLoginDataToRequest;

import org.apache.log4j.Logger;
import stalkerbotGUI.controller.commands.CommandExecutor;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.model.entity.enums.RoleType;
import stalkerbotGUI.service.GeneralUserService;
import stalkerbotGUI.service.impl.DefaultUserService;
import stalkerbotGUI.utils.HashUtils;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterSubmitCommand extends CommandExecutor {

    private GeneralUserService userService = new DefaultUserService();

    private static Logger logger = Logger.getLogger(RegisterSubmitCommand.class);

    public RegisterSubmitCommand() {
        super(PagesPath.REGISTER);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        saveLoginDataToRequest(request);
        String pageToGo = PagesPath.REGISTER;

        String email = request.getParameter(Attributes.PARAM_EMAIL);
        String password = request.getParameter(Attributes.PARAM_PASSWORD);
        String name = request.getParameter(Attributes.USER_NAME);
        String surname = request.getParameter(Attributes.USER_SURNAME);

        String passwordHash = HashUtils.getMD5Hash(password);

        User userNew = new User.Builder()
            .setName(name)
            .setSurname(surname)
            .setEmail(email)
            .setPasswordHash(passwordHash)
            .setRole(RoleType.USER)
            .getInstance();

        logger.debug("we want create user : "+userNew);
        userService.create(userNew);

        Optional<User> user = userService.login(email, password);

        if (user.isPresent()) {
            User person = user.get();
            pageToGo = getResultPageByUserRole(person);

            setUserParametersInSession(request, person);
        }

        clearLoginDataFromRequest(request);
        logger.debug("page to go : "+pageToGo);
        return pageToGo;
    }

    private void setUserParametersInSession(HttpServletRequest request, User person) {
        request.getSession().setAttribute(Attributes.USER_ID, person.getId());
        request.getSession().setAttribute(Attributes.USER_ROLE, person.getRole());
        request.getSession().setAttribute(Attributes.USER_NAME, person.getName());
        request.getSession().setAttribute(Attributes.USER_SURNAME, person.getSurname());
    }

    private String getResultPageByUserRole(User user) {
        String result = PagesPath.USER_HOME;
        if (user.getRole() == RoleType.ADMIN) {
            result = PagesPath.ADMIN_HOME;
        }
        return result;
    }
}