package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.List;
import java.sql.Time;
import java.time.DayOfWeek;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Librarian extends PersonRole{
    public Librarian(){

    }
    public Librarian(String id, Person person, OnlineAccount account){
        super(id, person, account);
    }
}
