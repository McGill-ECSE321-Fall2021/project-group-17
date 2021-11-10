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
    private static final int MEDIA_KEY = 7;
    
    @BeforeEach
    public void setMockOutput() {

        lenient().when(movieRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MEDIA_KEY)) {
                Movie movie = new Movie();
                movie.setId(MEDIA_KEY);
                return movie;
            } else {
                return null;
            }
        });
        lenient().when(bookRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MEDIA_KEY)) {
                Book book = new Book();
                book.setId(MEDIA_KEY);
                return book;
            } else {
                return null;
            }
        });
        lenient().when(musicRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MEDIA_KEY)){
                Music music = new Music();
                music.setId(MEDIA_KEY);
                return music;
            } else {
                return null;
            }
        });
        lenient().when(librarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MEDIA_KEY)){
                Librarian librarian = new Librarian();
                librarian.setId(MEDIA_KEY);
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
        int id = MEDIA_KEY;
        int id2 = LIBRARIAN_KEY;
        String name = MEDIA_NAME;
        Date datePublished = DATE_PUBLISHED;
        String director = AUTHOR_DIRECTOR_MUSICIAN;
        int runningTime = RUNNING_TIME;
        String rating = RATING;
        String distributor = PUBLISHER_DISTRIBUTOR;
        Movie movie = null;
        
        try{
            movie = movieService.createMovie(id2, id, name, datePublished, director, runningTime, rating, distributor);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(movie);
        assertEquals(movie.getId(), MEDIA_KEY);
        assertEquals(movie.getName(), MEDIA_NAME);
        assertEquals(movie.getDatePublished(), DATE_PUBLISHED);
        assertEquals(movie.getDirector(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(movie.getRunningTime(), RUNNING_TIME);
        assertEquals(movie.getRating(), RATING);
        assertEquals(movie.getFilmDistributor(), PUBLISHER_DISTRIBUTOR);
    }

    @Test
    public void createBook(){
    	int id = MEDIA_KEY;
        int id2 = LIBRARIAN_KEY;
        String name = MEDIA_NAME;
        Date datePublished = DATE_PUBLISHED;
        String author = AUTHOR_DIRECTOR_MUSICIAN;
        String genre = GENRE;
        String publisher = PUBLISHER_DISTRIBUTOR;
        Book book = null;
        
        try{
            book = bookService.createBook(id2, id, name, datePublished, author, publisher, genre);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(book);
        assertEquals(book.getId(), MEDIA_KEY);
        assertEquals(book.getName(), MEDIA_NAME);
        assertEquals(book.getDatePublished(), DATE_PUBLISHED);
        assertEquals(book.getAuthor(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(book.getPublisher(), PUBLISHER_DISTRIBUTOR);
        assertEquals(book.getGenre(), GENRE);
    }
    
    @Test
    public void createMusic(){
    	int id = MEDIA_KEY;
        int id2 = LIBRARIAN_KEY;
        String name = MEDIA_NAME;
        Date datePublished = DATE_PUBLISHED;
        String musician = AUTHOR_DIRECTOR_MUSICIAN;
        String recordLabel = RECORD_LABEL;
        Music music = null;
        
        try{
            music = musicService.createMusic(id2, id, name, datePublished, musician, recordLabel);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(music);
        assertEquals(music.getId(), MEDIA_KEY);
        assertEquals(music.getName(), MEDIA_NAME);
        assertEquals(music.getDatePublished(), DATE_PUBLISHED);
        assertEquals(music.getMusician(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(music.getRecordLabel(), RECORD_LABEL);
    }
    
    @Test
    public void createNewspaper(){
    	int id = MEDIA_KEY;
        int id2 = LIBRARIAN_KEY;
        String name = MEDIA_NAME;
        Date datePublished = DATE_PUBLISHED;
        String headline = HEADLINE;
        Newspaper newspaper = null;
        
        try{
            newspaper = newspaperService.createNewspaper(id2, id, name, datePublished, headline);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(newspaper);
        assertEquals(newspaper.getId(), MEDIA_KEY);
        assertEquals(newspaper.getName(), MEDIA_NAME);
        assertEquals(newspaper.getDatePublished(), DATE_PUBLISHED);
        assertEquals(newspaper.getHeadline(), HEADLINE);
    }
}