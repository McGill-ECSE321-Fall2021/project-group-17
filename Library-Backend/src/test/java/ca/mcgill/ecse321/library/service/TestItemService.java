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
        lenient().when(bookRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(BOOK_KEY)) {
                Book book = new Book();
                book.setId(BOOK_KEY);
                return book;
            } else {
                return null;
            }
        });
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
        lenient().when(musicRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MUSIC_KEY)){
                Music music = new Music();
                music.setId(MUSIC_KEY);
                return music;
            } else {
            	return null;
            }
        });
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
        lenient().when(newspaperRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(NEWSPAPER_KEY)){
                Newspaper newspaper = new Newspaper();
                newspaper.setId(NEWSPAPER_KEY);
                return newspaper;
            } else {
                return null;
            }
        });
        lenient().when(newspaperRepository.findNewspaperByHeadline(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(HEADLINE)){
                Newspaper newspaper = new Newspaper();
                newspaper.setHeadline(HEADLINE);
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

    @AfterEach
    public void clearDatabase() {
        movieRepository.deleteAll();
        bookRepository.deleteAll();
        musicRepository.deleteAll();
        newspaperRepository.deleteAll();

    }

    @Test
    public void createMovieValid(){
        Movie movie = null;
        
        try{
            movie = movieService.createMovie(LIBRARIAN_KEY, MOVIE_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR);
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
        assertEquals(movie.getFilmDistributor(), PUBLISHER_DISTRIBUTOR);
    }
    
    @Test
    public void createMovieNoName(){
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
        String error = "";
        try{
        	movieService.createMovie(null, MOVIE_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RUNNING_TIME, RATING, PUBLISHER_DISTRIBUTOR);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Librarian not found in request");
    }
    @Test
    public void createBookNoLibrarian(){
        String error = "";
        try{
            bookService.createBook(null, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Librarian not found in request");
    }
    @Test
    public void createMusicNoLibrarian(){
        String error = "";
        try{
            musicService.createMusic(null, MUSIC_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Librarian not found in request");
    }
    @Test
    public void createNewspaperNoLibrarian(){
        String error = "";
        try{
            newspaperService.createNewspaper(null, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, HEADLINE);
		}
		catch (Exception e){
		    error = e.getMessage();
		}
		assertEquals(error, "Librarian not found in request");
    }
    

    @Test
    public void createBookValid(){
        Book book = null;
        
        try{
            book = bookService.createBook(LIBRARIAN_KEY, BOOK_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, PUBLISHER_DISTRIBUTOR, GENRE);
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
        assertEquals(book.getGenre(), GENRE);
    }
    
    
    @Test
    public void createMusicValid(){
        Music music = null;
        
        try{
            music = musicService.createMusic(LIBRARIAN_KEY, MUSIC_KEY, MEDIA_NAME, DATE_PUBLISHED, AUTHOR_DIRECTOR_MUSICIAN, RECORD_LABEL);
        }
        catch(Exception e){
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
    public void createNewspaperValid(){
        Newspaper newspaper = null;
        
        try{
            newspaper = newspaperService.createNewspaper(LIBRARIAN_KEY, NEWSPAPER_KEY, MEDIA_NAME, DATE_PUBLISHED, HEADLINE);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(newspaper);
        assertEquals(newspaper.getId(), NEWSPAPER_KEY);
        assertEquals(newspaper.getName(), MEDIA_NAME);
        assertEquals(newspaper.getDatePublished(), DATE_PUBLISHED);
        assertEquals(newspaper.getHeadline(), HEADLINE);
    }
    
    @Test
    public void testDeleteMovieValid(){
        try{
            movieService.deleteMovie(MOVIE_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){        	
            fail();
        }
    }
    @Test
    public void testDeleteBookValid(){
        try{
            bookService.deleteBook(BOOK_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
            fail();
        }
    }
    @Test
    public void testDeleteMusicValid(){
        try{
            musicService.deleteMusic(MUSIC_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
            fail();
        }
    }
    @Test
    public void testDeleteNewspaperValid(){
        try{
            newspaperService.deleteNewspaper(NEWSPAPER_KEY, LIBRARIAN_KEY);
        }
        catch (Exception e){
            fail();
        }
    }
    
    
    @Test
    public void testDeleteMovieMissingId(){
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