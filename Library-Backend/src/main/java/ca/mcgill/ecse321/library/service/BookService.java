package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Transactional
    public Book createBook(int id, String name, Date date,String author, String publisher, String genre){
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
    @Transactional
    public void deleteBook(int id){
    	bookRepository.deleteById(id);
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
