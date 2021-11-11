package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.dao.ShiftRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.model.Shift;
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
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;

    @Transactional
    public Shift createShift(String startTime, String endTime, String DOW, Integer librarianId, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("This account is not authorized for this action");

        Shift shift = new Shift(startTime, endTime, DOW);
        Librarian librarian = findLibrarian(librarianId);

        shift.setLibrarian(librarian);
        shiftRepository.save(shift);
        return shift;

    }
    
    @Transactional
    public List<Shift> getLibrarianShifts(Integer id) {
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
    public Shift updateShift(Integer shiftId, String startTime, String endTime, String DOW, Integer librarianId, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("Active User is unauthorized for this action");

        Shift shift = getShift(shiftId);
        Librarian librarian = findLibrarian(librarianId);

        shift.updateStartTime(startTime);
        shift.updateDayOfWeek(DOW);
        shift.updateEndTime(endTime);
        shift.setLibrarian(librarian);
        shiftRepository.save(shift);
        return shift;
    }
    @Transactional
    public void deleteShift(String accountUsername, Integer shiftId){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("Active user is not authorized forthis action");

        Shift shift = getShift(shiftId);

        shiftRepository.deleteById(shiftId);
    }

    private OnlineAccount getActiveUser(String accountUsername){
        if(accountUsername == null) throw new OnlineAccountException("Invalid account id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(accountUsername);
        if(account == null) throw new OnlineAccountException("No account by that username");
        if(account.getLoggedIn() == false) throw new OnlineAccountException("This account is not the active user");
        return account;
    }
    private Librarian findLibrarian(Integer librarianId){
        if(librarianId == null || librarianId < 0) throw new OnlineAccountException("Invalid Id");
        Librarian librarian = (Librarian) personRoleRepository.findPersonRoleById(librarianId);
        if(librarian == null) throw new OnlineAccountException("No existing account with this Id");
        return librarian;
    }
}
