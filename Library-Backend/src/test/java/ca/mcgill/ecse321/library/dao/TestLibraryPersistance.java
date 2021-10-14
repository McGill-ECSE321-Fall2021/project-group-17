package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Book;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Music;
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
public class TestLibraryPersistance{

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
    String name= "My Brilliant Friend";
    Book book = new Book(1234,"My Brilliant Friend",date,author,publisher,genre);
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
    @Test
    public void testPersistAndLoadMovie(){
        Integer movieID= 1234;
        String director="Victoria";
        Integer runningTime=55;
        String rating="PG-13";
        String filmDistributor="Sony";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String name= "My Brilliant Friend";
        Movie movie = new Movie(1234,"My Brilliant Friend",date,director,runningTime,rating,filmDistributor);
        movieRepository.save(movie);
        movie=null;
        movie=(Movie) movieRepository.findItemById(movieID);
        assertNotNull(movie);
        assertEquals(movieID,movie.getId());
        assertEquals(name, movie.getName());
        assertEquals(date,movie.getDatePublished());
        assertEquals(director,movie.getDirector());
        assertEquals(runningTime,movie.getRunningTime());
        assertEquals(rating,movie.getRating());
        assertEquals(filmDistributor,movie.getFilmDistributor());

    }
    @Test
    public void testPersistAndLoadMusic(){
        Integer musicID= 1234;
        String musician="Victoria";
        String recordLabel="Sony";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String name= "My Brilliant Friend";
        Music music = new Music(1234,"My Brilliant Friend",date,musician,recordLabel);
        musicRepository.save(music);
        music=null;
        music=(Music) musicRepository.findItemById(musicID);
        assertNotNull(music);
        assertEquals(musicID,music.getId());
        assertEquals(name, music.getName());
        assertEquals(date,music.getDatePublished());
        assertEquals(musician,music.getMusician());
        assertEquals(recordLabel,music.getRecordLabel());
    }

}
