package stalkerbotGUI.utils;

import static stalkerbotGUI.utils.constants.Attributes.PARAM_EMAIL;
import static stalkerbotGUI.utils.constants.Attributes.PARAM_PASSWORD;

import stalkerbotGUI.utils.constants.Attributes;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class LoginUtils {

    public static void saveLoginDataToRequest(HttpServletRequest request) {
        request.setAttribute(Attributes.PREVIOUS_LOGIN_EMAIL, request.getParameter(PARAM_EMAIL));
        request.setAttribute(Attributes.PREVIOUS_LOGIN_PASSWORD, request.getParameter(PARAM_PASSWORD));
    }

    public static void clearLoginDataFromRequest(HttpServletRequest request) {
        request.removeAttribute(Attributes.PREVIOUS_LOGIN_EMAIL);
        request.removeAttribute(Attributes.PREVIOUS_LOGIN_PASSWORD);
    }

}
