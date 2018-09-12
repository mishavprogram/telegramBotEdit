package stalkerbotGUI.utils;

import stalkerbotGUI.utils.constants.Attributes;

import javax.servlet.http.HttpServletRequest;

public class InfoPageUtils {
    public static void prepareInfoForInfoPage(HttpServletRequest request, String title, String message){
        request.setAttribute(Attributes.INFO_TITLE, title);
        request.setAttribute(Attributes.INFO_MESSAGE, message);
    }
}
