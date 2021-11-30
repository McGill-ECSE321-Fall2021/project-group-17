package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMoviePersistence {
    @Autowired
    private MovieRepository movieRepository;
    @AfterEach
    public void clearDatabase() {
        movieRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadMovie(){
        //create object to be tested
        String director="Victoria";
        Integer runningTime=55;
        String rating="PG-13";
        String filmDistributor="Sony";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String name= "My Brilliant Friend";
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDatePublished(date);
        movie.setDirector(director);
        movie.setRunningTime(runningTime);
        movie.setRating(rating);
        movie.setFilmDistributor(filmDistributor);
        movieRepository.save(movie);
        Integer movieID= movie.getId();
        movie=null;
        movie=(Movie) movieRepository.findItemById(movieID);
        assertNotNull(movie);
        assertEquals(movieID,movie.getId());
        assertEquals(name, movie.getName());
        assertEquals(date,movie.getDatePublished());
        assertEquals(director,movie.getDirector());
        assertEquals(runningTime,movie.getRunningTime());
        assertEquals(rating,movie.getRating());
        assertEquals(filmDistributor,movie.getFilmDistributor());

    }
    @Test
    public void testFindMovieByDirector(){
        List<Movie> movies= new ArrayList<Movie>();
        String director = "James Cameron";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, director,180,"PG-13","Disney");
        Movie m2= new Movie(5678,name2,date, director,120,"PG","WarnerBros");
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByDirector(director);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    @Test
    @Transactional
    public void testFindMovieByFilmDistributor(){
        List<Movie> movies= new ArrayList<Movie>();
        String distributor = "Disney";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, "Sofia Coppola",180,"PG-13",distributor);
        Movie m2= new Movie(5678,name2,date, "Greta Gerwig",120,"PG",distributor);
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByFilmDistributor(distributor);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    @Test
    public void testFindMovieByRating(){
        List<Movie> movies= new ArrayList<Movie>();
        String rating="PG";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, "Sofia Coppola",180,rating,"Disney");
        Movie m2= new Movie(5678,name2,date, "Greta Gerwig",120,rating,"WarnerBros");
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByRating(rating);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
}
