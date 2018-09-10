package stalkerbotGUI.dao.impl.jdbc;

import stalkerbotGUI.dao.DaoFactory;
import stalkerbotGUI.dao.ExtendPhraseDao;
import stalkerbotGUI.dao.ExtendTelegramBotDao;
import stalkerbotGUI.dao.PhraseDao;
import stalkerbotGUI.dao.TelegramBotDao;
import stalkerbotGUI.dao.UserDao;
import stalkerbotGUI.dao.exception.DaoException;
import stalkerbotGUI.dao.impl.jdbc.norm_table.JDBCPhraseDao;
import stalkerbotGUI.dao.impl.jdbc.norm_table.JDBCTelegramBotDao;
import stalkerbotGUI.dao.impl.jdbc.temp_table.JDBCExtendPhraseDao;
import stalkerbotGUI.dao.impl.jdbc.temp_table.JDBCExtendTelegramBotDao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class JDBCDaoFactory extends DaoFactory {

    private volatile DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private synchronized Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public TelegramBotDao createTelegramBotDao() {
        return new JDBCTelegramBotDao(getConnection());
    }

    @Override
    public ExtendTelegramBotDao createExtendTelegramBotDao() {
        return new JDBCExtendTelegramBotDao(getConnection());
    }

    @Override
    public PhraseDao createPhraseDao() {
        return new JDBCPhraseDao(getConnection());
    }

    @Override
    public ExtendPhraseDao createExtendPhraseDao() {
        return new JDBCExtendPhraseDao(getConnection());
    }
}
