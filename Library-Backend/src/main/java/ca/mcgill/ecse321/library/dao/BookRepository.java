package ca.mcgill.ecse321.library.dao;


import ca.mcgill.ecse321.library.model.Book;

import java.util.List;
//adds features to find books based on attributes
public interface BookRepository extends CheckableItemRepository{
    List<Book> findBookByAuthor(String author);
    List<Book> findBookByPublisher(String publisher);
    List<Book> findBookByGenre(String publisher);

}
