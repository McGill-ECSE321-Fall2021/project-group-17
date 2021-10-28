package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.dto.HeadLibrarianDTO;
import ca.mcgill.ecse321.library.service.HeadLibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
public class HeadLibrarianRestController {
    @Autowired
    private HeadLibrarianService headService;

    @PostMapping(value= {"/headLibrarian/{id}","/headLibrarian/{id}/"})
    public HeadLibrarianDTO createHeadLibrarian(@PathVariable("id") String id) throws IllegalArgumentException{
        HeadLibrarian headLibrarian = headService.createHeadLibrarian(id);
        return convertToDTO(headLibrarian);
    }

    @GetMapping(value = {"/librarian/{id}", "/librarian/{id}/"})
    public HeadLibrarianDTO getHeadLibrarian(@PathVariable("id") String id) throws IllegalArgumentException{
        HeadLibrarian headLibrarian = headService.createHeadLibrarian(id);
        return convertToDTO(headLibrarian);
    }

    private HeadLibrarianDTO convertToDTO(HeadLibrarian headLibrarian){
        if (headLibrarian == null) {
            throw new IllegalArgumentException("There is no such Customer!");
        }
        HeadLibrarianDTO hDTO = new HeadLibrarianDTO();
        hDTO.setId(headLibrarian.getId());
        hDTO.setAccount(headLibrarian.getAccount());
        hDTO.setPerson(headLibrarian.getPerson());
        hDTO.setSystem(headLibrarian.getSystem());
        return hDTO;
    }
}
