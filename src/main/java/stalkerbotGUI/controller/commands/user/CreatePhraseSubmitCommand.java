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
            String bot_name = request.getParameter("bot");

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
                    logger.debug("помилка при створенні фрази. Бота не знайдено");
                    request.setAttribute(Attributes.INFO_TITLE, "Помилка при створенні фрази");
                    request.setAttribute(Attributes.INFO_MESSAGE, "bot nor found!");
                }
            } else{
                logger.debug("помилка при створенні фрази. Автора не знайдено");
                request.setAttribute(Attributes.INFO_TITLE, "Помилка при створенні фрази");
                request.setAttribute(Attributes.INFO_MESSAGE, "author not found!");
            }

            System.out.println("Ми дойшли сюди");
            request.setAttribute(Attributes.USER_NAME, "Успіх!");
            request.setAttribute(Attributes.USER_SURNAME, "Ви створили фразу!");

            request.setAttribute(Attributes.INFO_TITLE, "Успіх!");
            request.setAttribute(Attributes.INFO_MESSAGE, "Ви створили фразу!");
        }
        catch (NumberFormatException ex){
            logger.debug("number format exception");

            String info_title = "Number Format Exception";
            String info_message = "Ймовірно ви ввели неіснуюче id бота";

            request.setAttribute(Attributes.INFO_TITLE, info_title);
            request.setAttribute(Attributes.INFO_MESSAGE, info_message);
        }
        catch (ServiceException ex){
            logger.debug("service exception");

            request.setAttribute(Attributes.INFO_TITLE, "Помилка при створенні фрази");
            request.setAttribute(Attributes.INFO_MESSAGE, ex.getMessage());
        }

        logger.debug("info_title : "+request.getAttribute(Attributes.INFO_TITLE));
        logger.debug("info_message : "+request.getAttribute(Attributes.INFO_MESSAGE));

        request.getRequestDispatcher(pageToGo).forward(request, response);

        return PagesPath.FORWARD;
    }
}
