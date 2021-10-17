package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class OnlineAccount {

    private String username;
    private String password;


    @Id
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username; }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



/*
    @OneToOne
    private User user;
    public User getUser(){
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

 */
}
