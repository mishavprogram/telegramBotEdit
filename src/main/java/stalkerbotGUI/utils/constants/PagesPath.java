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

    public static final String CREATE_PHRASE = "/user/createPhrase";
    public static final String CREATE_PHRASE_PAGE = VIEW_JSP_CLASSPATH + "createPhrase.jsp";
    public static final String CONFIRM_PHRASE = "/admin/confirmPhrase";
    public static final String CONFIRM_PHRASE_PAGE = VIEW_JSP_CLASSPATH + "confirmPhrase.jsp";


    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";

    public static final String WELCOME = "/home";
    public static final String WELCOME_PAGE = VIEW_JSP_CLASSPATH +"/home.jsp";

    public static final String USER_HOME_PAGE = VIEW_JSP_CLASSPATH + "user.jsp";
    public static final String ADMIN_HOME_PAGE = VIEW_JSP_CLASSPATH + "admin.jsp";

    public static final String PHRASES_ALL_PAGE = VIEW_JSP_CLASSPATH + "phrases.jsp";
    public static final String BOT_ALL_PAGE = VIEW_JSP_CLASSPATH + "bots.jsp";

    public static final String LOGIN_PAGE = VIEW_JSP_CLASSPATH + "login.jsp";
    public static final String REGISTER_PAGE = VIEW_JSP_CLASSPATH + "register.jsp";
    public static final String INFO_PAGE = VIEW_JSP_CLASSPATH + "info.jsp";
    public static final String INFO = "/info";

}