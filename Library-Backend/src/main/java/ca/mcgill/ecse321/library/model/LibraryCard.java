package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LibraryCard {
    private String id;

    @Id
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}