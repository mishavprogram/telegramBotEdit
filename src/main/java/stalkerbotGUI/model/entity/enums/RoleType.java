package stalkerbotGUI.model.entity.enums;

public enum RoleType {
    USER("USER"),
    ADMIN("ADMIN");

    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RoleType getRole(String roleName) {
        for (RoleType roleType : values()) {
            if (roleType.getRoleName().equals(roleName))
                return roleType;
        }
        return null;
    }
}
