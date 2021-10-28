package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.dto.LibrarianDTO;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Service
public class LibrarianService {
    @Autowired
    private LibrarianRepository librarianRepository;

    @Transactional
    public Librarian createLibrarian(int id){
        Librarian librarian = new Librarian();
        librarian.setId(id);
        librarianRepository.save(librarian);
        return librarian;
    }
    @Transactional
    public Librarian getLibrarian(int id){return (Librarian) librarianRepository.findPersonRoleById(id);}
}
