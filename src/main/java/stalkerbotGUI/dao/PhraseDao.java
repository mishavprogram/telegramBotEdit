package stalkerbotGUI.dao;

import stalkerbotGUI.model.entity.Phrase;

import java.sql.Connection;

public interface PhraseDao extends GenericDao<Phrase> {
    long getAllCount();

    long getAllByUserIdCount(long userId);

    Connection getConnection();
}
