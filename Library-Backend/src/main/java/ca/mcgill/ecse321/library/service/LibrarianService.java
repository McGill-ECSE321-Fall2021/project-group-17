package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Person;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class LibrarianService {
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
        librarianRepository.save(librarian);
        return librarian;
    }
    @Transactional
    public Librarian updateLibrarian(Integer librarianId, Integer personId, String username){
        if(librarianId == null || librarianId < 0)throw new OnlineAccountException("Invalid Id");
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null) throw new OnlineAccountException("No librarian by this id");
        if(personId == null || personId < 0) throw new PersonException("Invalid Id");
        Person person = personRepository.findPersonById(personId);
        if(person == null) throw new PersonException("No person by this Id");
        if(username == null)throw new OnlineAccountException("Invalid Id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);
        if(account == null) throw new OnlineAccountException("No account by this username");
        librarian.setAccount(account);
        librarian.setPerson(person);
        librarianRepository.save(librarian);
        return librarian;
    }
    @Transactional
    public Librarian getLibrarian(Integer id){
        if(id == null || id < 0) throw new OnlineAccountException("Invalid Id");
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(id);
        if(librarian == null) throw new OnlineAccountException("No librarian by this id");
        return librarian;
    }
}
