package ca.mcgill.ecse321.library.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Library {
    private String name;
    
    public Library() {}
    
    public Library(String name) {
    	this.name = name;
    }

    @Id
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
