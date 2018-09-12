package stalkerbotGUI.service.impl;

import org.apache.log4j.Logger;
import stalkerbotGUI.dao.DaoFactory;
import stalkerbotGUI.dao.ExtendPhraseDao;
import stalkerbotGUI.dao.PhraseDao;
import stalkerbotGUI.dao.exception.DaoException;
import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.model.entity.Phrase;
import stalkerbotGUI.model.entity.enums.CrudAction;
import stalkerbotGUI.service.AdminService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultAdminService extends DefaultGeneralUserService implements AdminService {

    private Logger logger = Logger.getLogger(DefaultAdminService.class);

    @Override
    public List<ExtendPhrase> getExtendPhrases(int limit, int offset) {
        //TODO
        List<ExtendPhrase> extendPhraseList = new ArrayList<>();

        //TODO test if exception. Why initialization of extendPhraseList is not important?
        try(ExtendPhraseDao extendPhraseDao = DaoFactory.getInstance().createExtendPhraseDao()){
            extendPhraseList = extendPhraseDao.getAll(limit, offset);
        }

        return extendPhraseList;
    }

    @Override
    public Optional<ExtendPhrase> getExtendPhrase(long id) {
        Optional<ExtendPhrase> phraseOptional = Optional.empty();

        try(ExtendPhraseDao extendPhraseDao = DaoFactory.getInstance().createExtendPhraseDao()){
            phraseOptional = extendPhraseDao.findById(id);
        }

        return phraseOptional;
    }

    @Override
    public void confirm(long id) {
        try (PhraseDao phraseDao = DaoFactory.getInstance().createPhraseDao()){
            Connection connection = phraseDao.getConnection();

            try(ExtendPhraseDao extendPhraseDao = DaoFactory.getInstance().createExtendPhraseDao()){
                extendPhraseDao.changeConnection(connection);

                try {
                    connection.setAutoCommit(false);

                    Optional<ExtendPhrase> optionalExtendPhrase = extendPhraseDao.findById(id);
                    if (optionalExtendPhrase.isPresent()) {
                        ExtendPhrase extendPhrase = optionalExtendPhrase.get();

                        if (extendPhrase.getCrudAction()==CrudAction.CREATE){
                            Phrase phrase = new Phrase.Builder()
                                .setText(extendPhrase.getText())
                                .setAuthor(extendPhrase.getAuthor())
                                .setTelegramBot(extendPhrase.getTelegramBot())
                                .getPhrase();

                            phraseDao.create(phrase);
                            extendPhraseDao.delete(id);
                        }
                    }
                    connection.commit();
                } catch (SQLException e) {
                    try {
                        connection.rollback();//TODO why???
                    } catch (SQLException e1) {
                        throw new DaoException(e.getMessage());
                    }
                    throw new DaoException(e.getMessage());
                }

            }
        }
    }

    @Override
    public void reject(long id) {
        try(ExtendPhraseDao extendPhraseDao = DaoFactory.getInstance().createExtendPhraseDao()){
            extendPhraseDao.delete(id);
        }
    }
}
