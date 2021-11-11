package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.PersonRepository;
import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Person;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class LibrarianService {
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonRoleRepository personRoleRepository;

    @Transactional
    public Librarian createLibrarian(Integer id){
    	Person p = personRepository.findPersonById(id);
    	if(p == null) {
    		throw new PersonException("Person not found from id!");
    	}
        Librarian librarian = new Librarian();
        librarian.setPerson(p);
        librarianRepository.save(librarian);
        return librarian;
    }
    

    @Transactional
    public Librarian getLibrarian(int id){return (Librarian) librarianRepository.findPersonRoleById(id);}
    
}
