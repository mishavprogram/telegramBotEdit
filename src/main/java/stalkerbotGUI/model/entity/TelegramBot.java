package stalkerbotGUI.model.entity;

import stalkerbotGUI.model.Dependent;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TelegramBot extends Dependent {

    private String fullName;

    private String botUserName;

    private String botToken;

    public TelegramBot(){

    }

    public TelegramBot(String fullName, String botUserName, String botToken) {
        this.fullName = fullName;
        this.botUserName = botUserName;
        this.botToken = botToken;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBotUserName() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public static class Builder{
        private TelegramBot telegramBot = new TelegramBot();

        public Builder(){

        }

        public Builder setId(long id){
            telegramBot.setId(id);
            return this;
        }

        public Builder setFullName(String fullName){
            telegramBot.setFullName(fullName);
            return this;
        }

        public Builder setBotUserName(String botUserName){
            telegramBot.setBotUserName(botUserName);
            return this;
        }

        public Builder setToken(String token){
            telegramBot.setBotToken(token);
            return this;
        }

        public Builder setAuthor(User author){
            telegramBot.setAuthor(author);
            return this;
        }

        public Builder setLastModif(User user){
            telegramBot.setLastModif(user);
            return this;
        }

        public TelegramBot getTelegramBot(){
            return telegramBot;
        }
    }
}
