package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.AfterEach;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

/*
 * This file tests services that are related to movie, 
 * music, book and newspaper. Uses mock methods to
 * emulate interaction with the psql database.
 */
@ExtendWith(MockitoExtension.class)
public class TestItemService {
	/*
	 * Repositories for aiding the service.
	 * These repositories allow for the storage of data
	 * into the database. Allows for create, get, update and delete functionalities.
	 * However, these are mocked to emulate their functionality.
	 */
	@Mock
    private LibrarianRepository librarianRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MusicRepository musicRepository;
    @Mock
    private ItemInstanceRepository itemInstanceRepository;
    @Mock
    private NewspaperRepository newspaperRepository;


	/*
	 * Provided service classes that allow manipulation with the
	 * rest controller.
	 */
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

    /*
     * Fields that are used throughout the tests.
     */
    private static final String MEDIA_NAME = "Somename";
    private static final String AUTHOR_DIRECTOR_MUSICIAN = "Someone";
    private static final String AUTHOR_DIRECTOR_MUSICIAN2 = "Someone2";
    private static final String GENRE = "Genre";
    private static final String GENRE2 = "Genre2";
    private static final String RATING = "9/10";
    private static final String RATING2 = "10/10";
    private static final String HEADLINE = "Headline";
    private static final String HEADLINE2 = "Headline2";
    private static final String RECORD_LABEL = "Some record label";
    private static final String RECORD_LABEL2 = "Some record label2";
    private static final String PUBLISHER_DISTRIBUTOR = "Lionsgate Films";
    private static final String PUBLISHER_DISTRIBUTOR2 = "Disney";
    private static final Date DATE_PUBLISHED = Date.valueOf("2021-10-11");
    private static final int RUNNING_TIME = 120;
    private static final int LIBRARIAN_KEY = 5;
    private static final int NEWSPAPER_KEY = 121212;
    private static final int MOVIE_KEY = 232323;
    private static final int BOOK_KEY = 343434;
    private static final int MUSIC_KEY = 454545;
    
    @BeforeEach
    public void setMockOutput() {
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(movieRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MOVIE_KEY)) {
                Movie movie = new Movie();
                movie.setId(MOVIE_KEY);
                return movie;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(movieRepository.findItemByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MEDIA_NAME)) {
            	List<Movie> movies = new ArrayList<Movie>();
            	Movie movie = new Movie();
                movie.setName(MEDIA_NAME);
                movies.add(movie);
                return movies;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(movieRepository.findMovieByDirector(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(AUTHOR_DIRECTOR_MUSICIAN)) {
            	List<Movie> movies = new ArrayList<Movie>();
            	Movie movie = new Movie();
                movie.setDirector(AUTHOR_DIRECTOR_MUSICIAN);
                movies.add(movie);
                return movies;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(movieRepository.findMovieByFilmDistributor(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(PUBLISHER_DISTRIBUTOR)) {
                List<Movie> movies = new ArrayList<Movie>();
                Movie movie = new Movie();
                movie.setFilmDistributor(PUBLISHER_DISTRIBUTOR);
                movies.add(movie);
                return movies;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(movieRepository.findMovieByRating(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(RATING)) {
                List<Movie> movies = new ArrayList<Movie>();
                Movie movie = new Movie();
                movie.setRating(RATING);
                movies.add(movie);
                return movies;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(bookRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(BOOK_KEY)) {
                Book book = new Book();
                book.setId(BOOK_KEY);
                return book;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(bookRepository.findItemByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MEDIA_NAME)) {
            	List<Book> books = new ArrayList<Book>();
                Book book = new Book();
                book.setName(MEDIA_NAME);
                books.add(book);
                return books;
            } else {
                return null;
            }
        });
        // sets mock output so that no data is read from or deleted from the database.
        lenient().when(bookRepository.findBookByAuthor(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(AUTHOR_DIRECTOR_MUSICIAN)) {
                List<Book> books = new ArrayList<Book>();
                Book book = new Book();
                book.setAuthor(AUTHOR_DIRECTOR_MUSICIAN);
                books.add(book);
                return books;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(bookRepository.findBookByPublisher(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(PUBLISHER_DISTRIBUTOR)) {
                List<Book> books = new ArrayList<Book>();
                Book book = new Book();
                book.setPublisher(PUBLISHER_DISTRIBUTOR);
                books.add(book);
                return books;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(bookRepository.findBookByGenre(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(GENRE)) {
            	List<Book> books = new ArrayList<Book>();
                Book book = new Book();
                book.setGenre(GENRE);
                books.add(book);
                return books;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(musicRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MUSIC_KEY)){
                Music music = new Music();
                music.setId(MUSIC_KEY);
                return music;
            } else {
            	return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(musicRepository.findItemByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MEDIA_NAME)){
                List<Music> musics = new ArrayList<Music>();
                Music music = new Music();
                music.setName(MEDIA_NAME);
                musics.add(music);
                return musics;
            } else {
            	return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(musicRepository.findMusicByMusician(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(AUTHOR_DIRECTOR_MUSICIAN)){
                List<Music> musics = new ArrayList<Music>();
                Music music = new Music();
                music.setMusician(AUTHOR_DIRECTOR_MUSICIAN);
                musics.add(music);
                return musics;
            } else {
            	return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(musicRepository.findMusicByRecordLabel(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(RECORD_LABEL)){
            	List<Music> musics = new ArrayList<Music>();
                Music music = new Music();
                music.setRecordLabel(RECORD_LABEL);
                musics.add(music);
                return musics;
            } else {
            	return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(newspaperRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(NEWSPAPER_KEY)){
                Newspaper newspaper = new Newspaper();
                newspaper.setId(NEWSPAPER_KEY);
                return newspaper;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(newspaperRepository.findNewspaperByHeadline(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(HEADLINE)){
                Newspaper newspaper = new Newspaper();
                newspaper.setHeadline(HEADLINE);
                return newspaper;
            } else {
                return null;
            }
        });
    	// sets mock output so that no data is read from or deleted from the database.
        lenient().when(librarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARIAN_KEY)){
                Librarian librarian = new Librarian();
                librarian.setId(LIBRARIAN_KEY);
                return librarian;
            } else {
                return null;
            }
        });

        lenient().when(itemInstanceRepository.findByCheckableItem(any())).thenAnswer((InvocationOnMock invocation) -> {
            if(((CheckableItem)invocation.getArgument(0)).getId().equals(BOOK_KEY)){
                List<ItemInstance> list = new ArrayList<>();
                return list;
            }
            else if (((CheckableItem)invocation.getArgument(0)).getId().equals(MOVIE_KEY)){
                List<ItemInstance> list = new ArrayList<>();
                return list;
            }
            else if(((CheckableItem)invocation.getArgument(0)).getId().equals(MUSIC_KEY)){
                List<ItemInstance> list = new ArrayList<>();
                return list;
            }
            else {
                return null;
            }
        });
        
        // Whenever anything is saved, just return the parameter object
 		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
 			return invocation.getArgument(0);
 		};
    	// sets mock output so that no data is read from or deleted from the database.
 		lenient().when(movieRepository.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);
 		lenient().when(bookRepository.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
 		lenient().when(musicRepository.save(any(Music.class))).thenAnswer(returnParameterAsAnswer);
 		lenient().when(newspaperRepository.save(any(Newspaper.class))).thenAnswer(returnParameterAsAnswer);
 		
    }

    @AfterEach
    public void clearDatabase() {
    	// clears the repositories
        movieRepository.deleteAll();
        bookRepository.deleteAll();
        musicRepository.deleteAll();
        newspaperRepository.deleteAll();

    }

    @Test
    public void createMovieValid(){
    	/* This test creates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Movie movie = null;
        
        try{
            movie = movieService.createMovie(LIBRARIAN_KEY, MOVIE_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(movie);
        assertEquals(movie.getName(), MEDIA_NAME);
        assertEquals(movie.getDatePublished(), DATE_PUBLISHED);
        assertEquals(movie.getDirector(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(movie.getRunningTime(), RUNNING_TIME);
        assertEquals(movie.getRating(), RATING);
        assertEquals(movie.getFilmDistributor(), PUBLISHER_DISTRIBUTOR);
    }
    
    @Test
    public void createMovieNoName(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
        	movieService.createMovie(LIBRARIAN_KEY, MOVIE_KEY, null, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR);
        }
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Name needs to be provided!");
    }
    @Test
    public void createBookNoName(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            bookService.createBook(LIBRARIAN_KEY, BOOK_KEY, null, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE);
        }
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Name needs to be provided!");
    }
    @Test
    public void createMusicNoName(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            musicService.createMusic(LIBRARIAN_KEY, MUSIC_KEY, null, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL);
        }
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Name needs to be provided!");
    }
    @Test
    public void createNewspaperNoName(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            newspaperService.createNewspaper(LIBRARIAN_KEY, NEWSPAPER_KEY, null, DATE_PUBLISHED, HEADLINE);
        }
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Name needs to be provided!");
    }
    
    @Test
    public void createMovieNoId(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
        	movieService.createMovie(LIBRARIAN_KEY, null, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Id needs to be provided!");
    }
    @Test
    public void createBookNoId(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            bookService.createBook(LIBRARIAN_KEY,  null, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Id needs to be provided!");
    }
    @Test
    public void createMusicNoId(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            musicService.createMusic(LIBRARIAN_KEY, null, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Id needs to be provided!");
    }
    @Test
    public void createNewspaperNoId(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            newspaperService.createNewspaper(LIBRARIAN_KEY, null, MEDIA_NAME, DATE_PUBLISHED, HEADLINE);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Id needs to be provided!");
    }    
    
    @Test
    public void createMovieNoLibrarian(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
        	movieService.createMovie(null, MOVIE_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Librarian does not exist! ");
    }
    @Test
    public void createBookNoLibrarian(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            bookService.createBook(null, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Librarian does not exist! ");
    }
    @Test
    public void createMusicNoLibrarian(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            musicService.createMusic(null, MUSIC_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Librarian does not exist! ");
    }
    @Test
    public void createNewspaperNoLibrarian(){
    	/* Creates item without the specified field.
    	 * This can result in an error thrown, which is
    	 * then compared to pass/fail the test case.
    	 */
        String error = "";
        try{
            newspaperService.createNewspaper(null, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, HEADLINE);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Librarian does not exist! ");
    }
    

    @Test
    public void createBookValid(){
    	/* This test creates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Book book = null;
        
        try{
            book = bookService.createBook(LIBRARIAN_KEY, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(book);
        assertEquals(book.getName(), MEDIA_NAME);
        assertEquals(book.getDatePublished(), DATE_PUBLISHED);
        assertEquals(book.getAuthor(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(book.getPublisher(), PUBLISHER_DISTRIBUTOR);
        assertEquals(book.getGenre(), GENRE);
    }
    
    
    @Test
    public void createMusicValid(){
    	/* This test creates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Music music = null;
        
        try{
            music = musicService.createMusic(LIBRARIAN_KEY, MUSIC_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(music);
        assertEquals(music.getName(), MEDIA_NAME);
        assertEquals(music.getDatePublished(), DATE_PUBLISHED);
        assertEquals(music.getMusician(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(music.getRecordLabel(), RECORD_LABEL);
    }
    
    
    @Test
    public void createNewspaperValid(){
    	/* This test creates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Newspaper newspaper = null;
        
        try{
            newspaper = newspaperService.createNewspaper(LIBRARIAN_KEY, NEWSPAPER_KEY, MEDIA_NAME, DATE_PUBLISHED, HEADLINE);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(newspaper);
        assertEquals(newspaper.getName(), MEDIA_NAME);
        assertEquals(newspaper.getDatePublished(), DATE_PUBLISHED);
        assertEquals(newspaper.getHeadline(), HEADLINE);
    }
    
    @Test
    public void updateMovieDirector(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Movie movie = null;
        
        try{
            movie = movieService.updateMovie(LIBRARIAN_KEY, MOVIE_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN2, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(movie);
        assertEquals(movie.getId(), MOVIE_KEY);
        assertEquals(movie.getName(), MEDIA_NAME);
        assertEquals(movie.getDatePublished(), DATE_PUBLISHED);
        assertEquals(movie.getDirector(), AUTHOR_DIRECTOR_MUSICIAN2);
        assertEquals(movie.getRunningTime(), RUNNING_TIME);
        assertEquals(movie.getRating(), RATING);
        assertEquals(movie.getFilmDistributor(), PUBLISHER_DISTRIBUTOR);
    }
    
    @Test
    public void updateMovieDistributor(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Movie movie = null;
        
        try{
            movie = movieService.updateMovie(LIBRARIAN_KEY, MOVIE_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR2);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(movie);
        assertEquals(movie.getId(), MOVIE_KEY);
        assertEquals(movie.getName(), MEDIA_NAME);
        assertEquals(movie.getDatePublished(), DATE_PUBLISHED);
        assertEquals(movie.getDirector(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(movie.getRunningTime(), RUNNING_TIME);
        assertEquals(movie.getRating(), RATING);
        assertEquals(movie.getFilmDistributor(), PUBLISHER_DISTRIBUTOR2);
    }
    
    @Test
    public void updateMovieRating(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Movie movie = null;
        
        try{
            movie = movieService.updateMovie(LIBRARIAN_KEY, MOVIE_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING2, PUBLISHER_DISTRIBUTOR);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(movie);
        assertEquals(movie.getId(), MOVIE_KEY);
        assertEquals(movie.getName(), MEDIA_NAME);
        assertEquals(movie.getDatePublished(), DATE_PUBLISHED);
        assertEquals(movie.getDirector(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(movie.getRunningTime(), RUNNING_TIME);
        assertEquals(movie.getRating(), RATING2);
        assertEquals(movie.getFilmDistributor(), PUBLISHER_DISTRIBUTOR);
    }
    @Test
    public void updateMovieNotFound(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        String error = null;
        try{
            movieService.updateMovie(LIBRARIAN_KEY, MOVIE_KEY+1, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING2, PUBLISHER_DISTRIBUTOR);
        }
        catch(Exception e){
           error=e.getMessage();
        }
        assertEquals(error,"Can't update movie because no movie exists for the given id.");
    }
    
    @Test
    public void updateBookAuthor(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Book book = null;
        
        try{
            book = bookService.updateBook(LIBRARIAN_KEY, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN2, PUBLISHER_DISTRIBUTOR, GENRE);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(book);
        assertEquals(book.getId(), BOOK_KEY);
        assertEquals(book.getName(), MEDIA_NAME);
        assertEquals(book.getDatePublished(), DATE_PUBLISHED);
        assertEquals(book.getAuthor(), AUTHOR_DIRECTOR_MUSICIAN2);
        assertEquals(book.getPublisher(), PUBLISHER_DISTRIBUTOR);
        assertEquals(book.getGenre(), GENRE);
    }
    @Test
    public void updateBookPublisher(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Book book = null;
        
        try{
            book = bookService.updateBook(LIBRARIAN_KEY, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR2, GENRE);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(book);
        assertEquals(book.getId(), BOOK_KEY);
        assertEquals(book.getName(), MEDIA_NAME);
        assertEquals(book.getDatePublished(), DATE_PUBLISHED);
        assertEquals(book.getAuthor(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(book.getPublisher(), PUBLISHER_DISTRIBUTOR2);
        assertEquals(book.getGenre(), GENRE);
    }
    @Test
    public void updateBookGenre(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Book book = null;
        
        try{
            book = bookService.updateBook(LIBRARIAN_KEY, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE2);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(book);
        assertEquals(book.getId(), BOOK_KEY);
        assertEquals(book.getName(), MEDIA_NAME);
        assertEquals(book.getDatePublished(), DATE_PUBLISHED);
        assertEquals(book.getAuthor(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(book.getPublisher(), PUBLISHER_DISTRIBUTOR);
        assertEquals(book.getGenre(), GENRE2);
    }
    @Test
    public void updateBookNotFound(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        String error = "";
        try{
            bookService.updateBook(LIBRARIAN_KEY, BOOK_KEY+1, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE);
        }
        catch(Exception e){
            error=e.getMessage();
         }
         assertEquals(error,"Can't update book because no book exists for the given id.");
    }
    
    @Test
    public void updateMusicMusician(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Music music = null;
        
        try{
            music = musicService.updateMusic(LIBRARIAN_KEY, MUSIC_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN2, RECORD_LABEL);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(music);
        assertEquals(music.getId(), MUSIC_KEY);
        assertEquals(music.getName(), MEDIA_NAME);
        assertEquals(music.getDatePublished(), DATE_PUBLISHED);
        assertEquals(music.getMusician(), AUTHOR_DIRECTOR_MUSICIAN2);
        assertEquals(music.getRecordLabel(), RECORD_LABEL);
    }
    @Test
    public void updateMusicRecordLabel(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Music music = null;
        
        try{
            music = musicService.updateMusic(LIBRARIAN_KEY, MUSIC_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL2);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(music);
        assertEquals(music.getId(), MUSIC_KEY);
        assertEquals(music.getName(), MEDIA_NAME);
        assertEquals(music.getDatePublished(), DATE_PUBLISHED);
        assertEquals(music.getMusician(), AUTHOR_DIRECTOR_MUSICIAN);
        assertEquals(music.getRecordLabel(), RECORD_LABEL2);
    }
    @Test
    public void updateMusicNotFound(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        String error = "";
        
        try{
            musicService.updateMusic(LIBRARIAN_KEY, MUSIC_KEY+1, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL2);
        }
        catch(Exception e){
            error=e.getMessage();
         }
         assertEquals(error,"Can't update music because no music exists for the given id.");
    }
    
    @Test
    public void updateNewspaperHeadline(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        Newspaper newspaper = null;
        
        try{
            newspaper = newspaperService.updateNewspaper(LIBRARIAN_KEY, NEWSPAPER_KEY, MEDIA_NAME, DATE_PUBLISHED, HEADLINE2);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(newspaper);
        assertEquals(newspaper.getId(), NEWSPAPER_KEY);
        assertEquals(newspaper.getName(), MEDIA_NAME);
        assertEquals(newspaper.getDatePublished(), DATE_PUBLISHED);
        assertEquals(newspaper.getHeadline(), HEADLINE2);
    }
    public void updateNewspaperNotFound(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        String error = "";
        
        try{
            newspaperService.updateNewspaper(LIBRARIAN_KEY, NEWSPAPER_KEY, MEDIA_NAME, DATE_PUBLISHED, HEADLINE2);
        }
        catch(Exception e){
            error=e.getMessage();
         }
         assertEquals(error,"Can't update newspaper because no newspaper exists for the given id.");
    }
    
    
    @Test
    public void testDeleteMovieValid(){
    	/* This test updates an item with the provided
    	 * global keys above, and asserts that each of their fields is accounted for.
    	 * Uses the mock method getItemById to perform its' task.
    	 */
        try{
            movieService.deleteMovie(MOVIE_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){        	
            fail();
        }
    }
    @Test
    public void testDeleteBookValid(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails.
    	 */
        try{
            bookService.deleteBook(BOOK_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
            fail();
        }
    }
    @Test
    public void testDeleteMusicValid(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails.
    	 */
        try{
            musicService.deleteMusic(MUSIC_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
            fail();
        }
    }
    @Test
    public void testDeleteNewspaperValid(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails.
    	 */
        try{
            newspaperService.deleteNewspaper(NEWSPAPER_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
            fail();
        }
    }
    
    
    @Test
    public void testDeleteMovieMissingId(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
            movieService.deleteMovie(null, LIBRARIAN_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error, "Cannot find movie with id to delete");
    }
    @Test
    public void testDeleteBookMissingId(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
            bookService.deleteBook(null, LIBRARIAN_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error, "Cannot find book with id to delete");
    }
    @Test
    public void testDeleteMusicMissingId(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
            musicService.deleteMusic(null, LIBRARIAN_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error, "Cannot find music with id to delete");
    }
    @Test
    public void testDeleteNewspaperMissingId(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
            newspaperService.deleteNewspaper(null, LIBRARIAN_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error, "Cannot find newspaper with id to delete");
    }
    
    
    @Test
    public void testDeleteMovieNotExistingLibrarian(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
        	movieService.deleteMovie(MOVIE_KEY, LIBRARIAN_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Librarian not found in request");
    }
    @Test
    public void testDeleteMusicNotExistingLibrarian(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
        	musicService.deleteMusic(MUSIC_KEY, LIBRARIAN_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Librarian not found in request");
    }
    @Test
    public void testDeleteBookNotExistingLibrarian(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
        	bookService.deleteBook(BOOK_KEY, LIBRARIAN_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Librarian not found in request");
    }
    @Test
    public void testDeleteNewspaperNotExistingLibrarian(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
        	newspaperService.deleteNewspaper(NEWSPAPER_KEY, LIBRARIAN_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Librarian not found in request");
    }
    
    @Test
    public void testDeleteMovieNotFound(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
        	movieService.deleteMovie(MOVIE_KEY+1, LIBRARIAN_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot find movie to delete");
    } 
    @Test
    public void testDeleteMusicNotFound(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
        	musicService.deleteMusic(MUSIC_KEY+1, LIBRARIAN_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot find music to delete");
    }
    @Test
    public void testDeleteBookNotFound(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
        	bookService.deleteBook(BOOK_KEY+1, LIBRARIAN_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot find book to delete");
    }
    @Test
    public void testDeleteNewspaperNotFound(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
        	newspaperService.deleteNewspaper(NEWSPAPER_KEY+1, LIBRARIAN_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot find newspaper to delete");
    }
    
    @Test
    public void testDeleteMovieNullLibrarian(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
            movieService.deleteMovie(MOVIE_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot authorize librarian to delete movie");
    }
    @Test
    public void testDeleteMusicNullLibrarian(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
            musicService.deleteMusic(MUSIC_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot authorize librarian to delete music");
    }
    @Test
    public void testDeleteBookNullLibrarian(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
            bookService.deleteBook(BOOK_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot authorize librarian to delete book");
    }
    @Test
    public void testDeleteNewspaperNullLibrarian(){
    	/* This test deletes an item with the provided
    	 * global keys above. If an error occurs in the process,
    	 * it fails. Else, it checks if the error message is expected.
    	 */
        String error = "";
        try{
            newspaperService.deleteNewspaper(NEWSPAPER_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error, "Cannot authorize librarian to delete newspaper");
    }
    
    @Test
    public void testGetMovieValid() {
    	/* This test tests to see if the item is
    	 * valid based on getting its id.
    	 */
    	Movie movie = null;
    	try{
    		movie = movieService.getMovie(MOVIE_KEY);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(movie.getId(), MOVIE_KEY);
    }
    @Test
    public void testGetBookValid() {
    	/* This test tests to see if the item is
    	 * valid based on getting its id.
    	 */
    	Book book = null;
    	try{
    		book = bookService.getBook(BOOK_KEY);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(book.getId(), BOOK_KEY);
    }
    @Test
    public void testGetMusicValid() {
    	/* This test tests to see if the item is
    	 * valid based on getting its id.
    	 */
    	Music music = null;
    	try{
    		music = musicService.getMusic(MUSIC_KEY);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(music.getId(), MUSIC_KEY);
    }
    @Test
    public void testGetNewspaperValid() {
    	/* This test tests to see if the item is
    	 * valid based on getting its id.
    	 */
    	Newspaper newspaper = null;
    	try{
    		newspaper = newspaperService.getNewspaper(NEWSPAPER_KEY);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(newspaper.getId(), NEWSPAPER_KEY);
    }
    
    @Test
    public void testGetMovieByName() {
    	/* This test tests to see if the item is
    	 * valid based on getting its name. Returns a list.
    	 */
    	List<Item> movies = null;
    	try{
    		movies = movieService.getMovieByName(MEDIA_NAME);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(movies.get(0).getName(), MEDIA_NAME);
    }
    @Test
    public void testGetBookByName() {
    	/* This test tests to see if the item is
    	 * valid based on getting its name. Returns a list.
    	 */
    	List<Item> books = null;
    	try{
    		books = bookService.getBookByName(MEDIA_NAME);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(books.get(0).getName(), MEDIA_NAME);
    }
    @Test
    public void testGetMusicByName() {
    	/* This test tests to see if the item is
    	 * valid based on getting its name. Returns a list.
    	 */
    	List<Item> musics = null;
    	try{
    		musics = musicService.getMusicByName(MEDIA_NAME);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(musics.get(0).getName(), MEDIA_NAME);
    }
    @Test
    public void testGetNewspaperByHeadline() {
    	/* This test tests to see if the item is
    	 * valid based on getting its name. Returns a list.
    	 */
    	Newspaper newspaper = null;
    	try{
    		newspaper = newspaperService.getNewspaperByHeadline(HEADLINE);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(newspaper.getHeadline(), HEADLINE);
    }
    
    @Test
    public void testGetMovieByDirector() {
    	/* This test tests to see if the item is
    	 * valid based on getting its director. Returns a list.
    	 */
    	List<Movie> movies = null;
    	try{
    		movies = movieService.getMovieFromDirector(AUTHOR_DIRECTOR_MUSICIAN);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(movies.get(0).getDirector(), AUTHOR_DIRECTOR_MUSICIAN);
    }
    @Test
    public void testGetBookByAuthor() {
    	/* This test tests to see if the item is
    	 * valid based on getting its author. Returns a list.
    	 */
    	List<Book> books = null;
    	try{
    		books = bookService.getBookFromAuthor(AUTHOR_DIRECTOR_MUSICIAN);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(books.get(0).getAuthor(), AUTHOR_DIRECTOR_MUSICIAN);
    }
    @Test
    public void testGetMusicByMusician() {
    	/* This test tests to see if the item is
    	 * valid based on getting its musician. Returns a list.
    	 */
    	List<Music> musics = null;
    	try{
    		musics = musicService.getMusicFromMusician(AUTHOR_DIRECTOR_MUSICIAN);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(musics.get(0).getMusician(), AUTHOR_DIRECTOR_MUSICIAN);
    }
    
    @Test
    public void testGetMovieByDistributor() {
    	/* This test tests to see if the item is
    	 * valid based on getting its distributor. Returns a list.
    	 */
    	List<Movie> movies = null;
    	try{
    		movies = movieService.getMovieFromDistributor(PUBLISHER_DISTRIBUTOR);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(movies.get(0).getFilmDistributor(), PUBLISHER_DISTRIBUTOR);
    }
    @Test
    public void testGetBookByPublisher() {
    	/* This test tests to see if the item is
    	 * valid based on getting its publisher. Returns a list.
    	 */
    	List<Book> books = null;
    	try{
    		books = bookService.getBookFromPublisher(PUBLISHER_DISTRIBUTOR);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(books.get(0).getPublisher(), PUBLISHER_DISTRIBUTOR);
    }
    @Test
    public void testGetMusicByLabel() {
    	/* This test tests to see if the item is
    	 * valid based on getting its label. Returns a list.
    	 */
    	List<Music> musics = null;
    	try{
    		musics = musicService.getMusicFromLabel(RECORD_LABEL);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(musics.get(0).getRecordLabel(), RECORD_LABEL);
    }
    
    @Test
    public void testGetMovieByRating() {
    	/* This test tests to see if the item is
    	 * valid based on getting its rating. Returns a list.
    	 */
    	List<Movie> movies = null;
    	try{
    		movies = movieService.getMovieFromRating(RATING);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(movies.get(0).getRating(), RATING);
    }
    @Test
    public void testGetBookByGenre() {
    	/* This test tests to see if the item is
    	 * valid based on getting its genre. Returns a list.
    	 */
    	List<Book> books = null;
    	try{
    		books = bookService.getBookFromGenre(GENRE);
        }
        catch (Exception e){
            fail();
        }
    	assertEquals(books.get(0).getGenre(), GENRE);
    }
    
}