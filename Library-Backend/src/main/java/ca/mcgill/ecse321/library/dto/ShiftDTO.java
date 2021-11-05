package ca.mcgill.ecse321.library.dto;

import java.sql.Time;
import java.time.DayOfWeek;

import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class ShiftDTO {
	
    private LibraryManagementSystem system;
    private int id;
    private Time startTime;
    private Time endTime;
    private DayOfWeek dayOfWeek;
    private Librarian librarian;

    public ShiftDTO(){
        this.startTime = null;
        this.endTime = null;
        this.dayOfWeek = null;
    }
    
    public ShiftDTO(Time startTime, Time endTime, DayOfWeek dayOfWeek){
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

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

    public LibraryManagementSystem getSystem() {
        return system;
    }

    public Librarian getLibrarian(){ return this.librarian;}

    public void setId(Integer id){ this.id = id;}
    
    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }
    
    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }
    
    public void setDayOfWeek(DayOfWeek dayOfWeek){this.dayOfWeek = dayOfWeek;}
    
    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
    
    public void setLibrarian(Librarian librarian){ this.librarian = librarian;}

}
