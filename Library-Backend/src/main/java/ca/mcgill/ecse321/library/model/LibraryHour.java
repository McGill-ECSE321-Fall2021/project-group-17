package ca.mcgill.ecse321.library.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;


@Entity
public class LibraryHour {
	
	private int id;
    private Time startTime;
    private Time endTime;
    private DayOfWeek dayOfWeek;
    private Library library;
    private LibraryManagementSystem system;


    public LibraryHour() {}

    public LibraryHour(int id, Time startTime, Time endTime, DayOfWeek dayOfWeek, Library library) {
    	//this.id = id;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.dayOfWeek = dayOfWeek;
    	this.library = library;
    }

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
    	return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @ManyToOne(optional=false,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    public Library getLibrary() {
        return this.library;
    }

    public void setLibrary(Library library) {
        this.library = library;
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
    
    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }
    
    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }

}
