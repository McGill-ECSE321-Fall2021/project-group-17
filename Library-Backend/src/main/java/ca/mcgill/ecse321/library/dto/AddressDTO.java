package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class AddressDTO {
	
	public Customer customer;
	public int id;
    private LibraryManagementSystem system;
    
    public AddressDTO() {}
    
    public AddressDTO(int id) {
    	this.id = id;
    }
    
    public int getAddressID() {
        return id;
    }
    
    public void setAddressID(int id){
        this.id = id;
    }
    
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
    
    public Customer getCustomer() {
    	return this.customer;
    }
    
    public void setCustomer(Customer customer) {
    	this.customer = customer;
    }


}