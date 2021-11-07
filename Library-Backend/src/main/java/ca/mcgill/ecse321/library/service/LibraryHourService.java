package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.service.Exception.InvalidUserException;
import ca.mcgill.ecse321.library.service.Exception.LibraryException;
import ca.mcgill.ecse321.library.service.Exception.LibraryHourException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.LibraryRepository;
import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryHour;

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
    private LibraryService libraryService;
	
    @Transactional
    public LibraryHour createLibraryHour(Integer currentUserId, Library library, Time startTime, Time endTime, DayOfWeek DOW){
        if(currentUserId == null || currentUserId < 0) throw new OnlineAccountException("Invalid Account Id");
        if(startTime.toLocalTime().isAfter(endTime.toLocalTime())) throw new LibraryHourException("Invalid Start or End Time");
        if(DOW == null)throw new LibraryHourException("Invalid Day of Week");
        if(library == null) throw new LibraryHourException("Invalid Library");

        PersonRole l = personRoleRepository.findPersonRoleById(currentUserId);
        if(!(l instanceof HeadLibrarian)) throw new OnlineAccountException("User not authorized for this action");

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
    public LibraryHour updateLibraryHour(Integer accountId, Integer libHourId, Time startTime, Time endTime, DayOfWeek DOW, Integer libraryId){
        if(accountId < 0 || accountId == null) throw new OnlineAccountException("Invalid account id");
        PersonRole activeUser = personRoleRepository.findPersonRoleById(accountId);
        if(activeUser == null) throw new OnlineAccountException("There is no user by this account id");
        if(libHourId < 0 || libHourId == null) throw new LibraryHourException("Invalid Id");
        if(startTime.toLocalTime().isAfter(endTime.toLocalTime())) throw new LibraryHourException("Invalid Start or End Time");
        if(DOW == null)throw new LibraryHourException("Invalid Day of Week");
        if(libraryId == null || libraryId < 0) throw new LibraryHourException("Invalid Library");
        Library library = libraryService.getLibrary(libraryId);
        if(library == null) throw new LibraryException("There exists no library by that id");
        LibraryHour libraryHour = libraryHourRepository.findLibraryHourById(libHourId);
        if(libraryHour == null) throw new LibraryHourException("There does not exist a Library Hour by the Id");

        libraryHour.setLibrary(library);
        libraryHour.setDayOfWeek(DOW);
        libraryHour.setEndTime(endTime);
        libraryHour.setStartTime(startTime);
        libraryHourRepository.save(libraryHour);
        return libraryHour;
    }

    @Transactional
    public LibraryHour deleteLibraryHour(Integer id){
        if(id < 0 || id == null) throw new LibraryHourException("Invalid Id");

        LibraryHour libraryHour = libraryHourRepository.findLibraryHourById(id);
        if(libraryHour == null) throw new LibraryHourException("There does not exist a Library hour by Id: " + id.toString());

        libraryHourRepository.deleteById(id);
        return null;
    }

}
