package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.dao.LibraryRepository;
import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class LibraryService {
	@Autowired
    private LibraryManagementSystemRepository lmsRepository;
	@Autowired
    private LibraryRepository libraryRepository;

    @Transactional
    public Library createLibrary(String name, Integer systemId){
        Library library = new Library();
        library.setName(name);
        
        if(systemId != null){
            LibraryManagementSystem system = lmsRepository.findLibraryManagementSystemById(systemId);
            library.setSystem(system);
        }
        
        libraryRepository.save(library);
        return library;
    }
}
