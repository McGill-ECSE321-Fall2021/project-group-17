package ca.mcgill.ecse321.library.model;

import org.hibernate.annotations.Check;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie extends CheckableItem {
//    Integer movieId;
    String director;
    Integer runningTime;
    String rating;
    String filmDistributor;

//    public void setMovieId(Integer m){
//        this.movieId=m;
//    }
//    //@Id
//    public Integer getMovieId(){
//        return this.movieId;
//    }
    public void setDirector(String d){
        this.director=d;
    }
    public String getDirector(){
        return this.director;
    }
    public void setRunningTime(Integer r){
        this.runningTime=r;
    }
    public Integer getRunningTime(){
        return this.runningTime;
    }
    public void setRating(String r){
        this.rating=r;
    }
    public String getRating(){
        return this.rating;
    }
    public void setFilmDistributor(String f){
        this.filmDistributor=f;
    }
    public String getFilmDistributor(){
        return this.filmDistributor;
    }

}
