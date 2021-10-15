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
    /*
    Read test for book class. Ensure a book and its attributes are properly stored and read from the database.
    Written by Victoria Sanchez
     */
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
    /*
   Read test for Movie class. Ensure a movie and its attributes are properly stored and read from the database.
   Written by Victoria Sanchez
    */
    @Test
    public void testPersistAndLoadMovie(){
        Integer movieID= 1234; //create object to be tested
        String director="Victoria";
        Integer runningTime=55;
        String rating="PG-13";
        String filmDistributor="Sony";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String name= "My Brilliant Friend";
        Movie movie = new Movie();
        movie.setId(movieID);
        movie.setName(name);
        movie.setDatePublished(date);
        movie.setDirector(director);
        movie.setRunningTime(runningTime);
        movie.setRating(rating);
        movie.setFilmDistributor(filmDistributor);
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
    /*
Read test for music class. Ensure music and their attributes are properly stored and read from the database.
Written by Victoria Sanchez
 */
    @Test
    public void testPersistAndLoadMusic(){
        Integer musicID= 1234; //create object to be tested
        String musician="Victoria";
        String recordLabel="Sony";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String name= "My Brilliant Friend";
        Music music = new Music();
        music.setId(musicID);
        music.setName(name);
        music.setDatePublished(date);
        music.setMusician(musician);
        music.setRecordLabel((recordLabel));
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
