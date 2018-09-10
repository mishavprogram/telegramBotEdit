package stalkerbotGUI.controller.commands.user;

import stalkerbotGUI.controller.commands.CommandExecutor;
import stalkerbotGUI.model.entity.Phrase;
import stalkerbotGUI.model.entity.TelegramBot;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.service.UserService;
import stalkerbotGUI.service.exception.ServiceException;
import stalkerbotGUI.service.impl.DefaultUserService;
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
            String bot_name = request.getParameter(Attributes.BOT_ID);

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
                }else
                {
                    request.setAttribute(Attributes.INFO_TITLE, "Помилка при створенні фрази");
                    request.setAttribute(Attributes.INFO_MESSAGE, "bot nor found!");
                }
            } else{
                request.setAttribute(Attributes.INFO_TITLE, "Помилка при створенні фрази");
                request.setAttribute(Attributes.INFO_MESSAGE, "author not found!");
            }

            request.setAttribute(Attributes.INFO_TITLE, "Успіх!");
            request.setAttribute(Attributes.INFO_MESSAGE, "Ви створили фразу!");
        }
        catch (NumberFormatException ex){
            String info_title = "Number Format Exception";
            String info_message = "Ймовірно ви ввели неіснуюче id бота";

            request.setAttribute(Attributes.INFO_TITLE, info_title);
            request.setAttribute(Attributes.INFO_MESSAGE, info_message);
        }
        catch (ServiceException ex){
            request.setAttribute(Attributes.INFO_TITLE, "Помилка при створенні фрази");
            request.setAttribute(Attributes.INFO_MESSAGE, ex.getMessage());
        }



        return pageToGo;
    }
}
