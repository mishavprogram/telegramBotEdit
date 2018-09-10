package stalkerbotGUI.dao.impl.jdbc.temp_table;

import org.apache.log4j.Logger;
import stalkerbotGUI.dao.ExtendTelegramBotDao;
import stalkerbotGUI.dao.exception.DaoException;
import stalkerbotGUI.model.entity.ExtendTelegramBot;
import stalkerbotGUI.model.entity.TelegramBot;
import stalkerbotGUI.utils.extractors.ResultSetExtractors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCExtendTelegramBotDao implements ExtendTelegramBotDao {

    private static final String INSERT = "insert into temp_telegrambot_table\n"
                                         + "    values (null, ?, "
                                         + "null , ?, ?, ?, ?, ?);";

    private static final String GET_ALL_WITH_LIMIT_OFFSET = "select *\n"
                                                            + "  from temp_telegrambot_table as tpt left join user_table\n"
                                                            + "    on tpt.bot_author = user_table.user_id\n"
                                                            + "limit ? offset ?;";

    private static final String GET_ALL_WITH_LIMIT_OFFSET_BY_AUTHOR_ID = "select tpt.*\n"
                                                                         + "  from temp_telegrambot_table as tpt where tpt.bot_author = ?\n"
                                                                         + "limit ? offset ?;";

    private static final String GET_ALL_COUNT = "select count(*) from temp_telegrambot_table";

    private static final String GET_BY_USER_COUNT = "select count(*) from temp_telegrambot_table where bot_author=?";

    private Connection connection;

    private static Logger logger = Logger.getLogger(JDBCExtendTelegramBotDao.class);

    public JDBCExtendTelegramBotDao(Connection connection){
        this.connection = connection;
    }

    //TODO using ordinal() - is antipattern
    @Override
    public void create(ExtendTelegramBot object) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getCrudAction().name());
            preparedStatement.setString(2, object.getFullName());
            //TODO-null exception?
            preparedStatement.setLong(3, object.getAuthor().getId());

            if (object.getLastModif()==null)
                preparedStatement.setNull(4, Types.LONGVARCHAR);
            else preparedStatement.setLong(4, object.getLastModif().getId());

            preparedStatement.setString(5, object.getBotUserName());
            preparedStatement.setString(6, object.getBotToken());

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<ExtendTelegramBot> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void update(ExtendTelegramBot person) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ExtendTelegramBot> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ExtendTelegramBot> getAll(int limit, int offset) {
        List<ExtendTelegramBot> telegramBots = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WITH_LIMIT_OFFSET);

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            while (set.next()){
                ExtendTelegramBot telegramBot = ResultSetExtractors.extendExtendTelegramBotFromResultSet(set);
                telegramBots.add(telegramBot);
                logger.debug(telegramBot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telegramBots;
    }

    @Override
    public List<ExtendTelegramBot> getAll(int limit, int offset, long author_id) {
        List<ExtendTelegramBot> telegramBots = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WITH_LIMIT_OFFSET_BY_AUTHOR_ID);

            preparedStatement.setLong(1,author_id);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            while (set.next()){
                ExtendTelegramBot telegramBot = ResultSetExtractors.extendExtendTelegramBotFromResultSet(set);
                telegramBots.add(telegramBot);
                logger.debug(telegramBot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telegramBots;
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAllCount() {
        return 0;
    }

    @Override
    public long getAllByUserIdCount(long userId) {
        return 0;
    }

    @Override
    public void close() {
        try {
            System.out.println(connection.getClass());
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
