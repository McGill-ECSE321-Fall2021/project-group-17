package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class LibraryCardDTO {

    private String id;
    private LibraryManagementSystem system;

    public LibraryCardDTO(){}

    public LibraryCardDTO(String id){
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) { this.id = id; }

    public LibraryManagementSystem getSystem(){
        return system;
    }

    public void setSystem(LibraryManagementSystem system){
        this.system = system;
    }
}
