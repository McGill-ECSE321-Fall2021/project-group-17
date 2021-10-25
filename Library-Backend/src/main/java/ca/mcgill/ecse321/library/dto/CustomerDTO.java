package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.model.LibraryCard;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.Person;

public class CustomerDTO extends PersonRoleDTO{
    private int penalty;
    private Address address;
    private LibraryCard libCard;

    public CustomerDTO(){}
    public CustomerDTO(String id, Person person, int penalty, Address address, LibraryCard libCard, OnlineAccount account){
        super(id, person, account);
        this.address = address;
        this.libCard = libCard;
        this.penalty = penalty;
    }
    public Address getAddress(){return address;}
    public void setAddress(Address address){this.address = address;}
    public LibraryCard getLibCard(){return libCard;}
    public void setLibCard(LibraryCard libCard){this.libCard = libCard;}
    public int getPenalty(){return penalty;}
    public void setPenalty(int penalty){this.penalty = penalty;}

}
