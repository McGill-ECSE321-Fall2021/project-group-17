package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.service.Exception.BookException;
import ca.mcgill.ecse321.library.service.Exception.MovieException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class BookService {
	/*
	 * Repositories for aiding the service.
	 * These repositories allow for the storage of data
	 * into the database. Allows for create, get, update and delete functionalities.
	 */
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public Book createBook(Integer librarianId, Integer id, String name, Date date, 
    		String author, String publisher, String genre){
    	/*
    	 * Creates a movie item with the provided ids from the rest controller method.
    	 * Throws errors based on arguments, and whether they were provided or not.
    	 * Saves item into repository at the end.
    	 */
    	String error = "";
    	if (librarianId == null) {
    		throw new IllegalArgumentException("Librarian does not exist! ");
    	}
    	Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if (librarian == null) {
            error = error + "Librarian does not exist! ";
        }
        if (id == null) {
            error = error + "Id needs to be provided!";
        }
        if (name == null) {
            error = error + "Name needs to be provided!";
        }
        if (date == null) {
            error = error + "Date needs to be provided!";
        }
        error = error.trim();

        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        
    	Book book = new Book();
        book.setName(name);
        book.setDatePublished(date);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(genre);
        bookRepository.save(book);
        return book;
    }

    /**
     * Used to delete item
     * @param bookId
     * @param librarianId
     */
    @Transactional
    public void deleteBook(Integer bookId, Integer librarianId){
    	/*
    	 * Deletes item based on id, and requires librarian id to
    	 * perform this task. Deletes in repository at the end.
    	 */
        if(bookId == null){
            throw new BookException("Cannot find book with id to delete");
        }
        Book book = (Book) bookRepository.findItemById(bookId);
        if(book == null){
            throw new NotFoundException("Cannot find book to delete");
        }
        if(librarianId == null){
            throw new BookException("Cannot authorize librarian to delete book");
        }
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null){
            throw new OnlineAccountException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }

        List<ItemInstance> itemInstances = itemInstanceRepository.findByCheckableItem(book);

        for (ItemInstance itemInstance : itemInstances) {
            Loan loan = loanRepository.findByItemInstance(itemInstance);

            if (loan != null) {
                loanRepository.delete(loan);
            }

            Reservation reservation = reservationRepository.findByItemInstance(itemInstance);

            if (reservation != null) {
                reservationRepository.delete(reservation);
            }

            itemInstanceRepository.delete(itemInstance);
        }

        bookRepository.delete(book);
        book = null;
    }
    
    @Transactional
    public Book updateBook(Integer librarianId, Integer id, String name, Date date, String author, 
    		String publisher, String genre) {
       	/*
    	 * Updates an item with the provided ids from the rest controller method.
    	 * Throws errors based on arguments, and whether they were provided or not.
    	 * Saves item into repository at the end.
    	 */
    	if (librarianId == null || librarianRepository.findPersonRoleById(librarianId) == null) {
        	throw new PersonException("Librarian does not exist!");
    	}

    	Book book = (Book) bookRepository.findItemById(id);

        if (book == null) {
            throw new MovieException("Can't update book because no book exists for the given id.");
        }

        if (id != null) {
        	book.setId(id);
        }

        if (name != null) {
        	book.setName(name);
        }

        if (date != null) {
        	book.setDatePublished(date);
        }

        if (author != null) {
        	book.setAuthor(author);
        }
        
        if (genre != null) {
        	book.setGenre(genre);
        }
        
        if (publisher != null) {
        	book.setPublisher(publisher);
        }

        bookRepository.save(book);
        return book;
    }
    
    @Transactional
    public Book getBook(Integer bookId){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        Book book = (Book) bookRepository.findItemById(bookId);
        return book;
    }
    @Transactional
    public List<Item> getBookByName(String name){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Item> results = bookRepository.findItemByName(name);
        return results;
    }
    @Transactional
    public List<Book> getBookFromAuthor(String author){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Book> results = bookRepository.findBookByAuthor(author);
        return results;
    }
    @Transactional
    public List<Book> getBookFromPublisher(String publisher){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Book> results = bookRepository.findBookByPublisher(publisher);
        return results;
    }
    @Transactional
    public List<Book> getBookFromGenre(String genre){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Book> results = bookRepository.findBookByGenre(genre);
        return results;
    }

    @Transactional
    public List<Book> getBooks() {
        List<Item> items = (List<Item>) bookRepository.findAll();

        List<Book> books = new ArrayList<>();

        for (Item item : items) {
            if (item instanceof Book) {
                books.add((Book) item);
            }
        }
        return books;
    }
}
