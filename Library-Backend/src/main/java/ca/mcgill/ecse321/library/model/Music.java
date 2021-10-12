package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Music extends CheckableItem{
    private Integer musicId;
    private  String musician;
    private Integer yearReleased;
    private String recordLabel;

    public void setMusicId(Integer m){
        this.musicId=m;
    }
    @Id
    public Integer getMusicId(){
        return this.musicId;
    }

    public void setMusician(String m){
        this.musician=m;
    }
    public String getMusician(){
        return this.musician;
    }
    public void setYearReleased(Integer y){
        this.yearReleased=y;
    }
    public Integer getYearReleased(){
        return this.yearReleased;
    }
    public void setRecordLabel(String r){
        this.recordLabel=r;
    }
    public String getRecordLabel(){
        return this.recordLabel;
    }

}
