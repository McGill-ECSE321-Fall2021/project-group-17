package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.LibrarianException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class LibrarianService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;

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
        if(librarianId == null || librarianId < 0)throw new LibrarianException("Invalid Id");
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null) throw new LibrarianException("No librarian by this id");
        if(personId == null || personId < 0) throw new PersonException("Invalid Id");
        Person person = personRepository.findPersonById(personId);
        if(person == null) throw new PersonException("No person by this Id");
        if(username == null)throw new LibrarianException("Invalid Id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);
        if(account == null) throw new LibrarianException("No account by this username");
        librarian.setAccount(account);
        librarian.setPerson(person);
        librarianRepository.save(librarian);
        return librarian;
    }
    @Transactional
    public Librarian getLibrarian(Integer id){
        if(id == null || id < 0) throw new LibrarianException("Invalid Id");
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(id);
        if(librarian == null) throw new LibrarianException("No librarian by this id");
        return librarian;
    }
    @Transactional
    public List<PersonRole> getAllLibrarians() {
		Iterable<PersonRole> iter = (Iterable<PersonRole>) librarianRepository.findAll();
        List<PersonRole> librarians = new ArrayList<PersonRole>();
        iter.forEach(librarians::add);
        return librarians;
    }
    @Transactional
    public void deleteLibrarian(Integer id, String accountUsername){
        if (id == null) {
            throw new LibrarianException("Null id");
        }

        if (accountUsername == null) {
            throw new LibrarianException("Null username");
        }
        PersonRole role = headLibrarianRepository.findPersonRoleById(id);
        if(role == null) throw new LibrarianException("No person role asscoiated with this id");
        if(!(role instanceof HeadLibrarian)) throw new LibrarianException("Account is not authorized for this action");
        headLibrarianRepository.deleteById(id);
    }
}
