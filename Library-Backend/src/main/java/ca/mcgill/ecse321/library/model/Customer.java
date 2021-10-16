package ca.mcgill.ecse321.library.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Customer extends PersonRole{
    private int penalty;

    private List<Loan> loans;//0..5

    private Address address;

    private LibraryCard libCard;

    public Customer(){

    }

    public Customer(String roleType, Person person, int penalty,
                    List<Loan> loans, Address address, LibraryCard libCard, OnlineAccount account) {
        super(roleType, person, account);
        this.penalty = penalty;
        this.loans = loans;
        this.address = address;
        this.libCard = libCard;
    }

    //Getters
    public int getPenalty(){
        return this.penalty;
    }
    @OneToMany
    @JoinColumn()
    public List<Loan> getLoans(){ return this.loans;}
    @OneToOne
    @JoinColumn()
    public Address getAddress(){ return this.address;}
    @OneToOne
    @JoinColumn()
    public LibraryCard getLibraryCard(){ return this.libCard;}

    //Setters
    public void setPenalty(int penalty){
        this.penalty = penalty;
    }
    public void setLoans(List<Loan> loans){ this.loans = loans;}
    public void setAddress(Address address){this.address = address;}
    public void setLibraryCard(LibraryCard libCard){ this.libCard = libCard;}
    public void addLoan(Loan loan){ this.loans.add(loan);}
    public void removeLoan(Loan loan){ this.loans.remove(loan);}

}
