package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersonRole {

    @ManyToOne
    @JoinColumn()
    private Person person;
    @ManyToOne
    @JoinColumn()
    private LibraryManagementSystem system;
    private String roleType;
    //Association
    //private Person person;

    public PersonRole(){

    }
    public PersonRole(String type, Person person){
        this.roleType = type;
        //this.person = person;
    }

    @Id
    @Column (name="roleType", updatable = false, nullable = false)
    public String getId(){return this.roleType;}
    public void setId(String roleType){this.roleType = roleType;}

    public Person getPerson(){return this.person;}
    public void setPerson(Person person){this.person = person;}

    public LibraryManagementSystem getSystem() {
        return system;
    }
    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
