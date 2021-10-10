package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryHour;


public interface LibraryHourRepository extends CrudRepository<LibraryHour,String>{
	
	List<LibraryHour> findByLibrary(Library name);
	
	boolean existsByLibrary(Library library);

    Library findLibraryHourById(String id);
}