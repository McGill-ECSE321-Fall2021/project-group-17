package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class LibraryManagementSystemService {
    @Autowired
    private LibraryManagementSystemRepository systemRepository;

    @Transactional
    public LibraryManagementSystem createSystem(){
        LibraryManagementSystem system = new LibraryManagementSystem();

        system.setAddressList(new ArrayList<>());
        system.setLibraryHourList(new ArrayList<>());
        system.setPersonList(new ArrayList<>());
        system.setItemList(new ArrayList<>());
        system.setLibraryCardList(new ArrayList<>());
        system.setPersonRoleList(new ArrayList<>());
        system.setShiftList(new ArrayList<>());
        system.setItemInstanceList(new ArrayList<>());
        system.setReservationList(new ArrayList<>());
        system.setLoanList(new ArrayList<>());

        systemRepository.save(system);
        return system;
    }

    @Transactional
    public LibraryManagementSystem getSystem(Integer id){
        if(id == null){
            throw new ReservationException("System id cannot be null");
        }
        return systemRepository.findLibraryManagementSystemById(id);
    }
}
