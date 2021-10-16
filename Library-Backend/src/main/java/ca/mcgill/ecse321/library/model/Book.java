package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Date;
/*
Written by Victoria Sanchez
 */
@Entity
public class Book extends CheckableItem{
    private String author;
    private String publisher;
    private String genre;
    public Book(){ //default constructor

    }
    public Book(int id, String name, Date date,String author, String publisher, String genre){ //main constructor
        super(id,name,date);
        this.author=author;
        this.publisher=publisher;
        this.genre=genre;
    }
    //setters and getters
    public String getAuthor(){
        return this.author;
    }
    public void setAuthor(String a) {
        this.author=a;
    }
    public void setPublisher(String p){
        this.publisher=p;
    }
    public String getPublisher(){
        return this.publisher;
    }
    public void setGenre(String g){
        this.genre=g;
    }
    public String getGenre(){
        return this.genre;
    }

}
