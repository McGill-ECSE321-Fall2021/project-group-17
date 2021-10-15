package ca.mcgill.ecse321.library.model;

import java.util.List;
import java.sql.Time;
import java.time.DayOfWeek;

public class Librarian extends PersonRole{
    public Librarian(){

    }
    public Librarian(String roleType, Person person){
        super(roleType, person);
    }
}
