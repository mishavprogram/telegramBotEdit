package stalkerbotGUI.controller.commands.admin;

import stalkerbotGUI.controller.commands.CommandExecutor;
import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.service.AdminService;
import stalkerbotGUI.service.GeneralUserService;
import stalkerbotGUI.service.impl.DefaultAdminService;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageCommand extends CommandExecutor {

    private AdminService adminService = new DefaultAdminService();

    public AdminPageCommand() {
        super(PagesPath.ADMIN_HOME_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maxCountOfElemOnPage = getLimitValueOrDefault(request);
        logger.debug("max count elem on page : "+maxCountOfElemOnPage);

        //long userId = (long) (request.getSession().getAttribute(Attributes.USER_ID));

        long totalCount = adminService.getCountOfExtendPhrases();
        int totalPages = calculateOverallPagesCount(maxCountOfElemOnPage, (int) totalCount);
        int numberOfPage = getNumberOfPageOrDefault(request);

        List<ExtendPhrase> extendPhraseList = new ArrayList<>();

        extendPhraseList = adminService.getExtendPhrases(maxCountOfElemOnPage, maxCountOfElemOnPage*(numberOfPage-1));

        request.setAttribute(Attributes.EXTEND_PHRASES, extendPhraseList);
        request.setAttribute(Attributes.TOTAL_PAGES, totalPages);

        return PagesPath.ADMIN_HOME_PAGE;
    }
}
