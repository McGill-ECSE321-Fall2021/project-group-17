package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Date;

public class User extends PersonRole{
    private int penalty;
    //private OnlineAccount oAccount;
    //private List<Loan> loans;
    //private Address address;
    //private LibraryCard libCard;

    public User(){
        this.penalty = 0;
        //this.oAccount = null;
        //this.loans = null;
        //this.address = null;
        //this.libCard = null;
    }

    public User(int penalty) {
        this.penalty = penalty;
        //initialize rest here.
    }

    //Getters
    public int getPenalty(){
        return this.penalty;
    }
    //public OnlineAccount getAccount(){ return this.oAccount;}
    //public List<Loan> getLoans(){ return this.loans;}
    //public Address getAddress(){ return this.address}
    //public LibraryCard getLibraryCard(){ return this.libCard;}

    //Setters
    public void setPenalty(int penalty){
        this.penalty = penalty;
    }
    //public void setOnlineAccount(OnlineAccount oAccount){ this.oAccount = oAccount;}
    //public void setLoans(List<Loan> loans){ this.loans = loans;}
    //public void setAddress(Address address){this.address = address;}
    //public void setLibraryCard(LibraryCard libCard){ this.libCard = libCard;}
    //public void addLoan(Loan loan){ this.loans.add(loan);}
    //public Loan removeLoan(Loan loan){ this.loans.remove(loan);}

}
