package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.InvalidUserException;
import ca.mcgill.ecse321.library.service.Exception.LibraryException;
import ca.mcgill.ecse321.library.service.Exception.LibraryHourException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.LibraryRepository;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryHourService {
	@Autowired
    private LibraryHourRepository libraryHourRepository;
	@Autowired
    private LibraryRepository libraryRepository;;
    @Autowired
    private PersonRoleRepository personRoleRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
	
    @Transactional
    public LibraryHour createLibraryHour(Integer libraryId, Time startTime, Time endTime, DayOfWeek DOW, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(libraryId < 0 || libraryId == null) throw new LibraryException("Invalid Id");
        if(startTime.toLocalTime().isAfter(endTime.toLocalTime())) throw new LibraryHourException("Invalid Start or End Time");
        if(DOW == null)throw new LibraryHourException("Invalid Day of Week");
        Library library = libraryRepository.findLibraryById(libraryId);
        if(library == null) throw new LibraryException("No library by that Id");

        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("User not authorized for this action");

        LibraryHour libraryHour = new LibraryHour();
        libraryHour.setLibrary(library);
        libraryHour.setStartTime(startTime);
        libraryHour.setEndTime(endTime);
        libraryHour.setDayOfWeek(DOW);
        
        libraryHourRepository.save(libraryHour);
        return libraryHour;
    }
    
    @Transactional
    public LibraryHour getLibraryHour(Integer id) {
        if(id == null || id < 0) throw new LibraryHourException("Invalid Library Hour Id");
        return libraryHourRepository.findLibraryHourById(id);
    }

    @Transactional
    public List<LibraryHour> getLibraryHours(Integer libraryId){
        if(libraryId == null || libraryId < 0) throw new LibraryHourException("Invalid Library Id");
        Library lib = libraryRepository.findLibraryById(libraryId);
        if(lib == null) throw new LibraryException("No Library with given id exists");
        List<LibraryHour> libHours = new ArrayList<>();
        libHours = libraryHourRepository.findByLibrary(lib);
        if(libHours == null || libHours.isEmpty()) throw new LibraryHourException("This library does not contain any hours");
        return libHours;
    }

    @Transactional
    public LibraryHour updateLibraryHour(Integer libHourId, Time startTime, Time endTime, DayOfWeek DOW, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(libHourId < 0 || libHourId == null) throw new LibraryHourException("Invalid Id");
        if(startTime.toLocalTime().isAfter(endTime.toLocalTime())) throw new LibraryHourException("Invalid Start or End Time");
        if(DOW == null)throw new LibraryHourException("Invalid Day of Week");

        LibraryHour libraryHour = libraryHourRepository.findLibraryHourById(libHourId);
        if(libraryHour == null) throw new LibraryHourException("There does not exist a Library Hour by the Id");

        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("Account not authorized for this action");
        libraryHour.setDayOfWeek(DOW);
        libraryHour.setEndTime(endTime);
        libraryHour.setStartTime(startTime);
        libraryHourRepository.save(libraryHour);
        return libraryHour;
    }

    @Transactional
    public LibraryHour deleteLibraryHour(Integer libraryHourId, Integer accountId){
        if(libraryHourId < 0 || libraryHourId == null) throw new LibraryHourException("Invalid Id");

        LibraryHour libraryHour = libraryHourRepository.findLibraryHourById(libraryHourId);
        if(libraryHour == null) throw new LibraryHourException("There does not exist a Library hour by Id");

        if(accountId == null || accountId < 0) throw new OnlineAccountException("Invalid Account Id");
        PersonRole account = personRoleRepository.findPersonRoleById(accountId);
        if(!(account instanceof HeadLibrarian)) throw new OnlineAccountException("Active Account is Unauthorized for this Action");
        libraryHourRepository.deleteById(accountId);
        return null;
    }
    private OnlineAccount getActiveUser(String accountUsername){
        if(accountUsername == null) throw new OnlineAccountException("Invalid account id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(accountUsername);
        if(account == null) throw new OnlineAccountException("No account by that username");
        return account;
    }

}
