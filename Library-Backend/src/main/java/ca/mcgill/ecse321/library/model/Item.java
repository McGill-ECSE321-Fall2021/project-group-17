package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
    private Integer id;
    private String name;
    private Date datePublished;
    public Item(){

    }
    public Item(Integer id, String name, Date datePublished){
        this.id=id;
        this.name=name;
        this.datePublished=datePublished;
    }

    private LibraryManagementSystem system;

    @Id
    @Column(name="id", updatable=false, nullable=false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Date getDatePublished() {
        return this.datePublished;
    }

    public void setDatePublished(Date date) {
        this.datePublished = date;
    }

    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
