package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.ShiftRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Shift;
import ca.mcgill.ecse321.library.service.Exception.LibraryHourException;
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

}
