package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Music extends CheckableItem{
    private  String musician;

    private String recordLabel;
    public Music(){

    }
    public Music(int id, String name, Date date, String musician, String recordLabel){
        super(id,name,date);
        this.musician=musician;
        this.recordLabel=recordLabel;
    }

    public void setMusician(String m){
        this.musician=m;
    }
    public String getMusician(){
        return this.musician;
    }
    public void setRecordLabel(String r){
        this.recordLabel=r;
    }
    public String getRecordLabel(){
        return this.recordLabel;
    }

}
