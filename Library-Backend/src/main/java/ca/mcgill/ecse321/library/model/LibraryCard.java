package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class LibraryCard {

    private int id;

    private LibraryManagementSystem system;
    private Customer customer;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

    public void setId(int id) { this.id = id; }

    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem(){
        return system;
    }

    public void setSystem(LibraryManagementSystem system){
        this.system = system;
    }


    @OneToOne
    @JoinColumn
    public Customer getCustomer(){ return this.customer; }

    public void setCustomer(Customer customer){ this.customer = customer; }

}