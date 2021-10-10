package ca.mcgill.ecse321.library.model;

import javax.persistence.Id;

public class Library {
    private String name;

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
