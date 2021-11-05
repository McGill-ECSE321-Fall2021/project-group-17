package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

import java.sql.Date;

public class NewspaperDTO {
    private int id;
    private String name;
    private Date datePublished;
    private String headline;
    private LibraryManagementSystem system;

    public NewspaperDTO() {

    }

    public NewspaperDTO(int id, String name, Date datePublished, String headline) {
        this.id = id;
        this.name = name;
        this.datePublished = datePublished;
        this.headline = headline;
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

    public LibraryManagementSystem getSystem() {
        return system;
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

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
