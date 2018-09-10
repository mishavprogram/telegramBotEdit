package stalkerbotGUI.utils.extractors;

import stalkerbotGUI.model.entity.ExtendPhrase;
import stalkerbotGUI.model.entity.ExtendTelegramBot;
import stalkerbotGUI.model.entity.Phrase;
import stalkerbotGUI.model.entity.TelegramBot;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.model.entity.enums.CrudAction;
import stalkerbotGUI.model.entity.enums.RoleType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetExtractors {

    /**
     * Повертає TelegramBot в якого внутрішні об'єкти це null,
     * або такі, що містять лише ID
     * @param rs
     * @return
     * @throws SQLException
     */
    public static TelegramBot extractTelegramBotFromResultSet(ResultSet rs) throws SQLException {

        TelegramBot telegramBot = new TelegramBot.Builder()
            .setId(rs.getLong("id_telbot"))
            .setFullName(rs.getString("bot_fullname"))
            .setAuthor(extractUserFromResultSet(rs))
            .setBotUserName(rs.getString("bot_username"))
            .setToken(rs.getString("bot_token"))
            .getTelegramBot();

        return telegramBot;
    }

    /**
     * Повертає ExtendTelegramBot в якого внутрішні об'єкти це null,
     * або такі, що містять лише ID
     * @param rs
     * @return
     * @throws SQLException
     */
    public static ExtendTelegramBot extendExtendTelegramBotFromResultSet(ResultSet rs) throws SQLException{

        ExtendTelegramBot telegramBot = new ExtendTelegramBot.Builder()
            .setExtendId(rs.getInt("id"))
            .setCrudAction(CrudAction.getAction(rs.getString("crud_action")))
            .setId(rs.getInt("id_telbot"))
            .setFullName(rs.getString("bot_fullname"))
            .setAuthor(extractUserFromResultSet(rs))
            .setLastModif(new User.Builder()
                            .setId(rs.getLong("bot_lastmodificator"))
                          .getInstance())
            .setUserName(rs.getString("bot_username"))
            .setToken(rs.getString("bot_token"))
            .getExtendTelegramBot();

        return telegramBot;
    }

    /**
     * Повертає Phrase в якого внутрішні об'єкти це null,
     * або такі, що містять лише ID
     * @param rs
     * @return
     * @throws SQLException
     */
    public static Phrase extractPhraseFromResultSet(ResultSet rs) throws SQLException{

        Phrase phrase = new Phrase.Builder()
            .setId(rs.getLong("id_phrase"))
            .setText(rs.getString("phrase_text"))
            .setTelegramBot(new TelegramBot.Builder()
                        .setId(rs.getLong("phrase_bot_id"))
                            .getTelegramBot())
            .setAuthor(extractUserFromResultSet(rs))
            .setLastModif(new User.Builder()
                    .setId(rs.getLong("phrase_lastmodif_id"))
                    .getInstance())
            .getPhrase();

        return phrase;
    }

    /**
     * Повертає ExtendPhrase в якого внутрішні об'єкти це null,
     * або такі, що містять лише ID
     * @param rs
     * @return
     * @throws SQLException
     */
    public static ExtendPhrase extractExtendPhraseFromResultSet(ResultSet rs) throws SQLException{

        ExtendPhrase phrase = new ExtendPhrase.Builder()
            .setExtendId(rs.getInt("id"))
            .setCrudAction(CrudAction.getAction(rs.getString("crud_action")))
            .setId(rs.getLong("id_phrase"))
            .setText(rs.getString("phrase_text"))
            .setTelegramBot(new TelegramBot.Builder()
                                .setId(rs.getLong("phrase_bot_id"))
                                .getTelegramBot())
            .setAuthor(extractUserFromResultSet(rs))
            .setLastModif(new User.Builder()
                              .setId(rs.getLong("phrase_lastmodif_id"))
                              .getInstance())
            .getPhrase();

        return phrase;
    }

    public static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User.Builder()
            .setId(rs.getLong("user_id"))
            .setName(rs.getString("name"))
            .setSurname(rs.getString("surname"))
            .setEmail(rs.getString("email"))
            .setPasswordHash(rs.getString("password"))
            .setRole((RoleType.getRole(rs.getString("role"))))
            .getInstance();
        return user;
    }

}
