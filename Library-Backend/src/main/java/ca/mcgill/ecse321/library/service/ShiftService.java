package ca.mcgill.ecse321.library.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.ShiftRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Shift;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;

    @Transactional
    public Shift createShift(Time startTime, Time endTime, DayOfWeek DOW, Librarian librarian){
        Shift shift = new Shift();
        shift.setLibrarian(librarian);
        shift.setStartTime(startTime);
        shift.setDayOfWeek(DOW);
        shiftRepository.save(shift);
        return shift;

    }
    
    @Transactional
    public List<Shift> getShifts(Integer id) {
    	List<Shift> shifts = new ArrayList<Shift>();
        for(Shift s : shiftRepository.findAll()) {
        	if(s.getLibrarian().getId() == id) {
        		shifts.add(s);
        	}
        }
        return shifts;
    }

}
