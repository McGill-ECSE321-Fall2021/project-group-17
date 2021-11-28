package ca.mcgill.ecse321.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import javax.persistence.*;

@Entity
public class Customer extends PersonRole{
    private int penalty;

    private Address address;

    private LibraryCard libCard;

    private boolean isVerified;

    public Customer(){

    }

    public Customer(int id, Person person, int penalty,
                    Address address, LibraryCard libCard, OnlineAccount account) {
        super(id, person, account);
        this.penalty = penalty;
        this.address = address;
        this.libCard = libCard;
    }

    //Getters
    public int getPenalty(){
        return this.penalty;
    }
    @OneToOne
    @JoinColumn()
    public Address getAddress(){ return this.address;}
    @OneToOne
    @JsonManagedReference
    @JoinColumn()
    public LibraryCard getLibraryCard(){ return this.libCard;}

    //Setters
    public void setPenalty(int penalty){
        this.penalty = penalty;
    }
    public void setAddress(Address address){this.address = address;}
    public void setLibraryCard(LibraryCard libCard){ this.libCard = libCard;}

    public boolean getIsVerified(){
        return this.isVerified;
    }
    public void setIsVerified(boolean b){
        this.isVerified=b;
    }
}
