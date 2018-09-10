package stalkerbotGUI.model;

import stalkerbotGUI.model.entity.User;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Dependent extends Entity {

    @ManyToOne
    private User author;

    @ManyToOne
    private User lastModif;

    public Dependent() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getLastModif() {
        return lastModif;
    }

    public void setLastModif(User lastModif) {
        this.lastModif = lastModif;
    }

    @Override
    public String toString() {
        return "Dependent{" +
               "author=" + author +
               ", lastModif=" + lastModif +
               "} " + super.toString();
    }
}
