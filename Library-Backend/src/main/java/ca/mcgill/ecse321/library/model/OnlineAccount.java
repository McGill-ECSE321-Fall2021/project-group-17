package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OnlineAccount {
    private String username;
    private String password;

    @Id
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String serialNum) { this.username = username; }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String serialNum) {
        this.password = password;
    }

}
