package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private LibrarianRepository librarianRepository;
    
    @Transactional
    public Movie createMovie(Integer librarianId, Integer id, String name, Date date, String director, Integer runningTime, String rating, String distributor) throws Exception{
    	String error = "";
        if (librarianId == null) {
        	throw new PersonException("Librarian not found in request");
        } else if (librarianRepository.findPersonRoleById(librarianId) == null) {
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
    
    @Transactional
    public void deleteMovie(Integer movieId, Integer librarianId){
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
        movieRepository.deleteById(movieId);
        movie = null;
    }
    
    @Transactional
    public Movie getMovie(Integer movieId){
        Movie movie = (Movie) movieRepository.findItemById(movieId);
        return movie;
    }
    @Transactional
    public List<Item> getMovieByName(String name){
        List<Item> results = movieRepository.findItemByName(name);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromDirector(String director){
        List<Movie> results = movieRepository.findMovieByDirector(director);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromDistributor(String distributor){
        List<Movie> results = movieRepository.findMovieByFilmDistributor(distributor);
        return results;
    }
    @Transactional
    public List<Movie> getMovieFromRating(String rating){
        List<Movie> results = movieRepository.findMovieByRating(rating);
        return results;
    }
}
