package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class LibrarianService {
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @Autowired
    private LibrarianRepository librarianRepository;

    @Transactional
    public Librarian createLibrarian(Integer personId){
        Librarian librarian = new Librarian();
        if(personId == null || personId < 0) throw new PersonException("Invalid Id");
        Person person = personRepository.findPersonById(personId);
        if(person == null) throw new PersonException("No person by this Id");
        librarian.setPerson(person);
        headLibrarianRepository.save(librarian);
        return librarian;
    }
    @Transactional
    public Librarian updateLibrarian(Integer headLibrarian, Integer personId, String username){
        if(headLibrarian == null || headLibrarian < 0)throw new OnlineAccountException("Invalid Id");
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(headLibrarian);
        if(librarian == null) throw new OnlineAccountException("No librarian by this id");
        if(personId == null || personId < 0) throw new PersonException("Invalid Id");
        Person person = personRepository.findPersonById(personId);
        if(person == null) throw new PersonException("No person by this Id");
        if(username == null)throw new OnlineAccountException("Invalid Id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);
        if(account == null) throw new OnlineAccountException("No account by this username");
        librarian.setAccount(account);
        librarian.setPerson(person);
        headLibrarianRepository.save(librarian);
        return librarian;
    }
    @Transactional
    public Librarian getLibrarian(Integer id){
        if(id == null || id < 0) throw new OnlineAccountException("Invalid Id");
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(id);
        if(librarian == null) throw new OnlineAccountException("No librarian by this id");
        return librarian;
    }
    @Transactional
    public void deleteLibrarian(Integer id, String accountUsername){
        PersonRole role = getActiveUser(accountUsername).getPersonRole();
        if(role == null) throw new OnlineAccountException("No person role asscoiated with this account");
        if(!(role instanceof HeadLibrarian)) throw new OnlineAccountException("Account is not authorized for this action");
        if(id == null) throw new OnlineAccountException("invalid id");
        headLibrarianRepository.deleteById(id);
    }
    //Helper method for user authentication
    private OnlineAccount getActiveUser(String accountUsername){
        if(accountUsername == null) throw new OnlineAccountException("Invalid account id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(accountUsername);
        if(account == null) throw new OnlineAccountException("No account by that username");
        if(account.getLoggedIn() == false) throw new OnlineAccountException("This account is not the active user");
        return account;
    }
}
