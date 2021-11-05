package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.LibraryManagementSystemDTO;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.LibraryManagementSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LibraryManagementSystemRestController {
    @Autowired
    private LibraryManagementSystemService service;

    @PostMapping(value= {"/system/","/system"})
    public LibraryManagementSystemDTO createLibraryManagementSystem() throws IllegalArgumentException{
        LibraryManagementSystem system = service.createSystem();
        return convertToDTO(system);
    }

    @GetMapping(value={"/system/{id}","/system/{id}/"})
    public LibraryManagementSystemDTO getLibraryManagementSystem(@PathVariable Integer id){
        return convertToDTO(service.getSystem(id));
    }

    //DTO CONVERSION SECTION

    private LibraryManagementSystemDTO convertToDTO(LibraryManagementSystem s){
        if (s == null) {
            throw new NotFoundException("There is no such System!");
        }

        LibraryManagementSystemDTO systemDTO = new LibraryManagementSystemDTO();
        systemDTO.setId(s.getId());
        systemDTO.setAddressList(s.getAddressList());
        systemDTO.setPersonList(s.getPersonList());
        systemDTO.setItemList(s.getItemList());
        systemDTO.setLibraryCardList(s.getLibraryCardList());
        systemDTO.setPersonRoleList(s.getPersonRoleList());
        systemDTO.setShiftList(s.getShiftList());
        systemDTO.setItemInstanceList(s.getItemInstanceList());
        systemDTO.setReservationList(s.getReservationList());
        systemDTO.setLoanList(s.getLoanList());
        systemDTO.setLibraryHourList(s.getLibraryHourList());

        return systemDTO;
    }
}
