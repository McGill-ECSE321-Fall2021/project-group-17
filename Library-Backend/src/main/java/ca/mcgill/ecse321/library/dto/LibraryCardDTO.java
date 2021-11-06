package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Customer;

public class LibraryCardDTO {

    private int id;
    private Customer customer;

    public LibraryCardDTO(){}

    public LibraryCardDTO(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) { this.id = id; }


    public Customer getCustomer(){ return this.customer; }

    public void setCustomer(Customer customer){ this.customer = customer; }
}

