package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Item {
    private int id;
    private String name;

    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
