package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LibraryManagementSystemService {
    @Autowired
    private LibraryManagementSystemRepository systemRepository;

    @Transactional
    public LibraryManagementSystem createSystem(){
        LibraryManagementSystem system = new LibraryManagementSystem();
        systemRepository.save(system);
        return system;
    }

    @Transactional
    public LibraryManagementSystem getSystem(int id){
        return systemRepository.findLibraryManagementSystemById(id);
    }
}
