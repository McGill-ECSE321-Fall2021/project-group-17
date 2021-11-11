package ca.mcgill.ecse321.library.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import ca.mcgill.ecse321.library.dto.BookDTO;
import ca.mcgill.ecse321.library.dto.ItemDTO;
import ca.mcgill.ecse321.library.dto.MovieDTO;
import ca.mcgill.ecse321.library.dto.MusicDTO;
import ca.mcgill.ecse321.library.dto.NewspaperDTO;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Music;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.service.BookService;
import ca.mcgill.ecse321.library.service.ItemService;
import ca.mcgill.ecse321.library.service.MovieService;
import ca.mcgill.ecse321.library.service.MusicService;
import ca.mcgill.ecse321.library.service.NewspaperService;


@CrossOrigin(origins = "*")
@RestController
public class ItemRestController {

    @Autowired
    private ItemService service;
    @Autowired
    private MovieService movieService;
    @Autowired
    private BookService bookService;
    @Autowired
    private MusicService musicService;
    @Autowired
    private NewspaperService newspaperService;

    @PostMapping(value= {"/item/movie/{librarianId}", "/item/movie/{librarianId}/"})
    @ResponseBody
    public MovieDTO addMovie(@PathVariable("librarianId") Integer librarianId,
                                  @RequestBody JsonBodyMovie body) throws Exception{
        Movie movie = movieService.createMovie(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getDirector(),
					body.getRunningTime(), body.getRating(), body.getFilmDistributor());
        return convertMovieToDTO(movie);
    }

    private MovieDTO convertMovieToDTO(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("There is no such Movie!");
        }

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setName(movie.getName());
        movieDTO.setDatePublished(movie.getDatePublished());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setRunningTime(movie.getRunningTime());
        movieDTO.setFilmDistributor(movie.getFilmDistributor());
        return movieDTO;
    }
    
    private ItemDTO convertItemToDTO(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("There is no such Item!");
        }

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDatePublished(item.getDatePublished());
        return itemDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBodyMovie {
        private Integer id;
        private String name;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date datePublished;
        private String director;
        private Integer runningTime;
        private String rating;
        private String filmDistributor;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDatePublished() {
            return datePublished;
        }

        public void setDatePublished(Date datePublished) {
            this.datePublished = datePublished;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public Integer getRunningTime() {
            return runningTime;
        }

        public void setRunningTime(Integer runningTime) {
            this.runningTime = runningTime;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getFilmDistributor() {
            return filmDistributor;
        }

        public void setFilmDistributor(String filmDistributor) {
            this.filmDistributor = filmDistributor;
        }
        
        public JsonBodyMovie(){}
    }

    @PostMapping(value= {"/item/book/{librarianId}", "/item/book/{librarianId}/"})
    @ResponseBody
    public BookDTO addBook(@PathVariable("librarianId") int librarianId,
                            @RequestBody JsonBodyBook body) throws IllegalArgumentException{
        Book book = bookService.createBook(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getAuthor(),
                body.getPublisher(), body.getGenre());
        return convertBookToDTO(book);
    }

    private BookDTO convertBookToDTO(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("There is no such Book!");
        }

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setDatePublished(book.getDatePublished());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setGenre(book.getGenre());
        return bookDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBodyBook {
        private Integer id;
        private String name;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date datePublished;
        private String author;
        private String publisher;
        private String genre;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDatePublished() {
            return datePublished;
        }

        public void setDatePublished(Date datePublished) {
            this.datePublished = datePublished;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }
        
        public JsonBodyBook(){}
    }


    @PostMapping(value= {"/item/music/{librarianId}", "/item/music/{librarianId}/"})
    @ResponseBody
    public MusicDTO addMusic(@PathVariable("librarianId") int librarianId,
                             @RequestBody JsonBodyMusic body) throws IllegalArgumentException{
        Music music = musicService.createMusic(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getMusician(),
                body.getRecordLabel());
        return convertMusicToDTO(music);
    }

    private MusicDTO convertMusicToDTO(Music music) {
        if (music == null) {
            throw new IllegalArgumentException("There is no such Music!");
        }

        MusicDTO musicDTO = new MusicDTO();
        musicDTO.setId(music.getId());
        musicDTO.setName(music.getName());
        musicDTO.setDatePublished(music.getDatePublished());
        musicDTO.setMusician(music.getMusician());
        musicDTO.setRecordLabel(music.getRecordLabel());
        return musicDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBodyMusic {
        private Integer id;
        private String name;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date datePublished;
        private String musician;
        private String recordLabel;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDatePublished() {
            return datePublished;
        }

        public void setDatePublished(Date datePublished) {
            this.datePublished = datePublished;
        }

        public String getMusician() {
            return musician;
        }

        public void setMusician(String musician) {
            this.musician = musician;
        }

        public String getRecordLabel() {
            return recordLabel;
        }

        public void setRecordLabel(String recordLabel) {
            this.recordLabel = recordLabel;
        }
        
        public JsonBodyMusic(){}
    }

    @PostMapping(value= {"/item/newspaper/{librarianId}", "/item/newspaper/{librarianId}/"})
    @ResponseBody
    public NewspaperDTO addNewspaper(@PathVariable("librarianId") int librarianId,
                                 @RequestBody JsonBodyNewspaper body) throws IllegalArgumentException{
        Newspaper newspaper = newspaperService.createNewspaper(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getHeadline());
        return convertNewspaperToDTO(newspaper);
    }

    private NewspaperDTO convertNewspaperToDTO(Newspaper newspaper) {
        if (newspaper == null) {
            throw new IllegalArgumentException("There is no such Newspaper!");
        }

        NewspaperDTO newspaperDTO = new NewspaperDTO();
        newspaperDTO.setId(newspaper.getId());
        newspaperDTO.setName(newspaper.getName());
        newspaperDTO.setDatePublished(newspaper.getDatePublished());
        newspaperDTO.setHeadline(newspaper.getHeadline());
        return newspaperDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBodyNewspaper {
        private Integer id;
        private String name;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date datePublished;
        private String headline;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDatePublished() {
            return datePublished;
        }

        public void setDatePublished(Date datePublished) {
            this.datePublished = datePublished;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }
        
        public JsonBodyNewspaper(){}
    }
    
    // delete music, movie, book, newspaper
    
    @DeleteMapping(value= {"/item/movie/{librarianId}/{id}", "/item/movie/{librarianId}/{id}/"})
    public void deleteMovie(@PathVariable("librarianId") int librarianId,
    		@PathVariable("id") int id) throws IllegalArgumentException{
    	movieService.deleteMovie(id, librarianId);
    }
    
    @DeleteMapping(value= {"/item/book/{librarianId}/{id}", "/item/book/{librarianId}/{id}/"})
    public void deleteBook(@PathVariable("librarianId") int librarianId,
    		@PathVariable("id") int id) throws IllegalArgumentException{
    	bookService.deleteBook(id, librarianId);
    }
    
    @DeleteMapping(value= {"/item/music/{librarianId}/{id}", "/item/music/{librarianId}/{id}/"})
    public void deleteMusic(@PathVariable("librarianId") int librarianId,
    		@PathVariable("id") int id) throws IllegalArgumentException{
    	musicService.deleteMusic(id, librarianId);
    }
    
    @DeleteMapping(value= {"/item/newspaper/{librarianId}/{id}", "/item/newspaper/{librarianId}/{id}/"})
    public void deleteNewspaper(@PathVariable("librarianId") int librarianId,
    		@PathVariable("id") int id) throws IllegalArgumentException{
    	newspaperService.deleteNewspaper(id, librarianId);
    }
    
    @GetMapping(value= {"/item/movie/{id}", "/item/movie/{id}/"})
    public MovieDTO getMovieById(@PathVariable("id") int id) throws IllegalArgumentException{
    	Movie movie = movieService.getMovie(id);
    	return convertMovieToDTO(movie);    
    }
    @GetMapping(value= {"/item/book/{id}", "/item/book/{id}/"})
    public BookDTO getBookById(@PathVariable("id") int id) throws IllegalArgumentException{
    	Book book = bookService.getBook(id);
    	return convertBookToDTO(book);
    }
    @GetMapping(value= {"/item/music/{id}", "/item/music/{id}/"})
    public MusicDTO getMusicById(@PathVariable("id") int id) throws IllegalArgumentException{
    	Music music = musicService.getMusic(id);
    	return convertMusicToDTO(music);
    }
    @GetMapping(value= {"/item/newspaper/{id}", "/item/newspaper/{id}/"})
    public NewspaperDTO getNewspaperById(@PathVariable("id") int id) throws IllegalArgumentException{
    	Newspaper newspaper = newspaperService.getNewspaper(id);
    	return convertNewspaperToDTO(newspaper);
    }
    
    @GetMapping(value= {"/item/movie/{name}", "/item/movie/{name}/"})
    public List<ItemDTO> getMovieByName(@PathVariable("name") String name) throws IllegalArgumentException{
    	return movieService.getMovieByName(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    }
    @GetMapping(value= {"/item/music/{name}", "/item/music/{name}/"})
    public List<ItemDTO> getMusicByName(@PathVariable("name") String name) throws IllegalArgumentException{
    	return musicService.getMusicByName(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    }
    @GetMapping(value= {"/item/book/{name}", "/item/book/{name}/"})
    public List<ItemDTO> getBookByName(@PathVariable("name") String name) throws IllegalArgumentException{
    	return bookService.getBookByName(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    }
    @GetMapping(value= {"/item/newspaper/{name}", "/item/newspaper/{name}/"})
    public NewspaperDTO getNewspaperByHeadline(@PathVariable("name") String name) throws IllegalArgumentException{
    	Newspaper newspaper = newspaperService.getNewspaperByHeadline(name);
    	return convertNewspaperToDTO(newspaper);
    }
    
    
    
    
}
