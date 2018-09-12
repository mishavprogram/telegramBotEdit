package stalkerbotGUI.utils;

import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.utils.constants.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionParamUtils {

    public static void saveExtendPhrase(ExtendPhrase extendPhrase, HttpServletRequest request){
        HttpSession httpSession = request.getSession();

        if (extendPhrase!=null){
            httpSession.setAttribute(Attributes.TEMP_PHRASE_ID, extendPhrase.getExtendId());
            httpSession.setAttribute(Attributes.PHRASE_TEXT, extendPhrase.getText());
            httpSession.setAttribute(Attributes.AUTHOR_NAME, extendPhrase.getAuthor().getName());
            httpSession.setAttribute(Attributes.AUTHOR_SURNAME, extendPhrase.getAuthor().getSurname());
            httpSession.setAttribute(Attributes.CRUD_ACTION, extendPhrase.getCrudAction());
        }
    }

    public static void removeExtendPhrase(HttpServletRequest request){
        HttpSession httpSession = request.getSession();

        httpSession.removeAttribute(Attributes.TEMP_PHRASE_ID);
        httpSession.removeAttribute(Attributes.PHRASE_TEXT);
        httpSession.removeAttribute(Attributes.AUTHOR_NAME);
        httpSession.removeAttribute(Attributes.AUTHOR_SURNAME);
        httpSession.removeAttribute(Attributes.CRUD_ACTION);
    }

}
