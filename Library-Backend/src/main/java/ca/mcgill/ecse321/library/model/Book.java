package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Book extends CheckableItem{
    private String author;
    private String publisher;
    private String genre;
    public Book(){

    }
    public Book(int id, String name, Date date,String author, String publisher, String genre){
        super(id,name,date);
        this.author=author;
        this.publisher=publisher;
        this.genre=genre;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setAuthor(String a){
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
