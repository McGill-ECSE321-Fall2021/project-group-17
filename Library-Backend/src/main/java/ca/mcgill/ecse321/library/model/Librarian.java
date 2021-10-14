package ca.mcgill.ecse321.library.model;

import java.util.List;
import java.sql.Time;
import java.time.DayOfWeek;

public class Librarian extends PersonRole{
    private List<Shift> shifts;

    public Librarian(){
        this.shifts = null;
    }

    public Librarian(Shift shift){
        this.shifts.add(shift);
    }

    public List<Shift> getShift(){
        return this.shifts;
    }

    public void setShifts(List<Shift> shifts){
        this.shifts = shifts;
    }

    public void addShift(Time startTime, Time endTime, DayOfWeek dayOfWeek){
        this.shifts.add(new Shift(startTime, endTime, dayOfWeek));
    }
    public void removeShift(Shift shift){
        this.shifts.remove(shift);
    }
}
