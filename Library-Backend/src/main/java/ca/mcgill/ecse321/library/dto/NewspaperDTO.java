package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class NewspaperDTO {
    private int id;
    private String name;
    private Date datePublished;
    private String headline;

    public NewspaperDTO() {

    }

    public NewspaperDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getDatePublished() {
        return this.datePublished;
    }

    public String getHeadline() {
        return this.headline;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

}
