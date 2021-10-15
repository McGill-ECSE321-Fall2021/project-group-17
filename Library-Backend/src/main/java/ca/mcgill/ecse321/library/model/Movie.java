package ca.mcgill.ecse321.library.model;


import javax.persistence.Entity;
import java.sql.Date;
 /*
 class written by Victoria Sanhcez
  */
@Entity
public class Movie extends CheckableItem {
    String director;
    Integer runningTime;
    String rating;
    String filmDistributor;

    public Movie(){

    }
    public Movie(int id, String name, Date date, String director, Integer runningTime, String rating, String filmDistributor){
        super(id,name,date);
        this.director=director;
        this.runningTime=runningTime;
        this.rating=rating;
        this.filmDistributor=filmDistributor;
    }
    //setters and getters are generated
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

