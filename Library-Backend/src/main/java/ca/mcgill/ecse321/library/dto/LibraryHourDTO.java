package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Library;

public class LibraryHourDTO {
	
	private int id;
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



}
