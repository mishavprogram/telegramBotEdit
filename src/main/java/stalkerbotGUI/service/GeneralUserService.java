package stalkerbotGUI.service;

import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.service.exception.UserAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface GeneralUserService {

    Optional<User> login(String email, String password) throws UserAlreadyExistException;

    Optional<User> getUser(long id);

    void create(User user);

    long getCountOfExtendPhrases();

    long getCountOfExtendPhrases(long userId);

    long getCountOfPhrases();

    long getCountOfPhrases(long userId);

    long getCountOfTelegramBots();

    long getCountOfTelegramBots(long userId);

    long getCountOfExtendTelegramBots();

    long getCountOfExtendTelegramBots(long userId);

}
