package ca.mcgill.ecse321.library.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersonRole {



    private Person person;
    private int id;
    private OnlineAccount account;

    public PersonRole(){}

    public PersonRole(int id, Person person, OnlineAccount account){
        //this.id = id;
        this.person = person;
        this.account = account;
    }

    @Id
    @Column (name="id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}

    @OneToOne(cascade = {CascadeType.ALL})
    @JsonManagedReference
    @JoinColumn()
    @JsonBackReference
    public OnlineAccount getAccount(){return this.account;}
    public void setAccount(OnlineAccount account){this.account = account;}

    @ManyToOne
    @JoinColumn()
    public Person getPerson(){return this.person;}
    public void setPerson(Person person){this.person = person;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonRole)) return false;
        PersonRole that = (PersonRole) o;
        return Objects.equals(getPerson(), that.getPerson()) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getAccount(), that.getAccount());
    }

}
