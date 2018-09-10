package stalkerbotGUI.dao;

import stalkerbotGUI.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> getUserByEmail(String email);
}
