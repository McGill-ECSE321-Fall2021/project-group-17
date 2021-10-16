package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Person {
    @Id
    private String name;

    @ManyToOne
    @JoinColumn()
    private LibraryManagementSystem system;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<PersonRole> personRoleSet;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }

    public Set<PersonRole> getPersonRoleSet() {
        return personRoleSet;
    }

    public void setPersonRoleSet(Set<PersonRole> personRoleSet) {
        this.personRoleSet = personRoleSet;
    }
}
