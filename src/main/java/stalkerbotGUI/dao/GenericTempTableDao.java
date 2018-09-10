package stalkerbotGUI.dao;

public interface GenericTempTableDao<T> extends GenericDao<T>{

    //enum CrudAction {CREATE, READ, UPDATE, DELETE}

    long getAllCount();

    long getAllByUserIdCount(long userId);

}
