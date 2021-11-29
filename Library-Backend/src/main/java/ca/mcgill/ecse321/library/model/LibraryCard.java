package ca.mcgill.ecse321.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class LibraryCard {

    private int id;

    private Customer customer;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

    public void setId(int id) { this.id = id; }


    @OneToOne
    @JsonBackReference
    @JoinColumn
    public Customer getCustomer(){ return this.customer; }

    public void setCustomer(Customer customer){ this.customer = customer; }

}