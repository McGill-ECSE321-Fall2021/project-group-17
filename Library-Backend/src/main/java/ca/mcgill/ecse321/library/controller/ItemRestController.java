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
import org.springframework.web.bind.annotation.PutMapping;
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
import ca.mcgill.ecse321.library.service.MovieService;
import ca.mcgill.ecse321.library.service.MusicService;
import ca.mcgill.ecse321.library.service.NewspaperService;

/*
 * Rest controller that controls movie, book, music
 * and newspaper services. These services allow for
 * use with the rest controller.
 */
@CrossOrigin(origins = "*")
@RestController
public class ItemRestController {

	/*
	 * Provided service classes that allow manipulation with the
	 * rest controller.
	 */
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
    	/* calls the item service that corresponds to the input provided, 
    	 * and creates an item with provided elements from the json body.
    	 * Then, converts it to a DTO */
        Movie movie = movieService.createMovie(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getDirector(),
					body.getRunningTime(), body.getRating(), body.getFilmDistributor());
        return convertMovieToDTO(movie);
    }

    private MovieDTO convertMovieToDTO(Movie movie) {
    	/* Converts provided item to DTO. Checks if input movie is null,
    	 * then sets id in all fields. Return the DTO
    	 */
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
    	/* Converts item to DTO. Checks if input movie is null,
    	 * then sets id in all fields. Return the DTO
    	 */
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
    	/* given provided fields, initializes getters and setters
    	 * that are able to query and send back data from
    	 * the controller.
    	 */
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
    	/* calls the item service that corresponds to the input provided, 
    	 * and creates an item with provided elements from the json body.
    	 * Then, converts it to a DTO */
        Book book = bookService.createBook(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getAuthor(),
                body.getPublisher(), body.getGenre());
        return convertBookToDTO(book);
    }

    private BookDTO convertBookToDTO(Book book) {
    	/* Converts provided item to DTO. Checks if input movie is null,
    	 * then sets id in all fields. Return the DTO
    	 */
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
    	/* given provided fields, initializes getters and setters
    	 * that are able to query and send back data from
    	 * the controller.
    	 */
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
    	/* calls the item service that corresponds to the input provided, 
    	 * and creates an item with provided elements from the json body.
    	 * Then, converts it to a DTO */
        Music music = musicService.createMusic(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getMusician(),
                body.getRecordLabel());
        return convertMusicToDTO(music);
    }

    private MusicDTO convertMusicToDTO(Music music) {
    	/* Converts provided item to DTO. Checks if input movie is null,
    	 * then sets id in all fields. Return the DTO
    	 */
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
    	/* given provided fields, initializes getters and setters
    	 * that are able to query and send back data from
    	 * the controller.
    	 */
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
    	/* calls the item service that corresponds to the input provided, 
    	 * and creates an item with provided elements from the json body.
    	 * Then, converts it to a DTO */
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
    	/* given provided fields, initializes getters and setters
    	 * that are able to query and send back data from
    	 * the controller.
    	 */
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
    
    
    // update music, movie, book, newspaper
    
    @PutMapping(value= {"/item/movie/{librarianId}", "/item/movie/{librarianId}/"})
    @ResponseBody
    public MovieDTO updateMovie(@PathVariable("librarianId") int librarianId,
                                 @RequestBody JsonBodyMovie body) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and updates an item with provided elements from the json body.
    	 * Then, converts it to a DTO */
        Movie movie = movieService.updateMovie(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getDirector(),
				body.getRunningTime(), body.getRating(), body.getFilmDistributor());
        return convertMovieToDTO(movie);
    }
    
    @PutMapping(value= {"/item/music/{librarianId}", "/item/music/{librarianId}/"})
    @ResponseBody
    public MusicDTO updateMusic(@PathVariable("librarianId") int librarianId,
                                 @RequestBody JsonBodyMusic body) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and updates an item with provided elements from the json body.
    	 * Then, converts it to a DTO */
        Music music = musicService.updateMusic(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getMusician(),
                body.getRecordLabel());
        return convertMusicToDTO(music);
    }
    
    @PutMapping(value= {"/item/book/{librarianId}", "/item/book/{librarianId}/"})
    @ResponseBody
    public BookDTO updateBook(@PathVariable("librarianId") int librarianId,
                                 @RequestBody JsonBodyBook body) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and updates an item with provided elements from the json body.
    	 * Then, converts it to a DTO */
    	Book book = bookService.updateBook(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getAuthor(),
                body.getPublisher(), body.getGenre());
        return convertBookToDTO(book);
    }
    
    @PutMapping(value= {"/item/newspaper/{librarianId}", "/item/newspaper/{librarianId}/"})
    @ResponseBody
    public NewspaperDTO updateNewspaper(@PathVariable("librarianId") int librarianId,
                                 @RequestBody JsonBodyNewspaper body) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and updates an item with provided elements from the json body.
    	 * Then, converts it to a DTO */
        Newspaper newspaper = newspaperService.updateNewspaper(librarianId, body.getId(), body.getName(), body.getDatePublished(),body.getHeadline());
        return convertNewspaperToDTO(newspaper);
    }
    
    
    // delete music, movie, book, newspaper
    
    @DeleteMapping(value= {"/item/movie/{librarianId}/{id}", "/item/movie/{librarianId}/{id}/"})
    public void deleteMovie(@PathVariable("librarianId") int librarianId,
    		@PathVariable("id") int id) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 */
    	movieService.deleteMovie(id, librarianId);
    }
    
    @DeleteMapping(value= {"/item/book/{librarianId}/{id}", "/item/book/{librarianId}/{id}/"})
    public void deleteBook(@PathVariable("librarianId") int librarianId,
    		@PathVariable("id") int id) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 */
    	bookService.deleteBook(id, librarianId);
    }
    
    @DeleteMapping(value= {"/item/music/{librarianId}/{id}", "/item/music/{librarianId}/{id}/"})
    public void deleteMusic(@PathVariable("librarianId") int librarianId,
    		@PathVariable("id") int id) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 */
    	musicService.deleteMusic(id, librarianId);
    }
    
    @DeleteMapping(value= {"/item/newspaper/{librarianId}/{id}", "/item/newspaper/{librarianId}/{id}/"})
    public void deleteNewspaper(@PathVariable("librarianId") int librarianId,
    		@PathVariable("id") int id) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 */
    	newspaperService.deleteNewspaper(id, librarianId);
    }
    
    @GetMapping(value= {"/item/movie/id/{id}", "/item/movie/id/{id}/"})
    public MovieDTO getMovieById(@PathVariable("id") int id) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	Movie movie = movieService.getMovie(id);
    	return convertMovieToDTO(movie);    
    }
    @GetMapping(value= {"/item/book/id/{id}", "/item/book/id/{id}/"})
    public BookDTO getBookById(@PathVariable("id") int id) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	Book book = bookService.getBook(id);
    	return convertBookToDTO(book);
    }
    @GetMapping(value= {"/item/music/id/{id}", "/item/music/id/{id}/"})
    public MusicDTO getMusicById(@PathVariable("id") int id) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	Music music = musicService.getMusic(id);
    	return convertMusicToDTO(music);
    }
    @GetMapping(value= {"/item/newspaper/id/{id}", "/item/newspaper/id/{id}/"})
    public NewspaperDTO getNewspaperById(@PathVariable("id") int id) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	Newspaper newspaper = newspaperService.getNewspaper(id);
    	return convertNewspaperToDTO(newspaper);
    }
    
    @GetMapping(value= {"/item/movie/name/{name}", "/item/movie/name/{name}/"})
    public List<ItemDTO> getMovieByName(@PathVariable("name") String name) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	return movieService.getMovieByName(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    }
    @GetMapping(value= {"/item/music/name/{name}", "/item/music/name/{name}/"})
    public List<ItemDTO> getMusicByName(@PathVariable("name") String name) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	return musicService.getMusicByName(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    }
    @GetMapping(value= {"/item/book/name/{name}", "/item/book/name/{name}/"})
    public List<ItemDTO> getBookByName(@PathVariable("name") String name) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	return bookService.getBookByName(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    }
    @GetMapping(value= {"/item/newspaper/name/{name}", "/item/newspaper/name/{name}/"})
    public NewspaperDTO getNewspaperByHeadline(@PathVariable("name") String name) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	Newspaper newspaper = newspaperService.getNewspaperByHeadline(name);
    	return convertNewspaperToDTO(newspaper);
    }
    
    @GetMapping(value= {"/item/{type}/{name}", "/item/{type}/{name}/"})
    public List<ItemDTO> getItemByVariable(@PathVariable("type") String type, @PathVariable("name") String name) throws IllegalArgumentException{
    	/* calls the item service that corresponds to the input provided, 
    	 * and deletes the item with provided parameter from the json body.
    	 * Then, converts it to a DTO. */
    	if(type.toLowerCase().equals("director")) {
    		return movieService.getMovieFromDirector(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    	}
    	else if(type.toLowerCase().equals("musician")) {
        	return musicService.getMusicFromMusician(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    	}
    	else if(type.toLowerCase().equals("author")) {
        	return bookService.getBookFromAuthor(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    	}
    	else if(type.toLowerCase().equals("distributor")) { 
    		return movieService.getMovieFromDistributor(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    	}
    	else if(type.toLowerCase().equals("publisher")) {
        	return bookService.getBookFromPublisher(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    	}
    	else if(type.toLowerCase().equals("rating")) {
    		return movieService.getMovieFromRating(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    	}
    	else if(type.toLowerCase().equals("label")) {
        	return musicService.getMusicFromLabel(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    	}
    	else if(type.toLowerCase().equals("genre")) {
    		return bookService.getBookFromGenre(name).stream().map(this::convertItemToDTO).collect(Collectors.toList());
    	}
    	return null;
    }
    
    
}
