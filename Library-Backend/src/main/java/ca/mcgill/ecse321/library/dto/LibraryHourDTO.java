package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class LibraryHourDTO {
	
	private int id;
    private LibraryManagementSystem system;
    private Library library;
    
    public int getId() {
    	return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public Library getLibrary() {
    	return library;
    }
    
    public void setLibrary(Library library){
        this.library = library;
    }
    
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }


}
