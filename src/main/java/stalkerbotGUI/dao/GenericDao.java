package stalkerbotGUI.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {//use extends AutoCloseable for try with resourses block

    void create(T object);

    Optional<T> findById(long id);

    void update(T object);

    List<T> getAll();

    List<T> getAll(int limit, int offset);

    List<T> getAll(int limit, int offset, long author_id);

    void delete(long id);

    void close();
}
