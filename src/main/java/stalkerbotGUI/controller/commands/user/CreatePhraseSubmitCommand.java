package stalkerbotGUI.controller.commands.user;

import stalkerbotGUI.controller.commands.CommandExecutor;
import stalkerbotGUI.model.entity.Phrase;
import stalkerbotGUI.model.entity.TelegramBot;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.service.UserService;
import stalkerbotGUI.service.exception.ServiceException;
import stalkerbotGUI.service.impl.DefaultUserService;
import stalkerbotGUI.utils.InfoPageUtils;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.Messages;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePhraseSubmitCommand extends CommandExecutor {

    private UserService userService = new DefaultUserService();

    public CreatePhraseSubmitCommand(){
        super(PagesPath.CREATE_PHRASE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String pageToGo = PagesPath.INFO_PAGE;

        try {
            getUserBotAndCreatePhrase(request);
        }
        catch (NumberFormatException ex){
            InfoPageUtils.prepareInfoForInfoPage(request, Attributes.ERROR, Messages.YOU_ENTER_NOT_NUMBER);
        }
        catch (ServiceException ex){
            InfoPageUtils.prepareInfoForInfoPage(request,
                                                 Attributes.ERROR,
                                                 ex.getMessage());
        }

        request.getRequestDispatcher(pageToGo).forward(request, response);

        return PagesPath.FORWARD;
    }

    private void getUserBotAndCreatePhrase(HttpServletRequest request) {
        String text = request.getParameter(Attributes.PHRASE_TEXT);
        String bot_name = request.getParameter(Attributes.BOT);

        logger.debug("text : "+text);
        logger.debug("bot_name : "+bot_name);

        Optional<User> optionalUser = userService.getUser(Long.parseLong(request.getSession()
                                          .getAttribute(Attributes.USER_ID)
                                                         .toString()));

        if (optionalUser.isPresent()) {
            getTelegramBotAndCreatePhrase(request, text, bot_name, optionalUser.get());
        } else{
            InfoPageUtils.prepareInfoForInfoPage(request,
                                                 Attributes.ERROR,
                                                 Messages.AUTHOR_NOT_FOUND);
        }

        InfoPageUtils.prepareInfoForInfoPage(request,
                                             Attributes.SUCCESS,
                                             Messages.YOU_CREATE_PHRASE);
    }

    private void getTelegramBotAndCreatePhrase(HttpServletRequest request, String text, String bot_name,
                                               User user) {
        Optional<TelegramBot> telegramBot = userService.getTelegramBot(bot_name);

        if (telegramBot.isPresent()) {
            createPhrase(text, user, telegramBot.get());
        }else
        {
            InfoPageUtils.prepareInfoForInfoPage(request,
                                                 Attributes.ERROR,
                                                 Messages.BOT_NOT_FOUND);
        }
    }

    private void createPhrase(String text, User user, TelegramBot telegramBot) {
        Phrase phrase = new Phrase.Builder()
            .setText(text)
            .setTelegramBot(telegramBot)
            .setAuthor(user)
            .getPhrase();

        userService.create(phrase);
    }
}
