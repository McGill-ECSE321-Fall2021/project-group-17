package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class LibrarianService {
    @Autowired
    private LibrarianRepository librarianRepository;

    @Transactional
    public Librarian createLibrarian(String id){
        Librarian librarian = new Librarian();
        librarian.setId(id);
        librarianRepository.save(librarian);
        return librarian;
    }
    @Transactional
    public Librarian getLibrarian(String id){return (Librarian) librarianRepository.findPersonRoleById(id);}
}
