package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.CheckableItemRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.service.Exception.BookException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LibrarianRepository librarianRepository;
    @Autowired
    CheckableItemRepository checkableItemRepository;
    @Transactional
    public Book createBook(Integer librarianId, Integer id, String name, Date date,String author, String publisher, String genre){
    	
    	String error = "";
        if (librarianId == null) {
        	throw new PersonException("Librarian not found in request");
        } else if (librarianRepository.findPersonRoleById(librarianId) == null) {
            error = error + "Librarian does not exist! ";
        }
        if (id == null) {
            error = error + "Id needs to be provided!";
        } 
//        else if (bookRepository.findItemById(id) != null) {
//            error = error + "Item with id " + id + " already exists! ";
//        }
        if (name == null) {
            error = error + "Name needs to be provided!";
        }
        if (date == null) {
            error = error + "Date needs to be provided!";
        }
        if (author == null) {
            error = error + "Author needs to be provided!";
        }
        if (publisher == null) {
            error = error + "Publisher needs to be provided!";
        }
        if (genre == null) {
            error = error + "Genre needs to be provided!";
        }
        error = error.trim();

        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
    	
    	Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        
    	Book b= new Book();
        b.setId(id);
        b.setName(name);
        b.setDatePublished(date);
        b.setAuthor(author);
        b.setPublisher(publisher);
        b.setGenre(genre);
        bookRepository.save(b);
        return b;
    }
    /*@Transactional
    public void deleteBook(int id){
    	bookRepository.deleteById(id);
    }*/
    /**
     * Used to delete item
     * @param bookId
     * @param customerId
     */
    @Transactional
    public void deleteBook(Integer bookId, Integer librarianId){
        if(bookId == null){
            throw new BookException("Cannot find book with id to delete");
        }
        Optional<Item> book = bookRepository.findById(bookId);
        if(book == null){
            throw new NotFoundException("Cannot find book to delete");
        }
        if(librarianId == null){
            throw new BookException("Cannot authorize librarian to delete book");
        }
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null){
            throw new PersonException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        bookRepository.deleteById(bookId);
        book = null;
    }
    @Transactional
    public Book getBook(Integer bookId){
        Book b= (Book) bookRepository.findItemById(bookId);
        return b;
    }
    @Transactional
    public List<Item> getBookByName(String name){
        List<Item> results= bookRepository.findItemByName(name);
        return results;
    }
    @Transactional
    public List<Book> getBookFromAuthor(String author){
        List<Book> results= bookRepository.findBookByAuthor(author);
        return results;
    }
    @Transactional
    public List<Book> getBookFromPublisher(String publisher){
        List<Book> results= bookRepository.findBookByPublisher(publisher);
        return results;
    }
    @Transactional
    public List<Book> getBookFromGenre(String genre){
        List<Book> results= bookRepository.findBookByGenre(genre);
        return results;
    }
}
