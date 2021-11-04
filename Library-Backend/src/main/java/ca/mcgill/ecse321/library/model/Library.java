package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Library {
    private int id;
    private String name;
    private LibraryManagementSystem system;

    public Library() {
    }

    public Library(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }

=======
>>>>>>> 9a7720efbb1033d69dba58ba1ce14911ae029177
    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}

