package stalkerbotGUI.utils.extractors;

import stalkerbotGUI.model.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface defines class to extract dtos from result set
 * @param <E>
 */
public interface ResultSetExtactor <E extends Entity> {
    E extract(ResultSet set) throws SQLException;
}
