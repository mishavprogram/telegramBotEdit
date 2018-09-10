package stalkerbotGUI.dao;

import stalkerbotGUI.model.entity.TelegramBot;

public interface TelegramBotDao extends GenericDao<TelegramBot> {
    long getAllCount();

    long getAllByUserIdCount(long userId);
}
