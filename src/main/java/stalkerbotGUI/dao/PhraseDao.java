package stalkerbotGUI.dao;

import stalkerbotGUI.model.entity.Phrase;

public interface PhraseDao extends GenericDao<Phrase> {
    long getAllCount();

    long getAllByUserIdCount(long userId);
}
