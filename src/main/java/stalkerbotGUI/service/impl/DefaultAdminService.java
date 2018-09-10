package stalkerbotGUI.service.impl;

import org.apache.log4j.Logger;
import stalkerbotGUI.dao.DaoFactory;
import stalkerbotGUI.dao.ExtendPhraseDao;
import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.service.AdminService;

import java.util.ArrayList;
import java.util.List;

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
}
