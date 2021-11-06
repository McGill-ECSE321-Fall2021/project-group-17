package ca.mcgill.ecse321.library.dto;

public class LibraryDTO {
	
	private int id;
	private String name;
    
    public LibraryDTO() {}
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public LibraryDTO(int id) {
    	this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
