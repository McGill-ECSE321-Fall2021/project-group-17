package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class CheckableItem extends Item {
    public CheckableItem(){

    }
    public CheckableItem(String name, Date date) {
        super(name,date);
    }
}
