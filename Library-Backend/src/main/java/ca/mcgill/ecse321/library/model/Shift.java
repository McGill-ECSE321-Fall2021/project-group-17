package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;

@Entity
public class Shift {

    private int id;
    private Time startTime;
    private Time endTime;
    private DayOfWeek dayOfWeek;
    private Librarian librarian;

    public Shift(){
        this.startTime = null;
        this.endTime = null;
        this.dayOfWeek = null;
    }

    public Shift(String startTime, String endTime, String dayOfWeek){
        this.startTime = Time.valueOf(startTime);
        this.endTime = Time.valueOf(endTime);
        this.dayOfWeek = DayOfWeek.valueOf(dayOfWeek);
    }

    //Getters
    @Id
    @Column(name="id", updatable=false, nullable = false)
    public int getId(){return this.id;}
    public Time getStartTime(){
        return this.startTime;
    }
    public Time getEndTime(){
        return this.endTime;
    }
    public DayOfWeek getDayOfWeek(){
        return this.dayOfWeek;
    }
    @ManyToOne
    @JoinColumn()
    public Librarian getLibrarian(){ return this.librarian;}

    //Setters
    public void setId(Integer id){this.id = id;}
    public void updateStartTime(String startTime){
        this.startTime = Time.valueOf(startTime);
    }
    public void setStartTime(Time startTime){this.startTime = startTime;}
    public void updateEndTime(String endTime){
        this.endTime = Time.valueOf(endTime);
    }
    public void setEndTime(Time endTime){this.endTime = endTime;}
    public void updateDayOfWeek(String dayOfWeek){this.dayOfWeek = DayOfWeek.valueOf(dayOfWeek);}
    public void setDayOfWeek(DayOfWeek dayOfWeek){this.dayOfWeek = dayOfWeek;}
    public void setLibrarian(Librarian librarian){ this.librarian = librarian;}
}
