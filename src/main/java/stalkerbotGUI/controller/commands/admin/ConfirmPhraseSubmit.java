package stalkerbotGUI.controller.commands.admin;

import stalkerbotGUI.controller.commands.CommandExecutor;
import stalkerbotGUI.service.AdminService;
import stalkerbotGUI.service.impl.DefaultAdminService;
import stalkerbotGUI.utils.InfoPageUtils;
import stalkerbotGUI.utils.SessionParamUtils;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmPhraseSubmit extends CommandExecutor {

    public ConfirmPhraseSubmit() {
        super(PagesPath.CONFIRM_PHRASE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        AdminService adminService = new DefaultAdminService();

        long id = Long.parseLong(request.getSession()
                                     .getAttribute(Attributes.TEMP_PHRASE_ID)
                                     .toString());

        if (request.getParameter(Attributes.CONFIRM) != null) {
            adminService.confirm(id);

        } else if (request.getParameter(Attributes.REJECT) != null) {
            adminService.reject(id);
        }

        SessionParamUtils.removeExtendPhrase(request);//TODO
        InfoPageUtils.prepareInfoForInfoPage(request, "Success!", "no message");
        return PagesPath.INFO;
    }
}
