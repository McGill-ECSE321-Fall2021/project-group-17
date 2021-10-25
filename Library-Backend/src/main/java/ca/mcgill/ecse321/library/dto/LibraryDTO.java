package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class LibraryDTO {
	
	private String name;
    private LibraryManagementSystem system;
    
    public LibraryDTO() {}
    
    public LibraryDTO(String name) {
    	this.name = name;
    }
    
    public String getName() {
        return name;
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


}
