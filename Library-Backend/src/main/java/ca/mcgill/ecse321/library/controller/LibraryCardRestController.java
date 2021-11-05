package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.LibraryCardDTO;
import ca.mcgill.ecse321.library.model.LibraryCard;
import ca.mcgill.ecse321.library.service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LibraryCardRestController {
    @Autowired
    private LibraryCardService service;

    @GetMapping(value = {"/librarycard/{id}", "/librarycard/{id}/"})
    public LibraryCardDTO getLibraryCard(@PathVariable("id") int id) throws IllegalArgumentException{
        LibraryCard libraryCard = service.createLibraryCard(id);
        return convertToDTO(libraryCard);
    }

    @PostMapping(value= {"/librarycard/{id}","/librarycard/{id}/"})
    public LibraryCardDTO createLibraryCard(@PathVariable("id") int id) throws IllegalArgumentException{
        LibraryCard libraryCard = service.createLibraryCard(id);
        return convertToDTO(libraryCard);
    }

    private LibraryCardDTO convertToDTO(LibraryCard libraryCard){
        if (libraryCard == null) {
            throw new IllegalArgumentException("There is no such Customer!");
        }
        LibraryCardDTO lDTO = new LibraryCardDTO();
        lDTO.setId(libraryCard.getId());
        lDTO.setCustomer(libraryCard.getCustomer());
        lDTO.setSystem(libraryCard.getSystem());
        return lDTO;
    }
}