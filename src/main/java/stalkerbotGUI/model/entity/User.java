package stalkerbotGUI.model.entity;

import stalkerbotGUI.model.Entity;
import stalkerbotGUI.model.entity.enums.RoleType;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Entity {
    private String name;
    private String surname;

    private RoleType role;
    private String email;
    private String passwordHash;

    public User() {
    }

    public User(long id, String name, String surname, RoleType role, String email, String passwordHash) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (passwordHash != null ? !passwordHash.equals(user.passwordHash) : user.passwordHash != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email.hashCode());
        result = 31 * result + (name.hashCode());
        result = 31 * result + (surname.hashCode());
        result = 31 * result + (passwordHash.hashCode());
        result = 31 * result + (role.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", role=" + role +
               ", email='" + email + '\'' +
               ", passwordHash='" + passwordHash + '\'' +
               "} " + super.toString();
    }

    public static class Builder {
        private User instance = new User();

        public Builder() {

        }

        public Builder setId(long id) {
            instance.setId(id);
            return this;
        }

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            instance.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            instance.email = email;
            return this;
        }

        public Builder setPasswordHash(String passwordHash) {
            instance.passwordHash = passwordHash;
            return this;
        }

        public Builder setRole(RoleType role) {
            instance.role = role;
            return this;
        }

        public User getInstance() {
            return instance;
        }
    }
}
