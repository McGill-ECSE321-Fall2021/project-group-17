package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity

public class Person {

    private int id;
    private String name;

    private LibraryManagementSystem system;


    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {return id;}

    public void setId(Integer Id){this.id = Id;}

    private List<PersonRole> personRoleList;


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

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "roleType")
    public List<PersonRole> getPersonRoleList() {
        return personRoleList;
    }

    public void setPersonRoleList(List<PersonRole> personRoleList) {
        this.personRoleList = personRoleList;
    }
}
