package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Library {
    private int id;
    private String name;
    private LibraryManagementSystem system;
    
    public Library() {}
    
    public Library(int id) {
    	this.id = id;
    }

    @Id
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
}
