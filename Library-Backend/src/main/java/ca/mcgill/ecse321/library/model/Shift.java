package ca.mcgill.ecse321.library.model;

import java.sql.Time;
import java.time.DayOfWeek;

public class Shift {
    private Time startTime;
    private Time endTime;
    private DayOfWeek dayOfWeek;
    //may need month and/or year

    public Shift(){

    }

    public Shift(Time startTime, Time endTime, DayOfWeek dayOfWeek){
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    //Getters
    public Time getStartTime(){
        return this.startTime;
    }
    public Time getEndTime(){
        return this.endTime;
    }
    public DayOfWeek getDayOfWeek(){
        return this.dayOfWeek;
    }

    //Setters
    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }
    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }
    public void setDayOfWeek(DayOfWeek dayOfWeek){

    }
}
