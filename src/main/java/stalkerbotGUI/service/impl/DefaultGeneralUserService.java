package stalkerbotGUI.service.impl;

import stalkerbotGUI.dao.DaoFactory;
import stalkerbotGUI.dao.ExtendPhraseDao;
import stalkerbotGUI.dao.ExtendTelegramBotDao;
import stalkerbotGUI.dao.PhraseDao;
import stalkerbotGUI.dao.TelegramBotDao;
import stalkerbotGUI.dao.UserDao;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.service.GeneralUserService;
import stalkerbotGUI.service.exception.UserAlreadyExistException;
import stalkerbotGUI.utils.HashUtils;

import java.util.Optional;

public abstract class DefaultGeneralUserService implements GeneralUserService {

    @Override
    //TODO пароль що приходить від юзера перетворити в хеш-код, і з ним вже гратись
    //TODO на передачу NULL погратись
    public Optional<User> login(String email, String password) {

        UserDao userDao = DaoFactory.getInstance().createUserDao();

        Optional<User> userOptional = userDao.getUserByEmail(email);

        if (userOptional.isPresent()){
            User user = userOptional.get();

            if (user.getPasswordHash().equals(HashUtils.getMD5Hash(password)))
                return userOptional;
        }

        userDao.close();

        return Optional.empty();
    }

    @Override
    //TODO Якщо такий юзер вже є в базі?
    //Контроллер нічого не знає, тому хай сервіс просто не створює ще такого самого, якщо вже є?
    public void create(User user) throws UserAlreadyExistException {

        UserDao userDao = DaoFactory.getInstance().createUserDao();

        Optional<User> userOptional = userDao.getUserByEmail(user.getEmail());

        if (userOptional.isPresent()){
            throw new UserAlreadyExistException("User with email "
                                                +userOptional.get().getEmail()
                                                +" already exist");
        }

        userDao.create(user);

        userDao.close();
    }

    @Override
    public Optional<User> getUser(long id) {
        Optional<User> optionalUser = Optional.empty();

        try(UserDao userDao = DaoFactory.getInstance().createUserDao()){
            optionalUser = userDao.findById(id);
        }

        return optionalUser;
    }

    @Override
    public long getCountOfExtendPhrases() {

        try(ExtendPhraseDao extendPhraseDao = DaoFactory.getInstance().createExtendPhraseDao()){

            long count = extendPhraseDao.getAllCount();

            return count;
        }

    }

    @Override
    public long getCountOfExtendPhrases(long userId) {

        try(ExtendPhraseDao extendPhraseDao = DaoFactory.getInstance().createExtendPhraseDao()){

            long count = extendPhraseDao.getAllByUserIdCount(userId);

            return count;
        }
    }

    @Override
    public long getCountOfPhrases() {
        try(PhraseDao phraseDao = DaoFactory.getInstance().createPhraseDao()){
            return phraseDao.getAllCount();
        }
    }

    @Override
    public long getCountOfPhrases(long userId) {
        try(PhraseDao phraseDao = DaoFactory.getInstance().createPhraseDao()){
            return phraseDao.getAllByUserIdCount(userId);
        }
    }

    @Override
    public long getCountOfTelegramBots() {
        try(TelegramBotDao telegramBotDao = DaoFactory.getInstance().createTelegramBotDao()){
            return telegramBotDao.getAllCount();
        }
    }

    @Override
    public long getCountOfTelegramBots(long userId) {
        try(TelegramBotDao telegramBotDao = DaoFactory.getInstance().createTelegramBotDao()){
            return telegramBotDao.getAllByUserIdCount(userId);
        }
    }

    @Override
    public long getCountOfExtendTelegramBots() {
        try (ExtendTelegramBotDao extendTelegramBotDao = DaoFactory.getInstance().createExtendTelegramBotDao()){
            return extendTelegramBotDao.getAllCount();
        }
    }

    @Override
    public long getCountOfExtendTelegramBots(long userId) {
        try(ExtendTelegramBotDao extendTelegramBotDao = DaoFactory.getInstance().createExtendTelegramBotDao()){
            return extendTelegramBotDao.getAllByUserIdCount(userId);
        }
    }
}
