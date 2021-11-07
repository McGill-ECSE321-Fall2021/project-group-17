package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.Person;

public abstract class PersonRoleDTO {
    private int id;
    private Person person;
    private OnlineAccount account;

    public PersonRoleDTO(){

    }
    public PersonRoleDTO(int id, Person person, OnlineAccount account){
        this.id = id;
        this.person = person;
        this.account = account;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){this.id = id;}
    public Person getPerson(){return person;}
    public void setPerson(Person person){this.person = person;}
    public OnlineAccount getAccount(){return account;}
    public void setAccount(OnlineAccount account){this.account = account;}
}
