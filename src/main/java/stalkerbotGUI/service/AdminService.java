package stalkerbotGUI.service;

import stalkerbotGUI.model.entity.ExtendPhrase;

import java.util.List;
import java.util.Optional;

public interface AdminService extends GeneralUserService {
    List<ExtendPhrase> getExtendPhrases(int limit, int offset);

    Optional<ExtendPhrase> getExtendPhrase(long id);

    void confirm(long id);

    void reject(long id);
}
