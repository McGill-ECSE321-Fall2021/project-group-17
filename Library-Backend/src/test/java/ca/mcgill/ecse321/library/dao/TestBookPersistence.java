package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBookPersistence {
    @Autowired
    private BookRepository bookRepository;
    @AfterEach
    public void clearDatabase() {
        bookRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadBook(){
        Integer bookID= 1234; //create object to be tested
        String author="Victoria";
        String publisher="penguin";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String genre= "horror";
        String name= "My Brilliant Friend";
        Book book = new Book();
        book.setId(bookID);
        book.setName(name);
        book.setDatePublished(date);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(genre);
        bookRepository.save(book);
        book=null;
        book=(Book) bookRepository.findItemById(bookID);
        assertNotNull(book);
        assertEquals(bookID,book.getId());
        assertEquals(author, book.getAuthor());
        assertEquals(publisher,book.getPublisher());
        assertEquals(date,book.getDatePublished());
        assertEquals(genre,book.getGenre());
    }
    //checks database can return a list of books with the same author
    @Test
    public void testFindBookByAuthor(){
        List<Book> b= new ArrayList<Book>();
        String author= "Victoria";
        String name="The Lost Child";
        String name2="The Lost Daughter";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Book book= new Book(1234,name,date,author, "Penguin","horror");
        Book book2= new Book(5678,name2,date, author,"Harper", "romance");
        bookRepository.save(book);
        bookRepository.save(book2);
        b=bookRepository.findBookByAuthor(author);
        assertNotNull(b);
        book=b.get(0);
        book2=b.get(1);
        assertEquals(2,b.size());
        assertEquals(name, book.getName());
        assertEquals(name2,book2.getName());
    }
    //ensures database can return a list of books with the same publisher
    @Test
    public void testFindBookByPublisher(){
        List<Book> b= new ArrayList<Book>();
        String publisher= "Penguin";
        String name="The Lost Child";
        String name2="The Lost Daughter";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Book book= new Book(1234,name,date,"Victoria", publisher,"horror");
        Book book2= new Book(5678,name2,date, "Hana",publisher, "romance");
        bookRepository.save(book);
        bookRepository.save(book2);
        b=bookRepository.findBookByPublisher(publisher);
        assertNotNull(b);
        book=b.get(0);
        book2=b.get(1);
        assertEquals(2,b.size());
        assertEquals(name, book.getName());
        assertEquals(name2,book2.getName());
    }
    //ensures data base can returns a list of books of the same genre
    @Test
    public void testFindBookByGenre(){
        List<Book> b= new ArrayList<Book>();
        String genre= "Horror";
        String name="The Lost Child";
        String name2="The Lost Daughter";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Book book= new Book(1234,name,date,"Victoria", "Penguin",genre);
        Book book2= new Book(5678,name2,date, "Hana","Harper", genre);
        bookRepository.save(book);
        bookRepository.save(book2);
        b=bookRepository.findBookByGenre(genre);
        assertNotNull(b);
        book=b.get(0);
        book2=b.get(1);
        assertEquals(2,b.size());
        assertEquals(name, book.getName());
        assertEquals(name2,book2.getName());
    }
}
