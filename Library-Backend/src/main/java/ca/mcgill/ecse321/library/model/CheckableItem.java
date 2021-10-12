package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class CheckableItem extends Item {
    
}
