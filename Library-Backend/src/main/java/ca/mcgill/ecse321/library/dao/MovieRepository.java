package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie,Integer>{
    //List<Movie> findByDirector(String director);
    //List<Movie> findByRating(String rating);
    //List<Movie> findFilmDistributor(String distributor);
   Movie findMovieMovieID(Integer ID);


}
