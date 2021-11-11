package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Person;
import ca.mcgill.ecse321.library.dto.LibrarianDTO;
import ca.mcgill.ecse321.library.service.LibrarianService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonInclude;

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
        Librarian librarian = librarianService.getLibrarian(id);
        return convertToDTO(librarian);
    }
    
    private LibrarianDTO convertToDTO(Librarian librarian){
        if (librarian == null) {
            throw new IllegalArgumentException("There is no such Librarian!");
        }
        LibrarianDTO librarianDTO = new LibrarianDTO();
        librarianDTO.setId(librarian.getId());
        librarianDTO.setAccount(librarian.getAccount());
        librarianDTO.setPerson(librarian.getPerson());
        return librarianDTO;
    }
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        Integer personId;

        public void setPerson(Person person){
        	this.personId = personId;
        }
        
        public Integer getPersonId(){return personId;}

        public JsonBody(){}
    }
        
}
