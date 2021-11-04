package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class OnlineAccount {


    private PersonRole personRole;
    private String username;
    private String password;
    private LibraryManagementSystem system;
    private boolean loggedIn;


    @Id
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean b){
        this.loggedIn=b;
    }
    public boolean getLoggedIn(){
        return this.loggedIn;
    }

    @OneToOne
    @JoinColumn
    public PersonRole getPersonRole(){ return this.personRole; }

    public void setPersonRole(PersonRole role){ this.personRole = role; }

    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
