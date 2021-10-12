package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BookRepository extends CheckableItemRepository{

//    List<Book> findByAuthor(String name);
//    List<Book> findByDatePublished(Date date);
//    List<Book> findByGenre(String genre);
////    List<Book> findByPublisher(String publisher);
//    Book findBookById(Integer ID);
}
