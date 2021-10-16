package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersonRole {

    private Person person;
    private LibraryManagementSystem system;
    private String roleType;
    private OnlineAccount account;

    public PersonRole(){

    }
    public PersonRole(String type, Person person, OnlineAccount account){
        this.roleType = type;
        this.person = person;
    }

    @Id
    @Column (name="roleType", updatable = false, nullable = false)
    public String getRoleType(){return this.roleType;}
    public void setRoleType(String roleType){this.roleType = roleType;}

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
