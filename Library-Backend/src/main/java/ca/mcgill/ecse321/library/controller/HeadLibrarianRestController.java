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

    @PostMapping(value= {"/headLibrarian/{personid}","/headLibrarian/{personid}"})
    public HeadLibrarianDTO createHeadLibrarian(@PathVariable("personid") Integer personId) throws IllegalArgumentException{
        HeadLibrarian headLibrarian = headService.createHeadLibrarian(personId);
        return convertToDTO(headLibrarian);
    }

    @PutMapping(value= {"/headLibrarian/{username}/{personid}/{accountid}","/headLibrarian/{username}/{personid}/{accountid}"})
    public HeadLibrarianDTO updateHeadLibrarian(@PathVariable("personid") Integer personId,
                                                @PathVariable("accountid") Integer accountId,
                                                @PathVariable("username")String username) throws IllegalArgumentException{
        HeadLibrarian headLibrarian = headService.updateHeadLibrarian(personId, accountId, username);
        return convertToDTO(headLibrarian);
    }

    @GetMapping(value = {"/headLibrarian/{id}", "/headLibrarian/{id}"})
    public HeadLibrarianDTO getHeadLibrarian(@PathVariable("id") int id) throws IllegalArgumentException{
        HeadLibrarian headLibrarian = headService.getHeadLibrarian(id);
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
        return hDTO;
    }
}
