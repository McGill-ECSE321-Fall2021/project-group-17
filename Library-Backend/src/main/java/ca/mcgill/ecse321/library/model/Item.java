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

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
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

}
