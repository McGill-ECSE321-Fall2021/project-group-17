package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.service.Exception.LibraryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.LibraryRepository;
import ca.mcgill.ecse321.library.model.Library;

@Service
public class LibraryService {
	@Autowired
    private LibraryRepository libraryRepository;

    @Transactional
    public Library createLibrary(int id){
        Library library = new Library();
        library.setId(id);

        
        libraryRepository.save(library);
        return library;
    }
    @Transactional
    public Library getLibrary(Integer id){
        if(id == null || id < 0) throw new LibraryException("Invalid id");
        Library lib = libraryRepository.findLibraryById(id);
        if(lib == null) throw new LibraryException("There exist no library by that Id");
        return lib;
    }
}
