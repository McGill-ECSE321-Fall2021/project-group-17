package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Librarian;

import java.sql.Time;
import java.time.DayOfWeek;

public class ShiftDTO {
    private int id;
    private Time startTime;
    private Time endTime;
    private DayOfWeek dayOfWeek;
    private Librarian librarian;

    public ShiftDTO() {
    }

    public ShiftDTO(int id, Time startTime, Time endTime, DayOfWeek dayOfWeek, Librarian librarian) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.librarian = librarian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

}
