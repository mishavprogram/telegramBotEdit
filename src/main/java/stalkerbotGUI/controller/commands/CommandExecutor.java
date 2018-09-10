package stalkerbotGUI.controller.commands;

import org.apache.log4j.Logger;
import stalkerbotGUI.controller.validators.Errors;
import stalkerbotGUI.exception.ApplicationException;
import stalkerbotGUI.service.exception.ServiceException;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.MessageKeys;
import stalkerbotGUI.utils.constants.PagesPath;
import stalkerbotGUI.utils.extractors.RequestParamExtractor;

import java.io.IOException;
import java.time.LocalDate;
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
    private static final int DEFAULT_QUANTITY_VALUE = 5;
    private static final int DEFAULT_OFFSET_VALUE = 0;
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
            putErrorMessageInRequest(request, exception.getMessageKey());
            request.getRequestDispatcher(nextPage).forward(request, response);
        } catch (ApplicationException exception) {
            logger.error("application exception");
            putErrorMessageInRequest(request, exception.getMessageKey());
            request.getRequestDispatcher(PagesPath.ERROR_PAGE).forward(request, response);
        } catch (Exception exception) {
            logger.error("global exception..." + exception.getMessage());
            putErrorMessageInRequest(request, MessageKeys.UNKNOWN_ERROR_OCCURED);
            request.getRequestDispatcher(PagesPath.ERROR_PAGE).forward(request, response);
        }
        return PagesPath.FORWARD;
    }

    public void putErrorMessageInRequest(HttpServletRequest request, String messageKey) {
        Errors errors = (Errors) request.getAttribute(Attributes.ERRORS);
        if (errors == null) {
            errors = new Errors();
        }
        errors.addError(Attributes.ERROR, messageKey);
        request.setAttribute(Attributes.ERRORS, errors);
    }

    public abstract String performExecute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;

    protected int getLimitValueOrDefault(HttpServletRequest request) {
        return Optional.ofNullable(paramExtractor.extractPaginParam(request, Attributes.LIMIT))
                .orElse(DEFAULT_QUANTITY_VALUE);
    }

    protected int getOffsetValueOrDefault(HttpServletRequest request, int quantity) {
        return Optional.ofNullable(paramExtractor.extractPaginParam(request, Attributes.OFFSET))
                .map(page -> (page - 1) * quantity)
                .orElse(DEFAULT_OFFSET_VALUE);
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