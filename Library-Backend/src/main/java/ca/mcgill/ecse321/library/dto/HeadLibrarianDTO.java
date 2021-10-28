package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.Person;

public class HeadLibrarianDTO extends LibrarianDTO{

    public HeadLibrarianDTO(){}
    public HeadLibrarianDTO(int id, Person person, OnlineAccount account){
        super(id, person, account);
    }
}
