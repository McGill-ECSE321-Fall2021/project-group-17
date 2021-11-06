package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.LibraryRepository;
import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryHour;

@Service
public class LibraryHourService {
	@Autowired
    private LibraryHourRepository libraryHourRepository;
	@Autowired
    private LibraryRepository libraryRepository;;
	
    @Transactional
    public LibraryHour createLibraryHour(int id, Integer libraryId){
        LibraryHour libraryHour = new LibraryHour();
        libraryHour.setId(id);
        
        if (libraryId != null) {
            Library library = libraryRepository.findLibraryById(libraryId);
            if (library instanceof Library) {
            	libraryHour.setLibrary(library);
            }
        }
        
        libraryHourRepository.save(libraryHour);
        return libraryHour;
    }
    
    @Transactional
    public LibraryHour getLibraryHour(int id) {
        return libraryHourRepository.findLibraryHourById(id);
    }

}
