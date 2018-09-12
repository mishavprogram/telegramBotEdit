package stalkerbotGUI.dao.impl.jdbc;

import org.apache.log4j.Logger;
import stalkerbotGUI.dao.UserDao;
import stalkerbotGUI.dao.exception.DaoException;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.utils.extractors.ResultSetExtractors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.NotSupportedException;

public class JDBCUserDao implements UserDao {
    public static volatile int count = 0;

    private static final String INSERT_USER = "INSERT INTO test1.user_table (name, surname, role, email, password) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_EMAIL = "select * from test1.user_table where email = ?;";
    private static final String SELECT_USER_BY_ID = "select * from test1.user_table where user_id = ?;";
    private static final String DELETE_BY_ID = "delete from user_table where user_id=?";

    private Connection connection;

    private static Logger logger = Logger.getLogger(JDBCUserDao.class);

    JDBCUserDao(Connection connection) {
        this.connection = connection;
        count++;
    }

    public static synchronized void incCount(){
        count++;
    }

    public static synchronized void decCount(){
        count--;
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(5, user.getPasswordHash());
            preparedStatement.setString(3, user.getRole().toString());

            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> user = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet set = preparedStatement.getResultSet();

            if (set.isBeforeFirst()) {
                set.next();
                user = Optional.of(ResultSetExtractors.extractUserFromResultSet(set));
                logger.debug(user);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        throw new NotSupportedException();
    }

    @Override
    public List<User> getAll(int limit, int offset) {
        throw new NotSupportedException();
    }

    @Override
    public List<User> getAll(int limit, int offset, long author_id) {
        return null;
    }

    @Override
    public void update(User entity) {
        throw new NotSupportedException();
    }

    @Override
    public void delete(long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)){
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }


    @Override
    public void close() {
        try {
            System.out.println(connection.getClass());
            connection.close();
            decCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> user = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            ResultSet set = preparedStatement.getResultSet();

            if (set.isBeforeFirst()) {
                set.next();
                //UserMapper userMapper = new UserMapper();
                user = Optional.of(ResultSetExtractors.extractUserFromResultSet(set));
                logger.debug(user);

            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return user;
    }
}

