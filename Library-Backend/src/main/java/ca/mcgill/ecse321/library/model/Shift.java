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

    public Shift(Time startTime, Time endTime, DayOfWeek dayOfWeek){
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
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
    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }
    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }
    public void setDayOfWeek(DayOfWeek dayOfWeek){this.dayOfWeek = dayOfWeek;}
    public void setLibrarian(Librarian librarian){ this.librarian = librarian;}
}
