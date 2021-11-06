package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.PersonRole;

public class OnlineAccountDTO {
    private PersonRole personRole;
    private String username;
    private String password;
    private boolean loggedIn;

    public OnlineAccountDTO(){}

    public OnlineAccountDTO(String username,String password){
        this.username = username;
        this.password = password;
        this.loggedIn=true;
    }

    public void setLoggedIn(boolean b){
        this.loggedIn=b;
    }

    public boolean getLoggedIn(){
        return this.loggedIn;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) { this.password = password; }

    public PersonRole getPersonRole() { return this.personRole; }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }

}

