package stalkerbotGUI.model.entity;

import stalkerbotGUI.model.entity.enums.CrudAction;

public class ExtendPhrase extends Phrase {

    private int extendId;

    private CrudAction crudAction;

    public int getExtendId() {
        return extendId;
    }

    public void setExtendId(int extendId) {
        this.extendId = extendId;
    }

    public CrudAction getCrudAction() {
        return crudAction;
    }

    public void setCrudAction(CrudAction crudAction) {
        this.crudAction = crudAction;
    }

    public ExtendPhrase() {

    }

    public static class Builder{

        private ExtendPhrase phrase = new ExtendPhrase();

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

        public Builder setExtendId(int id){
            phrase.setExtendId(id);
            return this;
        }

        public Builder setCrudAction(CrudAction action){
            phrase.setCrudAction(action);
            return this;
        }

        public ExtendPhrase getPhrase(){
            return phrase;
        }
    }

    @Override
    public String toString() {
        return "ExtendPhrase{" +
               "extendId=" + extendId +
               ", crudAction=" + crudAction +
               "} " + super.toString();
    }
}
