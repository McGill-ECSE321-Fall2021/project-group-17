package ca.mcgill.ecse321.library.dto;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse321.library.model.PersonRole;

public class ItemDTO {
	private Integer id;
    private String name;
    private Date datePublished;

    public ItemDTO(){}

    public ItemDTO(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }
    
    public Date getDatePublished() {
        return datePublished;
    }


}
