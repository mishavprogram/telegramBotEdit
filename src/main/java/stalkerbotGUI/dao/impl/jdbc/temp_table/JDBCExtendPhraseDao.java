package stalkerbotGUI.dao.impl.jdbc.temp_table;

import org.apache.log4j.Logger;
import stalkerbotGUI.dao.ExtendPhraseDao;
import stalkerbotGUI.dao.exception.DaoException;
import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.model.entity.enums.CrudAction;
import stalkerbotGUI.utils.extractors.ResultSetExtractors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCExtendPhraseDao implements ExtendPhraseDao {

    private static final String INSERT = "insert into temp_phrase_table\n"
                                         + "values (null, ?, ? , ?, ?, ?, ?)";

    private static final String GET_ALL_WITH_LIMIT_OFFSET = "select *\n"
                                                            + "  from temp_phrase_table as tpt left join user_table\n"
                                                            + "    on tpt.phrase_author_id = user_table.user_id\n"
                                                            + "limit ? offset ?;";

    private static final String GET_ALL_WITH_LIMIT_OFFSET_BY_AUTHOR_ID = "select *\n"
                                                                         + "  from temp_phrase_table as tpt left join user_table\n"
                                                                         + "    on tpt.phrase_author_id = user_table.user_id\n"
                                                                         + "where tpt.phrase_author_id = ? "
                                                                         + "limit ? offset ?;";

    private static final String GET_ALL_COUNT = "select count(*) from temp_phrase_table";

    private static final String GET_BY_USER_COUNT = "select count(*) from temp_phrase_table where phrase_author_id=?";

    private static Logger logger = Logger.getLogger(JDBCExtendPhraseDao.class);

    private Connection connection;

    public JDBCExtendPhraseDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(ExtendPhrase object) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1, object.getCrudAction().name());

            if (object.getCrudAction()!=(CrudAction.CREATE))
                preparedStatement.setLong(2, object.getId());
            else preparedStatement.setNull(2, Types.LONGVARCHAR);

            preparedStatement.setString(3,object.getText());

            if (object.getTelegramBot()==null)
                preparedStatement.setNull(4, Types.LONGVARCHAR);
            else preparedStatement.setLong(4, object.getTelegramBot().getId());

            preparedStatement.setLong(5, object.getAuthor().getId());

            if (object.getLastModif()==null)
                preparedStatement.setNull(6, Types.LONGVARCHAR);
            else preparedStatement.setLong(6, object.getLastModif().getId());

            preparedStatement.execute();
            //preparedStatement.close();
        }catch (SQLException ex){
            logger.debug(ex.getMessage());
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<ExtendPhrase> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void update(ExtendPhrase object) {

    }

    @Override
    public List<ExtendPhrase> getAll() {
        return null;
    }

    @Override
    public List<ExtendPhrase> getAll(int limit, int offset) {
        List<ExtendPhrase> extendPhraseList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WITH_LIMIT_OFFSET);

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            while (set.next()){

                ExtendPhrase extendPhrase = ResultSetExtractors.extractExtendPhraseFromResultSet(set);
                extendPhraseList.add(extendPhrase);
                logger.debug(extendPhrase);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return extendPhraseList;
    }

    @Override
    public List<ExtendPhrase> getAll(int limit, int offset, long author_id) {

        //TODO - linked list ?
        List<ExtendPhrase> extendPhraseList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WITH_LIMIT_OFFSET_BY_AUTHOR_ID);

            /*logger.debug("author_id = "+author_id);
            logger.debug("limit = "+limit);
            logger.debug("offset = "+offset);*/

            preparedStatement.setLong(1, author_id);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            while (set.next()){

                ExtendPhrase extendPhrase = ResultSetExtractors.extractExtendPhraseFromResultSet(set);
                extendPhraseList.add(extendPhrase);
                logger.debug(extendPhrase);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return extendPhraseList;
    }

    @Override
    public void delete(long id) {

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

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
