package stalkerbotGUI.controller.commands.user;

import stalkerbotGUI.controller.commands.CommandExecutor;
import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.service.UserService;
import stalkerbotGUI.service.impl.DefaultUserService;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPageCommand extends CommandExecutor {

    private UserService userService = new DefaultUserService();

    public UserPageCommand() {
        super(PagesPath.USER_HOME_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maxCountOfElemOnPage = getLimitValueOrDefault(request);
        logger.debug("max count elem on page : "+maxCountOfElemOnPage);
        long userId = (long) (request.getSession().getAttribute(Attributes.USER_ID));

        long totalCount = userService.getCountOfExtendPhrases(userId);
        int totalPages = calculateOverallPagesCount(maxCountOfElemOnPage, (int) totalCount);
        int numberOfPage = getNumberOfPageOrDefault(request);

        long my_id = (long) request.getSession().getAttribute(Attributes.USER_ID);

        Optional<User> optionalUser = userService.getUser(my_id);

        List<ExtendPhrase> extendPhraseList = new ArrayList<>();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            extendPhraseList = userService.getExtendPhrases(maxCountOfElemOnPage, maxCountOfElemOnPage*(numberOfPage-1), user);
        }

        request.setAttribute(Attributes.EXTEND_PHRASES, extendPhraseList);
        request.setAttribute(Attributes.TOTAL_PAGES, totalPages);

        return PagesPath.USER_HOME_PAGE;
    }
}
