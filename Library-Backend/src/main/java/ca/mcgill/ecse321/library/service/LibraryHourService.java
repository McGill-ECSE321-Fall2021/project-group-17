package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.LibraryRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.service.Exception.LibraryException;
import ca.mcgill.ecse321.library.service.Exception.LibraryHourException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;

@Service
public class LibraryHourService {
	@Autowired
    private LibraryHourRepository libraryHourRepository;
	@Autowired
    private LibraryRepository libraryRepository;;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;
	
    @Transactional
    public LibraryHour createLibraryHour(Integer libraryId, String startTime, String endTime, String DOW, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(libraryId < 0 || libraryId == null) throw new LibraryException("Invalid Id");

        if(DOW == null)throw new LibraryHourException("Invalid Day of Week");
        Library library = libraryRepository.findLibraryById(libraryId);
        if(library == null) throw new LibraryException("No library by that Id");

        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("User not authorized for this action");

        LibraryHour libraryHour = new LibraryHour();
        libraryHour.setLibrary(library);
        libraryHour.updateStartTime(startTime);
        libraryHour.updateEndTime(endTime);
        libraryHour.updateDayOfWeek(DOW);
        
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
    public LibraryHour updateLibraryHour(Integer libHourId, String startTime, String endTime, String DOW, String accountUsername){
        PersonRole activeUser = getActiveUser(accountUsername).getPersonRole();
        if(libHourId < 0 || libHourId == null) throw new LibraryHourException("Invalid Id");

        if(DOW == null)throw new LibraryHourException("Invalid Day of Week");

        LibraryHour libraryHour = libraryHourRepository.findLibraryHourById(libHourId);
        if(libraryHour == null) throw new LibraryHourException("There does not exist a Library Hour by the Id");

        if(!(activeUser instanceof HeadLibrarian)) throw new OnlineAccountException("Account not authorized for this action");
        libraryHour.updateDayOfWeek(DOW);
        libraryHour.updateEndTime(endTime);
        libraryHour.updateStartTime(startTime);
        libraryHourRepository.save(libraryHour);
        return libraryHour;
    }

    @Transactional
    public LibraryHour deleteLibraryHour(Integer libraryHourId, String accountUsername){
        if(libraryHourId < 0 || libraryHourId == null) throw new LibraryHourException("Invalid Id");

        LibraryHour libraryHour = libraryHourRepository.findLibraryHourById(libraryHourId);
        if(libraryHour == null) throw new LibraryHourException("There does not exist a Library hour by Id");

        OnlineAccount account = getActiveUser(accountUsername);
        if(account.getLoggedIn() == false) throw new OnlineAccountException("Account must be logged in");
        PersonRole role = account.getPersonRole();
        if(!(role instanceof HeadLibrarian)) throw new OnlineAccountException("Account is not authorized for this action");

        libraryHourRepository.deleteById(libraryHourId);
        return null;
    }
    private OnlineAccount getActiveUser(String accountUsername){
        if(accountUsername == null) throw new OnlineAccountException("Invalid account id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(accountUsername);
        if(account == null) throw new OnlineAccountException("No account by that username");
        return account;
    }

}
