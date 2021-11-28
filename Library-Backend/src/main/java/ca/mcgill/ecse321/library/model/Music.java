package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import java.sql.Date;
/*
class written by Victoria Sanchez
 */

@Entity
public class Music extends CheckableItem{
    private  String musician;

    private String recordLabel;
    public Music(){ //default constructor

    }
    public Music(int id, String name, Date date, String musician, String recordLabel){
        super(name,date);
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
