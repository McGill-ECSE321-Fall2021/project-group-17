package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.PersonRole;

import java.util.List;

public class PersonDTO {
    private String name;
    private int id;
    private List<PersonRole> personRoleList;

    public PersonDTO(){}

    public PersonDTO(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<PersonRole> getPersonRoleList() {
        return personRoleList;
    }

    public void setPersonRoleList(List<PersonRole> personRoleList) {
        this.personRoleList = personRoleList;
    }
}
