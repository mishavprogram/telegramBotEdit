package stalkerbotGUI.dao.impl.jdbc.norm_table;

import org.apache.log4j.Logger;
import stalkerbotGUI.dao.TelegramBotDao;
import stalkerbotGUI.dao.exception.DaoException;
import stalkerbotGUI.model.entity.Phrase;
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
import javax.ws.rs.NotSupportedException;

public class JDBCTelegramBotDao implements TelegramBotDao {

    private static final String INSERT = "insert into telegrambot_table\n"
                                         + "    values (null , ?, ?, ?, ?, ?);";

    private static final String SELECT_BY_ID = "select * from telegrambot_table as tpt left join user_table u "
                                               + "on tpt.bot_author = u.user_id\n"
                                               + "where id_telbot = ?;";

    private static final String GET_ALL_WITH_LIMIT_OFFSET = "select *\n"
                                                            + "  from telegrambot_table as tpt left join user_table\n"
                                                            + "    on tpt.bot_author = user_table.user_id\n"
                                                            + "limit ? offset ?;";

    private static final String GET_ALL_WITH_LIMIT_OFFSET_BY_AUTHOR_ID = "select tpt.*\n"
                                                                         + "  from telegrambot_table as tpt where tpt.bot_author = ?\n"
                                                                         + "limit ? offset ?;";

    private static final String GET_ALL_COUNT = "select count(*) from telegrambot_table";

    private static final String GET_BY_USER_COUNT = "select count(*) from telegrambot_table where bot_author=?";

    private static Logger logger = Logger.getLogger(JDBCTelegramBotDao.class);

    private Connection connection;

    public JDBCTelegramBotDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(TelegramBot person) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1,person.getFullName());
            //TODO - null exception????
            preparedStatement.setLong(2, person.getAuthor().getId());

            if (person.getLastModif()==null)
            preparedStatement.setNull(3, Types.LONGVARCHAR);
            else preparedStatement.setLong(3, person.getLastModif().getId());

            preparedStatement.setString(4, person.getBotUserName());
            preparedStatement.setString(5, person.getBotToken());

            preparedStatement.execute();
        }catch (SQLException ex){
            logger.debug(ex.getMessage());
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<TelegramBot> findById(long id) {
        Optional<TelegramBot> optionalBot = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            if (set.isBeforeFirst()){
                set.next();

                optionalBot = Optional.of(ResultSetExtractors.extractTelegramBotFromResultSet(set));
                logger.debug(optionalBot);
            }

        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return optionalBot;
    }

    @Override
    public void update(TelegramBot person) {
        throw new NotSupportedException();
    }

    @Override
    public List<TelegramBot> getAll() {
        throw new NotSupportedException();
    }

    @Override
    public List<TelegramBot> getAll(int limit, int offset) {
        List<TelegramBot> telegramBots = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WITH_LIMIT_OFFSET);

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            while (set.next()){
                TelegramBot telegramBot = ResultSetExtractors.extractTelegramBotFromResultSet(set);
                telegramBots.add(telegramBot);
                logger.debug(telegramBot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telegramBots;
    }

    @Override
    public List<TelegramBot> getAll(int limit, int offset, long author_id) {

        List<TelegramBot> telegramBots = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WITH_LIMIT_OFFSET_BY_AUTHOR_ID);

            preparedStatement.setLong(1,author_id);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            while (set.next()){
                TelegramBot telegramBot = ResultSetExtractors.extractTelegramBotFromResultSet(set);
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
        throw new NotSupportedException();
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

    @Override
    public long getAllCount() {

        long count = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COUNT);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            set.next();

            count = set.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public long getAllByUserIdCount(long userId) {
        long count = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_COUNT);

            preparedStatement.setLong(1, userId);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            set.next();

            count = set.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
