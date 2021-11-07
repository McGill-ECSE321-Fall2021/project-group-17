package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.dao.ShiftRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.model.Shift;
import ca.mcgill.ecse321.library.service.Exception.LibraryHourException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
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
    @Autowired
    private PersonRoleRepository personRoleRepository;

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
    @Transactional
    public void deleteShift(Integer accountId, Integer shiftId){
        if(shiftId == null || shiftId < 0) throw new ShiftException("Invalid id");
        Shift shift = shiftRepository.findShiftById(shiftId);
        if(shift == null) throw new ShiftException("No shift by that Id");
        if(accountId == null || accountId < 0) throw new OnlineAccountException("Invalid accountId");
        PersonRole account = personRoleRepository.findPersonRoleById(accountId);
        if(account == null) throw new OnlineAccountException("There is no account by this Id");
        if(!(account instanceof HeadLibrarian)) throw new OnlineAccountException("This account does not have " +
                "the authorization to perform this action");
        shiftRepository.deleteById(shiftId);
    }

}
