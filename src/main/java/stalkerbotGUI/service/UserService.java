package stalkerbotGUI.service;

import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.model.entity.ExtendTelegramBot;
import stalkerbotGUI.model.entity.Phrase;
import stalkerbotGUI.model.entity.TelegramBot;
import stalkerbotGUI.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GeneralUserService {

    List<ExtendPhrase> getExtendPhrases(int limit, int offset, User author);

    List<Phrase> getPhrases(int limit, int offset);

    List<ExtendTelegramBot> getExtendTelegramBots(int limit, int offset, User author);

    List<TelegramBot> getTelegramBots(int limit, int offset);

    void create(Phrase phrase);

    Optional<TelegramBot> getTelegramBot(String bot_name);
}
