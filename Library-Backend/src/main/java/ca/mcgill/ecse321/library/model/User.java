package ca.mcgill.ecse321.library.model;

import java.util.List;
import javax.persistence.*;
import java.sql.Date;

public class User extends PersonRole{
    private int penalty;
    private OnlineAccount oAccount;
    private List<Loan> loans;//0..5
    private Address address;
    private LibraryCard libCard;

    public User(){

    }

    public User(String roleType, Person person, int penalty, OnlineAccount oAccount, List<Loan> loans, Address address, LibraryCard libCard) {
        super(roleType, person);
        this.penalty = penalty;
        this.oAccount = oAccount;
        this.loans = loans;
        this.address = address;
        this.libCard = libCard;
    }

    //Getters
    public int getPenalty(){
        return this.penalty;
    }
    public OnlineAccount getAccount(){ return this.oAccount;}
    public List<Loan> getLoans(){ return this.loans;}
    public Address getAddress(){ return this.address;}
    public LibraryCard getLibraryCard(){ return this.libCard;}

    //Setters
    public void setPenalty(int penalty){
        this.penalty = penalty;
    }
    public void setOnlineAccount(OnlineAccount oAccount){ this.oAccount = oAccount;}
    public void setLoans(List<Loan> loans){ this.loans = loans;}
    public void setAddress(Address address){this.address = address;}
    public void setLibraryCard(LibraryCard libCard){ this.libCard = libCard;}
    public void addLoan(Loan loan){ this.loans.add(loan);}
    public void removeLoan(Loan loan){ this.loans.remove(loan);}

}
