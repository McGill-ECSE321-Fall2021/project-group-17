package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Service
public class HeadLibrarianService {
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;

    @Transactional
    public HeadLibrarian createHeadLibrarian(Integer personId){ //Creates and save a new head Librarian to the repository.
        HeadLibrarian headLibrarian = new HeadLibrarian();
        if(personId == null || personId < 0) throw new PersonException("Invalid Id");
        Person person = personRepository.findPersonById(personId);
        if(person == null) throw new PersonException("No person by this Id");
        headLibrarian.setPerson(person);
        headLibrarianRepository.save(headLibrarian);
        return headLibrarian;
    }
    @Transactional
    public HeadLibrarian updateHeadLibrarian(Integer headLibrarian, Integer personId, String username){//Updates and save a new head Librarian to the repository.
        if(headLibrarian == null || headLibrarian < 0)throw new OnlineAccountException("Invalid Id");
        HeadLibrarian librarian = (HeadLibrarian) headLibrarianRepository.findPersonRoleById(headLibrarian);
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
    public HeadLibrarian getHeadLibrarian(Integer id){//Retrieves the Head Librarian with the given Id
        if(id == null || id < 0) throw new OnlineAccountException("Invalid Id");
        HeadLibrarian headLibrarian = (HeadLibrarian) headLibrarianRepository.findPersonRoleById(id);
        if(headLibrarian == null) throw new OnlineAccountException("No librarian by this id");
        return headLibrarian;
    }
    @Transactional
    public void deleteHeadLibrarian(Integer id, String accountUsername){
        OnlineAccount account = getActiveUser(accountUsername);
        if(account.getLoggedIn() == false) throw new OnlineAccountException("Account is not logged in");
        PersonRole role = account.getPersonRole();
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
