package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Library;

import java.sql.Time;
import java.time.DayOfWeek;

public class LibraryHourDTO {
	
	private int id;
    private Time startTime;
    private Time endTime;
    private DayOfWeek dayOfWeek;
    private Library library;
    
    public int getId() {
    	return id;
    }
    public void setId(int id){
        this.id = id;
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

    public Library getLibrary() {
    	return library;
    }
    public void setLibrary(Library library){
        this.library = library;
    }



}
