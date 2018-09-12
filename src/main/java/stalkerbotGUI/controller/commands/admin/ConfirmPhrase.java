package stalkerbotGUI.controller.commands.admin;

import stalkerbotGUI.controller.commands.Command;
import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.service.AdminService;
import stalkerbotGUI.service.impl.DefaultAdminService;
import stalkerbotGUI.utils.SessionParamUtils;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmPhrase implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String pageToGo = PagesPath.CONFIRM_PHRASE_PAGE;

        try{
            String id_str = request.getParameter(Attributes.TEMP_PHRASE_ID);
            long id = Long.parseLong(id_str);

            AdminService adminService = new DefaultAdminService();

            Optional<ExtendPhrase> optionalExtendPhrase = adminService.getExtendPhrase(id);

            if (optionalExtendPhrase.isPresent()){

                SessionParamUtils.saveExtendPhrase(optionalExtendPhrase.get(), request);

            } else throw new IllegalArgumentException();
        }
        catch (Exception ex){
            //TODO but there is no info about cause
            pageToGo = PagesPath.INFO_PAGE;
        }

        return pageToGo;
    }
}
