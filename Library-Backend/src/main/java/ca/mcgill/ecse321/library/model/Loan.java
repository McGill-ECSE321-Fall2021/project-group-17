package ca.mcgill.ecse321.library.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Loan {

    @ManyToOne
    @JoinColumn()
    private LibraryManagementSystem system;

    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
