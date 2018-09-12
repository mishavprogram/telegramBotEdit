package stalkerbotGUI.dao;

import stalkerbotGUI.model.entity.ExtendPhrase;

import java.sql.Connection;
import java.util.Optional;

public interface ExtendPhraseDao extends GenericTempTableDao<ExtendPhrase> {
    void changeConnection(Connection connection);
}
