package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Newspaper extends Item {
    private String headline;

    public Newspaper() {

    }

    public Newspaper(int id, String name, Date date, String headline) {
        super(id, name, date);
        this.headline = headline;
    }

    public String getHeadline() {
        return this.headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
