package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

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
import ca.mcgill.ecse321.library.service.Exception.PersonException;

public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CheckableItemRepository checkableItemRepository;
    @Autowired
    LibrarianRepository librarianRepository;
    @Autowired
    ItemInstanceRepository itemInstanceRepository;
    
    @Transactional
    public Movie createMovie(int librarianId, Integer id, String name, Date date, String director, Integer runningTime, String rating, String distributor){
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
    	if(librarian == null){
            throw new PersonException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
    	Movie m= new Movie();
        m.setId(id);
        m.setName(name);
        m.setDatePublished(date);
        m.setFilmDistributor(distributor);
        m.setRating(rating);
        m.setDirector(director);
        m.setRunningTime(runningTime);
        movieRepository.save(m);
        return m;
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
            throw new MovieException("Cannot authorize customer to delete movie");
        }
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null){
            throw new PersonException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        checkableItemRepository.deleteById(movieId);
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
