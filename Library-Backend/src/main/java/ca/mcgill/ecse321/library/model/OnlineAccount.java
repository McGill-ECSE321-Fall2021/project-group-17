package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class OnlineAccount {
    @Id
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn()
    private LibraryManagementSystem system;


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

   public LibraryManagementSystem getSystem(){
        return system;
    }

    public void setSystem(LibraryManagementSystem system){
        this.system = system;
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
