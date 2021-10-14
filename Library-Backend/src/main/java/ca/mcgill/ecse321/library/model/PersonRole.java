package ca.mcgill.ecse321.library.model;

public abstract class PersonRole {
    private Person person;

    public PersonRole(){

    }
    public PersonRole(Person person){
        this.person = person;
    }

    public Person getPerson(){
        return this.person;
    }

    public void setPerson(Person person){
        this.person = person;
    }
}
