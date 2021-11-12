package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.model.Shift;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.ShiftException;


@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;

    @Transactional //creates a new shift object and initializes the attributes with the passed parameters(only for account belonging to librarian
    public Shift createShiftLibrarian(String startTime, String endTime, String DOW, Integer librarianId, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("This account is not authorized for this action");

        Shift shift = new Shift(startTime, endTime, DOW);
        Librarian librarian = findLibrarian(librarianId);

        shift.setLibrarian(librarian);
        shiftRepository.save(shift);
        return shift;

    }
    //Creates a shift object with the passed parameters.(only for account belonging to Head Librarian)
    @Transactional
    public Shift createShiftHeadLibrarian(String startTime, String endTime, String DOW, Integer librarianId, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("This account is not authorized for this action");

        Shift shift = new Shift(startTime, endTime, DOW);
        HeadLibrarian librarian = findHeadLibrarian(librarianId);

        shift.setLibrarian(librarian);
        shiftRepository.save(shift);
        return shift;

    }
    
    @Transactional //Gets all shifts that are associated with a given librarian
    public List<Shift> getLibrarianShifts(Integer librarianId) {
    	String error = "";
    	if (librarianId == null) {
        	error = error + "Librarian not found in request";
        } else if (librarianRepository.findPersonRoleById(librarianId) == null &&
        		headLibrarianRepository.findPersonRoleById(librarianId) == null) {
            error = error + "Librarian does not exist! ";
        }
    	if(librarianId == null || librarianId < 0) {
    		error = error + "Invalid Id";
    	}
    	if(error.length() > 0) {
    		throw new IllegalArgumentException(error);
    	}
    	List<Shift> shifts = new ArrayList<Shift>();
        for(Shift s : shiftRepository.findAll()) {
        	if(s.getLibrarian().getId() == librarianId) {
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
    @Transactional // Updates a shift (only for account which belongs to librarian)
    public Shift updateShiftLibrarian(Integer shiftId, String startTime, String endTime, String DOW, Integer librarianId, String accountUsername){
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
    // Updates a shift (only for account which belongs to head librarian)
    @Transactional
    public Shift updateShiftHeadLibrarian(Integer shiftId, String startTime, String endTime, String DOW, Integer librarianId, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("Active User is unauthorized for this action");

        Shift shift = getShift(shiftId);
        HeadLibrarian headLibrarian = findHeadLibrarian(librarianId);

        shift.updateStartTime(startTime);
        shift.updateDayOfWeek(DOW);
        shift.updateEndTime(endTime);
        shift.setLibrarian(headLibrarian);
        shiftRepository.save(shift);
        return shift;
    }
    @Transactional //Deletes a shift with the passed Id
    public void deleteShift(String accountUsername, Integer shiftId){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("Active user is not authorized for this action");
        shiftRepository.deleteById(shiftId);
    }
    //Helper method for user authentication
    private OnlineAccount getActiveUser(String accountUsername){
        if(accountUsername == null) throw new OnlineAccountException("Invalid account id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(accountUsername);
        if(account == null) throw new OnlineAccountException("No account by that username");
        if(account.getLoggedIn() == false) throw new OnlineAccountException("This account is not the active user");
        return account;
    }
    //Helper method which finds a Librarian
    private Librarian findLibrarian(Integer librarianId){
        if(librarianId == null || librarianId < 0) throw new OnlineAccountException("Invalid Id");
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null) throw new OnlineAccountException("No existing account with this Id");
        return librarian;
    }
    //Helper method which finds a Head Librarian
    private HeadLibrarian findHeadLibrarian(Integer librarianId){
        if(librarianId == null || librarianId < 0) throw new OnlineAccountException("Invalid Id");
        HeadLibrarian librarian = (HeadLibrarian) headLibrarianRepository.findPersonRoleById(librarianId);
        if(librarian == null) throw new OnlineAccountException("No existing account with this Id");
        return librarian;
    }

}
