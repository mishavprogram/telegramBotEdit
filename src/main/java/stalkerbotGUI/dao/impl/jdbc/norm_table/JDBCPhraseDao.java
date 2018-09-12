package stalkerbotGUI.dao.impl.jdbc.norm_table;

import org.apache.log4j.Logger;
import stalkerbotGUI.dao.PhraseDao;
import stalkerbotGUI.dao.exception.DaoException;
import stalkerbotGUI.model.entity.Phrase;
import stalkerbotGUI.utils.extractors.ResultSetExtractors;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCPhraseDao implements PhraseDao {

    private static final String INSERT = "insert into phrase_table\n"
                                         + "    values (null , ?, ?, ?, ? );";

    private static final String GET_ALL_WITH_LIMIT_OFFSET = "select *\n"
                                                            + "  from phrase_table as tpt left join user_table\n"
                                                            + "    on tpt.phrase_author_id = user_table.user_id\n"
                                                            + "limit ? offset ?;";

    private static final String GET_ALL_WITH_LIMIT_OFFSET_BY_AUTHOR_ID = "select tpt.*\n"
                                                                         + "  from phrase_table as tpt where tpt.phrase_author_id = ?\n"
                                                                         + "limit ? offset ?;";

    private static final String GET_ALL_COUNT = "select count(*) from phrase_table";

    private static final String GET_BY_USER_COUNT = "select count(*) from phrase_table where phrase_author_id=?";

    private Connection connection;

    private static Logger logger = Logger.getLogger(JDBCPhraseDao.class);

    public JDBCPhraseDao(Connection connection){
        this.connection = connection;
    }

    public Connection getConnection(){
        return connection;
    }

    //TODO check object for necessary fields, for example - author. ANNOTATIONS!!!!!!!!
    @Override
    public void create(Phrase object) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1,object.getText());

            if (object.getTelegramBot()==null)
                preparedStatement.setNull(2, Types.LONGVARCHAR);
            else preparedStatement.setLong(2, object.getTelegramBot().getId());

            preparedStatement.setLong(3, object.getAuthor().getId());

            if (object.getLastModif()==null)
                preparedStatement.setNull(4, Types.LONGVARCHAR);
            else preparedStatement.setLong(4, object.getLastModif().getId());

            preparedStatement.execute();
        }catch (SQLException ex){
            logger.debug(ex.getMessage());
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<Phrase> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void update(Phrase person) {
        throw new NotImplementedException();
    }

    @Override
    public List<Phrase> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public List<Phrase> getAll(int limit, int offset) {
        List<Phrase> extendPhraseList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WITH_LIMIT_OFFSET);

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            while (set.next()){

                Phrase extendPhrase = ResultSetExtractors.extractPhraseFromResultSet(set);
                extendPhraseList.add(extendPhrase);
                logger.debug(extendPhrase);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return extendPhraseList;
    }

    @Override
    public List<Phrase> getAll(int limit, int offset, long author_id) {
        List<Phrase> extendPhraseList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WITH_LIMIT_OFFSET_BY_AUTHOR_ID);

            preparedStatement.setLong(1,author_id);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            while (set.next()){

                Phrase extendPhrase = ResultSetExtractors.extractPhraseFromResultSet(set);
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
        throw new NotImplementedException();
    }

    //TODO danger! stacktrace
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
