package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Person {

    private String name;

    private LibraryManagementSystem system;


    private Set<PersonRole> personRoleSet;

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public Set<PersonRole> getPersonRoleSet() {
        return personRoleSet;
    }

    public void setPersonRoleSet(Set<PersonRole> personRoleSet) {
        this.personRoleSet = personRoleSet;
    }
}
