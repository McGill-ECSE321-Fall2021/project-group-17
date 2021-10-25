package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.Person;

public class PersonRoleDTO {
    private LibraryManagementSystem system;
    private String id;
    private Person person;
    private OnlineAccount account;

    public PersonRoleDTO(){

    }
    public PersonRoleDTO(String id, Person person, OnlineAccount account){
        this.id = id;
        this.person = person;
        this.account = account;
    }

    public String getId() {
        return id;
    }
    public void setId(String id){this.id = id;}
    public Person getPerson(){return person;}
    public void setPerson(Person person){this.person = person;}
    public OnlineAccount getAccount(){return account;}
    public void setAccount(OnlineAccount account){this.account = account;}
    public LibraryManagementSystem getSystem(){return system;}
    public void setSystem(LibraryManagementSystem system){ this.system = system;}
}
