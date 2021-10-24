package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class PersonDTO {
    private String name;
    private int id;
    private LibraryManagementSystem system;

    public PersonDTO(){}

    public PersonDTO(int id){
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

    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }

    public int getId() {
        return id;
    }
}
