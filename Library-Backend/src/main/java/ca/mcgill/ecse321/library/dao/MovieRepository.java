package ca.mcgill.ecse321.library.dao;


import ca.mcgill.ecse321.library.model.Movie;

import java.util.List;
//adds features to find movies based on attributes
public interface MovieRepository extends CheckableItemRepository{
    List<Movie> findMovieByDirector(String director);
    List<Movie> findMovieByFilmDistributor(String distributor);
    List<Movie> findMovieByRating(String rating);


}
