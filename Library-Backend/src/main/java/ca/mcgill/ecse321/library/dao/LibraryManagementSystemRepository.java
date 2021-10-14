package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
import org.springframework.data.repository.CrudRepository;

public interface LibraryManagementSystemRepository extends CrudRepository<LibraryManagementSystem, Integer> {
    LibraryManagementSystem findLibraryManagementSystemById(Integer id);
}
