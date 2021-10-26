package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class BookDTO {
    private Integer id;
    private String name;
    private Date datePublished;
    private String author;
    private String publisher;
    private String genre;
    public BookDTO(){
    }
    public BookDTO(Integer id){
      this.id=id;
    }
    public BookDTO(int id, String name,Date date,String author, String publisher, String genre){
        this.id=id;
        this.name=name;
        this.datePublished=date;
        this.author=author;
        this.publisher=publisher;
        this.genre=genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
