package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;

@Entity
public class Newspaper extends Item {
    private String headline;

    public String getHeadline() {
        return this.headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
