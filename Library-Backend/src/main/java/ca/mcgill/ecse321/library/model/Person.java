package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.util.List;

@Entity

public class Person {

    private int id;
    private String name;

    private List<PersonRole> personRoleList;


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {return id;}

    public void setId(Integer Id){this.id = Id;}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(mappedBy = "id")
    public List<PersonRole> getPersonRoleList() {
        return personRoleList;
    }

    public void setPersonRoleList(List<PersonRole> personRoleList) {
        this.personRoleList = personRoleList;
    }
}
