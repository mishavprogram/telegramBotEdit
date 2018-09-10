package stalkerbotGUI.model.entity.enums;

public enum CrudAction {
    CREATE("CREATE"),
    READ("READ"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String actionName;

    CrudAction(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public static CrudAction getAction(String actionName) {
        for (CrudAction crudAction : values()) {
            if (crudAction.getActionName().equals(actionName))
                return crudAction;
        }
        return null;
    }
}
