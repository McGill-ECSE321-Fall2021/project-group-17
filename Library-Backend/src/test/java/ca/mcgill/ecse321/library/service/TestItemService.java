package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.stubbing.Answer;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestItemService {
	@Mock
    private LibrarianRepository librarianRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MusicRepository musicRepository;
    @Mock
    private NewspaperRepository newspaperRepository;

    @InjectMocks
    private LibrarianService librarianService;
    @InjectMocks
    private MovieService movieService;
    @InjectMocks
    private BookService bookService;
    @InjectMocks
    private MusicService musicService;
    @InjectMocks
    private NewspaperService newspaperService;

    private static final String MEDIA_NAME = "Good Will Hunting";
    private static final String AUTHOR_DIRECTOR_MUSICIAN = "Someone";
    private static final String GENRE = "Genre";
    private static final String RATING = "Fantastic";
    private static final String HEADLINE = "Headline";
    private static final String RECORD_LABEL = "Some record label";
    private static final String PUBLISHER_DISTRIBUTOR = "Lionsgate Films";
    private static final Date DATE_PUBLISHED = Date.valueOf("2021-10-11");
    private static final int RUNNING_TIME = 120;
    private static final int LIBRARIAN_KEY = 5;
    private static final int NEWSPAPER_KEY = 121212;
    private static final int MOVIE_KEY = 232323;
    private static final int BOOK_KEY = 343434;
    private static final int MUSIC_KEY = 454545;
    
    @BeforeEach
    public void setMockOutput() {

        lenient().when(movieRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MOVIE_KEY)) {
                Movie movie = new Movie();
                movie.setId(MOVIE_KEY);
                return movie;
            } else {
                return null;
            }
        });
        lenient().when(bookRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(BOOK_KEY)) {
                Book book = new Book();
                book.setId(BOOK_KEY);
                return book;
            } else {
                return null;
            }
        });
        lenient().when(musicRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MUSIC_KEY)){
                Music music = new Music();
                music.setId(MUSIC_KEY);
                return music;
            } else {
            	return null;
            }
        });
        lenient().when(newspaperRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(NEWSPAPER_KEY)){
                Newspaper newspaper = new Newspaper();
                newspaper.setId(NEWSPAPER_KEY);
                return newspaper;
            } else {
                return null;
            }
        });
        lenient().when(librarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARIAN_KEY)){
                Librarian librarian = new Librarian();
                librarian.setId(LIBRARIAN_KEY);
                return librarian;
            } else {
                return null;
            }
        });
        
        // Whenever anything is saved, just return the parameter object
 		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
 			return invocation.getArgument(0);
 		};
 		lenient().when(movieRepository.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);
 		lenient().when(bookRepository.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
 		lenient().when(musicRepository.save(any(Music.class))).thenAnswer(returnParameterAsAnswer);
 		lenient().when(newspaperRepository.save(any(Newspaper.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void createMovie(){
        Movie movie = null;
        
        try{
            movie = movieService.createMovie(LIBRARIAN_KEY, MOVIE_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR);
        }
        catch(Exception e){
        	System.out.println(e);
            fail();
        }
        assertNotNull(movie);
        assertEquals(movie.getId(), MOVIE_KEY);
        assertEquals(movie.getName(), MEDIA_NAME);
        assertEquals(movie.getDatePublished(), DATE_PUBLISHED);
        assertEquals(movie.getDirector(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(movie.getRunningTime(), RUNNING_TIME);
        assertEquals(movie.getRating(), RATING);
        assertEquals(movie.getFilmDistributor(), PUBLISHER_DISTRIBUTOR);
    }

    @Test
    public void createBook(){
        Book book = null;
        
        try{
            book = bookService.createBook(LIBRARIAN_KEY, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE);
        }
        catch(Exception e){
        	System.out.println(e);
            fail();
        }
        assertNotNull(book);
        assertEquals(book.getId(), BOOK_KEY);
        assertEquals(book.getName(), MEDIA_NAME);
        assertEquals(book.getDatePublished(), DATE_PUBLISHED);
        assertEquals(book.getAuthor(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(book.getPublisher(), PUBLISHER_DISTRIBUTOR);
        assertEquals(book.getGenre(), GENRE);
    }
    
    @Test
    public void createMusic(){
        Music music = null;
        
        try{
            music = musicService.createMusic(LIBRARIAN_KEY, MUSIC_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL);
        }
        catch(Exception e){
        	System.out.println(e);
            fail();
        }
        assertNotNull(music);
        assertEquals(music.getId(), MUSIC_KEY);
        assertEquals(music.getName(), MEDIA_NAME);
        assertEquals(music.getDatePublished(), DATE_PUBLISHED);
        assertEquals(music.getMusician(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(music.getRecordLabel(), RECORD_LABEL);
    }
    
    @Test
    public void createNewspaper(){
        Newspaper newspaper = null;
        
        try{
            newspaper = newspaperService.createNewspaper(LIBRARIAN_KEY, NEWSPAPER_KEY, MEDIA_NAME, DATE_PUBLISHED, HEADLINE);
        }
        catch(Exception e){
        	System.out.println(e);
            fail();
        }
        assertNotNull(newspaper);
        assertEquals(newspaper.getId(), NEWSPAPER_KEY);
        assertEquals(newspaper.getName(), MEDIA_NAME);
        assertEquals(newspaper.getDatePublished(), DATE_PUBLISHED);
        assertEquals(newspaper.getHeadline(), HEADLINE);
    }
    
    //START DELETE PERSON TESTS
    @Test
    public void testDeleteMovie(){
        try{
            movieService.deleteMovie(MOVIE_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){        	
        	System.out.println(e);
            fail();
        }
    }
    @Test
    public void testDeleteBook(){
        try{
            bookService.deleteBook(BOOK_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
        	System.out.println(e);
            fail();
        }
    }
    @Test
    public void testDeleteMusic(){
        try{
            bookService.deleteBook(MUSIC_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
        	System.out.println(e);
            fail();
        }
    }
    @Test
    public void testDeleteNewspaper(){
        try{
            newspaperService.deleteNewspaper(NEWSPAPER_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
        	System.out.println(e);
            fail();
        }
    }
    
}