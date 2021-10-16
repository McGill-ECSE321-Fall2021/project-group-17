package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class LibraryCard {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn()
    private LibraryManagementSystem system;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LibraryManagementSystem getSystem(){
        return system;
    }

    public void setSystem(LibraryManagementSystem system){
        this.system = system;
    }

    /*
    private User user;


    @OneToOne(optional=false)
    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

     */


}