package stalkerbotGUI.dao;

import stalkerbotGUI.model.entity.TelegramBot;

import java.util.Optional;

public interface TelegramBotDao extends GenericDao<TelegramBot> {
    long getAllCount();

    long getAllByUserIdCount(long userId);

    Optional<TelegramBot> findByName(String bot_name);
}
