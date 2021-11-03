package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.MovieRepository;

import ca.mcgill.ecse321.library.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Transactional
    public Movie createMovie(Integer id, String name, Date date, String director, Integer runningTime, String rating, String distributor){
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
    @Transactional
    public Movie getMovie(Integer movieId){
        Movie m= (Movie) movieRepository.findItemById(movieId);
        return m;
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
