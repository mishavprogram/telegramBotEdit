package stalkerbotGUI.dao;

import stalkerbotGUI.dao.impl.jdbc.JDBCDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract TelegramBotDao createTelegramBotDao();
    public abstract ExtendTelegramBotDao createExtendTelegramBotDao();

    public abstract PhraseDao createPhraseDao();
    public abstract ExtendPhraseDao createExtendPhraseDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
