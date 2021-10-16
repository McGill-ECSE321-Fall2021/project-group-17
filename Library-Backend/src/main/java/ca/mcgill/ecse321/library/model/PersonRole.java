package ca.mcgill.ecse321.library.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public abstract class PersonRole {
    @ManyToOne
    @JoinColumn()
    private Person person;

    @ManyToOne
    @JoinColumn()
    private LibraryManagementSystem system;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
