package stalkerbotGUI.controller.commands;

import org.apache.log4j.Logger;
import stalkerbotGUI.controller.validators.Errors;
import stalkerbotGUI.exception.ApplicationException;
import stalkerbotGUI.service.exception.ServiceException;
import stalkerbotGUI.utils.InfoPageUtils;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;
import stalkerbotGUI.utils.extractors.RequestParamExtractor;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wrapper class for specific commands which can throw exception
 */
public abstract class CommandExecutor implements Command {
    private final String nextPage;
    private RequestParamExtractor paramExtractor = new RequestParamExtractor();
    private static final int DEFAULT_QUANTITY_VALUE = 10;
    private static final int DEFAULT_PAGE = 1;

    protected Logger logger = Logger.getLogger(this.getClass());

    protected CommandExecutor(String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * main method which wrap all actions, which could throw some exception
     *
     * @param request  request from client
     * @param response response to client
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            return performExecute(request, response);
        } catch (ServiceException exception) {
            logger.error("service exception");
            InfoPageUtils.prepareInfoForInfoPage(request, "service error", exception.getMessage());
            request.getRequestDispatcher(nextPage).forward(request, response);
        } catch (ApplicationException exception) {
            logger.error("application exception");
            InfoPageUtils.prepareInfoForInfoPage(request, "application error", exception.getMessage());
            request.getRequestDispatcher(PagesPath.INFO_PAGE).forward(request, response);
        } catch (Exception exception) {
            logger.error("global exception..." + exception.getMessage());
            InfoPageUtils.prepareInfoForInfoPage(request, "global exception" , exception.getMessage());
            request.getRequestDispatcher(PagesPath.INFO_PAGE).forward(request, response);
        }
        return PagesPath.FORWARD;
    }


    public abstract String performExecute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;

    protected int getLimitValueOrDefault(HttpServletRequest request) {
        return Optional.ofNullable(paramExtractor.extractPaginParam(request, Attributes.LIMIT))
                .orElse(DEFAULT_QUANTITY_VALUE);
    }

    protected int calculateOverallPagesCount(int limit, int totalCount) {
        return (int) Math.ceil((totalCount + 0.0) / limit);
    }

    protected int getNumberOfPageOrDefault(HttpServletRequest request) {
        int numberOfPage;
        if (request.getParameter(Attributes.OFFSET) != null) {
            numberOfPage = Integer.parseInt(request.getParameter(Attributes.OFFSET));
        } else numberOfPage = DEFAULT_PAGE;
        return numberOfPage;
    }
}