package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.service.Exception.MovieException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class MovieService {
	/*
	 * Repositories for aiding the service.
	 * These repositories allow for the storage of data
	 * into the database. Allows for create, get, update and delete functionalities.
	 */
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Transactional
    public Movie createMovie(Integer librarianId, Integer id, String name, Date date, String director, Integer runningTime, String rating, String distributor) throws Exception{
    	/*
    	 * Creates a movie item with the provided ids from the rest controller method.
    	 * Throws errors based on arguments, and whether they were provided or not.
    	 * Saves item into repository at the end.
    	 */
    	String error = "";
    	if (librarianId == null) {
    		throw new IllegalArgumentException("Librarian does not exist! ");
    	}
    	Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if (librarian == null) {
            error = error + "Librarian does not exist! ";
        }
        if (id == null) {
            error = error + "Id needs to be provided!";
        }
        if (name == null) {
            error = error + "Name needs to be provided!";
        }
        error = error.trim();

        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        
    	Movie movie = new Movie();
        movie.setName(name);
        movie.setDatePublished(date);
        movie.setFilmDistributor(distributor);
        movie.setRating(rating);
        movie.setDirector(director);
        movie.setRunningTime(runningTime);
        movieRepository.save(movie);
        return movie;
    }
    
    @Transactional
    public void deleteMovie(Integer movieId, Integer librarianId){
    	/*
    	 * Deletes item based on id, and requires librarian id to
    	 * perform this task. Deletes in repository at the end.
    	 */
        if(movieId == null){
            throw new MovieException("Cannot find movie with id to delete");
        }
        Movie movie = (Movie) movieRepository.findItemById(movieId);
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

        List<ItemInstance> itemInstances = itemInstanceRepository.findByCheckableItem(movie);

        for (ItemInstance itemInstance : itemInstances) {
            Loan loan = loanRepository.findByItemInstance(itemInstance);

            if (loan != null) {
                loanRepository.delete(loan);
            }

            Reservation reservation = reservationRepository.findByItemInstance(itemInstance);

            if (reservation != null) {
                reservationRepository.delete(reservation);
            }

            itemInstanceRepository.delete(itemInstance);
        }

        movieRepository.delete(movie);
        movie = null;
    }
    
    @Transactional
    public Movie updateMovie(Integer librarianId, Integer id, String name, Date date, String director, 
    		Integer runningTime, String rating, String distributor) {
       	/*
    	 * Updates an item with the provided ids from the rest controller method.
    	 * Throws errors based on arguments, and whether they were provided or not.
    	 * Saves item into repository at the end.
    	 */
    	if (librarianId == null || librarianRepository.findPersonRoleById(librarianId) == null) {
        	throw new PersonException("Librarian does not exist!");
    	}
    	
    	Movie movie = (Movie) movieRepository.findItemById(id);

        if (movie == null) {
            throw new MovieException("Can't update movie because no movie exists for the given id.");
        }

        if (id != null) {
        	movie.setId(id);
        }

        if (name != null) {
            movie.setName(name);
        }

        if (date != null) {
            movie.setDatePublished(date);
        }

        if (director != null) {
            movie.setDirector(director);
        }
        
        if (runningTime != null) {
            movie.setRunningTime(runningTime);
        }
        
        if (rating != null) {
            movie.setRating(rating);
        }
        
        if (distributor != null) {
            movie.setFilmDistributor(distributor);
        }

        movieRepository.save(movie);
        return movie;
    }
    
    @Transactional
    public Movie getMovie(Integer movieId){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        Movie movie = (Movie) movieRepository.findItemById(movieId);
        return movie;
    }
    @Transactional
    public List<Item> getMovieByName(String name){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Item> results = movieRepository.findItemByName(name);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromDirector(String director){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Movie> results = movieRepository.findMovieByDirector(director);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromDistributor(String distributor){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Movie> results = movieRepository.findMovieByFilmDistributor(distributor);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromRating(String rating){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Movie> results = movieRepository.findMovieByRating(rating);
        return results;
    }

    @Transactional
    public List<Movie> getMovies() {
        List<Item> items = (List<Item>) movieRepository.findAll();

        List<Movie> movies = new ArrayList<>();

        for (Item item : items) {
            if (item instanceof Movie) {
                movies.add((Movie) item);
            }
        }
        return movies;
    }
}
