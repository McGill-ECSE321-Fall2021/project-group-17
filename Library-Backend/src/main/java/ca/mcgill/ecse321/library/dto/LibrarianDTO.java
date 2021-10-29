package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.Person;

public class LibrarianDTO extends PersonRoleDTO{

    public LibrarianDTO(){}
    public LibrarianDTO(int id, Person person, OnlineAccount account){
        super(id, person, account);
    }
}
