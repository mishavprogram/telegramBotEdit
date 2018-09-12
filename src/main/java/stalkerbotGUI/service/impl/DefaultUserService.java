package stalkerbotGUI.service.impl;

import org.apache.log4j.Logger;
import stalkerbotGUI.dao.DaoFactory;
import stalkerbotGUI.dao.ExtendPhraseDao;
import stalkerbotGUI.dao.ExtendTelegramBotDao;
import stalkerbotGUI.dao.PhraseDao;
import stalkerbotGUI.dao.TelegramBotDao;
import stalkerbotGUI.dao.UserDao;
import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.model.entity.ExtendTelegramBot;
import stalkerbotGUI.model.entity.Phrase;
import stalkerbotGUI.model.entity.TelegramBot;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.model.entity.enums.CrudAction;
import stalkerbotGUI.service.UserService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultUserService extends DefaultGeneralUserService implements UserService {

    private Logger logger = Logger.getLogger(DefaultUserService.class);

    @Override
    public List<ExtendPhrase> getExtendPhrases(int limit, int offset, User author) {

        List<ExtendPhrase> extendPhraseList = new ArrayList<>();

        try(UserDao userDao = DaoFactory.getInstance().createUserDao()){
            Optional<User> optionalUser = userDao.findById(author.getId());

            if (optionalUser.isPresent()){
                try(ExtendPhraseDao extendPhraseDao = DaoFactory.getInstance().createExtendPhraseDao()){
                    extendPhraseList = extendPhraseDao.getAll(limit, offset, author.getId());
                }
            }
            else{
                logger.warn(author+" not founded in database !!!");
            }
        }

        return extendPhraseList;
    }

    @Override
    public List<Phrase> getPhrases(int limit, int offset) {
        try(PhraseDao phraseDao = DaoFactory.getInstance().createPhraseDao()){
            return phraseDao.getAll(limit, offset);
        }
    }

    @Override
    public List<ExtendTelegramBot> getExtendTelegramBots(int limit, int offset, User author) {
        try(ExtendTelegramBotDao extendTelegramBotDao = DaoFactory.getInstance().createExtendTelegramBotDao()){
            return extendTelegramBotDao.getAll(limit, offset, author.getId());
        }
    }

    @Override
    public List<TelegramBot> getTelegramBots(int limit, int offset) {
        try(TelegramBotDao telegramBotDao = DaoFactory.getInstance().createTelegramBotDao()){
            return telegramBotDao.getAll(limit, offset);
        }
    }

    @Override
    public void create(Phrase phrase) {
        try(ExtendPhraseDao extendPhraseDao = DaoFactory.getInstance().createExtendPhraseDao()){

            ExtendPhrase extendPhrase = new ExtendPhrase.Builder()
                .setText(phrase.getText())
                .setAuthor(phrase.getAuthor())
                .setCrudAction(CrudAction.CREATE)
                .setTelegramBot(phrase.getTelegramBot())
                .getPhrase();

            extendPhraseDao.create(extendPhrase);
        }
    }

    @Override
    public Optional<TelegramBot> getTelegramBot(String bot_name) {
        try (TelegramBotDao telegramBotDao = DaoFactory.getInstance().createTelegramBotDao()) {
            return telegramBotDao.findByName(bot_name);
        }
    }
}
