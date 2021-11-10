package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.CheckableItemRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.ItemRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.service.Exception.MovieException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CheckableItemRepository checkableItemRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    
    @Transactional
    public Movie createMovie(Integer librarianId, Integer id, String name, Date date, String director, Integer runningTime, String rating, String distributor) throws Exception{
    	try {
    	String error = "";
        if (librarianId == null) {
        	throw new PersonException("Librarian not found in request");
        } else if (librarianRepository.findPersonRoleById(librarianId) == null) {
            error = error + "Librarian does not exist! ";
        }
        if (id == null) {
            error = error + "Id needs to be provided!";
        } 
//        else if (movieRepository.findItemById(id) != null) {
//            error = error + "Item with id " + id + " already exists! ";
//        }
        if (name == null) {
            error = error + "Name needs to be provided!";
        }
        if (date == null) {
            error = error + "Date needs to be provided!";
        }
        if (director == null) {
            error = error + "Director needs to be provided!";
        }
        if (runningTime == null) {
            error = error + "Running time needs to be provided!";
        }
        if (rating == null) {
            error = error + "Rating needs to be provided!";
        }
        if (distributor == null) {
            error = error + "Distributor needs to be provided!";
        }
        error = error.trim();

        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
    	Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        
    	Movie movie = new Movie();
        movie.setId(id);
        movie.setName(name);
        movie.setDatePublished(date);
        movie.setFilmDistributor(distributor);
        movie.setRating(rating);
        movie.setDirector(director);
        movie.setRunningTime(runningTime);
        movieRepository.save(movie);
        return movie;
    	}
    	catch (Exception e) {
    		throw new Exception(e.getMessage());
    	}
    }
    
    /*@Transactional
    public void deleteMovie(Integer id){
        movieRepository.deleteById(id);;
    }*/

    @Transactional
    public void deleteMovie(Integer movieId, Integer librarianId){
        if(movieId == null){
            throw new MovieException("Cannot find movie with id to delete");
        }
        Optional<Item> movie = movieRepository.findById(movieId);
        if(movie == null){
            throw new NotFoundException("Cannot find movie to delete");
        }
        if(librarianId == null){
            throw new MovieException("Cannot authorize librarian to delete movie");
        }
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null){
            throw new OnlineAccountException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        movieRepository.deleteById(movieId);
        movie = null;
    }
    
    @Transactional
    public Movie getMovie(Integer movieId){
        Movie m= (Movie) movieRepository.findItemById(movieId);
        return m;
    }
    @Transactional
    public List<Item> getMovieByName(String name){
        List<Item> results= movieRepository.findItemByName(name);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromDirector(String director){
        List<Movie> results= movieRepository.findMovieByDirector(director);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromDistributor(String distributor){
        List<Movie> results= movieRepository.findMovieByFilmDistributor(distributor);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromRating(String rating){
        List<Movie> results= movieRepository.findMovieByRating(rating);
        return results;
    }
}
