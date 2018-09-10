package stalkerbotGUI.model.entity;

import stalkerbotGUI.model.Dependent;

import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Phrase extends Dependent {

    private String text;

    @ManyToOne
    private TelegramBot telegramBot;

    public Phrase(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TelegramBot getTelegramBot() {
        return telegramBot;
    }

    public void setTelegramBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public static class Builder{
        private Phrase phrase = new Phrase();

        public Builder setId(long id){
            phrase.setId(id);
            return this;
        }

        public Builder setText(String text){
            phrase.setText(text);
            return this;
        }

        public Builder setTelegramBot(TelegramBot bot){
            phrase.setTelegramBot(bot);
            return this;
        }

        public Builder setAuthor(User user){
            phrase.setAuthor(user);
            return this;
        }

        public Builder setLastModif(User user){
            phrase.setLastModif(user);
            return this;
        }

        public Phrase getPhrase(){
            return phrase;
        }
    }

    @Override
    public String toString() {
        return "Phrase{" +
               "text='" + text + '\'' +
               ", telegramBot=" + telegramBot +
               "} " + super.toString();
    }
}
