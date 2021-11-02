package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class LibraryHourService {
	@Autowired
    private LibraryHourRepository libraryHourRepository;
	@Autowired
    private LibraryManagementSystemRepository lmsRepository;
	
    @Transactional
    public LibraryHour createLibraryHour(int id, Integer systemId){
        LibraryHour libraryHour = new LibraryHour();
        libraryHour.setId(id);
        
        if(systemId != null){
            LibraryManagementSystem system = lmsRepository.findLibraryManagementSystemById(systemId);
            libraryHour.setSystem(system);
        }
        
        libraryHourRepository.save(libraryHour);
        return libraryHour;
    }
    
    @Transactional
    public LibraryHour getLibraryHour(int id) {
        return libraryHourRepository.findLibraryHourById(id);
    }

}
