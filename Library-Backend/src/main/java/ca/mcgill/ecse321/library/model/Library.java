package ca.mcgill.ecse321.library.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Library {
    private String name;
    
    public Library() {
    	
    }
    
    public Library(String name) {
    	this.name = name;
    }

    @Id
    @Column(name="name", updatable=false, nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
