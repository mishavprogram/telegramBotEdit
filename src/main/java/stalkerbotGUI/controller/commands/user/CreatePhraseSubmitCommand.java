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
            String text = request.getParameter(Attributes.PHRASE_TEXT);
            String bot_name = request.getParameter(Attributes.BOT);

            logger.debug("text : "+text);
            logger.debug("bot_name : "+bot_name);

            Optional<User> optionalUser = userService.getUser(Long.parseLong(request.getSession()
                                              .getAttribute(Attributes.USER_ID)
                                                             .toString()));

            if (optionalUser.isPresent()) {

                Optional<TelegramBot> telegramBot = userService.getTelegramBot(bot_name);

                if (telegramBot.isPresent()) {

                    Phrase phrase = new Phrase.Builder()
                        .setText(text)
                        .setTelegramBot(telegramBot.get())
                        .setAuthor(optionalUser.get())
                        .getPhrase();

                    userService.create(phrase);
                    logger.debug("фраза наче створилась без проблем");
                }else
                {
                    InfoPageUtils.prepareInfoForInfoPage(request,
                                                         "Створення фрази",
                                                         "bot not found!");
                }
            } else{
                InfoPageUtils.prepareInfoForInfoPage(request,
                                                     "Створення фрази",
                                                     "author not found!");
            }
            InfoPageUtils.prepareInfoForInfoPage(request,
                                                 "Успіх!",
                                                 "Ви створили фразу!");
        }
        catch (NumberFormatException ex){
            String info_title = "Number Format Exception";
            String info_message = "Ймовірно ви ввели неіснуюче id бота";

            InfoPageUtils.prepareInfoForInfoPage(request, info_title, info_message);
        }
        catch (ServiceException ex){
            InfoPageUtils.prepareInfoForInfoPage(request,
                                                 "Помилка при створенні фрази."
                                                 + "Рівень сервісів",
                                                 ex.getMessage());
        }

        request.getRequestDispatcher(pageToGo).forward(request, response);

        return PagesPath.FORWARD;
    }
}
