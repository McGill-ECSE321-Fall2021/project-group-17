package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class MusicDTO {
    private Integer id;
    private String name;
    private Date datePublished;
    private  String musician;
    private String recordLabel;
    public MusicDTO(){

    }
    public MusicDTO(Integer id){
        this.id=id;
    }
    public MusicDTO(int id, String name, Date date, String musician, String recordLabel){
        this.id=id;
        this.name=name;
        this.datePublished=date;
        this.musician=musician;
        this.recordLabel=recordLabel;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public String getMusician() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
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
}
