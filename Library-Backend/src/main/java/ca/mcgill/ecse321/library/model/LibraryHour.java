package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;


@Entity
public class LibraryHour {
	
	private String libraryHourId;
    private Time startTime;
    private Time endTime;
    private DayOfWeek dayOfWeek;
    
    public LibraryHour() {
    	
    }

    
    public LibraryHour(String libraryHourId, Time startTime, Time endTime, DayOfWeek dayOfWeek) {
    	this.libraryHourId = libraryHourId;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.dayOfWeek = dayOfWeek;
    }

    @Id
    @Column(name="libraryHourId", updatable=false, nullable=false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public String getLibraryHourId() {
    	return libraryHourId;
    }



    private LibraryManagementSystem system;

    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
    
    public String setLibraryHourId() {
    	return libraryHourId;
    }
    
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    
    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}
