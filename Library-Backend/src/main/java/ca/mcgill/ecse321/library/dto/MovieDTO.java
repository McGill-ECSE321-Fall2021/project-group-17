package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class MovieDTO {
    private Integer id;
    private String name;
    private Date datePublished;
    private  String director;
    private Integer runningTime;
    private String rating;
    private String filmDistributor;
    public MovieDTO(){

    }
    public MovieDTO(Integer id){
        this.id=id;
    }
    public MovieDTO(int id, String name, Date date, String director, Integer runningTime, String rating, String filmDistributor){
        this.id=id;
        this.name=name;
        this.datePublished=date;
        this.director=director;
        this.runningTime=runningTime;
        this.rating=rating;
        this.filmDistributor=filmDistributor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilmDistributor() {
        return filmDistributor;
    }

    public void setFilmDistributor(String filmDistributor) {
        this.filmDistributor = filmDistributor;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
        this.runningTime = runningTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }
}
