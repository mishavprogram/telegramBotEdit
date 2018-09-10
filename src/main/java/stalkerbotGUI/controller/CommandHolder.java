package stalkerbotGUI.controller;

import stalkerbotGUI.controller.commands.Command;
import stalkerbotGUI.controller.commands.HomeCommand;
import stalkerbotGUI.controller.commands.LogoutCommand;
import stalkerbotGUI.controller.commands.PageNotFoundCommand;
import stalkerbotGUI.controller.commands.admin.AdminPageCommand;
import stalkerbotGUI.controller.commands.common.PhrasePageCommand;
import stalkerbotGUI.controller.commands.common.TelegramBotPageCommand;
import stalkerbotGUI.controller.commands.login.LoginCommand;
import stalkerbotGUI.controller.commands.login.LoginSubmitCommand;
import stalkerbotGUI.controller.commands.login.RegisterCommand;
import stalkerbotGUI.controller.commands.login.RegisterSubmitCommand;
import stalkerbotGUI.controller.commands.user.CreatePhraseCommand;
import stalkerbotGUI.controller.commands.user.CreatePhraseSubmitCommand;
import stalkerbotGUI.controller.commands.user.UserPageCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * class which contains all commands, which are necessary to response user
 */
public class CommandHolder {
    public static final String NUMBER_BETWEEN_SLASHES_PATTERN = "/\\d+(?=/|$)";
    private Map<String, Command> commands = new HashMap<>();

    public CommandHolder() {
        fillCommands();
    }

    /**
     * method which convert url and find necessary command
     *
     * @param key
     * @return command
     */
    Command findCommand(String key) {
        String convertedKey = removeAllNumbersFromUrl(key);
        return commands.getOrDefault(convertedKey, new PageNotFoundCommand());
    }

    //TODO
    private void fillCommands() {
        commands.put("GET:/", new HomeCommand());

        commands.put("GET:/login", new LoginCommand());
        commands.put("POST:/login", new LoginSubmitCommand());

        commands.put("GET:/register", new RegisterCommand());
        commands.put("POST:/register", new RegisterSubmitCommand());

        commands.put("GET:/logout", new LogoutCommand());

        commands.put("GET:/user", new UserPageCommand());
        commands.put("GET:/admin", new AdminPageCommand());

        commands.put("GET:/phrases_all", new PhrasePageCommand());
        commands.put("GET:/bots_all", new TelegramBotPageCommand());

        commands.put("GET:/user/createPhrase", new CreatePhraseCommand());
        commands.put("POST:/user/createPhrase", new CreatePhraseSubmitCommand());
    }

    /**
     * this method replaces all digits between slashes to "id"
     * this is necessary because search algorithm doesn't support regular expressions
     *
     * @param url
     * @return converted url
     */
    private String removeAllNumbersFromUrl(String url) {
        return url.replaceAll(NUMBER_BETWEEN_SLASHES_PATTERN, "/id");
    }
}