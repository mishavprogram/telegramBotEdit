package stalkerbotGUI.utils.constants;

/**
 * This class contains paths to jsp pages and URLs, which are supported
 */
public final class PagesPath {
    public static final String REDIRECT = "REDIRECT";
    public static final String FORWARD = "FORWARD";

    private static final String VIEW_JSP_CLASSPATH = "/WEB-INF/pages/";

    public static final String USER_HOME = "/user";
    public static final String ADMIN_HOME = "/admin";

    public static final String PHRASES_ALL = "/phrases_all";
    public static final String BOT_ALL = "/bot_all";

    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";

    public static final String WELCOME = "/home";
    public static final String WELCOME_PAGE = VIEW_JSP_CLASSPATH +"/home.jsp";

    public static final String ADDING_ACTIVITY = "/addingActivity";

    public static final String USER_HOME_PAGE = VIEW_JSP_CLASSPATH + "user.jsp";
    public static final String ADMIN_HOME_PAGE = VIEW_JSP_CLASSPATH + "admin.jsp";

    public static final String PHRASES_ALL_PAGE = VIEW_JSP_CLASSPATH + "phrases.jsp";
    public static final String BOT_ALL_PAGE = VIEW_JSP_CLASSPATH + "bots.jsp";

    public static final String LOGIN_PAGE = VIEW_JSP_CLASSPATH + "login.jsp";
    public static final String REGISTER_PAGE = VIEW_JSP_CLASSPATH + "register.jsp";
    public static final String ERROR_PAGE = VIEW_JSP_CLASSPATH + "info.jsp";

    public static final String ADDING_ACTIVITY_PAGE = VIEW_JSP_CLASSPATH+"addAct.jsp";
    public static final String SET_TIME_PAGE = VIEW_JSP_CLASSPATH + "setTimePage.jsp";
    public static final String MAKE_DECISION_PAGE = VIEW_JSP_CLASSPATH+"makeDecisionPage.jsp";

    public static final String SET_TIME = "/setTime";
    public static final String MAKE_DECISION = "/makeDecision";
}