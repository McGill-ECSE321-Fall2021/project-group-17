package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.*;

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
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryPersistance{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CheckableItemRepository checkableItemRepository;
    @Autowired
    private NewspaperRepository newspaperRepository;

    @AfterEach
    public void clearDatabase() {
        itemInstanceRepository.deleteAll();
        bookRepository.deleteAll();
        movieRepository.deleteAll();
        musicRepository.deleteAll();
        checkableItemRepository.deleteAll();
        newspaperRepository.deleteAll();
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
    //ensures database can return of list of movies with the same director
    @Test
    public void testFindMovieByDirector(){
        List<Movie> movies= new ArrayList<Movie>();
        String director = "James Cameron";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, director,180,"PG-13","Disney");
        Movie m2= new Movie(5678,name2,date, director,120,"PG","WarnerBros");
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByDirector(director);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of movies with the same distributor
    @Test
    public void testFindMovieByFilmDistributor(){
        List<Movie> movies= new ArrayList<Movie>();
        String distributor = "Disney";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, "Sofia Coppola",180,"PG-13",distributor);
        Movie m2= new Movie(5678,name2,date, "Greta Gerwig",120,"PG",distributor);
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByFilmDistributor(distributor);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of movies with the same rating
    @Test
    public void testFindMovieByRating(){
        List<Movie> movies= new ArrayList<Movie>();
        String rating="PG";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, "Sofia Coppola",180,rating,"Disney");
        Movie m2= new Movie(5678,name2,date, "Greta Gerwig",120,rating,"WarnerBros");
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByRating(rating);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of music by the same artist
    @Test
    public void testFindMusicByMusician(){
        List<Music> music= new ArrayList<Music>();
        String musician= "Drake";
        String name="Club Paradise";
        String name2="5am in Toronto";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Music m= new Music(1234,name,date, musician,"OVO");
        Music m2= new Music(5678,name2,date, musician, "Label");
        musicRepository.save(m);
        musicRepository.save(m2);
        music=musicRepository.findMusicByMusician(musician);
        assertNotNull(music);
        m=music.get(0);
        m2=music.get(1);
        assertEquals(2,music.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of music produced by the same label
    @Test
    public void testFindMusicByRecordLabel(){
        List<Music> music= new ArrayList<Music>();
        String label= "Ovo";
        String name="Club Paradise";
        String name2="5am in Toronto";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Music m= new Music(1234,name,date, "Adele",label);
        Music m2= new Music(5678,name2,date, "Taylor Swift", label);
        musicRepository.save(m);
        musicRepository.save(m2);
        music=musicRepository.findMusicByRecordLabel(label);
        assertNotNull(music);
        m=music.get(0);
        m2=music.get(1);
        assertEquals(2,music.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }


    @Test
    public void testPersistAndLoadItemInstance() {
        String serialNum = "1234";
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);
        ItemInstance itemInstance = new ItemInstance(serialNum, checkableItem);
        itemInstanceRepository.save(itemInstance);
        itemInstance = itemInstanceRepository.findItemInstanceBySerialNum(serialNum);
        assertNotNull(itemInstance);
        assertEquals(serialNum, itemInstance.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance.getCheckableItem().getId());
    }

    @Test
    public void testFindItemInstanceByCheckableItem() {
        String serialNum1 = "1234";
        String serialNum2 = "5678";
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);
        ItemInstance itemInstance1 = new ItemInstance(serialNum1, checkableItem);
        ItemInstance itemInstance2 = new ItemInstance(serialNum2, checkableItem);
        itemInstanceRepository.save(itemInstance1);
        itemInstanceRepository.save(itemInstance2);
        List<ItemInstance> itemInstances = itemInstanceRepository.findByCheckableItem(checkableItem);
        assertNotNull(itemInstances);
        itemInstance1 = itemInstances.get(0);
        assertEquals(serialNum1, itemInstance1.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance1.getCheckableItem().getId());
        itemInstance2 = itemInstances.get(1);
        assertEquals(serialNum2, itemInstance2.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance2.getCheckableItem().getId());
    }

    /*@Test
    public void testFindCheckableItemByItemInstance() {
        String serialNum = "1234";
        Integer id = 1234;
        String name = "My Brilliant Friend";
        Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String musician = "Victoria";
        String recordLabel = "Sony";
        CheckableItem checkableItem = new Music(id,name,
                date,musician,recordLabel);
        checkableItemRepository.save(checkableItem);
        ItemInstance itemInstance = new ItemInstance(serialNum, checkableItem);
        checkableItem = checkableItemRepository.findCheckableItemByItemInstance(itemInstance);
        assertNotNull(checkableItem);
        assertEquals(checkableItem.getId(), id);
        assertEquals(checkableItem.getName(), name);
        assertEquals(checkableItem.getDatePublished(), date);

        Music music = (Music) checkableItem;
        assertEquals(music.getMusician(), musician);
        assertEquals(music.getRecordLabel(), recordLabel);
    }*/

    @Test
    public void testPersistAndLoadNewspaper() {
        Integer id = 1234;
        String name = "New York Times";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.MAY,24));
        String headline = "US deaths near 100,000, an incalculable loss";
        Newspaper newspaper = new Newspaper(id, name, date, headline);
        newspaperRepository.save(newspaper);
        newspaper = (Newspaper) newspaperRepository.findItemById(id);
        assertNotNull(newspaper);
        assertEquals(id,newspaper.getId());
        assertEquals(name, newspaper.getName());
        assertEquals(date, newspaper.getDatePublished());
        assertEquals(headline, newspaper.getHeadline());
    }

    @Test
    public void testFindNewspaperByHeadline() {
        Integer id = 1234;
        String name = "New York Times";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.MAY,24));
        String headline = "US deaths near 100,000, an incalculable loss";
        Newspaper newspaper = new Newspaper(id, name, date, headline);
        newspaperRepository.save(newspaper);
        newspaper = newspaperRepository.findNewspaperByHeadline(headline);
        assertNotNull(newspaper);
        assertEquals(id,newspaper.getId());
        assertEquals(name, newspaper.getName());
        assertEquals(date, newspaper.getDatePublished());
        assertEquals(headline, newspaper.getHeadline());
    }
}

