package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Book extends CheckableItem{
    private int bookId;
    private String author;
    private String publisher;
    private java.sql.Date datePublished;
    public void setBookId(int s){
        this.bookId=s;
    }
    @Id
    public int getBookId(){
        return this.bookId;
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
    public String getPublisher(String p){
        return this.publisher;
    }
    public void setDatePublished(Date d){
        this.datePublished=d;
    }
    public Date getDatePublished(){
        return this.datePublished;
    }

}
