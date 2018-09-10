package stalkerbotGUI.controller.commands.common;

import stalkerbotGUI.controller.commands.CommandExecutor;
import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.model.entity.Phrase;
import stalkerbotGUI.service.AdminService;
import stalkerbotGUI.service.UserService;
import stalkerbotGUI.service.impl.DefaultAdminService;
import stalkerbotGUI.service.impl.DefaultUserService;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhrasePageCommand extends CommandExecutor {

    private UserService userService = new DefaultUserService();

    //TODO page?
    public PhrasePageCommand() {
        super(PagesPath.PHRASES_ALL);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                                  IOException {
        int maxCountOfElemOnPage = getLimitValueOrDefault(request);
        logger.debug("max count elem on page : "+maxCountOfElemOnPage);

        //long userId = (long) (request.getSession().getAttribute(Attributes.USER_ID));

        long totalCount = userService.getCountOfPhrases();
        int totalPages = calculateOverallPagesCount(maxCountOfElemOnPage, (int) totalCount);
        int numberOfPage = getNumberOfPageOrDefault(request);

        List<Phrase> phraseList = new ArrayList<>();

        phraseList = userService.getPhrases(maxCountOfElemOnPage, maxCountOfElemOnPage*(numberOfPage-1));

        request.setAttribute(Attributes.PHRASES, phraseList);
        request.setAttribute(Attributes.TOTAL_PAGES, totalPages);

        return PagesPath.PHRASES_ALL_PAGE;
    }

}
