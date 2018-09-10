package stalkerbotGUI.model.entity;

import stalkerbotGUI.model.entity.enums.CrudAction;

public class ExtendTelegramBot extends TelegramBot {

    private int extendId;

    private CrudAction crudAction;

    public CrudAction getCrudAction() {
        return crudAction;
    }

    public void setCrudAction(CrudAction crudAction) {
        this.crudAction = crudAction;
    }

    public int getExtendId() {
        return extendId;
    }

    public void setExtendId(int extendId) {
        this.extendId = extendId;
    }

    public static class Builder{

        private ExtendTelegramBot bot = new ExtendTelegramBot();

        public Builder setId(long id){
            bot.setId(id);
            return this;
        }

        public Builder setFullName(String text){
            bot.setFullName(text);
            return this;
        }

        public Builder setAuthor(User user){
            bot.setAuthor(user);
            return this;
        }

        public Builder setLastModif(User user){
            bot.setLastModif(user);
            return this;
        }

        public Builder setExtendId(int id){
            bot.setExtendId(id);
            return this;
        }

        public Builder setUserName(String userName){
            bot.setBotUserName(userName);
            return this;
        }

        public Builder setToken(String token){
            bot.setBotToken(token);
            return this;
        }


        public Builder setCrudAction(CrudAction action){
            bot.setCrudAction(action);
            return this;
        }

        public ExtendTelegramBot getExtendTelegramBot(){
            return bot;
        }
    }

}
