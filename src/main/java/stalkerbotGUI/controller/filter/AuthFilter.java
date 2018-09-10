package stalkerbotGUI.controller.filter;

import stalkerbotGUI.model.entity.enums.RoleType;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.EnumMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class is authorization filter.
 * Filter checks every user request, to find out his permissions.
 * If user don't have permissions, the filter forward user to login page.
 */
public class AuthFilter implements Filter {
    private static final String USER_NOT_AUTHORIZED = "User isn't authorized";

    private static EnumMap<RoleType, Authorizer> authorizeByRole = new EnumMap<>(RoleType.class);

    static {
        authorizeByRole.put(RoleType.USER, new UserAuthorizer());
        authorizeByRole.put(RoleType.ADMIN, new AdminAuthorizer());
    }

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                                     ServletException {
        logger.debug("do auth Filter");
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        logger.debug("Uri: "+uri);
        Object userId = session.getAttribute(Attributes.USER_ID);
        RoleType roleType = (RoleType) session.getAttribute(Attributes.USER_ROLE);

        if (!checkUserPermissions(uri, userId, roleType)) {
            req.getRequestDispatcher(PagesPath.WELCOME_PAGE).forward(request, response);
            return;
        }
        logger.debug("to next filter after auth");
        chain.doFilter(request, response);
    }

    /**
     * this method check user permissions
     *
     * @param uri
     * @param userId
     * @param roleType
     * @return
     */
    private boolean checkUserPermissions(String uri, Object userId, RoleType roleType) {
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png")) {
            return true;
        }
        Authorizer authorizer = authorizeByRole.getOrDefault(roleType, new AnonymAuthorizer());
        return authorizer.check(uri, userId);
    }

    private interface Authorizer {
        boolean check(String uri, Object userId);//вертає true якщо все норм?
    }

    //TODO check if everythink is norm here, because i changed smth
    private static class UserAuthorizer implements Authorizer {
        public boolean check(String uri, Object userId) {
            return userId != null && !uri.startsWith(PagesPath.ADMIN_HOME);
        }
    }

    private static class AdminAuthorizer implements Authorizer {
        public boolean check(String uri, Object userId) {
            return userId != null && (uri.startsWith(PagesPath.ADMIN_HOME) ||
                    uri.startsWith(PagesPath.LOGIN) ||
                    uri.startsWith(PagesPath.REGISTER)) ||
                   uri.startsWith(PagesPath.WELCOME) ||
                    uri.startsWith(PagesPath.LOGOUT) ||
                uri.startsWith(PagesPath.BOT_ALL) ||
                uri.startsWith(PagesPath.PHRASES_ALL);
        }
    }

    private class AnonymAuthorizer implements Authorizer {
        public boolean check(String uri, Object userId) {
            return uri.startsWith(PagesPath.LOGIN) ||
                    uri.startsWith(PagesPath.REGISTER) ||
                   uri.startsWith(PagesPath.WELCOME) ||
                    uri.startsWith(PagesPath.LOGOUT) ||
                uri.startsWith(PagesPath.BOT_ALL) ||
                uri.startsWith(PagesPath.PHRASES_ALL);
        }
    }

    /**
     * method which perform filter initialization
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    //TODO
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * method, which will be performed before filter destroy
     */
    @Override
    public void destroy() {

    }
}