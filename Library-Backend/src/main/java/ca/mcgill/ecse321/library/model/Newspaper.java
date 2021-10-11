package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Newspaper extends Item {
    private Date datePublished;

    public Date getDatePublished() {
        return this.datePublished;
    }

    public void setDatePublished(Date date) {
        this.datePublished = date;
    }
}
