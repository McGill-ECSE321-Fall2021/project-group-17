package ca.mcgill.ecse321.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class OnlineAccount {


    private PersonRole personRole;
    private String username;
    private String password;
    private boolean loggedIn;
    private String email;

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

    public boolean getLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne
    @JoinColumn
    @JsonManagedReference
    public PersonRole getPersonRole(){ return this.personRole; }

    public void setPersonRole(PersonRole role){ this.personRole = role; }


}
