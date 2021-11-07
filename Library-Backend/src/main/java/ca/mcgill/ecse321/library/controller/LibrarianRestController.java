package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.dto.LibrarianDTO;
import ca.mcgill.ecse321.library.service.LibrarianService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
public class LibrarianRestController {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping(value = {"/librarian/{id}","/librarian/{id}/"})
    public LibrarianDTO createLibrarian(@PathVariable("id") int id) throws IllegalArgumentException{
        Librarian librarian = librarianService.createLibrarian(id);
        return convertToDTO(librarian);
    }

    @GetMapping(value = {"/librarian/{id}", "/librarian/{id}/"})
    public LibrarianDTO getLibrarian(@PathVariable("id") int id) throws IllegalArgumentException{
        Librarian librarian = librarianService.createLibrarian(id);
        return convertToDTO(librarian);
    }
    
    private LibrarianDTO convertToDTO(Librarian librarian){
        if (librarian == null) {
            throw new IllegalArgumentException("There is no such Librarian!");
        }
        LibrarianDTO lDTO = new LibrarianDTO();
        lDTO.setId(librarian.getId());
        lDTO.setAccount(librarian.getAccount());
        lDTO.setPerson(librarian.getPerson());
        return lDTO;
    }
        
}
