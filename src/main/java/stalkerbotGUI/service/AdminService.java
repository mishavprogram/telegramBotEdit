package stalkerbotGUI.service;

import stalkerbotGUI.model.entity.ExtendPhrase;

import java.util.List;

public interface AdminService extends GeneralUserService {
    List<ExtendPhrase> getExtendPhrases(int limit, int offset);
}
