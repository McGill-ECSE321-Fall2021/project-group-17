package ca.mcgill.ecse321.library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;

import ca.mcgill.ecse321.library.dto.LibrarianDTO;
import ca.mcgill.ecse321.library.dto.PersonRoleDTO;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Person;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.service.LibrarianService;

@CrossOrigin(origins = "*")
@RestController
public class LibrarianRestController {
	
    @Autowired
    private LibrarianService librarianService;

    @PostMapping(value = {"/librarian/{personid}","/librarian/{personid}"})
    public LibrarianDTO createLibrarian(@PathVariable("personid") Integer personId) throws IllegalArgumentException{
        Librarian librarian = librarianService.createLibrarian(personId);
        return convertToDTO(librarian);
    }

    @PutMapping(value= {"/librarian/{username}/{personid}/{accountid}","/librarian/{username}/{personid}/{accountid}"})
    public LibrarianDTO createLibrarian(@PathVariable("personid") Integer personId,
                                                @PathVariable("accountid") Integer accountId,
                                                @PathVariable("username")String username) throws IllegalArgumentException{
        Librarian librarian = librarianService.updateLibrarian(personId, accountId, username);
        return convertToDTO(librarian);
    }

    @GetMapping(value = {"/librarian/{id}", "/librarian/{id}"})
    public LibrarianDTO getLibrarian(@PathVariable("id") Integer id) throws IllegalArgumentException{
        Librarian librarian = librarianService.getLibrarian(id);
        return convertToDTO(librarian);
    }
    
    @GetMapping(value = {"/librarians", "/librarians/"})
    public List<PersonRoleDTO> getAllLibrarians() throws IllegalArgumentException{
        return librarianService.getAllLibrarians().stream().map(this::convertPersonRoleToDTO).collect(Collectors.toList());
    }

    @DeleteMapping(value = {"/librarian/{id}", "/librarian/{id}"})
    public void deleteHeadLibrarian(@PathVariable("id")Integer id, @RequestParam("accountUsername")String accountUsername) throws IllegalArgumentException{
        librarianService.deleteLibrarian(id, accountUsername);
    }
    
    private PersonRoleDTO convertPersonRoleToDTO(PersonRole librarian){
        if (librarian == null) {
            throw new IllegalArgumentException("There is no such Librarian!");
        }
        LibrarianDTO librarianDTO = new LibrarianDTO();
        librarianDTO.setId(librarian.getId());
        librarianDTO.setAccount(librarian.getAccount());
        librarianDTO.setPerson(librarian.getPerson());
        return librarianDTO;
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
