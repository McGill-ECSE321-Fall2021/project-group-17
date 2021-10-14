package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Date;

public class User extends PersonRole{
    private int penalty;
    //private onlineAccount oAccount;
    //private List<Loan> loans;
    //private Address address;
    //private libraryCard libCard;

    public User(){
    }

    public User(int penalty){
        this.penalty = penalty;
    }
    public int getPenalty(){
        return this.penalty;
    }

    public void setPenalty(int penalty){
        this.penalty = penalty;
    }

}
