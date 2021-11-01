package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class LibraryCardDTO {

    private int id;
    private LibraryManagementSystem system;
    private Customer customer;

    public LibraryCardDTO(){}

    public LibraryCardDTO(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) { this.id = id; }

    public LibraryManagementSystem getSystem(){
        return system;
    }

    public void setSystem(LibraryManagementSystem system){
        this.system = system;
    }

    public Customer getCustomer(){ return this.customer; }

    public void setCustomer(Customer customer){ this.customer = customer; }
}

