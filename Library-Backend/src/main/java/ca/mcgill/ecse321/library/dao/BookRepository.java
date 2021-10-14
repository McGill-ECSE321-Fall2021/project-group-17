package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BookRepository extends CheckableItemRepository{

}
