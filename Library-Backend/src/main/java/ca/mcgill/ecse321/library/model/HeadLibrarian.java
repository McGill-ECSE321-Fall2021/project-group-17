package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;

@Entity
public class HeadLibrarian extends Librarian{
    public HeadLibrarian(){

    }
    public HeadLibrarian(int id, Person person, OnlineAccount account){
        super(id, person, account);
    }
}
