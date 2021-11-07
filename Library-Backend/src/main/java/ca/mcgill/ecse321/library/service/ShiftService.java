package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.ShiftRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Shift;
import ca.mcgill.ecse321.library.service.Exception.LibraryHourException;
import ca.mcgill.ecse321.library.service.Exception.ShiftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

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
    public Shift getShift(Integer id){
        if(id == null || id < 0) throw new ShiftException("Invalid Id");
        Shift s = shiftRepository.findShiftById(id);
        if(s == null) throw new ShiftException("No Shift Exists with Provided Id");
        return s;
    }

}
