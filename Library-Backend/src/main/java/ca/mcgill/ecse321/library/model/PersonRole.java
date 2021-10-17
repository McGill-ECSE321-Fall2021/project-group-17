package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersonRole {



    private Person person;
    private LibraryManagementSystem system;
    private String id;
    private OnlineAccount account;

    public PersonRole(){}

    public PersonRole(String id, Person person, OnlineAccount account){
        this.id = id;
        this.person = person;
        this.account = account;
    }

    @Id
    @Column (name="id", updatable = false, nullable = false)
    public String getId(){return this.id;}
    public void setId(String id){this.id = id;}

    @OneToOne
    @JoinColumn()
    public OnlineAccount getAccount(){return this.account;}
    public void setAccount(OnlineAccount account){this.account = account;}

    @ManyToOne
    @JoinColumn()
    public Person getPerson(){return this.person;}
    public void setPerson(Person person){this.person = person;}


    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }
    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
