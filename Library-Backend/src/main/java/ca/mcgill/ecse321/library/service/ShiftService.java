package ca.mcgill.ecse321.library.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.dao.ShiftRepository;
import ca.mcgill.ecse321.library.model.*;

import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.ShiftException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private PersonRoleRepository personRoleRepository;

    @Transactional
    public Shift createShift(Time startTime, Time endTime, DayOfWeek DOW, Integer librarianId, Integer accountId){
        Shift shift = new Shift(startTime, endTime, DOW);
        if(librarianId == null || librarianId < 0) throw new OnlineAccountException("Invalid Id");
        Librarian librarian = (Librarian) personRoleRepository.findPersonRoleById(librarianId);
        if(librarian == null) throw new OnlineAccountException("No existing account with this Id");
        if(accountId == null || accountId < 0) throw new OnlineAccountException("Invalid Id");
        PersonRole account = personRoleRepository.findPersonRoleById(accountId);
        if(account == null) throw new OnlineAccountException("No existing account with this Id");
        if(!(account instanceof HeadLibrarian)) throw new OnlineAccountException("Not Authorized for this action");
        shift.setLibrarian(librarian);
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
    @Transactional
    public Shift getShift(Integer id){
        if(id == null || id < 0) throw new ShiftException("Invalid Id");
        Shift s = shiftRepository.findShiftById(id);
        if(s == null) throw new ShiftException("No Shift Exists with Provided Id");
        return s;
    }
    @Transactional
    public Shift updateShift(Integer shiftId, Time startTime, Time endTime, DayOfWeek DOW, Integer librarianId, Integer accountId){
        if(shiftId == null || shiftId < 0) throw new ShiftException("Invalid Id");
        Shift shift = shiftRepository.findShiftById(shiftId);
        if(shift == null) throw new ShiftException("No existing shift by that Id");
        if(librarianId == null || librarianId < 0) throw new OnlineAccountException("Invalid Id");
        Librarian librarian = (Librarian) personRoleRepository.findPersonRoleById(librarianId);
        if(librarian == null) throw new OnlineAccountException("No existing account with this Id");
        if(accountId == null || accountId < 0) throw new OnlineAccountException("Invalid Id");
        PersonRole account = personRoleRepository.findPersonRoleById(accountId);
        if(account == null) throw new OnlineAccountException("No existing account with this Id");
        if(!(account instanceof HeadLibrarian)) throw new OnlineAccountException("Not Authorized for this action");

        shift.setStartTime(startTime);
        shift.setDayOfWeek(DOW);
        shift.setEndTime(endTime);
        shift.setLibrarian(librarian);
        shiftRepository.save(shift);
        return shift;
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
