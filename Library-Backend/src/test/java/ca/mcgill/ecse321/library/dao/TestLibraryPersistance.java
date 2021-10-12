package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Book;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryPersistance {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MusicRepository musicRepository;

    @AfterEach
    public void clearDatabase() {
        bookRepository.deleteAll();
        movieRepository.deleteAll();
        musicRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadBook(){
        Integer bookID= 1234;
        String author="Victoria";
        String publisher="penguin";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String genre= "horror";

        Book book = new Book();
        book.setBookId(bookID);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setDatePublished(date);
        book.setGenre(genre);
        bookRepository.save(book);
        book=null;
        book=bookRepository.findBookByBookId(bookID);
        assertNotNull(book);
        assertEquals(bookID,book.getBookId());
        assertEquals(author, book.getAuthor());
        assertEquals(publisher,book.getPublisher());
        assertEquals(date,book.getDatePublished());
        assertEquals(genre,book.getGenre());


    }



}
