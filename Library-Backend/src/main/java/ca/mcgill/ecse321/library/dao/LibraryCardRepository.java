package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.LibraryCard;

public interface LibraryCardRepository extends CrudRepository<LibraryCard, String>{
    LibraryCard findLibraryCardById(String id);
}
