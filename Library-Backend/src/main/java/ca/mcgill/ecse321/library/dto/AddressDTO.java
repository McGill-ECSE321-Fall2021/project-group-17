package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class AddressDTO {
	
	public Customer customer;
	public int addressID;
    private LibraryManagementSystem system;
    
    public AddressDTO() {}
    
    public AddressDTO(int addressID) {
    	this.addressID = addressID;
    }
    
    public int getAddressID() {
        return addressID;
    }
    
    public void setAddressID(int id){
        this.addressID = id;
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
