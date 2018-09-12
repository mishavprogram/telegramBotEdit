package stalkerbotGUI.utils.extractors;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class RequestParamExtractor {
    private static final String NUMBER_BETWEEN_SLASHES_PATTERN = "\\d+(?=/|$)";
    private Pattern numberPattern = Pattern.compile(NUMBER_BETWEEN_SLASHES_PATTERN);

    public Integer extractPaginParam(HttpServletRequest request, String param) {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (NumberFormatException e) {
        }
        return null;
    }
}