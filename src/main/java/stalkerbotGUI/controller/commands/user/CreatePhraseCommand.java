package stalkerbotGUI.controller.commands.user;

import stalkerbotGUI.controller.commands.Command;
import stalkerbotGUI.model.entity.TelegramBot;
import stalkerbotGUI.service.UserService;
import stalkerbotGUI.service.impl.DefaultUserService;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePhraseCommand implements Command {

    private static final int LIMIT_OF_BOTS_TO_SHOW_USER = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<String> bots = new ArrayList<>();

        try{
            UserService userService = new DefaultUserService();

            bots = userService.getTelegramBots(LIMIT_OF_BOTS_TO_SHOW_USER, 0)
                .stream()
                .map(a->a.getFullName())
                .collect(Collectors.toList());
        }
        catch (Exception ex){
            //TODO show reasons
            return PagesPath.INFO_PAGE;
        }

        request.getSession().setAttribute(Attributes.BOT_LIST, bots);
        return PagesPath.CREATE_PHRASE_PAGE;
    }
}
